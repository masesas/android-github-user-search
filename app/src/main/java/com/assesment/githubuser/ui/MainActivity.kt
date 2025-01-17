package com.assesment.githubuser.ui

import android.os.Bundle
import android.view.LayoutInflater
import com.assesment.githubuser.databinding.ActivityMainBinding
import com.assesment.shared.contract.NotificationPermissionContract
import com.assesment.shared.extension.showToast
import com.assesment.shared.ui.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val requestNotificationPermission =
        registerForActivityResult(NotificationPermissionContract()) { isGranted ->
            if (isGranted) {

            } else {
                showToast("Please enable notification to preview chucker debugging")
            }
        }

    override val bindLayout: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        requestNotificationPermission.launch(null)
    }
}