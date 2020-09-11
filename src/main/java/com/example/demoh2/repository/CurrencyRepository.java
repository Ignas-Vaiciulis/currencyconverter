package com.example.demoh2.repository;

import com.example.demoh2.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository
        extends JpaRepository<Currency, Long> {

    List<Currency> findByAbbreviationAndDate(String abbreviation, String date);

    List<Currency> findAllByAbbreviation(String abbreviation);
}
