package com.tecsup.aurora_admin.repository;

import com.tecsup.aurora_admin.model.LegalDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LegalDocumentRepository extends JpaRepository<LegalDocument, Long> {

    // Buscar por código (ej: 100 para Términos)
    Optional<LegalDocument> findByCode(Integer code);
}