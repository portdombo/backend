package com.portdombo.backend.infrastructure.service;

import com.portdombo.backend.adapters.gateway.ICreateTechnologyGateway;
import com.portdombo.backend.adapters.gateway.IExistsTechnologyByNameGateway;
import com.portdombo.backend.adapters.gateway.IReadAllTechnologiesGateway;
import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.infrastructure.mapper.TechnologyMapper;
import com.portdombo.backend.infrastructure.persistence.entity.TechnologyEntity;
import com.portdombo.backend.infrastructure.persistence.repository.TechnologyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TechnologyService implements
        ICreateTechnologyGateway,
        IExistsTechnologyByNameGateway,
        IReadAllTechnologiesGateway {

    private final TechnologyRepository repository;
    private final TechnologyMapper mapper;

    @Override
    public void create(Technology entity) {
        TechnologyEntity technologyEntity = mapper.map(entity, TechnologyEntity.class);
        repository.save(technologyEntity);
    }

    @Override
    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }

    @Override
    public List<Technology> readAll() {
        List<TechnologyEntity> entities = repository.findAll();
        return mapper.toTechnologyList(entities);
    }
}
