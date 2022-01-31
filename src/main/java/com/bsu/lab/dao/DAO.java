package com.bsu.lab.dao;

import java.util.List;

public interface DAO<Entity> {
    void create(Entity model);

    List<Entity> read();

    void update(Entity model);

    void delete(Entity model);

    void deleteAll();


}
