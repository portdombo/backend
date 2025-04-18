package com.portdombo.backend.infrastructure.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTechnologyRequest {
    @NotNull(message = "Name is required!")
    @NotBlank(message = "Name is required!")
    private String name;
    private String description;
    private String image;
    private boolean highlighted;
}
