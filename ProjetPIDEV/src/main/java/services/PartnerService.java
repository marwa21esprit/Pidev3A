package services;

import models.Event;
import models.Partner;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartnerService implements IService<Partner>{

    private Connection connection;

    public PartnerService() {
        connection = MyDatabase.getInstance().getConnection();
    }
    @Override
    public void add(Partner partner) throws SQLException {
        String sql = "INSERT INTO `partner`(`namePartner`,`typePartner`,`description`,`email`,`tel`) VALUES(?,?,?,?,?)";
        System.out.println(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, partner.getNamePartner());
        preparedStatement.setString(2, partner.getTypePartner());
        preparedStatement.setString(3, partner.getDescription());
        preparedStatement.setString(4, partner.getEmail());
        preparedStatement.setInt(5, partner.getTel());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Partner partner,int id) throws SQLException {
        String sql = "update partner set namePartner = ?,  typePartner = ?, description = ?, email = ?," +
                "tel = ? where idPartner = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, partner.getNamePartner());
        preparedStatement.setString(2, partner.getTypePartner());
        preparedStatement.setString(3, partner.getDescription());
        preparedStatement.setString(4, partner.getEmail());
        preparedStatement.setInt(5, partner.getTel());
        preparedStatement.setInt(6, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from partner where idPartner = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

    @Override
    public List<Partner> getAll() throws SQLException {
        String sql = "select * from partner";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Partner> partners = new ArrayList<>();
        while (rs.next()) {
            Partner p = new Partner();
            p.setIdPartner(rs.getInt("idPartner"));
            p.setNamePartner(rs.getString("namePartner"));
            p.setTypePartner(rs.getString("typePartner"));
            p.setDescription(rs.getString("description"));
            p.setEmail(rs.getString("email"));
            p.setTel(rs.getInt("tel"));


            partners.add(p);
        }
        return partners;
    }

    @Override
    public Partner getById(int id) throws SQLException {
        String sql = "SELECT  `namePartner`, `typePartner`, `description`,`email`,`tel` " +
                "FROM `event` WHERE `idPartner` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String namePartner = resultSet.getString("namePartner");
            String typePartner = resultSet.getString("typePartner");
            String email = resultSet.getString("email");
            int tel = resultSet.getInt("tel");
            String description = resultSet.getString("description");

            return new Partner(id, namePartner, typePartner, description,email,tel);
        } else {
            // Handle the case when no matching record is found
            return null;
        }
    }
}
