package com.portdombo.backend.adapters.usecaseImpl;

import com.portdombo.backend.adapters.gateway.IExistsTechnologyByNameGateway;
import com.portdombo.backend.adapters.usesaceImpl.ExistsTechnologyByName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ExistsTechnologyByNameTests {
    private ExistsTechnologyByName existsTechnologyByName;
    private IExistsTechnologyByNameGateway gateway;

    @BeforeEach
    void setUp() {
        gateway = Mockito.mock(IExistsTechnologyByNameGateway.class);
        existsTechnologyByName = new ExistsTechnologyByName(gateway);
    }

    @Test
    @DisplayName("Should return false if technology name does not exist")
    void shouldReturnFalseIfTechnologyNameDoesNotExist() {
        String name = "any_name";
        when(gateway.existsByName(name)).thenReturn(false);
        boolean result = existsTechnologyByName.existsByName(name);
        assertFalse(result);
        verify(gateway, times(1)).existsByName(name);
    }

    @Test
    @DisplayName("Should return true if technology name exists")
    void shouldReturnTrueIfTechnologyNameExists() {
        String name = "any_name";
        when(gateway.existsByName(name)).thenReturn(true);
        boolean result = existsTechnologyByName.existsByName(name);
        assertTrue(result);
        verify(gateway, times(1)).existsByName(name);
    }
}
