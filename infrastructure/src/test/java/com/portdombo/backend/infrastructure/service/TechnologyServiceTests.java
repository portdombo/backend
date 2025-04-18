package com.portdombo.backend.infrastructure.service;

import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.infrastructure.mocks.TechnologyFactory;
import com.portdombo.backend.infrastructure.persistence.entity.TechnologyEntity;
import com.portdombo.backend.infrastructure.persistence.repository.TechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
        Technology technology = TechnologyFactory.technologyFactory();
        TechnologyEntity entity = TechnologyFactory.toTechnologyEntityFactory(technology);
        TechnologyEntity savedEntity = TechnologyFactory.toSaveTechnologyEntityFactory(entity);
        when(mapper.map(technology, TechnologyEntity.class)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        technologyService.create(technology);
        verify(repository, times(1)).save(entity);
    }
}
