package com.documents.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.TextField;

public final class InputProcessor {
    public static LocalDate getDateFromTextField(TextField textField) {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
       return LocalDate.parse(textField.getText(), formatter);
    }
}
