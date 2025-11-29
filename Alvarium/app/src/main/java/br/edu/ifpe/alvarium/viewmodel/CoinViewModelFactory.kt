package br.edu.ifpe.alvarium.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.ifpe.alvarium.data.local.AppDatabase
import br.edu.ifpe.alvarium.data.remote.RetrofitInstance
import br.edu.ifpe.alvarium.data.repository.CoinRepositoryImpl

class CoinViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val db = AppDatabase.getInstance(context)
        val api = RetrofitInstance.api
        val repo = CoinRepositoryImpl(api, db.coinDao())

        return CoinViewModel(repo) as T
    }
}
