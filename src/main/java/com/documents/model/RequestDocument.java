package com.documents.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestDocument extends AbstractDocument {
    private String counterParty;
    private double amount;
    private String currency;
    private double exchangeRate;
    private double commission;

    @Override
    public String getName() {
        return "Заявка на оплату";
    }
}
