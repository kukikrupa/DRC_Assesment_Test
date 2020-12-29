package com.drc.assesment.utils

import android.content.Context
import android.net.ConnectivityManager
import com.kaopiz.kprogresshud.KProgressHUD
import com.drc.assesment.R


/**
 * A utility class which contains methods with all the validation logic of whole app.
 */
object Common {

    var hud: KProgressHUD? = null

    fun showProgress(context: Context) {
        hud = KProgressHUD.create(context)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setBackgroundColor(context.resources.getColor(R.color.design_default_color_primary))
            .setLabel("Please wait")
            .setCancellable(true)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
            .show();
    }


    fun dismissProgress() {
        if (hud != null) {
            hud!!.dismiss()
        }
    }

    fun hasInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }


}