package services;

import controllers.data;
import models.Apprenant;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ApprenantServices implements IServiceApprenant {
    private Connection connection;


    public ApprenantServices() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void add(Apprenant apprenant) throws SQLException {

        String sql = "INSERT INTO apprenants (Name,Email,FormationEtudies,Niveau,image,idNiveau,StatutNiveau) VALUES (?,?,?,?,?,?,?)";
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        System.out.println(apprenant.toString());
        statement.setString(1, apprenant.getName());
        statement.setString(2, apprenant.getEmail());
        statement.setString(3, apprenant.getStatutNiveau());
        statement.setString(4, apprenant.getNiveauName());
        String path = data.path;
        path = path.replace("\\", "");
        statement.setString(5, path); // Chemin de l'image
        statement.setInt(6, apprenant.getIdNiveau()); // Utilisez la méthode getIntNiveau() si c'est un entier
        statement.setString(7, apprenant.getFormationEtudies());
        statement.executeUpdate();

    }

    @Override
    public List<Apprenant> getAll() throws SQLException {
        String sql = "select * from apprenants";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Apprenant> apprenants = new ArrayList<>();
        while (rs.next()) {
            System.out.println(rs.getString(1));
            Apprenant apprenant = new Apprenant();
            apprenant.setId(rs.getInt("id"));
            apprenant.setName(rs.getString("Name"));
            apprenant.setEmail(rs.getString("Email"));
            apprenant.setStatutNiveau(rs.getString("StatutNiveau"));
            apprenant.setFormationEtudies(rs.getString("FormationEtudies"));
            apprenant.setNiveauName(rs.getString("Niveau"));
            apprenant.setIdNiveau(rs.getInt("idNiveau"));
            apprenant.setImage(rs.getString("Image"));
            apprenants.add(apprenant);

        }
        return apprenants;

    }


    @Override
    public void update(Apprenant apprenant, int id) throws SQLException {
        String sql = "update apprenants set idNiveau = ?,  name = ?, email = ?, statutNiveau = ?," +
                "formationEtudies = ?,niveau = ?,image = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, apprenant.getIdNiveau());
        preparedStatement.setString(2, apprenant.getName());
        preparedStatement.setString(3, apprenant.getEmail());
        preparedStatement.setString(4, apprenant.getStatutNiveau());
        preparedStatement.setString(5, apprenant.getFormationEtudies());
        preparedStatement.setString(6, apprenant.getNiveauName());
        String path = data.path;
        if (path == null) {
            System.out.println("path is null");
            path = "C:\\Users\\Hadhemi\\IdeaProjects\\demo\\src\\main\\resources\\images"; // Replace with your default path

        } else {
            path = path.replace("\\", "\\\\");
        }
        preparedStatement.setString(7, path);
        preparedStatement.setInt(8, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from apprenants where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }


    @Override
    public Apprenant getById(int id) throws SQLException {
        String sql = "SELECT `idNiveau`, `Name`, `Email`, `StatutNiveau`, `FormationEtudies`, `Niveau` , `image` FROM `apprenants` WHERE `id` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {

            String name = resultSet.getString("Name");
            String email = resultSet.getString("Email");
            String statutNiveau = resultSet.getString("Statut");
            String formationEtudies = resultSet.getString("Formation");
            String niveau = resultSet.getString("Niveau");
            String image = resultSet.getString("Image");
            int idNiveau = resultSet.getInt("idNiveau");
            //return new Apprenant(id, name, email, password, statutNiveau, formationEtudies, niveau);
            return new Apprenant(id, name, email, statutNiveau, formationEtudies, niveau, image,idNiveau);
        } else {
            // Handle the case when no matching record is found
            return null;
        }
    }

    Apprenant apprenant;

    /*@Override
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
     */
}

