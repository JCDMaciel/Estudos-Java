package org.example.checkout.domain.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.HashMap;
import java.util.Map;

@Entity
public class CurrencyTable {
    @ElementCollection
    private Map<String, Double> value;
    @Id
    private Long id;

    public CurrencyTable() {
        this.value = new HashMap<>();
        this.value.put("BRL", 1.0);
    }

    public void addCurrency(String currency, Double value) {
        this.value.put(currency, value);
    }

    public double getCurrency(String currency) {
        return this.value.get(currency);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
