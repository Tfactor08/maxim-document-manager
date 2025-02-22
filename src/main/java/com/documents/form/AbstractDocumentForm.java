package com.documents.form;

import java.util.Map;
import java.util.HashMap;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.documents.model.AbstractDocument;

public abstract class AbstractDocumentForm extends Stage {

    protected AbstractDocument document;
    protected Button submitButton;
    protected Map<String, TextField> field_textField;

    public AbstractDocumentForm(AbstractDocument document, String[] fields) {
        this.document = document;

        this.setTitle(document.getName());
        this.initModality(Modality.APPLICATION_MODAL);

        field_textField = new HashMap<String, TextField>();

        for (String field : fields)
            field_textField.put(field, new TextField());

        // Layout
        VBox vbox = new VBox();

        // Create UI components
        submitButton = new Button("Ok");
        submitButton.setOnAction(event -> {
            close();
        });

        for (var field_textField_items : field_textField.entrySet()) {
            var label = new Label(field_textField_items.getKey());
            var textField = field_textField_items.getValue();
            vbox.getChildren().addAll(label, textField);
        }
        vbox.getChildren().add(this.submitButton);

        // Set the scene
        Scene scene = new Scene(vbox);
        this.setScene(scene);
    }

    public String getDocumentName() {
        return document.getName();
    }

    //protected abstract void initializeForm();
    public abstract AbstractDocument getFilledDocument();
}
