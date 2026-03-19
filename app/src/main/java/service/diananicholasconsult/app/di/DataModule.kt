package service.diananicholasconsult.app.di

import service.diananicholasconsult.app.data.repository.BookingRepository
import service.diananicholasconsult.app.data.repository.OnboardingRepository
import service.diananicholasconsult.app.data.repository.ServiceRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        OnboardingRepository(
            onboardingDataStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ServiceRepository() }

    single{
        BookingRepository(
            bookingDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}