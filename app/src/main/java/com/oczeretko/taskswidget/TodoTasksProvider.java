package com.oczeretko.taskswidget;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class TodoTasksProvider {

    private XPath xpath = XPathFactory.newInstance().newXPath();

    public TodoTask[] fetchTasks() {
        try {
            Document document = fetchXml(Secrets.RssAddress);
            return parseXmlDocument(document);
        } catch (Exception e) {
            Log.e(TodoTasksProvider.class.getSimpleName(), "Exception when fetching tasks", e);
            return new TodoTask[0];
        }
    }

    private TodoTask[] parseXmlDocument(Document document) throws XPathExpressionException {

        String[] titles = nodeListToStrings(evaluateXPath(document, "//*[local-name()='title' and position() > 2]"));
        String[] dueDates = nodeListToStrings(evaluateXPath(document, "//*[@class='rtm_due_value']"));
        String[] priorities = nodeListToStrings(evaluateXPath(document, "//*[@class='rtm_priority_value']"));

        TodoTask[] tasks = new TodoTask[titles.length];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new TodoTask(titles[i], dueDates[i], priorities[i]);
        }
        return tasks;
    }

    private String[] nodeListToStrings(NodeList nodeList) {
        String[] results = new String[nodeList.getLength()];
        for (int i = 0; i < results.length; i++) {
            results[i] = nodeList.item(i).getTextContent();
        }
        return results;
    }

    private NodeList evaluateXPath(Document document, String xpathExpression) throws XPathExpressionException {
        return (NodeList) xpath.evaluate(
                xpathExpression,
                document.getDocumentElement(),
                XPathConstants.NODESET);
    }

    private Document fetchXml(String address) throws IOException, ParserConfigurationException, SAXException {
        InputStream responseContent = fetchString(address);
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return documentBuilder.parse(responseContent);
    }

    private InputStream fetchString(String address) throws IOException {
        HttpGet httpGet = new HttpGet(address);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(httpGet);
        return response.getEntity().getContent();
    }
}
