package com.bsu.lab.dao;


import com.bsu.lab.model.Flat;
import com.bsu.lab.model.Floor;
import com.bsu.lab.util.database.dbconnection.DataBaseConnection;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FloorDAO implements DAO<Floor> {
    private static FloorDAO floorDAO;
    private final FlatDAO flatDAO = FlatDAO.getInstance();

    private FloorDAO() {

    }

    public static FloorDAO getInstance() {
        if (floorDAO == null) {
            floorDAO = new FloorDAO();
        }
        return floorDAO;
    }

    @Override
    public void create(@NotNull final Floor floor) {
        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(FloorSQL.INSERT.QUERY)) {
            statement.setInt(1, floor.getEntranceId());
            statement.setInt(2, floor.getFloorNumber());
            statement.setInt(3, floor.getFlatsCount());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int floorId = resultSet.getInt(1);
            floor.setId(floorId);
            for (Flat flat : floor.getFlats()) {
                flat.setFloorId(floorId);
                flatDAO.create(flat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public @NotNull List<Floor> read(@NotNull final Integer entranceId) {
        final List<Floor> result = new ArrayList<>();
        Floor floor;
        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(FloorSQL.GET.QUERY)) {
            statement.setInt(1, entranceId);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                floor = new Floor();
                floor.setId(rs.getInt("id"));
                floor.setEntranceId(rs.getInt("entranceId"));
                floor.setFloorNumber(rs.getInt("floorNumber"));
                floor.setFlatsCount(rs.getInt("flatsCount"));
                floor.getFlats().addAll(flatDAO.read(floor.getId()));
                result.add(floor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(@NotNull final Floor floor) {
        boolean result = false;

        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(FloorSQL.UPDATE.QUERY)) {
            statement.setInt(1, floor.getFloorNumber());
            statement.setInt(2, floor.getFlatsCount());
            statement.setInt(3, floor.getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(@NotNull final Floor floor) {
        boolean result = false;

        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(FloorSQL.DELETE.QUERY)) {
            for (Flat flat : floor.getFlats()) {
                flatDAO.delete(flat);
            }
            statement.setInt(1, floor.getId());
            result = statement.executeQuery().next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteAll() {
        boolean result = false;
        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(FloorSQL.DELETE_ALL.QUERY)) {
            result = statement.executeQuery().next();
            flatDAO.deleteAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    enum FloorSQL {
        GET("SELECT * FROM floors WHERE floors.entranceId = (?)"),
        INSERT("INSERT INTO floors (id, entranceId, floorNumber, flatsCount) VALUES (DEFAULT, (?), (?), (?)) " +
                "RETURNING id"),
        DELETE("DELETE FROM floors WHERE id = (?)  RETURNING id"),
        UPDATE("UPDATE floors SET floorNumber = (?), flatsCount = (?),  WHERE id = (?) RETURNING id"),
        DELETE_ALL("DELETE FROM floors * RETURNING ID");

        String QUERY;

        FloorSQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
