package com.portdombo.backend.adapters.mocks;

import com.portdombo.backend.domain.entity.Technology;

public class TechnologyFactory {
    public static Technology createTechnologyFactory() {
        return Technology
                .builder()
                .code(1L)
                .name("Name")
                .description("Description")
                .image("Image")
                .highlighted(false)
                .highlighted(true)
                .build();
    }
}
