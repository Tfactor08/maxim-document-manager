package com.documents;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.documents.model.*;
import com.documents.form.*;

// TODO: add fields validation (validate invalid date input and allow floating numbers); add documents deletion feature; refactor the abstract document form class constructor -- create private methods for decomposition

public class DocumentManagerApplication extends Application {

    private Map<String, AbstractDocumentForm> documentNameAndForm;
    private ObservableList<AbstractDocument> documents;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        var invoiceDocumentForm = new InvoiceDocumentForm();
        var paymentDocumentForm = new PaymentDocumentForm();
        var requestDocumentForm = new RequestDocumentForm();
        documentNameAndForm = Map.of(
            invoiceDocumentForm.getDocumentName(), invoiceDocumentForm,
            paymentDocumentForm.getDocumentName(), paymentDocumentForm,
            requestDocumentForm.getDocumentName(), requestDocumentForm
        );

        BorderPane root = new BorderPane();
        VBox buttonBox = new VBox(10);
        root.setPadding(new Insets(10));
        buttonBox.setPadding(new Insets(0, 0, 0, 10));

        documents = FXCollections.observableArrayList();
        ListView<AbstractDocument> listView = new ListView<>(documents);       

        for (var form : documentNameAndForm.values()) {
            Button newDocumentBtn = new Button(form.getDocumentName());

            newDocumentBtn.setOnAction(e -> {
                form.showAndWait();
                AbstractDocument document = form.getDocument();
                System.out.println(document);
                if (document != null)
                    documents.add(document);
            });

            buttonBox.getChildren().add(newDocumentBtn);
        }

        var viewButton = new Button("Просмотр");
        viewButton.setOnAction(e -> {
            AbstractDocument selectedDocument = listView.getSelectionModel().getSelectedItem();
            if (selectedDocument == null)
                return;
            var form = documentNameAndForm.get(selectedDocument.getName());
            form.showDocumentAndWait(selectedDocument);
            var updatedDoc = form.getDocument();
            if (updatedDoc != null)
                updateDocumentInListview(selectedDocument, form.getDocument());
        });
        buttonBox.getChildren().add(viewButton);

        root.setCenter(listView);
        root.setRight(buttonBox);

        Scene scene = new Scene(root, 700, 400);
        primaryStage.setTitle("Document Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateDocumentInListview(AbstractDocument oldDoc, AbstractDocument newDoc) {
        var oldDocIndex = documents.indexOf(oldDoc);
        documents.set(oldDocIndex, newDoc);
    }
}
