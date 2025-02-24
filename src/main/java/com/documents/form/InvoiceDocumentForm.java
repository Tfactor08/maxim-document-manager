package com.documents.form;

import java.util.Map;
import java.util.HashMap;

import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextFormatter;

import com.documents.model.AbstractDocument;
import com.documents.model.InvoiceDocument;
import com.documents.form.AbstractDocumentForm;
import com.documents.util.InputProcessor;
import com.documents.control.NumberTextField;

public class InvoiceDocumentForm extends AbstractDocumentForm {
    private static Map<String, Control> fieldAndInputControl = Map.of(
        "Номер", new TextField(),
        "Дата", new DatePicker(),
        "Пользователь", new TextField(),
        "Сумма", new NumberTextField(),
        "Валюта", new TextField(),
        "Курс", new NumberTextField(),
        "Товар", new NumberTextField(),
        "Количество", new NumberTextField()
    );

    public InvoiceDocumentForm() {
        super(fieldAndInputControl);

        //TextFormatter<String> dateFormatter = new TextFormatter<>(change -> {
        //    String newText = change.getControlNewText(); // Get the updated text
        //    if (newText.isEmpty()) {
        //        return change; // Allow empty input
        //    }
        //    if (isDateValid(newText, "dd-MM-yyyy")) {
        //        return change; // Allow valid date input
        //    }
        //    return null; // Reject invalid input
        //});
    }

    @Override
    protected void initDocument() {
        this.document = new InvoiceDocument();
    }

    @Override
    protected void fillDocument() {
        LocalDate date = ((DatePicker)fieldAndInputControl.get("Дата")).getValue();
        String user = ((TextField)fieldAndInputControl.get("Пользователь")).getText();
        String number = ((TextField)fieldAndInputControl.get("Номер")).getText();
        String currency = ((TextField)fieldAndInputControl.get("Валюта")).getText();
        double amount = Double.parseDouble(((NumberTextField)fieldAndInputControl.get("Сумма")).getText());
        double exchangeRate = Double.parseDouble(((NumberTextField)fieldAndInputControl.get("Курс")).getText());
        double product = Double.parseDouble(((NumberTextField)fieldAndInputControl.get("Товар")).getText());
        double quantity = Double.parseDouble(((NumberTextField)fieldAndInputControl.get("Количество")).getText());

        ((InvoiceDocument)this.document).setDate(date);
        ((InvoiceDocument)this.document).setUser(user);
        ((InvoiceDocument)this.document).setNumber(number);
        ((InvoiceDocument)this.document).setCurrency(currency);
        ((InvoiceDocument)this.document).setAmount(amount);
        ((InvoiceDocument)this.document).setExchangeRate(exchangeRate);
        ((InvoiceDocument)this.document).setProduct(product);
        ((InvoiceDocument)this.document).setQuantity(quantity);
    }

    @Override
    protected void fillFields() {
        LocalDate date = ((InvoiceDocument)document).getDate();
        String user = ((InvoiceDocument)document).getUser();
        String number = ((InvoiceDocument)document).getNumber();
        String currency = ((InvoiceDocument)document).getCurrency();
        String amount = String.valueOf(((InvoiceDocument)document).getAmount());
        String exchangeRate = String.valueOf(((InvoiceDocument)document).getExchangeRate());
        String product = String.valueOf(((InvoiceDocument)document).getProduct());
        String quantity = String.valueOf(((InvoiceDocument)document).getQuantity());

        ((DatePicker)fieldAndInputControl.get("Дата")).setValue(date);
        ((TextField)fieldAndInputControl.get("Пользователь")).setText(user);
        ((TextField)fieldAndInputControl.get("Номер")).setText(number);
        ((TextField)fieldAndInputControl.get("Валюта")).setText(currency);
        ((NumberTextField)fieldAndInputControl.get("Сумма")).setText(amount);
        ((NumberTextField)fieldAndInputControl.get("Курс")).setText(exchangeRate);
        ((NumberTextField)fieldAndInputControl.get("Товар")).setText(product);
        ((NumberTextField)fieldAndInputControl.get("Количество")).setText(quantity);
    }

    private boolean isDateValid(String input, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false); // Disable lenient parsing (strict format checking)
        try {
            sdf.parse(input); // Try to parse the input
            return true; // Input is a valid date
        } catch (ParseException e) {
            return false; // Input is not a valid date
        }
    }
}
