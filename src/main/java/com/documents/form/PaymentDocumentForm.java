package com.documents.form;

import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;

import javafx.scene.control.*;

import com.documents.model.AbstractDocument;
import com.documents.model.PaymentDocument;
import com.documents.form.AbstractDocumentForm;
import com.documents.util.InputProcessor;
import com.documents.control.NumberTextField;

public class PaymentDocumentForm extends AbstractDocumentForm {
    private static Map<String, Control> fieldAndInputControl = Map.of(
        "Номер", new TextField(),
        "Дата", new DatePicker(),
        "Пользователь", new TextField(),
        "Сумма", new NumberTextField(),
        "Сотрудник", new TextField()
    );

    public PaymentDocumentForm() {
        super(fieldAndInputControl);
    }

    @Override
    protected void initDocument() {
        this.document = new PaymentDocument();
    }

    @Override
    public void fillDocument() {
        LocalDate date = ((DatePicker)fieldAndInputControl.get("Дата")).getValue();
        String user = ((TextField)fieldAndInputControl.get("Пользователь")).getText();
        String number = ((TextField)fieldAndInputControl.get("Номер")).getText();
        String employee = ((TextField)fieldAndInputControl.get("Сотрудник")).getText();
        double amount = Double.parseDouble(((NumberTextField)fieldAndInputControl.get("Сумма")).getText());

        ((PaymentDocument)this.document).setDate(date);
        ((PaymentDocument)this.document).setNumber(number);
        ((PaymentDocument)this.document).setUser(user);
        ((PaymentDocument)this.document).setAmount(amount);
        ((PaymentDocument)this.document).setEmployee(employee);
    }

    @Override
    protected void fillFields() {
        LocalDate date = ((PaymentDocument)document).getDate();
        String user = ((PaymentDocument)document).getUser();
        String number = ((PaymentDocument)document).getNumber();
        String amount = String.valueOf(((PaymentDocument)document).getAmount());
        String employee = ((PaymentDocument)document).getEmployee();

        ((DatePicker)fieldAndInputControl.get("Дата")).setValue(date);
        ((TextField)fieldAndInputControl.get("Пользователь")).setText(user);
        ((TextField)fieldAndInputControl.get("Номер")).setText(number);
        ((NumberTextField)fieldAndInputControl.get("Сумма")).setText(amount);
        ((TextField)fieldAndInputControl.get("Сотрудник")).setText(employee);
    }
}
