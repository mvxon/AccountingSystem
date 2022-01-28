package com.bsu.lab.dao;

public interface DAO<Entity> {
    void create(Entity model);

    Entity read(int id);

    void update(Entity model);

    void delete(Entity model);

    void deleteAll();


}
