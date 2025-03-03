package com.documents.form;

import java.util.Map;
import java.util.HashMap;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

import com.documents.model.AbstractDocument;

public abstract class AbstractDocumentForm extends Stage {
    private VBox vbox;

    protected AbstractDocument document;
    protected Map<String, Control> fieldAndInputControl;

    protected abstract void initDocument();
    protected abstract void fillDocument();
    protected abstract void fillFields();

    public AbstractDocumentForm(Map<String, Control> fieldAndInputControl) {
        this.fieldAndInputControl = fieldAndInputControl;

        initDocument();
        initForm();
        initLayout();

        addInputFields();
        addSubmitButton();

        initScene();
    }

    private void initForm() {
        this.setTitle(document.getName());
        this.initModality(Modality.APPLICATION_MODAL);
        this.setOnCloseRequest(e -> {
            setDocumentToNull();
            clearFields();
        });
    }

    private void initLayout() {
        vbox = new VBox();
        vbox.setPadding(new Insets(10));
    }

    private void initScene() {
        var scene = new Scene(vbox);
        setScene(scene);
    }

    private void addSubmitButton() {
        var submitButton = new Button("Ok");
        submitButton.setOnAction(event -> {
            try {
                fillDocument();
                clearFields();
                super.close();
            }
            catch (Exception e) {
                showAlert("Некорректный ввод!");
                System.out.println(e);
            }
        });
        vbox.getChildren().add(submitButton);
    }

    private void addInputFields() {
        for (var fieldAndInputControlItem : fieldAndInputControl.entrySet()) {
            var label = new Label(fieldAndInputControlItem.getKey());
            var inputControl = fieldAndInputControlItem.getValue();
            vbox.getChildren().addAll(label, inputControl);
        }
    }

    private void setDocumentToNull() {
        this.document = null;
    }

    private void showAlert(String message) {
        var alert = new Alert(AlertType.WARNING);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        for (var control : fieldAndInputControl.values())
            if (control instanceof DatePicker dp)
                dp.setValue(null);
            else
                ((TextField)control).clear();
    }

    @Override
    public void showAndWait() {
        initDocument();
        super.showAndWait();
    }

    public String getDocumentName() {
        return document.getName();
    }

    public AbstractDocument getDocument() {
        return this.document;
    }

    public void showDocumentAndWait(AbstractDocument document) {
        this.document = document;
        fillFields();
        super.showAndWait();
    }
}
