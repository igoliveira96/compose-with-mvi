package goulart.composemvi

import android.app.Application
import goulart.composemvi.di.dataLocalModule
import goulart.composemvi.di.dataModule
import goulart.composemvi.di.domainModule
import goulart.composemvi.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TodoMVIApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(
                domainModule,
                presentationModule,
                dataModule,
                dataLocalModule
            )
        }
    }
}