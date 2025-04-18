package com.portdombo.backend.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTechnologyRequest {
    private String name;
    private String description;
    private String image;
    private boolean highlighted;
}
