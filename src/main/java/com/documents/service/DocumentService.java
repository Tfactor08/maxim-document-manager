package com.documents.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDate;

import com.documents.model.*;

public class DocumentService {
    private FileWriter fileWriter;
    private Scanner scanner;

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
