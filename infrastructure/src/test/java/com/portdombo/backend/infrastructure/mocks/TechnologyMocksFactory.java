package com.portdombo.backend.infrastructure.mocks;

import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.infrastructure.api.dto.CreateTechnologyRequest;
import com.portdombo.backend.infrastructure.persistence.entity.TechnologyEntity;

import java.util.List;
import java.util.UUID;

public class TechnologyMocksFactory {
    public static CreateTechnologyRequest createTechnologyRequestFactory() {
        return CreateTechnologyRequest
                .builder()
                .name("Name")
                .description("Description")
                .image("Image")
                .highlighted(false)
                .build();
    }

    public static TechnologyEntity toTechnologyEntityFactory(CreateTechnologyRequest request) {
        return TechnologyEntity
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .image(request.getImage())
                .highlighted(request.isHighlighted())
                .build();
    }

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

    public static TechnologyEntity toTechnologyEntity() {
        return TechnologyEntity
                .builder()
                .id(UUID.randomUUID())
                .code(1L)
                .name("Name")
                .description("Description")
                .image("Image")
                .highlighted(false)
                .build();
    }

    public static TechnologyEntity toTechnologyEntity(Technology technology) {
        return TechnologyEntity
                .builder()
                .id(UUID.randomUUID())
                .code(1L)
                .name(technology.getName())
                .description(technology.getDescription())
                .image(technology.getImage())
                .highlighted(technology.isHighlighted())
                .build();
    }

    public static List<TechnologyEntity> toListTechnologyEntity() {
        return List.of(TechnologyMocksFactory.toTechnologyEntity(), TechnologyMocksFactory.toTechnologyEntity());
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

    public static List<Technology> toListTechnology(List<TechnologyEntity> entities) {
        return entities.stream().map(TechnologyMocksFactory::toTechnology).toList();
    }

    public static Technology toTechnology(TechnologyEntity entity) {
        return Technology.builder()
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .image(entity.getImage())
                .highlighted(entity.isHighlighted())
                .build();
    }
}
