/*
package com.bsu.lab.dao;

import com.bsu.lab.model.Entrance;
import com.bsu.lab.model.Floor;
import com.bsu.lab.util.database.connection.DataBaseConnection;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EntranceDAO implements DAO<Entrance> {

    private static EntranceDAO entranceDAO;
    private final FloorDAO floorDAO = FloorDAO.getInstance();

    private EntranceDAO() {

    }

    public static EntranceDAO getInstance() {
        if (entranceDAO == null) {
            entranceDAO = new EntranceDAO();
        }
        return entranceDAO;
    }


    @Override
    public boolean create(@NotNull final Entrance entrance) {
        boolean result = false;
        try (PreparedStatement statement = DataBaseConnection.getConnection().
                prepareStatement(EntranceSQL.INSERT.QUERY)) {
            statement.setInt(1, entrance.getHouseId());
            statement.setInt(2, entrance.getEntranceNumber());
            statement.setInt(3, entrance.getFloorsCount());
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
            int entranceId = resultSet.getInt(1);
            entrance.setId(entranceId);
            for (Floor floor : entrance.getFloors()) {
                floor.setEntranceId(entranceId);
                floorDAO.create(floor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public @NotNull Entrance read(int id) {
        Entrance entrance = new Entrance();
        try (PreparedStatement statement = DataBaseConnection.getConnection()
                .prepareStatement(EntranceSQL.GET.QUERY)) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                entrance.setId(rs.getInt("id"));
                entrance.setHouseId(rs.getInt("houseId"));
                entrance.setEntranceNumber(rs.getInt("entranceNumber"));
                entrance.setFloorsCount(rs.getInt("floorsCount"));
                entrance.getFloors().addAll(floorDAO.readAllFromEntrance(entrance.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrance;
    }

    public @NotNull List<Entrance> readAllFromHouse(@NotNull final Integer houseId) {
        final List<Entrance> result = new ArrayList<>();
        Entrance entrance;
        try (PreparedStatement statement = DataBaseConnection.getConnection()
                .prepareStatement(EntranceSQL.GET_ALL_FROM_HOUSE.QUERY)) {
            statement.setInt(1, houseId);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                entrance = new Entrance();
                entrance.setId(rs.getInt("id"));
                entrance.setHouseId(rs.getInt("houseId"));
                entrance.setEntranceNumber(rs.getInt("entranceNumber"));
                entrance.setFloorsCount(rs.getInt("floorsCount"));
                entrance.getFloors().addAll(floorDAO.readAllFromEntrance(entrance.getId()));
                result.add(entrance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(@NotNull final Entrance entrance) {
        boolean result = false;

        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(EntranceSQL.
                UPDATE.QUERY)) {
            statement.setInt(1, entrance.getEntranceNumber());
            statement.setInt(2, entrance.getFloorsCount());
            statement.setInt(3, entrance.getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(@NotNull final Entrance entrance) {
        boolean result = false;

        try (PreparedStatement statement = DataBaseConnection.getConnection().
                prepareStatement(EntranceSQL.DELETE.QUERY)) {
            for (Floor floor : entrance.getFloors()) {
                floorDAO.delete(floor);
            }
            statement.setInt(1, entrance.getId());
            result = statement.executeQuery().next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteAll() {
        boolean result = false;
        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(EntranceSQL.
                DELETE_ALL.QUERY)) {
            result = statement.executeQuery().next();
            floorDAO.deleteAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    enum EntranceSQL {
        GET_ALL_FROM_HOUSE("SELECT * FROM entrances WHERE entrances.houseId = (?)"),
        GET("SELECT * FROM entrances WHERE entrances.id = (?)"),
        INSERT("INSERT INTO entrances (id, houseId, entranceNumber, floorsCount) VALUES" +
                " (DEFAULT, (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM entrances WHERE id = (?)  RETURNING id"),
        UPDATE("UPDATE entrances SET entranceNumber = (?), floorsCount = (?) WHERE id = (?) RETURNING id"),
        DELETE_ALL("DELETE FROM entrances * RETURNING ID");
        String QUERY;

        EntranceSQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}

*/
