package com.example.wiseleap.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wiseleap.Model.markets
import com.example.wiseleap.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel: ViewModel() {

    val usersData: LiveData<List<markets>> = MutableLiveData()

    init {
        viewModelScope.launch {
            usersData as MutableLiveData

            usersData.value = getUsersData()
        }
    }
}


private suspend fun  getUsersData() :List<markets>{
    return  withContext(Dispatchers.IO){
        UserRepository().getUsers().body()?.markets!!
    }
}