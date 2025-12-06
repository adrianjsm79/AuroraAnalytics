package com.tecsup.aurora_admin.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "users_legaldocument")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LegalDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer code;

    @Column(length = 200)
    private String title;

    @Column(name = "content_html", columnDefinition = "TEXT")
    private String contentHtml;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}