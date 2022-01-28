package com.bsu.lab.dao;

import com.bsu.lab.model.Entrance;
import com.bsu.lab.model.House;
import com.bsu.lab.util.HibernateUtil;
import com.bsu.lab.util.database.connection.DataBaseConnection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HouseDAO implements DAO<House> {
    private static HouseDAO houseDAO;


    private HouseDAO() {

    }

    public static HouseDAO getInstance() {
        if (houseDAO == null) {
            houseDAO = new HouseDAO();
        }
        return houseDAO;
    }

    @Override
    public void create(@NotNull final House house) {
        SessionFactory factory = null;
        factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(house);
        session.getTransaction().commit();

    }

    public House read(final int id) {
        try (final Session session = HibernateUtil.getSession()) {
            return session.get(House.class, id);
        }
    }

    /*public List<House> readAllExisting() {
        try (final Session session = HibernateUtil.getSession()) {
            final List<House> result = session.createQuery("SELECT * FROM houses").list();
            return result;
        }
    }*/


    @Override
    public void update(@NotNull final House house) {
       /* boolean result = false;

        try (PreparedStatement statement = DataBaseConnection.getConnection().
                prepareStatement(HouseSQL.UPDATE.QUERY)) {
            statement.setInt(1, house.getHouseNumber());
            statement.setInt(2, house.getEntrancesCount());
            statement.setInt(3, house.getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;*/
    }

    @Override
    public void delete(@NotNull final House house) {
        /*boolean result = false;

        try (PreparedStatement statement = DataBaseConnection.getConnection().
                prepareStatement(HouseSQL.DELETE.QUERY)) {
            for (Entrance entrance : house.getEntrances()) {
                entranceDAO.delete(entrance);
            }
            statement.setInt(1, house.getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;*/
    }

    @Override
    public void deleteAll() {
        /*boolean result = false;
        try (PreparedStatement statement = DataBaseConnection.getConnection().
                prepareStatement(HouseSQL.DELETE_ALL.QUERY)) {
            result = statement.executeQuery().next();
            entranceDAO.deleteAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;*/
    }

    public static int getHousesCount() {
        int housesCount = 0;

        try (PreparedStatement statement = DataBaseConnection.getConnection().
                prepareStatement(HouseSQL.GET_COUNT.QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            housesCount = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return housesCount;
    }

    enum HouseSQL {
        GET("SELECT houses.id, houses.houseNumber, houses.entrancesCount FROM houses WHERE houses.id = (?)"),
        INSERT("INSERT INTO houses (id, houseNumber, entrancesCount) VALUES (DEFAULT, (?), (?)) RETURNING id"),
        DELETE("DELETE FROM houses WHERE id = (?)  RETURNING id"),
        UPDATE("UPDATE houses SET houseNumber = (?), entrancesCount = (?) WHERE id = (?) RETURNING id"),
        DELETE_ALL("DELETE FROM houses * RETURNING ID"),
        GET_COUNT("SELECT count(*) from houses"),
        GET_ALL_EXISTING("SELECT * FROM houses");

        String QUERY;

        HouseSQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
