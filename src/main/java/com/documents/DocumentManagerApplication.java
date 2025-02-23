package com.documents;

import java.util.List;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.documents.model.*;
import com.documents.form.*;

// TODO: add fields validation (validate invalid date input and allow floating numbers); handle null document object; add all the other document types; add list of created documents to the main form; refactor the abstract document form class constructor -- create private methods for decomposition

public class DocumentManagerApplication extends Application {

    private List<AbstractDocumentForm> forms;
    private ObservableList<AbstractDocument> documents;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        forms = new ArrayList<>();
        forms.add(new InvoiceDocumentForm());
        forms.add(new RequestDocumentForm());
        forms.add(new PaymentDocumentForm());

        VBox root = new VBox();

        documents = FXCollections.observableArrayList();
        ListView<AbstractDocument> listView = new ListView<>(documents);       

        for (var form : forms) {
            Button newDocumentBtn = new Button(form.getDocumentName());

            newDocumentBtn.setOnAction(e -> {
                newDocumentBtn.setDisable(true);
                form.showAndWait();
                AbstractDocument document = form.getDocument();
                System.out.println(document);
                if (document != null)
                    documents.add(document);
                newDocumentBtn.setDisable(false);
            });

            root.getChildren().addAll(newDocumentBtn);
        }

        root.getChildren().add(listView);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Document Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
