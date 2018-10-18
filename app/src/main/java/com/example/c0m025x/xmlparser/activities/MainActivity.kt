package com.example.c0m025x.xmlparser.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.c0m025x.xmlparser.R
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputStream = readFileLineByLineUsingForEachLine("employees.xml")
        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        val xpp = factory.newPullParser()

        xpp.setInput(inputStream.reader())
        var eventType = xpp.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                //XmlPullParser.START_DOCUMENT -> println("Start document")
                //XmlPullParser.END_DOCUMENT -> println("End document")
                XmlPullParser.START_TAG -> System.out.println("Start tag " + xpp.name)
                XmlPullParser.END_TAG -> System.out.println("End tag " + xpp.name)
                XmlPullParser.TEXT -> System.out.println("Text " + xpp.text)
            }
            eventType = xpp.next()
        }
    }

    private fun readFileLineByLineUsingForEachLine(fileName: String) = assets.open(fileName)
}
