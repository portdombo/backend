package com.portdombo.backend.infrastructure.service;

import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.infrastructure.persistence.entity.TechnologyEntity;
import com.portdombo.backend.infrastructure.persistence.repository.TechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


@SpringBootTest
@ActiveProfiles("test")
public class TechnologyServiceTests {
    private TechnologyService technologyService;
    @Mock
    private TechnologyRepository repository;
    @Mock
    private ModelMapper mapper;


    @BeforeEach
    void setUp() {
        technologyService = new TechnologyService(repository, mapper);
    }

    @Test
    @DisplayName("Should create technology")
    void shouldCreateTechnology() {
        Technology technology = Technology
                .builder()
                .name("Name")
                .description("Description")
                .image("Image")
                .highlighted(false)
                .build();

        TechnologyEntity entity = TechnologyEntity
                .builder()
                .name(technology.getName())
                .description(technology.getDescription())
                .image(technology.getImage())
                .highlighted(technology.isHighlighted())
                .build();

        TechnologyEntity savedEntity = TechnologyEntity
                .builder()
                .id(UUID.randomUUID())
                .name(technology.getName())
                .description(technology.getDescription())
                .image(technology.getImage())
                .highlighted(technology.isHighlighted())
                .build();


        when(mapper.map(technology, TechnologyEntity.class)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        technologyService.create(technology);
        verify(repository, times(1)).save(entity);
    }
}
