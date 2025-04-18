package com.portdombo.backend.adapters.usecaseImpl;

import com.portdombo.backend.adapters.usesaceImpl.ExistsTechnologyByName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ExistsTechnologyByNameTests {
    private ExistsTechnologyByName existsTechnologyByName;


    @BeforeEach
    void setUp() {
        existsTechnologyByName = new ExistsTechnologyByName();
    }

    @Test
    @DisplayName("Should return false if technology name does not exist")
    void shouldReturnFalseIfTechnologyNameDoesNotExist() {
        String name = "any_name";
        boolean result = existsTechnologyByName.existsByName(name);
        assertFalse(result);
    }
}
