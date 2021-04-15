package com.pumi.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.pumi.app.http.phumiService
import com.pumi.app.repository.MainRepository

class PhumiApp : Application() {

    companion object {

        lateinit var appContext: Context
            private set

        lateinit var mainRepository: MainRepository
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        mainRepository = MainRepository(context = this, service = phumiService)
    }
}