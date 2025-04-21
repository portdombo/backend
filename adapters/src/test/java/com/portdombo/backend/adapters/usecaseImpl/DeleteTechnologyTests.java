package com.portdombo.backend.adapters.usecaseImpl;

import com.portdombo.backend.adapters.gateway.IDeleteTechnologyGateway;
import com.portdombo.backend.adapters.mocks.TechnologyFactory;
import com.portdombo.backend.adapters.usesaceImpl.DeleteTechnology;
import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.domain.exceptions.NotFoundException;
import com.portdombo.backend.usecase.technology.IDeleteTechnology;
import com.portdombo.backend.usecase.technology.IReadTechnologyByCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;

public class DeleteTechnologyTests {
    private IDeleteTechnology deleteTechnology;
    private IReadTechnologyByCode readTechnologyByCode;
    private IDeleteTechnologyGateway gateway;

    @BeforeEach
    void setUp() {
        readTechnologyByCode = mock(IReadTechnologyByCode.class);
        gateway = mock(IDeleteTechnologyGateway.class);
        deleteTechnology = new DeleteTechnology(readTechnologyByCode, gateway);
    }

    @Test
    @DisplayName("Should throw NotFoundException if technology does not exist")
    void shouldThrowNotFoundExceptionIfTechnologyDoesNotExist() {
        Long code = 1L;
        when(readTechnologyByCode.read(code)).thenThrow(new NotFoundException("Technology not found"));
        Throwable exception = catchThrowable(() -> deleteTechnology.delete(code));
        assertThat(exception).isInstanceOf(NotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo("Technology not found");
        verify(readTechnologyByCode, times(1)).read(code);
        verify(gateway, times(0)).delete(code);
    }

    @Test
    @DisplayName("Should delete technology")
    void shouldDeleteTechnology() {
        Technology technology = TechnologyFactory.createTechnologyFactory();
        when(readTechnologyByCode.read(technology.getCode())).thenReturn(technology);
        deleteTechnology.delete(technology.getCode());
        verify(readTechnologyByCode, times(1)).read(technology.getCode());
        verify(gateway, times(1)).delete(technology.getCode());
    }
}
