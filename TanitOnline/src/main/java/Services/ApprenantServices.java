package Services;

import models.Apprenant;
import util.DbConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;


public class ApprenantServices implements IServiceApprenant{
    private Connection connection;


    public ApprenantServices() {
        connection = DbConnect.getInstance().getConnection();
    }

    @Override
    public void add(Apprenant apprenant) throws SQLException {

            String sql = "INSERT INTO apprenants (Name,Email,Password,StatutNiveau,FormationEtudies,Niveau,idNiveau) VALUES (?,?,?,?,?,?,?)";
            System.out.println(sql);
            PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, apprenant.getName());
        statement.setString(2, apprenant.getEmail());
        statement.setString(3, apprenant.getPassword());
        statement.setString(4, apprenant.getStatutNiveau());
        statement.setString(5, apprenant.getFormationEtudies());
        statement.setString(6, apprenant.getNiveauName());
        statement.setString(7, "1");
            statement.executeUpdate();

        }
    @Override
    public List<Apprenant> getAll() throws SQLException {
        String sql = "select * from apprenants";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Apprenant> apprenants = new ArrayList<>();
        while (rs.next()) {
            Apprenant apprenant = new Apprenant();
            apprenant.setId(rs.getInt("id"));
            apprenant.setName(rs.getString("Name"));
            apprenant.setEmail(rs.getString("Email"));
            apprenant.setEmail(rs.getString("Password"));
            apprenant.setStatutNiveau(rs.getString("StatutNiveau"));
            apprenant.setFormationEtudies(rs.getString("FormationEtudies"));
            apprenant.setNiveauName(rs.getString("Niveau"));
            apprenant.setIdNiveau(rs.getInt("1"));
            apprenants.add(apprenant);

        }
        return apprenants;

    }




    @Override
    public void update(Apprenant apprenant) throws SQLException {
        String sql = "UPDATE apprenants SET Name = ?, Password = ?, StatutNiveau = ?, FormationEtudies = ?, Niveau = ? WHERE Email = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, apprenant.getName());
        preparedStatement.setString(2, apprenant.getPassword());
        preparedStatement.setString(3, apprenant.getStatutNiveau());
        preparedStatement.setString(4, apprenant.getFormationEtudies());
        preparedStatement.setString(5, apprenant.getNiveauName());
        preparedStatement.setString(6, apprenant.getEmail());

        preparedStatement.executeUpdate();
        System.out.println(apprenant);
    }

    @Override
    public void delete(String email) throws SQLException {
        String sql = "DELETE FROM apprenants WHERE Email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        }

    }




   /* @Override
    public Apprenant getById(int id) throws SQLException {
        String sql = "SELECT `Name`, `Email`, `Password`, `StatutNiveau`, `FormationEtudies`, `Niveau` FROM `apprenants` WHERE `id` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("Name");
            String email = resultSet.getString("Email");
            String password = resultSet.getString("Password");
            String statutNiveau = resultSet.getString("Statut");
            String formationEtudies = resultSet.getString("Formation");
            String niveau = resultSet.getString("Niveau");

            //return new Apprenant(id, name, email, password, statutNiveau, formationEtudies, niveau);
            return new Apprenant(name, email, password, statutNiveau, formationEtudies, 1);
        } else {
            // Handle the case when no matching record is found
            return null;
        }
    }*/
Apprenant apprenant;

    @Override
    public Apprenant getByEmail(String Email) throws SQLException {

        Apprenant apprenant = null;
        String sql = "SELECT * FROM apprenants WHERE Email = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, Email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String password = resultSet.getString("Password");
                    String statutNiveau = resultSet.getString("StatutNiveau");
                    String formationEtudies = resultSet.getString("FormationEtudies");
                    String niveau = resultSet.getString("Niveau");

                    // Create an Apprenant object using the retrieved values
                    apprenant = new Apprenant(name, Email, password, statutNiveau, formationEtudies, niveau,1);

                }
            }
        }

        return apprenant;


    }}

