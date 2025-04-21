package com.portdombo.backend.infrastructure.service;

import com.portdombo.backend.adapters.gateway.*;
import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.infrastructure.mapper.TechnologyMapper;
import com.portdombo.backend.infrastructure.persistence.entity.TechnologyEntity;
import com.portdombo.backend.infrastructure.persistence.repository.TechnologyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TechnologyService implements
        ICreateTechnologyGateway,
        IExistsTechnologyByNameGateway,
        IReadAllTechnologiesGateway,
        IReadTechnologyByCodeGateway,
        IUpdateTechnologyGateway,
        IDeleteTechnologyGateway{

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

    @Override
    public Optional<Technology> read(Long code) {
        return repository.findByCode(code)
                .map(entity -> mapper.map(entity, Technology.class));
    }

    @Override
    public void update(Technology technology) {
        TechnologyEntity technologyEntity = mapper.map(technology, TechnologyEntity.class);
        UUID id = repository.findByCode(technology.getCode()).get().getId();
        technologyEntity.setId(id);
        repository.save(technologyEntity);
    }

    @Override
    public void delete(Long code) {
        TechnologyEntity entity =  repository.findByCode(code).get();
        repository.delete(entity);
    }
}
