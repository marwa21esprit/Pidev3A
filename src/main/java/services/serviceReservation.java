package services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Event;
import models.Reservation;
import utils.myBD;
public class serviceReservation {

    private Connection cnx;

    public serviceReservation() {
        cnx = myBD.getInstance().getConnection();
    }


    public Reservation add(Reservation r ,int eventId,String eventName , String image) {
        String sql = "INSERT INTO `reservation` (`name`, `email`, `nb_places`, `nameE` , `id_event`, `imageSrc`) VALUES (?, ?, ?, ?,?,?)";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, r.getName());
            preparedStatement.setString(2, r.getEmail());
            preparedStatement.setInt(3, r.getNb_places());
            preparedStatement.setString(4, eventName);
            preparedStatement.setInt(5, eventId);
            preparedStatement.setString(6, image);

            if (preparedStatement.executeUpdate() > 0) {
                // Retrieve the auto-generated ID of the newly added reservation
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    r.setId(generatedKeys.getInt(1));
                }
                System.out.println("Insertion Reservation successful.");
            } else {
                System.out.println("Problem Add Reservation");
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while executing the SQL query: " + e.getMessage());
        }
        return r;
    }



    public void update(Reservation r) {
        String sql = "update reservation set nb_places= ? where id = ?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);

            preparedStatement.setInt(1, r.getNb_places());
            preparedStatement.setInt(2, r.getId());
            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("Update Reservation successful.");

            } else {
                System.out.println("Problem Update Reservation");
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while executing the SQL query: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "delete from reservation where id = ?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("Delete Reservation successful.");

            } else {
                System.out.println("Problem Delete Reservation");
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while executing the SQL query: " + e.getMessage());
        }
    }


    public Reservation getReservationById(int id) {
        Reservation reservation = null;
        String sql = "SELECT * FROM reservation WHERE id = ?";
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int nb_places = resultSet.getInt("nb_places");
                String nameE = resultSet.getString("nameE");
                reservation = new Reservation(name, email, nameE, nb_places);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error occurred while retrieving reservation: " + e.getMessage());
        }
        return reservation;
    }


    public List<Reservation> getCardlist() {
        String sql = "SELECT id, nameE , nb_places , imageSrc FROM reservation ";
        List<Reservation> list = new ArrayList<>();
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Reservation res = new Reservation();
                res.setId(rs.getInt("id"));
                res.setNameE(rs.getString("nameE"));
                res.setNb_places(rs.getInt("nb_places"));
                res.setImageSrc(rs.getString("imageSrc"));
                list.add(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list);

        return list;
    }


    public List<Reservation> getlistBack() {
        String sql = "SELECT id, nameE , nb_places , name, email FROM reservation ";
        List<Reservation> list = new ArrayList<>();
        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Reservation res = new Reservation();
                res.setId(rs.getInt("id"));
                res.setNameE(rs.getString("nameE"));
                res.setNb_places(rs.getInt("nb_places"));
                res.setName(rs.getString("name"));
                res.setEmail(rs.getString("email"));
                list.add(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list);

        return list;
    }

    public int getTotalReservedPlacesForEvent(int eventId) {
        String query = "SELECT SUM(nb_places) FROM reservation WHERE id_event = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(query)) {
            preparedStatement.setInt(1, eventId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    return 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}






