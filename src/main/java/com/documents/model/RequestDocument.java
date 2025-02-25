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

    @Override
    public String getFullDesc() {
        String start = super.getFullDesc();
        return String.format("%sСумма: %s\nВалюта: %s\nКурс: %s\nКонтрагент: %s\nКомиссия: %s", start, amount, currency, exchangeRate, counterParty, commission);
    }
}
