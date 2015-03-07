package com.oczeretko.taskswidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


public class TodoWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews updateViews = createWidgetViews(context);
        updateWidgetViews(context, updateViews);
    }

    private RemoteViews createWidgetViews(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        Intent intent = new Intent(context, TodoWidgetListService.class);
        views.setRemoteAdapter(R.id.widget_list, intent);
        return views;
    }

    private void updateWidgetViews(Context context, RemoteViews updateViews) {
        ComponentName componentName = new ComponentName(context, TodoWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(componentName, updateViews);
    }
}


