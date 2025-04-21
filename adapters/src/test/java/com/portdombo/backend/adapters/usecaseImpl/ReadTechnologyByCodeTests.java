package com.portdombo.backend.adapters.usecaseImpl;

import com.portdombo.backend.adapters.gateway.IReadTechnologyByCodeGateway;
import com.portdombo.backend.adapters.usesaceImpl.ReadTechnologyByCode;
import com.portdombo.backend.domain.exceptions.NotFoundException;
import com.portdombo.backend.usecase.technology.IReadTechnologyByCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;

public class ReadTechnologyByCodeTests {
    private IReadTechnologyByCode readTechnologyByCode;
    private IReadTechnologyByCodeGateway gateway;


    @BeforeEach
    public void setUp() {
        gateway = Mockito.mock(IReadTechnologyByCodeGateway.class);
        readTechnologyByCode = new ReadTechnologyByCode(gateway);
    }

    @Test
    @DisplayName("Should throws NotFoundException if technology code does not exist")
    void shouldThrowsNotFoundExceptionIfTechnologyCodeDoesNotExist() {
        Long code = 1L;
        when(gateway.read(code)).thenReturn(Optional.empty());
        Throwable exception = catchThrowable(() -> readTechnologyByCode.read(code));
        assertThat(exception).isInstanceOf(NotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo("Technology not found");
        verify(gateway, times(1)).read(code);
    }
}
