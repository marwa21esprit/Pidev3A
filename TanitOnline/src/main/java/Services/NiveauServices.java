package services;

import controllers.data;
import models.Niveau;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NiveauServices implements IServiceNiveau {
    private Connection connection;

    public NiveauServices() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void add(Niveau niveau) throws SQLException {
        String sql = "INSERT INTO niveau (Name, Prerequis, Duree, Nbformation, Certificat, Description,image) VALUES (?, ?, ?, ?, ?, ?,?)";
        System.out.println(sql);

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, niveau.getName());
            statement.setString(2, niveau.getPrerequis());
            statement.setString(3, niveau.getDuree());
            statement.setInt(4, niveau.getNbformation());
            statement.setString(5, niveau.getCertificat());
            statement.setString(6, niveau.getDescription());
            statement.setString(7, niveau.getImage());


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
    public void update(Niveau niveau, int id) throws SQLException {
        String sql = "UPDATE niveau SET Name = ?, Prerequis = ?, Duree = ?, Nbformation = ?, Certificat = ?, Description = ?, image = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, niveau.getName());
        preparedStatement.setString(2, niveau.getPrerequis());
        preparedStatement.setString(3, niveau.getDuree());
        preparedStatement.setInt(4, niveau.getNbformation());
        preparedStatement.setString(5, niveau.getCertificat());
        preparedStatement.setString(6, niveau.getDescription());
        String path = data.path1;
        if (path == null) {
            System.out.println("path is null");
            path = "C:\\Users\\Hadhemi\\IdeaProjects\\demo\\src\\main\\resources\\images"; // Replace with your default path

        } else {
            path = path.replace("\\", "\\\\");
        }
        preparedStatement.setString(7, path);

        preparedStatement.setInt(8, id);
        preparedStatement.executeUpdate();
        System.out.println(niveau);
    }



    @Override
    public void delete(int id) throws SQLException {
        // Supprimer d'abord les apprenants associés au niveau
        String deleteApprenantQuery = "DELETE FROM apprenants WHERE idNiveau = ?";
        try (PreparedStatement apprenantStatement = connection.prepareStatement(deleteApprenantQuery)) {
            apprenantStatement.setInt(1, id);
            apprenantStatement.executeUpdate();
        }

        // Ensuite, supprimer le niveau lui-même
        String deleteNiveauQuery = "DELETE FROM niveau WHERE id = ?";
        try (PreparedStatement niveauStatement = connection.prepareStatement(deleteNiveauQuery)) {
            niveauStatement.setInt(1, id);
            niveauStatement.executeUpdate();
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
            niveau.setCertificat(rs.getString("Certificat"));
            niveau.setDescription(rs.getString("Description"));
            niveau.setImage(rs.getString("image"));
            niveaus.add(niveau);
        }
        return niveaus;
    }

    @Override
    public Niveau getById(int id) throws SQLException {
        String sql = "SELECT `Name`, `Prerequis`, `Duree`, `Nbformation`,`Certificat`, `Description`, `image` FROM `niveau` WHERE `id` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("Name");
            String prerequis = resultSet.getString("Prerequis");
            String duree = resultSet.getString("Duree");
            int nbformation = Integer.parseInt(String.valueOf(resultSet.getInt("Nbformation")));
            String certificat = resultSet.getString("Certificat");
            String description = resultSet.getString("Description");
            String image = resultSet.getString("image");


            return new Niveau(id, name, prerequis, duree, nbformation, certificat, description, image);
        } else {
            // Handle the case when no matching record is found
            return null;
        }
    }
/*
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
    }*/

    }



