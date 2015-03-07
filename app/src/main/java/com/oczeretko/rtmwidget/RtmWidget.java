package com.oczeretko.rtmwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;


public class RtmWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews updateViews = createWidgetViews(context);
        updateWidgetViews(context, updateViews);
    }

    private RemoteViews createWidgetViews(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.rtm_widget);
        Intent intent = new Intent(context, RtmWidgetListService.class);
        views.setRemoteAdapter(R.id.rtm_widget_list, intent);
        return views;
    }

    private void updateWidgetViews(Context context, RemoteViews updateViews) {
        ComponentName componentName = new ComponentName(context, RtmWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(componentName, updateViews);
    }
}


