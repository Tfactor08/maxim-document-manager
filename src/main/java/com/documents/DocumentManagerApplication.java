package com.documents;

import java.util.List;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.documents.model.AbstractDocument;
import com.documents.model.InvoiceDocument;
import com.documents.form.AbstractDocumentForm;
import com.documents.form.InvoiceDocumentForm;

// TODO: take the to-date method to utils; add changes to git finally; add fields validation; handle null document object; add all the other document types; add list of created documents to the main form

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
        Button formButton = new Button("New Document");

        for (var form : forms) {
            formButton.setOnAction(e -> {
                formButton.setDisable(true);
                form.showAndWait();
                AbstractDocument document = form.getFilledDocument();
                System.out.println(document);
                formButton.setDisable(false);
            });

            root.getChildren().addAll(formButton);
        }

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Main Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //private AbstractDocument showDialog(Stage owner, AbstractDocumentForm form) {
        //Stage dialog = new Stage();
        //dialog.setTitle("Dialog Window");
        //dialog.initOwner(owner);

        //StackPane dialogRoot = new StackPane();
        //dialogRoot.getChildren().add(form);

        //Scene dialogScene = new Scene(dialogRoot, 200, 100);
        //dialog.setScene(dialogScene);
        //dialog.showAndWait();

        //AbstractDocument document;

        //dialog.setOnCloseRequest(e -> {
         //   System.out.println("fuck");
          //  document = form.getFilledDocument();
        //});

        //return form.getFilledDocument();
    //}

    // TODO: add submitHandler. Receives a filled AbstractDocument and somehow proccesses it.
}
