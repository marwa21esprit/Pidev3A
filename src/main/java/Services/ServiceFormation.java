package Services;

import entities.Formation;
<<<<<<< HEAD
import entities.Tuteur;
=======
import javafx.scene.control.TextField;
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceFormation {
    private Connection cnx;

    public ServiceFormation() {
        this.cnx = MyDB.getInstance().getCnx();
        if (this.cnx == null) {
            // Handle the case where connection is not properly initialized
            throw new IllegalStateException("Database connection is not properly initialized");
        }
    }

    public void addFormation(Formation formation) throws SQLException {
        String sql = "INSERT INTO formation (id_tuteur, nom_niveau, categorie, titre, description, date_d, date_f, prix, lien) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, formation.getId_tuteur());
            preparedStatement.setString(2, formation.getNom_niveau());
            preparedStatement.setString(3, formation.getCategorie());
            preparedStatement.setString(4, formation.getTitre());
            preparedStatement.setString(5, formation.getDescription());
            preparedStatement.setDate(6, formation.getDate_d());
            preparedStatement.setDate(7, formation.getDate_f());
            preparedStatement.setFloat(8, formation.getPrix());
            preparedStatement.setString(9, formation.getLien());
            // Execution de la requete
            preparedStatement.executeUpdate();
        }
    }



<<<<<<< HEAD
    public void updateFormation(Formation formation, int id) throws SQLException {
        String sql = "UPDATE formation SET id_tuteur = ?, nom_niveau = ?, categorie = ?, titre = ?, description = ?, date_d = ?, date_f = ?, prix = ?, lien = ? WHERE id_formation = ?";
=======
    public void updateFormation(Formation formation, TextField id_formation1) throws SQLException {
        String sql = "UPDATE formation SET id_tuteur = ?, id_niveau = ?, categorie = ?, titre = ?, description = ?, date_d = ?, date_f = ?, prix = ?, lien = ? WHERE id_formation = ?";
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55

        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, formation.getId_tuteur());
            preparedStatement.setString(2, formation.getNom_niveau());
            preparedStatement.setString(3, formation.getCategorie());
            preparedStatement.setString(4, formation.getTitre());
            preparedStatement.setString(5, formation.getDescription());
            preparedStatement.setDate(6, formation.getDate_d());
            preparedStatement.setDate(7, formation.getDate_f());
            preparedStatement.setFloat(8, formation.getPrix());
            preparedStatement.setString(9, formation.getLien());
            preparedStatement.setInt(10, id);

            // Execution de la requete
            preparedStatement.executeUpdate();
        }
    }

    public void deleteFormation(int id_formation) throws SQLException {
        String sql = "DELETE FROM formation WHERE id_formation = ?";
        PreparedStatement preparedStatement = cnx.prepareStatement(sql);
        preparedStatement.setInt(1, id_formation);
        preparedStatement.executeUpdate();
    }

    public List<Formation> getAll() throws SQLException {
        String sql = "SELECT * FROM formation";
        Statement statement = cnx.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Formation> formations = new ArrayList<>();

        while (rs.next()) {
            Formation formation = new Formation();

            formation.setId_formation(rs.getInt("id_formation"));
            formation.setId_tuteur(rs.getInt("id_tuteur"));
            formation.setNom_niveau(rs.getString("nom_niveau"));
            formation.setCategorie(rs.getString("categorie"));
            formation.setTitre(rs.getString("titre"));
            formation.setDescription(rs.getString("description"));
            formation.setDate_d(rs.getDate("date_d"));
            formation.setDate_f(rs.getDate("date_f"));
            formation.setPrix(rs.getFloat("prix"));
            formation.setLien(rs.getString("lien"));

            formations.add(formation);
        }
        return formations;
    }

    public Formation getById(int id_formation) throws SQLException {
        String sql = "SELECT id_tuteur, nom_niveau, categorie, titre, description, date_d, date_f, prix, lien FROM formation WHERE id_formation = ?";
        PreparedStatement preparedStatement = cnx.prepareStatement(sql);
        preparedStatement.setInt(1, id_formation);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int id_tuteur = resultSet.getInt("id_tuteur");
            String nom_niveau = resultSet.getString("nom_niveau");
            String categorie = resultSet.getString("categorie");
            String titre = resultSet.getString("titre");
            String description = resultSet.getString("description");
            Date date_d = resultSet.getDate("date_d");
            Date date_f = resultSet.getDate("date_f");
            float prix = resultSet.getFloat("prix");
            String lien = resultSet.getString("lien");

            return new Formation(id_tuteur, nom_niveau, categorie, titre, description, date_d, date_f, prix, lien);
        } else {
            return null;
        }
    }

    public List<String> getTitres() throws SQLException {
        List<String> titrefo = new ArrayList<>();
<<<<<<< HEAD
        String sql = "SELECT titre FROM formation"; // Requête SQL pour récupérer tous les titres
=======
        String sql = "SELECT titre FROM tuteur"; // Requête SQL pour récupérer tous les titres
>>>>>>> fbee1fb1734da57bf6f5445215b18fdea9e2aefe
        try (Statement statement = cnx.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                String titre = rs.getString("titre");
                titrefo.add(titre);
            }
        }

        return titrefo;
    }

    public List<Formation> getFormationList() {
        List<Formation> formations = new ArrayList<>();
        String sql = "SELECT id_tuteur, nom_niveau, categorie, titre, description, date_d, date_f, prix, lien  FROM formation";

        try {

            try (Statement statement = cnx.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                while (rs.next()) {
                    Formation formation = new Formation();
                    formation.setId_formation(rs.getInt("id_formation"));
                    formation.setId_tuteur(rs.getInt("id_tuteur"));
                    formation.setNom_niveau(rs.getString("nom_niveau"));
                    formation.setCategorie(rs.getString("categorie"));
                    formation.setTitre(rs.getString("titre"));
                    formation.setDescription(rs.getString("description"));
                    formation.setDate_d(rs.getDate("date_d"));
                    formation.setDate_f(rs.getDate("date_f"));
                    formation.setPrix(rs.getFloat("prix"));
                    formation.setLien(rs.getString("lien"));

                    formations.add(formation);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return formations;

    }

    private List<Formation> getFormationFromResultSet(String sql) throws SQLException {
        List<Formation> formations = new ArrayList<>();
        try (Statement statement = cnx.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Formation formation = new Formation();
                formation.setId_formation(rs.getInt("id_formation"));
                formation.setId_tuteur(rs.getInt("id_tuteur"));
                formation.setNom_niveau(rs.getString("nom_niveau"));
                formation.setCategorie(rs.getString("categorie"));
                formation.setTitre(rs.getString("titre"));
                formation.setDescription(rs.getString("description"));
                formation.setDate_d(rs.getDate("date_d"));
                formation.setDate_f(rs.getDate("date_f"));
                formation.setPrix(rs.getFloat("prix"));
                formation.setLien(rs.getString("lien"));

                formations.add(formation);
            }
        }
        return formations;
    }


    public List<Formation> trierParCategorie() throws SQLException {
        String sql = "SELECT * FROM formation ORDER BY categorie ASC";
        return getFormationFromResultSet(sql);
    }

    public List<Formation> trierParNiveau() throws SQLException {
        String sql = "SELECT * FROM formation ORDER BY nom_niveau ASC";
        return getFormationFromResultSet(sql);
    }

    public List<Formation> trierParPrix() throws SQLException {
        String sql = "SELECT * FROM formation ORDER BY prix ASC";
        return getFormationFromResultSet(sql);
    }


}
