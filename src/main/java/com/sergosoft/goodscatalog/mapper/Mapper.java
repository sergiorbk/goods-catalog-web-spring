package com.sergosoft.goodscatalog.mapper;

public interface Mapper<Entity, DTO> {

    Entity toEntity(DTO dto);
    DTO toDto(Entity entity);
}
