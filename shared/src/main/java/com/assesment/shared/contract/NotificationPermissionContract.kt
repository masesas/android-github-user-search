package com.assesment.shared.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

/**
 * Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
 */
class NotificationPermissionContract : ActivityResultContract<Void?, Boolean>() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun createIntent(context: Context, input: Void?): Intent {
        require(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            "This permission is only required on Android 13+"
        }

        return ActivityResultContracts.RequestPermission()
            .createIntent(context, android.Manifest.permission.POST_NOTIFICATIONS)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun getSynchronousResult(context: Context, input: Void?): SynchronousResult<Boolean>? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return SynchronousResult(true)
        }

        val granted = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED

        return if (granted) {
            SynchronousResult(true)
        } else {
            null
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return true;
        }

        if (intent == null || resultCode != Activity.RESULT_OK) return false

        val grantResults =
            intent.getIntArrayExtra(RequestMultiplePermissions.EXTRA_PERMISSION_GRANT_RESULTS)

        return grantResults?.any { result ->
            result == PackageManager.PERMISSION_GRANTED
        } == true
    }
}