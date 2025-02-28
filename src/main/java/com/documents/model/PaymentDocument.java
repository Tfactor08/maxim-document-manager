package com.documents.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDocument extends AbstractDocument {
    private double amount;
    private String employee;

    @Override
    public String getName() {
        return "Платёжка";
    }

    @Override
    public String getFullDesc() {
        String start = super.getFullDesc();
        return String.format("%sСумма: %s\nСотрудник: %s", start, amount, employee);
    }
}
