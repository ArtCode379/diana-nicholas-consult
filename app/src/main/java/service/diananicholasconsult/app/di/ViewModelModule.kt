package service.diananicholasconsult.app.di

import service.diananicholasconsult.app.ui.viewmodel.BookingViewModel
import service.diananicholasconsult.app.ui.viewmodel.CheckoutViewModel
import service.diananicholasconsult.app.ui.viewmodel.OnboardingViewModel
import service.diananicholasconsult.app.ui.viewmodel.ServiceDetailsViewModel
import service.diananicholasconsult.app.ui.viewmodel.ServiceViewModel
import service.diananicholasconsult.app.ui.viewmodel.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        SplashViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        OnboardingViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        ServiceViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        ServiceDetailsViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        BookingViewModel(
            bookingRepository = get(),
            serviceRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            bookingRepository = get(),
        )
    }
}