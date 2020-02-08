package com.eduramza.mybraziliexapp.app

import com.eduramza.mybraziliexapp.data.source.RemoteService
import com.eduramza.mybraziliexapp.data.repository.PublicRepository
import com.eduramza.mybraziliexapp.data.repository.PublicRepositoryImpl
import com.eduramza.mybraziliexapp.ui.listcrypto.ListCryptoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RemoteService().getRemote() }
    single<PublicRepository> { PublicRepositoryImpl(get()) }
    viewModel { ListCryptoViewModel(get()) }
}