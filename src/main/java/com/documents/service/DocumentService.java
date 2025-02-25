package com.documents.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

import com.documents.model.AbstractDocument;

public class DocumentService {
    private FileWriter fileWriter;

    public void saveToFile(AbstractDocument document, File file) throws IOException {
        fileWriter = new FileWriter(file);
        fileWriter.write(document.getFullDesc());
        fileWriter.close();
    }

    public AbstractDocument readFromFile(String path) throws IOException {
        return null;
    }
}
