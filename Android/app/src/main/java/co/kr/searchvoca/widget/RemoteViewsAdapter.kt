package co.kr.searchvoca.widget

import android.widget.RemoteViews
import android.widget.RemoteViewsService

abstract class RemoteViewsAdapter<T>(
    private val list: List<T>
) : RemoteViewsService.RemoteViewsFactory {
    override fun onCreate() {}

    override fun onDataSetChanged() {}

    override fun onDestroy() {}

    override fun getCount(): Int = list.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun hasStableIds(): Boolean = false
}