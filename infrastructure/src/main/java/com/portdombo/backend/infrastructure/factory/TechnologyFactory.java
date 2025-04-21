package com.portdombo.backend.infrastructure.factory;

import com.portdombo.backend.adapters.usesaceImpl.*;
import com.portdombo.backend.infrastructure.service.TechnologyService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class TechnologyFactory {
    private final TechnologyService technologyService;

    @Bean
    public CreateTechnology createTechnology() {
        return new CreateTechnology(existsTechnologyByName(), technologyService);
    }

    @Bean
    public ExistsTechnologyByName existsTechnologyByName() {
        return new ExistsTechnologyByName(technologyService);
    }

    @Bean
    public ReadAllTechnologies readAllTechnologies() {
        return new ReadAllTechnologies(technologyService);
    }

    @Bean
    public ReadTechnologyByCode readTechnologyByCode() {
        return new ReadTechnologyByCode(technologyService);
    }

    @Bean
    public UpdateTechnology updateTechnology() {
        return new UpdateTechnology(readTechnologyByCode(), existsTechnologyByName(), technologyService);
    }
}
