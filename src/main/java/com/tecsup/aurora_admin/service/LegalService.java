package com.tecsup.aurora_admin.service;

import com.tecsup.aurora_admin.model.LegalDocument;
import com.tecsup.aurora_admin.repository.LegalDocumentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LegalService {

    private final LegalDocumentRepository repository;

    public LegalService(LegalDocumentRepository repository) {
        this.repository = repository;
    }

    public String getTermsAndConditions() {
        // Código 100 es Términos, según tu lógica en Django
        return repository.findByCode(100)
                .map(LegalDocument::getContentHtml)
                .orElse("<h1>No terms found</h1>");
    }

    public void updateTerms(String newHtmlContent) {
        Optional<LegalDocument> docOpt = repository.findByCode(100);
        
        if (docOpt.isPresent()) {
            LegalDocument doc = docOpt.get();
            doc.setContentHtml(newHtmlContent);
            doc.setLastUpdated(LocalDateTime.now());
            repository.save(doc);
        } else {
            // Si no existe, lo crea (compatible con Django)
            LegalDocument newDoc = new LegalDocument();
            newDoc.setCode(100);
            newDoc.setTitle("Términos y Condiciones");
            newDoc.setContentHtml(newHtmlContent);
            newDoc.setLastUpdated(LocalDateTime.now());
            repository.save(newDoc);
        }
    }
}