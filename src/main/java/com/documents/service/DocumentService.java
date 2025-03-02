package com.documents.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.context.ConfigurableApplicationContext;

import com.documents.model.*;
import com.documents.repository.*;

@Service
public class DocumentService {
    private InvoiceDocumentRepository invoiceDocumentRepository;
    private RequestDocumentRepository requestDocumentRepository;
    private PaymentDocumentRepository paymentDocumentRepository;

    private FileWriter fileWriter;
    private Scanner scanner;

    public DocumentService(ConfigurableApplicationContext springContext) {
        invoiceDocumentRepository = springContext.getBean(InvoiceDocumentRepository.class);
        requestDocumentRepository = springContext.getBean(RequestDocumentRepository.class);
        paymentDocumentRepository = springContext.getBean(PaymentDocumentRepository.class);
    }

    public void saveToDb(AbstractDocument document) {
        switch (document.getName()) {
            case "Накладная":
                invoiceDocumentRepository.save((InvoiceDocument)document);
                break;
            case "Заявка":
                requestDocumentRepository.save((RequestDocument)document);
                break;
            case "Платёжка":
                paymentDocumentRepository.save((PaymentDocument)document);
                break;
        }
    }

    public List<AbstractDocument> getDocumentsFromDb() {
        var docs = new ArrayList<AbstractDocument>();
        docs.addAll(invoiceDocumentRepository.findAll());
        docs.addAll(requestDocumentRepository.findAll());
        docs.addAll(paymentDocumentRepository.findAll());
        return docs;
    }

    public void saveToFile(AbstractDocument document, File file) throws IOException {
        fileWriter = new FileWriter(file);
        fileWriter.write(document.getFullDesc());
        fileWriter.close();
    }

    public AbstractDocument readFromFile(File file) throws IOException {
        AbstractDocument document;
        scanner = new Scanner(file);
        var lines = new ArrayList<String>();

        if (!scanner.hasNextLine())
            return null;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
        }

        switch (lines.get(0)) {
            case ("Накладная"):
                document = new InvoiceDocument();
                break;
            case ("Платёжка"):
                document = new PaymentDocument();
                break;
            case ("Заявка на оплату"):
                document = new RequestDocument();
                break;
            default:
                document = null;
                break;
        }

        try {
            String line = lines.get(1);
            LocalDate date = LocalDate.parse(line.split("Дата:")[1].trim());
            line = lines.get(2);
            String number = line.split("Номер:")[1].trim();
            document.setDate(date);
            document.setNumber(number);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return document;
    }
}
