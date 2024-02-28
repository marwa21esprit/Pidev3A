package Services;

import models.Apprenant;
import models.Niveau;
import util.DbConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class NiveauServices implements IServiceNiveau {
    private Connection connection;

    public NiveauServices() {
        connection = DbConnect.getInstance().getConnection();
    }

    @Override
    public void add(Niveau niveau) throws SQLException {
        String sql = "INSERT INTO niveau (Name, Prerequis, Duree, Nbformation, Suivi, Certificat, Competences) VALUES (?, ?, ?, ?, ?, ?, ?)";
        System.out.println(sql);

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, niveau.getName());
            statement.setString(2, niveau.getPrerequis());
            statement.setString(3, niveau.getDuree());
            statement.setInt(4, niveau.getNbformation());
            statement.setString(5, niveau.getSuivi());
            statement.setString(6, niveau.getCertificat());
            statement.setString(7, niveau.getCompetences());

            if (statement.executeUpdate() > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        niveau.setId(generatedKeys.getInt(1));
                    } else {
                        System.out.println("No generated keys were returned for the inserted record.");
                    }
                }
                System.out.println("Insertion Niveau successful.");
            } else {
                System.out.println("Problem Add Niveau");
            }
        }
    }


    @Override
    public void update(Niveau niveau) throws SQLException {
        String sql = "UPDATE niveau SET Prerequis = ?, Duree = ?, Nbformation = ?, Suivi = ?, Certificat = ?, Competences = ? where Name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, niveau.getPrerequis());
        preparedStatement.setString(2, niveau.getDuree());
        preparedStatement.setInt(3, niveau.getNbformation());
        preparedStatement.setString(4, niveau.getSuivi());
        preparedStatement.setString(5, niveau.getCertificat());
        preparedStatement.setString(6, niveau.getCompetences());
        preparedStatement.setString(7, niveau.getName());
        preparedStatement.executeUpdate();
        System.out.println(niveau);
    }



    @Override
    public void delete(String name) throws SQLException {
        String sql = "DELETE FROM niveau WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Niveau> getAll() throws SQLException {
        String sql = "select * from niveau";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Niveau> niveaus = new ArrayList<>();
        while (rs.next()) {
            Niveau niveau = new Niveau();
            niveau.setId(rs.getInt("id"));
            niveau.setName(rs.getString("Name"));
            niveau.setPrerequis(rs.getString("Prerequis"));
            niveau.setDuree(rs.getString("Duree"));
            niveau.setNbformation(rs.getInt("Nbformation"));
            niveau.setSuivi(rs.getString("Suivi"));
            niveau.setCertificat(rs.getString("Certificat"));
            niveau.setCompetences(rs.getString("Competences"));


        }
        return niveaus;
    }

    @Override
    public Niveau getById(int id) throws SQLException {
        String sql = "SELECT `Name`, `Prerequis`, `Duree`, `Nbformation`, `Suivi`,`Certificat`, `Competences` FROM `niveau` WHERE `id` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("Name");
            String prerequis = resultSet.getString("Prerequis");
            String duree = resultSet.getString("Duree");
            int nbformation = Integer.parseInt(String.valueOf(resultSet.getInt("Nbformation")));
            String suivi  = resultSet.getString("Suivi");
            String certificat = resultSet.getString("Certificat");
            String competences = resultSet.getString("Competences");


            return new Niveau(id, name, prerequis, duree, nbformation, suivi, certificat, competences);
        } else {
            // Handle the case when no matching record is found
            return null;
        }
    }

    @Override
    public Niveau getByName(String name) throws SQLException {
        Niveau niveau = null;
        String sql = "SELECT * FROM niveau WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String prerequis = resultSet.getString("Prerequis");
                    String duree = resultSet.getString("Duree");
                    int nbformation = resultSet.getInt("Nbformation");
                    String suivi = resultSet.getString("Suivi");
                    String certificat = resultSet.getString("Certificat");
                    String competences = resultSet.getString("Competences");
                    // Create an Apprenant object using the retrieved values
                    niveau = new Niveau(name, prerequis, duree, nbformation, suivi, certificat, competences);
                }
            }
        }

        return niveau;
    }

    @Override
    public List<String> getName() throws SQLException {
        List<String> nameNiveaux = new ArrayList<>();
        String sql = "SELECT Name FROM niveau"; // Requête SQL pour récupérer tous les noms d'établissements

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                String name = rs.getString("Name");
                nameNiveaux.add(name);
            }
        }

        return nameNiveaux;
    }

    }



