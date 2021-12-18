package com.kamenev.mapper;

public interface Mapper<Entity, Dto> {

    Dto entityToDto(Entity entity);
}
