package com.example.demoh2.api;

import com.example.demoh2.exeption.RecordNotFoundException;
import com.example.demoh2.model.Currency;
import com.example.demoh2.service.CurrencyService;
import com.example.demoh2.service.DataRetrieveService;
import com.example.demoh2.service.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("currency")
public class CurrencyController {

    @Autowired
    CurrencyService service;
    @Autowired
    DataRetrieveService dataRetrieveService;
    @Autowired
    XmlParser xmlParser;

    @GetMapping("/{id}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        Currency currency = service.getCurrencyById(id);

        return new ResponseEntity<>(currency, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{name}/{date}")
    public ResponseEntity<Currency> getCurrencyByNameAndDate(@PathVariable("name") String name, @PathVariable("date") String date) throws RecordNotFoundException {
        Currency currency = service.getCurrencyByNameAndDate(name, date);

        return new ResponseEntity<>(currency, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{name}/{startDate}/{endDate}")
    public ResponseEntity<List<Currency>> getCurrencyByNameStartDateAndEndDate(@PathVariable("name") String name, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws RecordNotFoundException {
        String response = dataRetrieveService.getCurrencyFromToDate(name, startDate, endDate);
        List<Currency> currencyList = xmlParser.xmlToCurrencyList(response);

        return new ResponseEntity<>(currencyList, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Currency> getCurrencyByName(@RequestParam(value="name") String name) throws RecordNotFoundException {
        Currency response = service.getCurrencyByName(name);

        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
