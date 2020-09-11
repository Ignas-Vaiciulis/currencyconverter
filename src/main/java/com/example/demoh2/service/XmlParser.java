package com.example.demoh2.service;

import com.example.demoh2.model.Currency;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class XmlParser {

    public List<Currency> xmlToCurrencyList(String xmlString) {
        List<Currency> currencyList = new ArrayList<>();
        InputSource is = new InputSource(new StringReader(xmlString));
        Document document = createDocument(is);

        document.getDocumentElement().normalize();
        NodeList fxRateList = document.getElementsByTagName("FxRate");

        for (int i = 0; i < fxRateList.getLength(); i++) {
            NodeList ccyAmt = fxRateList.item(i).getChildNodes();
            NodeList ccyAmtInside = ccyAmt.item(7).getChildNodes();

            String currencyName = ccyAmtInside.item(1).getTextContent();
            double currencyRate = Double.parseDouble(ccyAmtInside.item(3).getTextContent());
            String currencyDate = ccyAmt.item(3).getTextContent();

            Currency currency = new Currency(currencyName, currencyRate, currencyDate);
            currencyList.add(currency);
        }
        return currencyList;
    }

    private static Document createDocument(InputSource xmlString) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document document = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            document = builder.parse(xmlString);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
