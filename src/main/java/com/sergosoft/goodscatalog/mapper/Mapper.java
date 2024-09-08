package com.sergosoft.goodscatalog.mapper;

public interface Mapper <Entity, DTO> {

    DTO toDto(Entity entity);
    Entity toEntity(DTO dto);
}
