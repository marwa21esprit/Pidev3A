package services;

import controllers.data;
import utils.MyDB;
import models.Etablissement;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EtablissementServices implements IEtabServices<Etablissement> {

    private Connection cnx ;
    public  EtablissementServices(){ cnx = MyDB.getInstance().getCnx();}

    @Override
    public void addSchool(Etablissement etablissement) throws SQLException {
        String sql = "INSERT INTO etablissement (img_Etablissement ,Nom_Etablissement, Adresse_Etablissement, Type_Etablissement, Tel_Etablissement, Directeur_Etablissement, Date_Fondation) VALUES ( ? , ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = cnx.prepareStatement(sql);

        String path = data.path;
        path = path.replace("\\","\\\\");
        preparedStatement.setString(1, path);
        preparedStatement.setString(2, etablissement.getNom_Etablissement());
        preparedStatement.setString(3, etablissement.getAdresse_Etablissement());
        preparedStatement.setString(4, etablissement.getType_Etablissement());
        preparedStatement.setInt(5, etablissement.getTel_Etablissement());
        preparedStatement.setString(6, etablissement.getDirecteur_Etablissement());
        preparedStatement.setDate(7, etablissement.getDate_Fondation());

        preparedStatement.executeUpdate();

    }

    @Override
    public void updateSchool(Etablissement etablissement,int id) throws SQLException {
        String sql = "UPDATE etablissement SET img_Etablissement= ? , Nom_Etablissement = ?, Adresse_Etablissement = ?, Type_Etablissement = ?, Tel_Etablissement = ?, Directeur_Etablissement = ?, Date_Fondation = ? WHERE ID_Etablissement = ?";
        PreparedStatement preparedStatement = cnx.prepareStatement(sql);
        preparedStatement.setString(1, etablissement.getImg_Etablissement());
        preparedStatement.setString(2, etablissement.getNom_Etablissement());
        preparedStatement.setString(3, etablissement.getAdresse_Etablissement());
        preparedStatement.setString(4, etablissement.getType_Etablissement());
        preparedStatement.setInt(5, etablissement.getTel_Etablissement());
        preparedStatement.setString(6, etablissement.getDirecteur_Etablissement());
        preparedStatement.setDate(7, etablissement.getDate_Fondation());
        preparedStatement.setInt(8, id);

        preparedStatement.executeUpdate();
    }


    @Override
    public void deleteSchool(int id) throws SQLException {
        // Supprimer d'abord les certificats associés à l'établissement
        String deleteCertificatesQuery = "DELETE FROM certificat WHERE ID_Etablissement = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(deleteCertificatesQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }

        String deleteEtablissementQuery = "DELETE FROM etablissement WHERE ID_Etablissement = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(deleteEtablissementQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }


    @Override
    public List<Etablissement> getAll() throws SQLException {
        String sql = "SELECT * FROM etablissement";
        Statement statement = cnx.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Etablissement> etablissements = new ArrayList<>();
        while (rs.next()) {
            Etablissement etablissement = new Etablissement();
            etablissement.setID_Etablissement(rs.getInt("ID_Etablissement"));
            etablissement.setImg_Etablissement(rs.getString("img_Etablissement"));
            etablissement.setNom_Etablissement(rs.getString("Nom_Etablissement"));
            etablissement.setAdresse_Etablissement(rs.getString("Adresse_Etablissement"));
            etablissement.setType_Etablissement(rs.getString("Type_Etablissement"));
            etablissement.setTel_Etablissement(rs.getInt("Tel_Etablissement"));
            etablissement.setDirecteur_Etablissement(rs.getString("Directeur_Etablissement"));
            etablissement.setDate_Fondation(rs.getDate("Date_Fondation"));
            etablissements.add(etablissement);
        }
        return etablissements;
    }

    @Override
    public Etablissement getById(int idEtablissement) throws SQLException {
        String sql = "SELECT `img_Etablissement`, `Nom_Etablissement`, `Adresse_Etablissement`, `Type_Etablissement`, `Tel_Etablissement`, `Directeur_Etablissement`, `Date_Fondation` FROM `etablissement` WHERE `ID_Etablissement` = ?";

        PreparedStatement preparedStatement = cnx.prepareStatement(sql);
        preparedStatement.setInt(1, idEtablissement);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String img_Etablissement = resultSet.getString("img_Etablissement");
            String nomEtablissement = resultSet.getString("Nom_Etablissement");
            String adresseEtablissement = resultSet.getString("Adresse_Etablissement");
            String typeEtablissement = resultSet.getString("Type_Etablissement");
            int telEtablissement = resultSet.getInt("Tel_Etablissement");
            String directeurEtablissement = resultSet.getString("Directeur_Etablissement");
            Date dateFondation = resultSet.getDate("Date_Fondation");

            return new Etablissement(img_Etablissement, nomEtablissement, adresseEtablissement, typeEtablissement, telEtablissement, directeurEtablissement, dateFondation);
        } else {
            return null;
        }
    }

    public List<String> getNoms() throws SQLException {
        List<String> nomsEtablissements = new ArrayList<>();
        String sql = "SELECT Nom_Etablissement FROM etablissement"; // récupérer tous les noms d'établissements

        try (Statement statement = cnx.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                String nomEtablissement = rs.getString("Nom_Etablissement");
                nomsEtablissements.add(nomEtablissement);
            }
        }

        return nomsEtablissements;
    }


    public int getIDByNom(String nomEtablissement) throws SQLException {
        String sql = "SELECT ID_Etablissement FROM etablissement WHERE Nom_Etablissement = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setString(1, nomEtablissement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("ID_Etablissement");
                } else {
                    throw new SQLException("Aucun établissement trouvé avec le nom spécifié : " + nomEtablissement);
                }
            }
        }
    }

    public Map<String, Integer> getNombreCertificatsParEtablissement() throws SQLException {
        Map<String, Integer> nombreCertificatsParEtablissement = new HashMap<>();
        String sql = "SELECT e.Nom_Etablissement, COUNT(c.ID_Certificat) AS NombreCertificats " +
                "FROM etablissement e " +
                "LEFT JOIN certificat c ON e.ID_Etablissement = c.ID_Etablissement " +
                "GROUP BY e.Nom_Etablissement";
        try (Statement statement = cnx.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String nomEtablissement = resultSet.getString("Nom_Etablissement");
                int nombreCertificats = resultSet.getInt("NombreCertificats");
                nombreCertificatsParEtablissement.put(nomEtablissement, nombreCertificats);
            }
        }
        return nombreCertificatsParEtablissement;
    }



    public int getNombreCertificatsByEtablissement(int idEtablissement) throws SQLException {
        String sql = "SELECT COUNT(*) AS NombreCertificats FROM certificat WHERE ID_Etablissement = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, idEtablissement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("NombreCertificats");
                } else {
                    return 0; // Aucun certificat trouvé pour cet établissement
                }
            }
        }
    }

    public boolean isUniqueEtablissement(String nomEtablissement, String adresseEtablissement, String typeEtablissement, int telEtablissement, String directeurEtablissement, Date dateFondation) throws SQLException {
        String sql = "SELECT COUNT(*) FROM etablissement WHERE Nom_Etablissement = ? AND Adresse_Etablissement = ? AND Type_Etablissement = ? AND Tel_Etablissement = ? AND Directeur_Etablissement = ? AND Date_Fondation = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setString(1, nomEtablissement);
            preparedStatement.setString(2, adresseEtablissement);
            preparedStatement.setString(3, typeEtablissement);
            preparedStatement.setInt(4, telEtablissement);
            preparedStatement.setString(5, directeurEtablissement);
            preparedStatement.setDate(6, dateFondation);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) == 0;
            }
        }
        return false;
    }

    public List<Etablissement> getEtablissementList() {
        List<Etablissement> etablissements = new ArrayList<>();
        String sql = "SELECT ID_Etablissement, Nom_Etablissement, Adresse_Etablissement, img_Etablissement, Type_Etablissement, Tel_Etablissement, Directeur_Etablissement, Date_Fondation FROM etablissement";

        try (Statement statement = cnx.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Etablissement etablissement = new Etablissement();
                etablissement.setID_Etablissement(rs.getInt("ID_Etablissement"));
                etablissement.setImg_Etablissement(rs.getString("img_Etablissement"));
                etablissement.setNom_Etablissement(rs.getString("Nom_Etablissement"));
                etablissement.setAdresse_Etablissement(rs.getString("Adresse_Etablissement"));
                etablissement.setType_Etablissement(rs.getString("Type_Etablissement"));
                etablissement.setTel_Etablissement(rs.getInt("Tel_Etablissement"));
                etablissement.setDirecteur_Etablissement(rs.getString("Directeur_Etablissement"));
                etablissement.setDate_Fondation(rs.getDate("Date_Fondation"));

                etablissements.add(etablissement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etablissements;
    }


}





