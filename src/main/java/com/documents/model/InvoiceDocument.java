package com.documents.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InvoiceDocument extends AbstractDocument {
    private double amount;
    private String currency;
    private double exchangeRate;
    private double product;
    private double quantity;

    @Override
    public String getName() {
        return "Накладная";
    }

    @Override
    public String getFullDesc() {
        String start = super.getFullDesc();
        return String.format("%sСумма: %s\nВалюта: %s\nКурс: %s\nТовар: %s\nКоличество: %s", start, amount, currency, exchangeRate, product, quantity);
    }
}
