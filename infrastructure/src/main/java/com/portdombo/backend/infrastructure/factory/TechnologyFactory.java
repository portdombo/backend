package com.portdombo.backend.infrastructure.factory;

import com.portdombo.backend.adapters.usesaceImpl.CreateTechnology;
import com.portdombo.backend.adapters.usesaceImpl.ExistsTechnologyByName;
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
}
