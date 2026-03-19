package service.diananicholasconsult.app

import android.app.Application
import service.diananicholasconsult.app.di.dataModule
import service.diananicholasconsult.app.di.dispatcherModule
import service.diananicholasconsult.app.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ServiceApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@ServiceApp)
            modules(appModules)
        }
    }
}