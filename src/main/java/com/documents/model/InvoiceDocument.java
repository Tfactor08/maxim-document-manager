package com.documents.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

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

    //public InvoiceDocument() { }

    //public InvoiceDocument(String number, LocalDate date, String user, double amount, String currency, double exchangeRate, double product, double quantity) {
        //this.number = number;
        //this.date = date;
        //this.user = user;
        //this.amount = amount;
        //this.currency = currency;
        //this.exchangeRate = exchangeRate;
        //this.product = product;
        //this.quantity = quantity;
    //}

    //public String getNumber() { return this.number; }
    //public void setNumber(String number) { this.number = number; }

    //public String getUser() { return this.user; }
    //public void setUser(String user) { this.user = user; }
}
