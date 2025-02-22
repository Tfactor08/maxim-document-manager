package com.documents;

import java.util.List;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.documents.model.AbstractDocument;
import com.documents.model.InvoiceDocument;
import com.documents.form.AbstractDocumentForm;
import com.documents.form.InvoiceDocumentForm;

// TODO: add fields validation; handle null document object; add all the other document types; add list of created documents to the main form

public class DocumentManagerApplication extends Application {

    private List<AbstractDocumentForm> forms;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        forms = new ArrayList<>();
        forms.add(new InvoiceDocumentForm());

        VBox root = new VBox();

        for (var form : forms) {
            Button newDocumentBtn = new Button(form.getDocumentName());

            newDocumentBtn.setOnAction(e -> {
                newDocumentBtn.setDisable(true);
                form.showAndWait();
                AbstractDocument document = form.getFilledDocument();
                System.out.println(document);
                newDocumentBtn.setDisable(false);
            });

            root.getChildren().addAll(newDocumentBtn);
        }

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Document Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
