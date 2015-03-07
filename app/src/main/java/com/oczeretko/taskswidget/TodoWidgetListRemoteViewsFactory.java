package com.oczeretko.taskswidget;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class TodoWidgetListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context context;
    private TodoTask[] tasks = new TodoTask[0];

    public TodoWidgetListRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
    }

    @Override
    public void onDataSetChanged() {
        tasks = new TodoTasksProvider().fetchTasks();
    }

    @Override
    public int getCount() {
        return tasks.length;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        TodoTask task = tasks[position];
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_item);
        rv.setTextViewText(R.id.widget_item_title, task.getTitle());
        rv.setTextViewText(R.id.widget_item_due, task.getDue());
        rv.setViewVisibility(R.id.widget_item_priority_1, View.GONE);
        rv.setViewVisibility(R.id.widget_item_priority_2, View.GONE);
        rv.setViewVisibility(R.id.widget_item_priority_3, View.GONE);

        switch(task.getPriority()) {
            case "1" :
                rv.setViewVisibility(R.id.widget_item_priority_1, View.VISIBLE);
                break;
            case "2" :
                rv.setViewVisibility(R.id.widget_item_priority_2, View.VISIBLE);
                break;
            case "3" :
                rv.setViewVisibility(R.id.widget_item_priority_3, View.VISIBLE);
                break;
        }

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDestroy() {
    }
}
