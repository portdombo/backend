package com.portdombo.backend.adapters.usecaseImpl;

import com.portdombo.backend.adapters.gateway.IReadAllTechnologiesGateway;
import com.portdombo.backend.adapters.mocks.TechnologyFactory;
import com.portdombo.backend.adapters.usesaceImpl.ReadAllTechnologies;
import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.usecase.technology.IReadAllTechnologies;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;

public class ReadAllTechnologiesTests {
    private IReadAllTechnologies readAllTechnologies;
    private IReadAllTechnologiesGateway gateway;

    @BeforeEach
    public void setUp() {
        gateway = Mockito.mock(IReadAllTechnologiesGateway.class);
        readAllTechnologies = new ReadAllTechnologies(gateway);
    }

    @Test
    @DisplayName("Should read all technologies")
    void shouldReadAllTechnologies() {
        List<Technology> list = List.of(TechnologyFactory.createTechnologyFactory()
                , TechnologyFactory.createTechnologyFactory());

        when(gateway.readAll()).thenReturn(list);
        List<Technology> result = readAllTechnologies.readAll();
        Assertions.assertThat(result).isEqualTo(list);
        assert result.size() == 2;
        verify(gateway, times(1)).readAll();
    }
}
