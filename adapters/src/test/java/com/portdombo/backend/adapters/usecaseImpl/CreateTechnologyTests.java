package com.portdombo.backend.adapters.usecaseImpl;

import com.portdombo.backend.adapters.gateway.ICreateTechnologyGateway;
import com.portdombo.backend.adapters.usesaceImpl.CreateTechnology;
import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.domain.exceptions.ConflictException;
import com.portdombo.backend.usecase.technology.IExistsTechnologyByCode;
import com.portdombo.backend.usecase.technology.IExistsTechnologyByName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;


public class CreateTechnologyTests {
    private CreateTechnology createTechnology;
    private IExistsTechnologyByName existsTechnologyByName;
    private IExistsTechnologyByCode existsTechnologyByCode;
    private ICreateTechnologyGateway createTechnologyGateway;

    @BeforeEach
    void setUp() {
        existsTechnologyByName = Mockito.mock(IExistsTechnologyByName.class);
        createTechnologyGateway = Mockito.mock(ICreateTechnologyGateway.class);
        existsTechnologyByCode = Mockito.mock(IExistsTechnologyByCode.class);
        createTechnology = new CreateTechnology(existsTechnologyByName, createTechnologyGateway);
    }

    @Test
    @DisplayName("Should throw ConflictException if technology already exists")
    void shouldThrowConflictExceptionIfTechnologyAlreadyExists() {
        Technology technology = Technology
                .builder()
                .code(1L)
                .name("Name")
                .description("Description")
                .image("Image")
                .highlighted(false)
                .highlighted(true)
                .build();

        when(existsTechnologyByName.existsByName(technology.getName())).thenReturn(true);
        Throwable exception = catchThrowable(() -> createTechnology.create(technology));

        assertThat(exception).isInstanceOf(ConflictException.class);
        assertThat(exception.getMessage()).isEqualTo("Technology already exists");
        verify(existsTechnologyByName, times(1)).existsByName(technology.getName());
    }

    @Test
    @DisplayName("Should save technology with new generated code if does not exist")
    void shouldSaveTechnologyWithNewGeneratedCodeIfDoesNotExist() {
        Technology technology = Technology
                .builder()
                .code(1L)
                .name("Name")
                .description("Description")
                .image("Image")
                .highlighted(false)
                .highlighted(true)
                .build();

        when(existsTechnologyByName.existsByName(technology.getName())).thenReturn(false);

        createTechnology.create(technology);
        verify(existsTechnologyByName, times(1)).existsByName(technology.getName());
    }
}
