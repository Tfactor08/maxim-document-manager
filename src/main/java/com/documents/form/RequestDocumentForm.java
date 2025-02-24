package com.documents.form;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextFormatter;

import com.documents.model.AbstractDocument;
import com.documents.model.RequestDocument;
import com.documents.form.AbstractDocumentForm;
import com.documents.util.InputProcessor;
import com.documents.control.NumberTextField;

public class RequestDocumentForm extends AbstractDocumentForm {
    private static Map<String, Control> fieldAndInputControl = Map.of(
        "Номер", new TextField(),
        "Дата", new DatePicker(),
        "Пользователь", new TextField(),
        "Контрагент", new TextField(),
        "Сумма", new NumberTextField(),
        "Валюта", new TextField(),
        "Курс", new NumberTextField(),
        "Комиссия", new NumberTextField()
    );

    public RequestDocumentForm() {
        super(fieldAndInputControl);
    }

    @Override
    protected void initDocument() {
        this.document = new RequestDocument();
    }

    @Override
    public void fillDocument() {
        LocalDate date = ((DatePicker)fieldAndInputControl.get("Дата")).getValue();
        String user = ((TextField)fieldAndInputControl.get("Пользователь")).getText();
        String number = ((TextField)fieldAndInputControl.get("Номер")).getText();
        String currency = ((TextField)fieldAndInputControl.get("Валюта")).getText();
        double amount = Double.parseDouble(((NumberTextField)fieldAndInputControl.get("Сумма")).getText());
        double exchangeRate = Double.parseDouble(((NumberTextField)fieldAndInputControl.get("Курс")).getText());
        String counterParty = ((TextField)fieldAndInputControl.get("Контрагент")).getText();
        double commission = Double.parseDouble(((NumberTextField)fieldAndInputControl.get("Комиссия")).getText());

        ((RequestDocument)this.document).setDate(date);
        ((RequestDocument)this.document).setUser(user);
        ((RequestDocument)this.document).setNumber(number);
        ((RequestDocument)this.document).setCurrency(currency);
        ((RequestDocument)this.document).setAmount(amount);
        ((RequestDocument)this.document).setExchangeRate(exchangeRate);
        ((RequestDocument)this.document).setCounterParty(counterParty);
        ((RequestDocument)this.document).setCommission(commission);
    }

    @Override
    protected void fillFields() {
        LocalDate date = ((RequestDocument)document).getDate();
        String user = ((RequestDocument)document).getUser();
        String number = ((RequestDocument)document).getNumber();
        String currency = ((RequestDocument)document).getCurrency();
        String amount = String.valueOf(((RequestDocument)document).getAmount());
        String exchangeRate = String.valueOf(((RequestDocument)document).getExchangeRate());
        String counterParty = String.valueOf(((RequestDocument)document).getCounterParty());
        String commission = String.valueOf(((RequestDocument)document).getCommission());

        ((DatePicker)fieldAndInputControl.get("Дата")).setValue(date);
        ((TextField)fieldAndInputControl.get("Пользователь")).setText(user);
        ((TextField)fieldAndInputControl.get("Номер")).setText(number);
        ((TextField)fieldAndInputControl.get("Валюта")).setText(currency);
        ((NumberTextField)fieldAndInputControl.get("Сумма")).setText(amount);
        ((NumberTextField)fieldAndInputControl.get("Курс")).setText(exchangeRate);
        ((TextField)fieldAndInputControl.get("Контрагент")).setText(counterParty);
        ((NumberTextField)fieldAndInputControl.get("Комиссия")).setText(commission);
    }
}
