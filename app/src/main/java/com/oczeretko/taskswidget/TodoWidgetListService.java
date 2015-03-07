package com.oczeretko.taskswidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class TodoWidgetListService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new TodoWidgetListRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}

