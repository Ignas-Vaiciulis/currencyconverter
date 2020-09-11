package com.example.demoh2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataRetrieveService {

   private final String URLforAllCurrencies = "https://lb.lt/webservices/FxRates/FxRates.asmx/getFxRates?tp=EU&dt=";
   private final String URLforOneCurrency = "https://lb.lt/webservices/FxRates/FxRates.asmx/getFxRatesForCurrency?tp=EU&ccy=";

    @Autowired
    RestTemplate restTemplate;

    public String getAllCurrencies(String date){
       return restTemplate.getForObject(URLforAllCurrencies + date,String.class);
    }

    public String getCurrencyFromToDate(String currencyAbbreviation, String startDate, String endDate){
        return restTemplate.getForObject(URLforOneCurrency + currencyAbbreviation + "&dtFrom=" + startDate + "&dtTo=" + endDate, String.class);
    }

}
