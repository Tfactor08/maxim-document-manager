package com.documents.form;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Consumer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import com.documents.model.AbstractDocument;
import com.documents.model.InvoiceDocument;
import com.documents.form.AbstractDocumentForm;

public class InvoiceDocumentForm extends AbstractDocumentForm {
    private static String[] fields = new String[] {"Номер", "Дата", "Пользователь", "Сумма", "Валюта", "Курс", "Товар", "Количество"};
    //private static Map<String, TextField> field_textField = new HashMap<String, TextField>();

    public InvoiceDocumentForm() {
        super(new InvoiceDocument(), fields);
    }

    private LocalDate getDateFromTextField(TextField textField) {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
       return LocalDate.parse(textField.getText(), formatter);
    }

    @Override
    public AbstractDocument getFilledDocument() {
        LocalDate date = getDateFromTextField(field_textField.get("Дата"));
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
