package com.portdombo.backend.infrastructure.mocks;

import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.infrastructure.persistence.entity.TechnologyEntity;

import java.util.UUID;

public class TechnologyFactory {
    public static Technology technologyFactory() {
        return Technology
                .builder()
                .name("Name")
                .description("Description")
                .image("Image")
                .highlighted(false)
                .build();
    }

    public static TechnologyEntity toTechnologyEntityFactory(Technology technology) {
        return TechnologyEntity
                .builder()
                .name(technology.getName())
                .description(technology.getDescription())
                .image(technology.getImage())
                .highlighted(technology.isHighlighted())
                .build();
    }

    public static TechnologyEntity toSaveTechnologyEntityFactory(TechnologyEntity technology) {
        return TechnologyEntity
                .builder()
                .id(UUID.randomUUID())
                .name(technology.getName())
                .description(technology.getDescription())
                .image(technology.getImage())
                .highlighted(technology.isHighlighted())
                .build();
    }
}
