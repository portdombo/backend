package com.portdombo.backend.infrastructure.service;

import com.portdombo.backend.adapters.gateway.ICreateTechnologyGateway;
import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.infrastructure.persistence.entity.TechnologyEntity;
import com.portdombo.backend.infrastructure.persistence.repository.TechnologyRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TechnologyService implements ICreateTechnologyGateway {
    private final TechnologyRepository repository;
    private final ModelMapper mapper;

    @Override
    public void create(Technology entity) {
        TechnologyEntity technologyEntity = mapper.map(entity, TechnologyEntity.class);
        repository.save(technologyEntity);
    }
}
