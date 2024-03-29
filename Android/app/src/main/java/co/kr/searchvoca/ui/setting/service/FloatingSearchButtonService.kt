package co.kr.searchvoca.ui.setting.service

import android.content.Intent
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
import android.view.WindowManager.LayoutParams.WRAP_CONTENT
import android.widget.ImageButton
import co.kr.searchvoca.R

class FloatingSearchButtonService : FloatingService(R.layout.layout_floating_button) {

    private val btnSearch: ImageButton by lazy {
        viewGroup.findViewById(R.id.btn_search_floating)
    }

    override val windowLayoutParams: WindowManager.LayoutParams =
        getWindowParams(WRAP_CONTENT, WRAP_CONTENT, FLAG_NOT_FOCUSABLE, Gravity.CENTER)

    override fun init() {
        btnSearch.apply {
            setOnClickListener {
                startService(
                    Intent(
                        this@FloatingSearchButtonService,
                        FloatingSearchService::class.java
                    )
                )
            }

            setOnTouchListener(object : View.OnTouchListener {
                private var x = 0f
                private var y = 0f
                private var px = 0f
                private var py = 0f

                override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                    when (event?.action) {
                        MotionEvent.ACTION_DOWN -> {
                            x = windowLayoutParams.x.toFloat()
                            y = windowLayoutParams.y.toFloat()
                            px = event.rawX
                            py = event.rawY
                        }

                        MotionEvent.ACTION_MOVE -> {
                            windowLayoutParams.x = ((x + event.rawX) - px).toInt()
                            windowLayoutParams.y = ((y + event.rawY) - py).toInt()
                            updateViewLayout()
                        }
                    }
                    return false
                }
            })
        }
    }
}