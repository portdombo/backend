package com.portdombo.backend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Technology {
    private Long code;
    private String name;
    private String description;
    private String image;
    private boolean highlighted;
}
