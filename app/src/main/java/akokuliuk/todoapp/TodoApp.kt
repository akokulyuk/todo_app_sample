package akokuliuk.todoapp

import akokuliuk.todoapp.di.ApplicationComponent
import akokuliuk.todoapp.di.DaggerApplicationComponent
import android.app.Application


class TodoApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        applicationComponent = DaggerApplicationComponent.builder().build()
    }

    /**
     * Basically Application is always singleton, we just expose it to the app
     */
    companion object {
        lateinit var instance: TodoApp
            private set
    }
}
