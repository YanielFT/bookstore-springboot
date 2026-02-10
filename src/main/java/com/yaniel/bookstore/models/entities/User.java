package com.yaniel.bookstore.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 30)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(length = 30,unique = true)
    private String username;
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean enabled;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        String base = this.name.toLowerCase()
                .replace(" ", "")
                .replaceAll("[^a-z0-9]", ""); // normaliza

        int randomSuffix = ThreadLocalRandom.current().nextInt(100, 999); // número aleatorio de 3 dígitos
        this.username = base + "_" + randomSuffix;
        if (this.enabled == null) {
            this.enabled = true;
        }
    }

}
