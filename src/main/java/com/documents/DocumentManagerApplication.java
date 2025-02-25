package com.documents;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.documents.model.*;
import com.documents.form.*;

import com.documents.service.DocumentService;

// TODO: add fields validation (validate invalid date input and allow floating numbers); add documents deletion feature; refactor the abstract document form class constructor -- create private methods for decomposition

public class DocumentManagerApplication extends Application {

    private Map<String, AbstractDocumentForm> documentNameAndForm;
    private ObservableList<AbstractDocument> documents;
    private DocumentService documentService;

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

        documentService = new DocumentService();

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

        var saveButton = new Button("Сохранить");
        saveButton.setOnAction(e -> {
            AbstractDocument selectedDocument = listView.getSelectionModel().getSelectedItem();
            if (selectedDocument == null)
                return;
            var fileChooser = new FileChooser();
            fileChooser.setTitle("Сохранить в файл");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file == null)
                return;

            try {
                documentService.saveToFile(selectedDocument, file);
                var alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Документ сохранён");
                alert.showAndWait();
            }
            catch (IOException ioe) {
                var alert = new Alert(AlertType.WARNING);
                alert.setContentText(ioe.getMessage());
                alert.showAndWait();
            }
        });
        buttonBox.getChildren().add(saveButton);

        var readButton = new Button("Загрузить");
        readButton.setOnAction(e -> {
            var fileChooser = new FileChooser();
            fileChooser.setTitle("Загрузить из файла");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file == null)
                return;

            try {
                AbstractDocument document = documentService.readFromFile(file);
                if (document != null)
                    documents.add(document);
            }
            catch (IOException ioe) {
                var alert = new Alert(AlertType.WARNING);
                alert.setContentText(ioe.getMessage());
                alert.showAndWait();
            }
        });
        buttonBox.getChildren().add(readButton);

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
