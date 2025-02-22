package com.documents.form;

import java.time.LocalDate;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import com.documents.model.AbstractDocument;
import com.documents.model.InvoiceDocument;
import com.documents.form.AbstractDocumentForm;

import com.documents.util.InputProcessor;

public class InvoiceDocumentForm extends AbstractDocumentForm {
    private static String[] fields = new String[] {"Номер", "Дата", "Пользователь", "Сумма", "Валюта", "Курс", "Товар", "Количество"};

    public InvoiceDocumentForm() {
        super(new InvoiceDocument(), fields);
    }

    @Override
    public AbstractDocument getFilledDocument() {
        LocalDate date = InputProcessor.getDateFromTextField(field_textField.get("Дата"));
        String user = field_textField.get("Пользователь").getText();
        String number = field_textField.get("Номер").getText();
        String currency = field_textField.get("Валюта").getText();
        double amount = Double.parseDouble(field_textField.get("Сумма").getText());
        double exchangeRate = Double.parseDouble(field_textField.get("Курс").getText());
        double product = Double.parseDouble(field_textField.get("Товар").getText());
        double quantity = Double.parseDouble(field_textField.get("Количество").getText());

        return new InvoiceDocument(number, date, user, amount, currency, exchangeRate, product, quantity);
    }
}
