package com.example.demoh2.service;

import com.example.demoh2.model.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class TaskSheduler {

    private static final Logger log = LoggerFactory.getLogger(TaskSheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    DataRetrieveService dataRetrieveService;
    @Autowired
    XmlParser xmlParser;
    @Autowired
    CurrencyService currencyService;

    //Fires at 12 PM every day:
    @Scheduled(cron = "0 0 12 * * ?")
    public void retrieveCurrencies() {
        String currentDate = dateFormat.format(new Date());
        log.info("Retrieving currency rates for date: {}", currentDate);

        String response = dataRetrieveService.getAllCurrencies(currentDate);
        List<Currency> currencyList = xmlParser.xmlToCurrencyList(response);
        currencyList.forEach(currency -> currencyService.addCurrency(currency));
    }

}
