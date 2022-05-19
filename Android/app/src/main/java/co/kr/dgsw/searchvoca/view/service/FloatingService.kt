package co.kr.dgsw.searchvoca.view.service

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes

abstract class FloatingService : Service() {
    private val windowManager: WindowManager by lazy { getSystemService(WINDOW_SERVICE) as WindowManager }
    protected lateinit var viewGroup: ViewGroup
    protected abstract val windowLayoutParams: WindowManager.LayoutParams

    @LayoutRes
    protected abstract fun getLayoutRes(): Int
    protected abstract fun init()

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        setFloatView()
        init()
        windowManager.addView(viewGroup, windowLayoutParams)
    }

    protected fun updateViewLayout() {
        windowManager.updateViewLayout(viewGroup, windowLayoutParams)
    }

    protected fun getWindowParams(
        width: Int,
        height: Int,
        flags: Int,
        gravity: Int
    ): WindowManager.LayoutParams =
        WindowManager.LayoutParams(
            width,
            height,
            getLayoutType(),
            flags,
            PixelFormat.TRANSLUCENT
        ).apply {
            this.gravity = gravity
            x = 0
            y = 0
        }

    protected fun stopService() {
        stopSelf()
        (getSystemService(WINDOW_SERVICE) as WindowManager).removeViewImmediate(viewGroup)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService()
    }

    private fun setFloatView() {
        (getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater).apply {
            viewGroup = inflate(getLayoutRes(), null) as ViewGroup
        }
    }

    private fun getLayoutType() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        else WindowManager.LayoutParams.TYPE_TOAST
}