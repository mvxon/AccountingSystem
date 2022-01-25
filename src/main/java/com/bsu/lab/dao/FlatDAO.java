package com.bsu.lab.dao;


import com.bsu.lab.model.Flat;
import com.bsu.lab.model.Room;
import com.bsu.lab.util.database.connection.DataBaseConnection;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlatDAO implements DAO<Flat> {
    private static FlatDAO flatDAO;
    private final RoomDAO roomDAO = RoomDAO.getInstance();

    private FlatDAO() {

    }

    public static FlatDAO getInstance() {
        if (flatDAO == null) {
            flatDAO = new FlatDAO();
        }
        return flatDAO;
    }

    @Override
    public boolean create(@NotNull final Flat flat) {
        boolean result = false;
        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(FlatSQL.INSERT.QUERY)) {
            statement.setInt(1, flat.getFloorId());
            statement.setInt(2, flat.getFlatNumber());
            statement.setInt(3, flat.getRoomsCount());
            statement.setInt(4, flat.getResidentsCount());
            ResultSet resultSet = statement.executeQuery();
            result = resultSet.next();
            int flatId = resultSet.getInt(1);
            flat.setId(flatId);
            for (Room room : flat.getRooms()) {
                room.setFlatId(flatId);
                roomDAO.create(room);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Flat read(int id) {
        Flat flat = new Flat();
        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(FlatSQL.GET.QUERY)) {
            statement.setInt(1, id);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                flat.setId(rs.getInt("id"));
                flat.setFloorId(rs.getInt("floorId"));
                flat.setFlatNumber(rs.getInt("flatNumber"));
                flat.setRoomsCount(rs.getInt("roomsCount"));
                flat.setResidentsCount(rs.getInt("residentsCount"));
                flat.getRooms().addAll(roomDAO.readAllFromFlat(flat.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flat;
    }

    public @NotNull List<Flat> readAllFromFloor(@NotNull final Integer floorId) {
        final List<Flat> result = new ArrayList<>();
        Flat flat;
        try (PreparedStatement statement = DataBaseConnection.getConnection().
                prepareStatement(FlatSQL.GET_ALL_FROM_FLOOR.QUERY)) {
            statement.setInt(1, floorId);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                flat = new Flat();
                flat.setId(rs.getInt("id"));
                flat.setFloorId(rs.getInt("floorId"));
                flat.setFlatNumber(rs.getInt("flatNumber"));
                flat.setRoomsCount(rs.getInt("roomsCount"));
                flat.setResidentsCount(rs.getInt("residentsCount"));
                flat.getRooms().addAll(roomDAO.readAllFromFlat(flat.getId()));
                result.add(flat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(@NotNull final Flat flat) {
        boolean result = false;

        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(FlatSQL.UPDATE.QUERY)) {
            statement.setInt(1, flat.getFlatNumber());
            statement.setInt(2, flat.getRoomsCount());
            statement.setInt(3, flat.getResidentsCount());
            statement.setInt(4, flat.getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(@NotNull final Flat flat) {
        boolean result = false;

        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(FlatSQL.DELETE.QUERY)) {
            for (Room room : flat.getRooms()) {
                roomDAO.delete(room);
            }
            statement.setInt(1, flat.getId());
            result = statement.executeQuery().next();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteAll() {
        boolean result = false;
        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(FlatSQL.DELETE_ALL.QUERY)) {
            result = statement.executeQuery().next();
            roomDAO.deleteAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    enum FlatSQL {
        GET_ALL_FROM_FLOOR("SELECT * FROM flats WHERE flats.floorId = (?)"),
        GET("SELECT * FROM flats WHERE flats.id = (?)"),
        INSERT("INSERT INTO flats (id, floorId, flatNumber, roomsCount, residentsCount) VALUES" +
                " (DEFAULT, (?), (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM flats WHERE id = (?)  RETURNING id"),
        UPDATE("UPDATE flats SET flatNumber = (?), roomsCount = (?), residentsCount = (?)" +
                "WHERE id = (?) RETURNING id"),
        DELETE_ALL("DELETE FROM flats * RETURNING ID");

        String QUERY;

        FlatSQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
