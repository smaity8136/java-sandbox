package com.seedollar.sanbox.moneyexchange.forex.repository;

import com.seedollar.sanbox.moneyexchange.forex.domain.ExchangePair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangePairRepository extends JpaRepository<ExchangePair, Long> {

    ExchangePair findByFromAndTo(String from, String to);
}
