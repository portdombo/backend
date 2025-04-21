package com.portdombo.backend.infrastructure.service;

import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.infrastructure.mapper.TechnologyMapper;
import com.portdombo.backend.infrastructure.mocks.TechnologyMocksFactory;
import com.portdombo.backend.infrastructure.persistence.entity.TechnologyEntity;
import com.portdombo.backend.infrastructure.persistence.repository.TechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


@SpringBootTest
@ActiveProfiles("test")
public class TechnologyServiceTests {
    private TechnologyService technologyService;
    @Mock
    private TechnologyRepository repository;
    @Mock
    private TechnologyMapper mapper;


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

    @Test
    @DisplayName("Should return a list of technologies on read all")
    void shouldReturnListOfTechnologiesOnReadAll() {
        List<TechnologyEntity> entitiesList = TechnologyMocksFactory.toListTechnologyEntity();
        List<Technology> technologies = TechnologyMocksFactory.toListTechnology(entitiesList);
        when(repository.findAll()).thenReturn(entitiesList);
        when(mapper.toTechnologyList(entitiesList)).thenReturn(technologies);
        List<Technology> result = technologyService.readAll();
        verify(repository, times(1)).findAll();
        assertThat(result).isEqualTo(technologies);
        assert result.size() == 2;
    }

    @Test
    @DisplayName("Should return optional of empty if technology does not exist")
    void shouldReturnOptionalOfEmptyIfTechnologyDoesNotExist() {
        Long code = 1L;
        when(repository.findByCode(code)).thenReturn(Optional.empty());
        Optional<Technology> result = technologyService.read(code);
        verify(repository, times(1)).findByCode(code);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should return optional of technology if technology exists on read by code")
    void shouldReturnOptionalOfTechnologyIfTechnologyExistsOnReadByCode() {
        Technology technology = TechnologyMocksFactory.technologyFactory();
        TechnologyEntity entity = TechnologyMocksFactory.toTechnologyEntity(technology);
        technology.setCode(entity.getCode());
        when(repository.findByCode(entity.getCode())).thenReturn(Optional.of(entity));
        when(mapper.map(entity, Technology.class)).thenReturn(technology);
        Optional<Technology> result = technologyService.read(entity.getCode());
        verify(repository, times(1)).findByCode(entity.getCode());
        assertThat(result).isEqualTo(Optional.of(technology));
    }

    @Test
    @DisplayName("Should update technology")
    void shouldUpdateTechnology() {
        Technology technology = TechnologyMocksFactory.technologyFactory();
        TechnologyEntity entity = TechnologyMocksFactory.toTechnologyEntity(technology);
        TechnologyEntity savedEntity = TechnologyMocksFactory.toSaveTechnologyEntityFactory(entity);
        when(mapper.map(technology, TechnologyEntity.class)).thenReturn(entity);
        when(repository.findByCode(technology.getCode())).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(savedEntity);
        technologyService.update(technology);
        verify(repository, times(1)).save(entity);
    }

    @Test
    @DisplayName("Should delete technology")
    void shouldDeleteTechnology() {
        Technology technology = TechnologyMocksFactory.technologyFactory();
        TechnologyEntity entity =  TechnologyMocksFactory.toTechnologyEntity(technology);
        when(repository.findByCode(technology.getCode())).thenReturn(Optional.of(entity));
        technologyService.delete(technology.getCode());
        verify(repository, times(1)).delete(entity);
    }
}
