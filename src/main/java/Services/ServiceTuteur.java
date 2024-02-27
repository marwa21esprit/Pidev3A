package Services;

import entities.Tuteur;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceTuteur implements ITuteur <Tuteur>
{
    private Connection cnx;

    public ServiceTuteur () { cnx= MyDB.getInstance().getCnx();}


    public void addTuteur (Tuteur tuteur) throws SQLException
    {
        String sql ="INSERT INTO tuteur (nom,prenom,date_naisc,tlf,profession,email,image)VALUES (?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement =cnx.prepareStatement(sql);

        preparedStatement.setString(1, tuteur.getNom());
        preparedStatement.setString(2,tuteur.getPrenom());
        preparedStatement.setDate(3,tuteur.getDate_naisc());
        preparedStatement.setInt(4,tuteur.getTlf());
        preparedStatement.setString(5,tuteur.getProfession());
        preparedStatement.setString(6,tuteur.getEmail());
        preparedStatement.setString(7,tuteur.getImage());

        //execution de la requete
        preparedStatement.executeUpdate();
    }

    public void updateTuteur (Tuteur tuteur , int id) throws SQLException {
        String sql = "UPDATE tuteur SET nom = ?, prenom = ?, date_naisc = ?, tlf = ?, profession = ?, email = ? , image = ? WHERE id_tuteur = ? ";

        PreparedStatement preparedStatement = cnx.prepareStatement(sql);

        preparedStatement.setString(1, tuteur.getNom());
        preparedStatement.setString(2,tuteur.getPrenom());
        preparedStatement.setDate(3,tuteur.getDate_naisc());
        preparedStatement.setInt(4,tuteur.getTlf());
        preparedStatement.setString(5,tuteur.getProfession());
        preparedStatement.setString(6,tuteur.getEmail());
        preparedStatement.setString(7,tuteur.getImage());
        preparedStatement.setInt(8,id);
        System.out.println("tutteur updated");
        //execution de la requete
        preparedStatement.executeUpdate();
    }

    public void deleteTuteur (int id_tuteur) throws SQLException {
        String sql = "DELETE FROM tuteur WHERE id_tuteur = ?";
        PreparedStatement preparedStatement = cnx.prepareStatement(sql);
        preparedStatement.setInt(1,id_tuteur);
        preparedStatement.executeUpdate();

    }

    public List<Tuteur> getAll() throws SQLException {

        String sql = "SELECT * FROM tuteur";
        Statement statement = cnx.createStatement();
        ResultSet rs =statement.executeQuery(sql);
        List <Tuteur> tuteurs = new ArrayList<>();

        while (rs.next()){
            Tuteur tuteur = new Tuteur();

            tuteur.setId_tuteur(rs.getInt("id_tuteur"));
            tuteur.setNom(rs.getString("nom"));
            tuteur.setPrenom(rs.getString("prenom"));
            tuteur.setDate_naisc(rs.getDate("date_naisc"));
            tuteur.setTlf(rs.getInt("tlf"));
            tuteur.setProfession(rs.getString("profession"));
            tuteur.setEmail(rs.getString("email"));
            tuteur.setImage(rs.getString("image"));

            tuteurs.add(tuteur);
        } return tuteurs;


    }

    public Tuteur getById (int id_tuteur) throws SQLException {
        String sql = "SELECT `nom`, `prenom`, `date_naisc`, `tlf`, `profession`, `email`, `image` FROM `tuteur` WHERE `id_tuteur` = ?";
        PreparedStatement preparedStatement = cnx.prepareStatement(sql);
        preparedStatement.setInt(1, id_tuteur);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {

            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            Date date_naisc = resultSet.getDate("date_naisc");
            int tlf = resultSet.getInt("tlf");
            String profession = resultSet.getString("profession");
            String email = resultSet.getString("email");
            String image = resultSet.getString("image");
            return new Tuteur(id_tuteur, nom, prenom, date_naisc, tlf, profession, email,image);
        } else {

            return null;
        }
    }


}