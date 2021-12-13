package com.bsu.lab.dao;



import com.bsu.lab.model.Room;
import com.bsu.lab.util.database.dbconnection.DataBaseConnection;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO implements DAO<Room> {
    private static RoomDAO roomDAO;

    private RoomDAO() {

    }

    public static RoomDAO getInstance() {
        if (roomDAO == null) {
            roomDAO = new RoomDAO();
        }
        return roomDAO;
    }

    @Override
    public void create(@NotNull final Room room) {
        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(RoomSQL.INSERT.QUERY)) {
            statement.setInt(1, room.getFlatId());
            statement.setInt(2, room.getRoomNumber());
            statement.setDouble(3, room.getRoomSquare());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int roomId = resultSet.getInt(1);
            room.setId(roomId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public @NotNull List<Room> read(@NotNull final Integer flatId) {
        final List <Room> result = new ArrayList<>();
        Room room;
        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(RoomSQL.GET.QUERY)) {
            statement.setInt(1, flatId);
            final ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               room = new Room();
               room.setId(rs.getInt("id"));
               room.setRoomNumber(rs.getInt("roomNumber"));
               room.setRoomSquare(rs.getDouble("roomSquare"));
               result.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(@NotNull final Room room) {
        boolean result = false;

        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(RoomSQL.UPDATE.QUERY)) {
            statement.setInt(1, room.getRoomNumber());
            statement.setDouble(2, room.getRoomSquare());
            statement.setInt(3, room.getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(@NotNull final Room room) {
        boolean result = false;

        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(RoomSQL.DELETE.QUERY)) {
            statement.setInt(1, room.getId());
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteAll() {
        boolean result = false;
        try (PreparedStatement statement = DataBaseConnection.getConnection().prepareStatement(RoomSQL.DELETE_ALL.QUERY)) {
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    enum RoomSQL {
        GET("SELECT * FROM rooms WHERE rooms.flatId = (?)"),
        INSERT("INSERT INTO rooms (id, flatId, roomNumber, roomSquare) VALUES" +
                " (DEFAULT, (?), (?), (?)) RETURNING id"),
        DELETE("DELETE FROM rooms WHERE id = (?)  RETURNING id"),
        UPDATE("UPDATE rooms SET roomNumber = (?), roomsSquare = (?)  WHERE id = (?) RETURNING id"),
        DELETE_ALL("DELETE FROM rooms * RETURNING ID");

        String QUERY;

        RoomSQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
