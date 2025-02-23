package com.documents.form;

import java.util.Map;
import java.util.HashMap;

import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.documents.model.AbstractDocument;

public abstract class AbstractDocumentForm extends Stage {
    private VBox vbox;

    protected AbstractDocument document;
    protected Map<String, Control> fieldAndInputControl;

    public AbstractDocumentForm(Map<String, Control> fieldAndInputControl) {
        initDocument();

        this.setTitle(document.getName());
        this.initModality(Modality.APPLICATION_MODAL);
        this.setOnCloseRequest(e -> setDocumentToNull());

        // Layout
        vbox = new VBox();

        // Create UI components
        var submitButton = new Button("Ok");
        submitButton.setOnAction(event -> {
            try {
                this.fillDocument();
                super.close();
            }
            catch (Exception e) {
                showAlert("Некорректный ввод!");
                System.out.println(e);
            }
        });

        for (var fieldAndInputControlItem : fieldAndInputControl.entrySet()) {
            var label = new Label(fieldAndInputControlItem.getKey());
            var inputControl = fieldAndInputControlItem.getValue();
            vbox.getChildren().addAll(label, inputControl);
        }
        vbox.getChildren().add(submitButton);

        var scene = new Scene(vbox);
        setScene(scene);
    }

    protected abstract void initDocument();
    protected abstract void fillDocument();

    private void setDocumentToNull() {
        this.document = null;
    }

    private void showAlert(String message) {
        var alert = new Alert(AlertType.WARNING);
        alert.setContentText(message);
        alert.showAndWait();
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
    };

    //protected abstract void initializeForm();
}
