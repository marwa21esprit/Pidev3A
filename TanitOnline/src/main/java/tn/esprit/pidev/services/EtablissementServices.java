package tn.esprit.pidev.services;
import tn.esprit.pidev.Utils.MyDB;
import  tn.esprit.pidev.entities.Etablissement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import  java.util.ArrayList;
import java.sql.*;
import java.util.List;


public class EtablissementServices implements IEtabServices<Etablissement> {

    private Connection cnx ;
    public  EtablissementServices(){ cnx = MyDB.getInstance().getCnx();}

    @Override
    public void addSchool(Etablissement etablissement) throws SQLException {
        String sql = "INSERT INTO etablissement (Nom_Etablissement, Adresse_Etablissement, Type_Etablissement, Tel_Etablissement, Directeur_Etablissement, Date_Fondation, ID_Certificat) VALUES ( ?, ?, ?, ?, ?, ?, ?)";

// Création d'une PreparedStatement
        PreparedStatement preparedStatement = cnx.prepareStatement(sql);

// Attribution des valeurs aux paramètres de la requête
        preparedStatement.setString(1, etablissement.getNom_Etablissement()); // Suppose que Nom_Etablissement est de type String
        preparedStatement.setString(2, etablissement.getAdresse_Etablissement()); // Suppose que Adresse_Etablissement est de type String
        preparedStatement.setString(3, etablissement.getType_Etablissement()); // Suppose que Type_Etablissement est de type String
        preparedStatement.setInt(4, etablissement.getTel_Etablissement()); // Suppose que Tel_Etablissement est de type int
        preparedStatement.setString(5, etablissement.getDirecteur_Etablissement()); // Suppose que Directeur_Etablissement est de type String
        preparedStatement.setDate(6, etablissement.getDate_Fondation()); // Suppose que Date_Fondation est de type java.sql.Date
        preparedStatement.setInt(7, etablissement.getID_Certificat()); // Suppose que ID_Certificat





// Exécution de la requête
        preparedStatement.executeUpdate();

    }

    @Override
    public void updateSchool(Etablissement etablissement) throws SQLException {
            String sql = "UPDATE etablissement SET Nom_Etablissement = ?, Adresse_Etablissement = ?, Type_Etablissement = ?, Tel_Etablissement = ?, Directeur_Etablissement = ?, Date_Fondation = ?, ID_Certificat = ? WHERE ID_Etablissement = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setString(1, etablissement.getNom_Etablissement());
            preparedStatement.setString(2, etablissement.getAdresse_Etablissement());
            preparedStatement.setString(3, etablissement.getType_Etablissement());
            preparedStatement.setInt(4, etablissement.getTel_Etablissement());
            preparedStatement.setString(5, etablissement.getDirecteur_Etablissement());
            preparedStatement.setDate(6, etablissement.getDate_Fondation());
            preparedStatement.setInt(7, etablissement.getID_Certificat());
            preparedStatement.setInt(8, etablissement.getID_Etablissement());

            preparedStatement.executeUpdate();

    }

    @Override
    public void deleteSchool(int id) throws SQLException {
            String sql = "DELETE FROM etablissement WHERE ID_Etablissement = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();



    }

    @Override
    public List<Etablissement> getAll() throws SQLException {
            String sql = "SELECT * FROM etablissement"; // Utilisation de la table etabliss
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            List<Etablissement> etablissements = new ArrayList<>();
            while (rs.next()) {
                Etablissement etablissement = new Etablissement();
                // Assurez-vous d'utiliser les bons noms de colonnes et de récupérer les données correctement
                etablissement.setID_Etablissement(rs.getInt("ID_Etablissement"));
                etablissement.setNom_Etablissement(rs.getString("Nom_Etablissement"));
                etablissement.setAdresse_Etablissement(rs.getString("Adresse_Etablissement"));
                etablissement.setType_Etablissement(rs.getString("Type_Etablissement"));
                etablissement.setTel_Etablissement(rs.getInt("Tel_Etablissement"));
                etablissement.setDirecteur_Etablissement(rs.getString("Directeur_Etablissement"));
                etablissement.setDate_Fondation(rs.getDate("Date_Fondation"));
                etablissement.setID_Certificat(rs.getInt("ID_Certificat "));

                etablissements.add(etablissement);
            }
            return etablissements;


    }

    @Override
    public Etablissement getById(int idEtablissement) throws SQLException {
            String sql = "SELECT `Nom_Etablissement`, `Adresse_Etablissement`, `Type_Etablissement`, `Tel_Etablissement`, `Directeur_Etablissement`, `Date_Fondation`, `ID_Certificat` FROM `etablissement` WHERE `ID_Etablissement` = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, idEtablissement);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nomEtablissement = resultSet.getString("Nom_Etablissement");
                String adresseEtablissement = resultSet.getString("Adresse_Etablissement");
                String typeEtablissement = resultSet.getString("Type_Etablissement");
                int telEtablissement = resultSet.getInt("Tel_Etablissement");
                String directeurEtablissement = resultSet.getString("Directeur_Etablissement");
                Date dateFondation = resultSet.getDate("Date_Fondation");
                int ID_Certificat = resultSet.getInt("ID_Certificat");

                return new Etablissement(idEtablissement, nomEtablissement, adresseEtablissement, typeEtablissement, telEtablissement, directeurEtablissement, dateFondation, ID_Certificat);
            } else {
                // Gérer le cas où aucun enregistrement correspondant n'est trouvé
                return null;
            }
        }




}
