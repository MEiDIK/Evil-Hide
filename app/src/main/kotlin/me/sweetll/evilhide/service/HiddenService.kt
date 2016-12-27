package me.sweetll.evilhide.service

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

import eu.chainfire.libsuperuser.Shell
import me.sweetll.evilhide.AppApplication

class HiddenService : IntentService("hidden-service") {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            val action = it.action
            if (!action.isNullOrEmpty()) {
                Log.d("evil", "action = $action")
                Shell.SU.run(action)
            }
        }
    }

    companion object {
        fun performAction(action: String) {
            val svc = Intent(AppApplication.get(), HiddenService::class.java)
            svc.action = action
            AppApplication.get().startService(svc)
        }
    }

}
