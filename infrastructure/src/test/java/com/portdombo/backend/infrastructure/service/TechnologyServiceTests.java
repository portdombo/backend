package com.portdombo.backend.infrastructure.service;

import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.infrastructure.mocks.TechnologyMocksFactory;
import com.portdombo.backend.infrastructure.persistence.entity.TechnologyEntity;
import com.portdombo.backend.infrastructure.persistence.repository.TechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
        Technology technology = TechnologyMocksFactory.technologyFactory();
        TechnologyEntity entity = TechnologyMocksFactory.toTechnologyEntityFactory(technology);
        TechnologyEntity savedEntity = TechnologyMocksFactory.toSaveTechnologyEntityFactory(entity);
        when(mapper.map(technology, TechnologyEntity.class)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        technologyService.create(technology);
        verify(repository, times(1)).save(entity);
    }

    @Test
    @DisplayName("Should return false if technology name does not exist")
    void shouldReturnFalseIfTechnologyNameDoesNotExist() {
        String name = "Name";
        when(repository.existsByName(name)).thenReturn(false);
        boolean result = technologyService.existsByName(name);
        verify(repository, times(1)).existsByName(name);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Should return true if technology name exists")
    void shouldReturnTrueIfTechnologyNameExists() {
        String name = "Name";
        when(repository.existsByName(name)).thenReturn(true);
        boolean result = technologyService.existsByName(name);
        verify(repository, times(1)).existsByName(name);
        assertThat(result).isTrue();
    }
}
