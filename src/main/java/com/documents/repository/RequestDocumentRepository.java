package com.documents.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.documents.model.RequestDocument;

public interface RequestDocumentRepository extends JpaRepository<RequestDocument, String> {
}
