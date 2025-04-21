package com.portdombo.backend.infrastructure.mapper;

import com.portdombo.backend.domain.entity.Technology;
import com.portdombo.backend.infrastructure.persistence.entity.TechnologyEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TechnologyMapper extends ModelMapper {

    public List<Technology> toTechnologyList(List<TechnologyEntity> entities) {
        return entities.stream().map(this::toTechnology).toList();
    }

    public Technology toTechnology(TechnologyEntity entity) {
        return super.map(entity, Technology.class);
    }
}
