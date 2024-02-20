package services;

import models.Event;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventService implements IService<Event>{

    private Connection connection;

    public EventService() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void add(Event event) throws SQLException {
        String sql = "INSERT INTO `event`(`idEstab`,`nameEvent`,`dateEvent`,`nbrMax`,`description`) VALUES(?,?,?,?,?)";
        System.out.println(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, event.getIdEstab());
        preparedStatement.setString(2, event.getNameEvent());
        preparedStatement.setDate(3, event.getDateEvent());
        preparedStatement.setInt(4, event.getNbrMax());
        preparedStatement.setString(5, event.getDescription());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Event event,int id) throws SQLException {
        String sql = "update event set idEstab = ?,  nameEvent = ?, dateEvent = ?, nbrMax = ?," +
                "description = ? where idEvent = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, event.getIdEstab());
        preparedStatement.setString(2, event.getNameEvent());
        preparedStatement.setDate(3, event.getDateEvent());
        preparedStatement.setInt(4, event.getNbrMax());
        preparedStatement.setString(5, event.getDescription());
        preparedStatement.setInt(6, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from event where idEvent = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

    @Override
    public List<Event> getAll() throws SQLException {
        String sql = "select * from event";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Event> events = new ArrayList<>();
        while (rs.next()) {
            Event e = new Event();
            e.setIdEvent(rs.getInt("idEvent"));
            e.setIdEstab(rs.getInt("idEstab"));
            e.setNameEvent(rs.getString("nameEvent"));
            e.setDateEvent(rs.getDate("dateEvent"));
            e.setNbrMax(rs.getInt("nbrMax"));
            e.setDescription(rs.getString("description"));

            events.add(e);
        }
        return events;
    }

    @Override
    public Event getById(int id) throws SQLException {
        String sql = "SELECT `idEstab`, `nameEvent`, `dateEvent`, `nbrMax`, `description` " +
                "FROM `event` WHERE `idEvent` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int idEstab = resultSet.getInt("idEstab");
            String nameEvent = resultSet.getString("nameEvent");
            Date dateEvent = resultSet.getDate("dateEvent");
            int nbrMax = resultSet.getInt("nbrMax");
            String description = resultSet.getString("description");

            return new Event(id, idEstab, nameEvent, dateEvent,nbrMax,description);
        } else {
            // Handle the case when no matching record is found
            return null;
        }
    }
}
