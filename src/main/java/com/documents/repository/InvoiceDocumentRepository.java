package com.documents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.documents.model.InvoiceDocument;

public interface InvoiceDocumentRepository extends JpaRepository<InvoiceDocument, String> {
}
