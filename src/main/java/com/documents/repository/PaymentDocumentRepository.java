package com.documents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.documents.model.PaymentDocument;

public interface PaymentDocumentRepository extends JpaRepository<PaymentDocument, Long> {
}
