package com.mithun.simplebible.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.mithun.simplebible.R

class VerseWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val views = RemoteViews(context.packageName, R.layout.widget_verse_of_the_day)
        
        // In a real app, this would fetch the daily verse from a repository
        views.setTextViewText(R.id.tvWidgetVerse, "For God so loved the world, that he gave his only begotten Son, that whosoever believeth in him should not perish, but have everlasting life.")
        views.setTextViewText(R.id.tvWidgetReference, "John 3:16")

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}
