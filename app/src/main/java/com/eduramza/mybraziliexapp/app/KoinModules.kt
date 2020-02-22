package com.eduramza.mybraziliexapp.app

import com.eduramza.mybraziliexapp.data.local.AppDatabase
import com.eduramza.mybraziliexapp.data.local.LocalRepository
import com.eduramza.mybraziliexapp.data.source.RemoteService
import com.eduramza.mybraziliexapp.data.repository.PublicRepository
import com.eduramza.mybraziliexapp.data.repository.PublicRepositoryImpl
import com.eduramza.mybraziliexapp.ui.CurrenciesViewModel
import com.eduramza.mybraziliexapp.ui.balance.BalanceViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RemoteService().getRemote() }
    single<PublicRepository> { PublicRepositoryImpl(get()) }
    viewModel { CurrenciesViewModel(get()) }
}

val localModules = module {

    single { AppDatabase(get()) }
    single {LocalRepository(get()) }
    viewModel { BalanceViewModel(get()) }

}