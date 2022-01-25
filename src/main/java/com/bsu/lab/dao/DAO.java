package com.bsu.lab.dao;

public interface DAO<Entity> {
    boolean create(Entity model);

    Entity read(int id);

    boolean update(Entity model);

    boolean delete(Entity model);

    boolean deleteAll();


}
