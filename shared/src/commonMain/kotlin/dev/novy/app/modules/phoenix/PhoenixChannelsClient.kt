import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlin.random.Random

@Serializable
data class PhoenixMessage(
    val joinRef: String?,
    val msgRef: String,
    val topic: String,
    val event: String,
    val payload: JsonObject
)

class PhoenixClient(private val url: String) {
    private val client = HttpClient(CIO) {
        install(WebSockets)
    }
    private var session: DefaultClientWebSocketSession? = null
    private val scope = CoroutineScope(Dispatchers.Default)
    private val channels = mutableMapOf<String, PhoenixChannel>()

    private val _messages = MutableSharedFlow<PhoenixMessage>()
    val messages = _messages.asSharedFlow()

    suspend fun connect() {
        client.webSocket(url) {
            session = this
            listenForMessages()
        }
    }

    fun generateRef(): String = Random.nextInt(1000, 9999).toString()

    fun createChannel(topic: String): PhoenixChannel {
        val channel = PhoenixChannel(this, topic)
        channels[topic] = channel
        return channel
    }

    suspend fun sendMessage(message: PhoenixMessage) {
        session?.send(Frame.Text(Json.encodeToString(PhoenixMessage.serializer(), message)))
    }

    private suspend fun listenForMessages() {
        for (frame in session?.incoming ?: return) {
            if (frame is Frame.Text) {
                val text = frame.readText()
                val parsedMessage = Json.decodeFromString<PhoenixMessage>(text)
                _messages.emit(parsedMessage)
            }
        }
    }

    suspend fun disconnect() {
        session?.close()
        client.close()
    }
}

class PhoenixChannel(private val client: PhoenixClient, private val topic: String) {
    private val scope = CoroutineScope(Dispatchers.Default)
    private val _messages = MutableSharedFlow<PhoenixMessage>()
    val messages = _messages.asSharedFlow()

    suspend fun join(
        payload: JsonObject = JsonObject(mapOf()),
        onSuccess: (JsonObject) -> Unit = {},
        onError: (JsonObject) -> Unit = {}
    ) {
        val msgRef = client.generateRef()
        val message = PhoenixMessage(client.generateRef(), msgRef, topic, "phx_join", payload)
        client.sendMessage(message)

        scope.launch {
            client.messages.collect { response ->
                if (response.msgRef == msgRef) {
                    when (response.event) {
                        "phx_reply" -> {
                            if (response.payload["status"]?.toString() == "ok") {
                                onSuccess(response.payload)
                            } else {
                                onError(response.payload)
                            }
                        }
                    }
                }
            }
        }
    }

    suspend fun leave() {
        val message = PhoenixMessage(null, client.generateRef(), topic, "phx_leave", JsonObject(mapOf()))
        client.sendMessage(message)
    }

    suspend fun sendEvent(event: String, payload: JsonObject) {
        val message = PhoenixMessage(null, client.generateRef(), topic, event, payload)
        client.sendMessage(message)
    }
}
