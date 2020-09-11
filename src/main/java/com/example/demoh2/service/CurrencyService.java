package com.example.demoh2.service;

import com.example.demoh2.exeption.RecordNotFoundException;
import com.example.demoh2.model.Currency;
import com.example.demoh2.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.RecordTypeNotSupportedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository repository;

    public Currency getCurrencyById(Long id) throws RecordNotFoundException {
        Optional<Currency> currency = repository.findById(id);

        if (currency.isPresent()) {
            return currency.get();
        } else {
            throw new RecordNotFoundException("No currency exists with given id");
        }
    }


    public Currency getCurrencyByNameAndDate(String name, String date) throws RecordNotFoundException {

        List<Currency> currencyList = repository.findByAbbreviationAndDate(name, date);
        Optional<Currency> currency = currencyList.stream().findAny();

        if (currency.isPresent()) {
            return currency.get();
        } else {
            throw new RecordNotFoundException("No currency exists with given id");
        }
    }

    public Currency getCurrencyByName(String name) throws RecordNotFoundException {
        List<Currency> currencyList = repository.findAllByAbbreviation(name);
        Optional<Currency> currencyFiltered = currencyList.stream()
                .max(Comparator.comparing(Currency::getId));

        if (currencyFiltered.isPresent()) {
            return currencyFiltered.get();
        } else {
            throw new RecordNotFoundException("No currency exists with given id");
        }
    }

    public void addCurrency(Currency currency) {
        repository.save(currency);
    }
}
