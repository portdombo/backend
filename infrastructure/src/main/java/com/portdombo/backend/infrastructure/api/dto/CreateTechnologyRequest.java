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

    @NotNull(message = "Description is required!")
    @NotBlank(message = "Description is required!")
    private String description;

    @NotNull(message = "ImageURL is required!")
    @NotBlank(message = "ImageURL is required!")
    private String image;
    private boolean highlighted;
}
