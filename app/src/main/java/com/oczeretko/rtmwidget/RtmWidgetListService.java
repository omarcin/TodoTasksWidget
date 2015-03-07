package com.oczeretko.rtmwidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class RtmWidgetListService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RtmWidgetListRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}

