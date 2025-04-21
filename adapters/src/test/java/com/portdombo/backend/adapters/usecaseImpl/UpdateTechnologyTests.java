package com.portdombo.backend.adapters.usecaseImpl;

import com.portdombo.backend.adapters.IUpdateTechnologyGateway;
import com.portdombo.backend.adapters.mocks.TechnologyFactory;
import com.portdombo.backend.adapters.usesaceImpl.UpdateTechnology;
import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.domain.exceptions.NotFoundException;
import com.portdombo.backend.usecase.technology.IReadTechnologyByCode;
import com.portdombo.backend.usecase.technology.IUpdateTechnology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;

public class UpdateTechnologyTests {
    private IUpdateTechnology updateTechnology;
    private IReadTechnologyByCode readTechnologyByCode;
    private IUpdateTechnologyGateway gateway;

    @BeforeEach
    void setUp() {
        readTechnologyByCode = mock(IReadTechnologyByCode.class);
        gateway =  mock(IUpdateTechnologyGateway.class);
        updateTechnology = new UpdateTechnology(readTechnologyByCode,gateway);
    }


    @Test
    @DisplayName("Should throw NotFoundException if technology does not exist")
    void shouldThrowNotFoundExceptionIfTechnologyDoesNotExist() {
        Technology technology = TechnologyFactory.createTechnologyFactory();
        when(readTechnologyByCode.read(technology.getCode())).thenThrow(new NotFoundException("Technology not found"));
        Throwable exception = catchThrowable(() -> updateTechnology.update(technology));
        assertThat(exception).isInstanceOf(NotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo("Technology not found");
        verify(readTechnologyByCode, times(1)).read(technology.getCode());
        verify(gateway, times(0)).update(technology);
    }
}
