import fr.novy.app.appModule

// Helper.kt

fun initKoin(){
    startKoin {
        modules(appModule())
    }
}