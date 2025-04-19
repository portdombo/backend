package com.portdombo.backend.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_technologies")
public class TechnologyEntity extends BaseEntity {

    @Id
    @Column(length = 32)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private Long code;

    private String name;
    private String description;
    private String image;
    private boolean highlighted;

    @PrePersist
    public void generateCode() {
        if (code == null) {
            this.code = ThreadLocalRandom.current().nextLong(100000, 999999);
        }
    }
}
