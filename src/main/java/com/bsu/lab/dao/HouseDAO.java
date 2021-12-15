package com.bsu.lab.dao;

import com.bsu.lab.model.Entrance;
import com.bsu.lab.model.House;
import com.bsu.lab.util.database.connection.DataBaseConnection;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HouseDAO implements DAO<House> {
    private static HouseDAO houseDAO;
    private final EntranceDAO entranceDAO = EntranceDAO.getInstance();

    private HouseDAO() {

    }

    public static HouseDAO getInstance() {
        if (houseDAO == null) {
            houseDAO = new HouseDAO();
        }
        return houseDAO;
    }

    @Override
    public boolean create(@NotNull final House house) {
        boolean result = false;
        try (PreparedStatement statement = DataBaseConnection.getConnection()
                .prepareStatement(HouseSQL.INSERT.QUERY)) {
            statement.setInt(1, house.getHouseNumber());
            statement.setInt(2, house.getEntrancesCount());

            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
            int houseId = resultSet.getInt(1);
            house.setId(houseId);
            for (Entrance entrance : house.getEntrances()) {
                entrance.setHouseId(houseId);
                entranceDAO.create(entrance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public House read(final int id) {
        final House resultHouse = new House();

        try (PreparedStatement statement = DataBaseConnection.getConnection().
                prepareStatement(HouseSQL.GET.QUERY)) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                resultHouse.setId(rs.getInt("id"));
                resultHouse.setHouseNumber(rs.getInt("houseNumber"));
                resultHouse.setEntrancesCount(rs.getInt("entrancesCount"));
            }
            resultHouse.getEntrances().addAll(entranceDAO.readAllFromHouse(resultHouse.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultHouse;
    }

    public List<House> readAllExisting() {
        final List<House> result = new ArrayList<>();
        House house;

        try (PreparedStatement statement = DataBaseConnection.getConnection().
                prepareStatement(HouseSQL.GET_ALL_EXISTING.QUERY)) {
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                house = new House();
                house.setId(rs.getInt("id"));
                house.setHouseNumber(rs.getInt("houseNumber"));
                house.setEntrancesCount(rs.getInt("entrancesCount"));
                house.getEntrances().addAll(entranceDAO.readAllFromHouse(house.getId()));
                result.add(house);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public boolean update(@NotNull final House house) {
        boolean result = false;

        try (PreparedStatement statement = DataBaseConnection.getConnection().
                prepareStatement(HouseSQL.UPDATE.QUERY)) {
            statement.setInt(1, house.getHouseNumber());
            statement.setInt(2, house.getEntrancesCount());
            statement.setInt(3, house.getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(@NotNull final House house) {
        boolean result = false;

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
        return result;
    }

    @Override
    public boolean deleteAll() {
        boolean result = false;
        try (PreparedStatement statement = DataBaseConnection.getConnection().
                prepareStatement(HouseSQL.DELETE_ALL.QUERY)) {
            result = statement.executeQuery().next();
            entranceDAO.deleteAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
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
