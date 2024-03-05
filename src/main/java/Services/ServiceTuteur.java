package Services;

import entities.Tuteur;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public class ServiceTuteur implements ITuteur <Tuteur> {
    private static Connection cnx;
    public int getIdTuteurByNomPrenom;

    public ServiceTuteur() {
        cnx = MyDB.getInstance().getCnx();
    }


    public void addTuteur(Tuteur tuteur) throws SQLException {
        String sql = "INSERT INTO tuteur (nom,prenom,date_naisc,tlf,profession,email,image)VALUES (?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = cnx.prepareStatement(sql);

        preparedStatement.setString(1, tuteur.getNom());
        preparedStatement.setString(2, tuteur.getPrenom());
        preparedStatement.setDate(3, tuteur.getDate_naisc());
        preparedStatement.setInt(4, tuteur.getTlf());
        preparedStatement.setString(5, tuteur.getProfession());
        preparedStatement.setString(6, tuteur.getEmail());
        preparedStatement.setString(7, tuteur.getImage());
=======
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
>>>>>>> fbee1fb1734da57bf6f5445215b18fdea9e2aefe


        preparedStatement.executeUpdate();
    }

<<<<<<< HEAD
    public void updateTuteur(Tuteur tuteur, int id) throws SQLException {
=======
    public void updateTuteur (Tuteur tuteur , int id) throws SQLException {
>>>>>>> fbee1fb1734da57bf6f5445215b18fdea9e2aefe
        String sql = "UPDATE tuteur SET nom = ?, prenom = ?, date_naisc = ?, tlf = ?, profession = ?, email = ? , image = ? WHERE id_tuteur = ? ";

        PreparedStatement preparedStatement = cnx.prepareStatement(sql);

        preparedStatement.setString(1, tuteur.getNom());
<<<<<<< HEAD
        preparedStatement.setString(2, tuteur.getPrenom());
        preparedStatement.setDate(3, tuteur.getDate_naisc());
        preparedStatement.setInt(4, tuteur.getTlf());
        preparedStatement.setString(5, tuteur.getProfession());
        preparedStatement.setString(6, tuteur.getEmail());
        preparedStatement.setString(7, tuteur.getImage());
        preparedStatement.setInt(8, id);
=======
        preparedStatement.setString(2,tuteur.getPrenom());
        preparedStatement.setDate(3,tuteur.getDate_naisc());
        preparedStatement.setInt(4,tuteur.getTlf());
        preparedStatement.setString(5,tuteur.getProfession());
        preparedStatement.setString(6,tuteur.getEmail());
        preparedStatement.setString(7,tuteur.getImage());
        preparedStatement.setInt(8,id);
>>>>>>> fbee1fb1734da57bf6f5445215b18fdea9e2aefe
        System.out.println("tutteur updated");
        //execution de la requete
        preparedStatement.executeUpdate();
    }

<<<<<<< HEAD
    public void deleteTuteur(int id_tuteur) throws SQLException {
        String sql = "DELETE FROM tuteur WHERE id_tuteur = ?";
        PreparedStatement preparedStatement = cnx.prepareStatement(sql);
        preparedStatement.setInt(1, id_tuteur);
=======
    public void deleteTuteur (int id_tuteur) throws SQLException {
        String sql = "DELETE FROM tuteur WHERE id_tuteur = ?";
        PreparedStatement preparedStatement = cnx.prepareStatement(sql);
        preparedStatement.setInt(1,id_tuteur);
>>>>>>> fbee1fb1734da57bf6f5445215b18fdea9e2aefe
        preparedStatement.executeUpdate();

    }

    public List<Tuteur> getAll() throws SQLException {

        String sql = "SELECT * FROM tuteur";
        Statement statement = cnx.createStatement();
<<<<<<< HEAD
        ResultSet rs = statement.executeQuery(sql);
        List<Tuteur> tuteurs = new ArrayList<>();

        while (rs.next()) {
=======
        ResultSet rs =statement.executeQuery(sql);
        List <Tuteur> tuteurs = new ArrayList<>();

        while (rs.next()){
>>>>>>> fbee1fb1734da57bf6f5445215b18fdea9e2aefe
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
<<<<<<< HEAD
        }
        return tuteurs;
=======
        } return tuteurs;
>>>>>>> fbee1fb1734da57bf6f5445215b18fdea9e2aefe


    }

<<<<<<< HEAD
    public Tuteur getById(int id_tuteur) throws SQLException {
=======
    public Tuteur getById (int id_tuteur) throws SQLException {
>>>>>>> fbee1fb1734da57bf6f5445215b18fdea9e2aefe
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
<<<<<<< HEAD
            return new Tuteur(id_tuteur, nom, prenom, date_naisc, tlf, profession, email, image);
=======
            return new Tuteur(id_tuteur, nom, prenom, date_naisc, tlf, profession, email,image);
>>>>>>> fbee1fb1734da57bf6f5445215b18fdea9e2aefe
        } else {

            return null;
        }
    }


<<<<<<< HEAD
    public List<Integer> getId_tuteur() throws SQLException {
        List<Integer> idsTuteurs = new ArrayList<>();
        String sql = "SELECT id_tuteur FROM tuteur";
=======
<<<<<<< HEAD
    public List<Integer> getId_tuteur() throws SQLException {
        List<Integer> idsTuteurs = new ArrayList<>();
        String sql = "SELECT id_tuteur FROM tuteur";
=======
<<<<<<< HEAD
    public List<String> getNom() throws SQLException {
        List<String> nomsTuteurs = new ArrayList<>();
        String sql = "SELECT nom FROM tuteur";
>>>>>>> e4a64c996fa0a287801d8671a2d22f8dfb2f992c
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
        try (Statement statement = cnx.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                Integer id_tuteur = rs.getInt("id_tuteur"); // Récupérer l'entier correctement
                idsTuteurs.add(id_tuteur);
            }
        }

        return idsTuteurs;
    }




<<<<<<< HEAD
    public static int getID(String nom, String prenom) throws SQLException {
        String sql = "SELECT id_tuteur FROM tuteur WHERE nom = ? AND prenom = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
=======
    public int getID(Integer id_tuteur) throws SQLException {
        String sql = "SELECT id_tuteur FROM tuteur WHERE id_tuteur = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_tuteur);
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id_tuteur");
                } else {
                    // Gérer le cas où aucun enregistrement correspondant n'est trouvé
<<<<<<< HEAD
                    throw new SQLException("Aucun tuteur trouvé avec le nom et prénom spécifiés : " + nom + " " + prenom);
=======
                    throw new SQLException("Aucun tuteur trouvé avec l'id spécifié : " + id_tuteur);
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
                }
            }
        }
    }
<<<<<<< HEAD

   /* public static List<Tuteur> getIdTuteurByNomPrenom(String nom, String prenom) throws SQLException {
        List<Integer> idsTuteurs = new ArrayList<>();
        String sql = "SELECT id_tuteur FROM tuteur WHERE nom = ? AND prenom = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    idsTuteurs.add(resultSet.getInt("id_tuteur"));
                }
            }
        }
        return idsTuteurs;
    }*/

    @Override
    public  List<String> getNoms() throws SQLException {
        List<String> nomsTuteurs = new ArrayList<>();
        String sql = "SELECT  nom, prenom FROM tuteur";
        try (Statement statement = cnx.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String fullName = nom+" "+prenom;
                nomsTuteurs.add(fullName);
            }
        }

        return nomsTuteurs;
    }
    @Override
    public int getIDByNom(String nom,String prenom) throws SQLException {
        String sql = "SELECT id_tuteur FROM tuteur WHERE nom LIKE ? AND prenom LIKE ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id_tuteur");
                } else {
                    // Gérer le cas où aucun enregistrement correspondant n'est trouvé
                    throw new SQLException("Aucun partenaire trouvé avec le nom spécifié : " + nom+prenom);
                }
            }
        }
    }

    @Override
    public String getNameByID(int id) throws SQLException {
        String sql = "SELECT nom , prenom FROM tuteur WHERE id_tuteur = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    String fullName = nom+" "+prenom;
                    return fullName;
                } else {
                    // Gérer le cas où aucun enregistrement correspondant n'est trouvé
                    throw new SQLException("Aucun établissement trouvé avec l'id spécifié : " + id);
                }
            }
        }
    }


    public List<Tuteur> getTuteurList() {
        List<Tuteur> tuteurs = new ArrayList<>();
        String sql = "SELECT id_tuteur,nom, prenom, date_naisc, tlf, profession, email, image FROM tuteur ";

        try {

            try (Statement statement = cnx.createStatement();
                 ResultSet rs = statement.executeQuery(sql)) {

                while (rs.next()) {
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
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tuteurs;

    }


    private List<Tuteur> getTuteurFromResultSet(String sql) throws SQLException {
        List<Tuteur> tuteurs = new ArrayList<>();
        try (Statement statement = cnx.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
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
            }
        }
        return tuteurs;
    }

    public List<Tuteur> trierParNom() throws SQLException {
        String sql = "SELECT * FROM tuteur ORDER BY nom ASC";
        return getTuteurFromResultSet(sql);
    }

    public List<Tuteur> trierParPrenom() throws SQLException {
        String sql = "SELECT * FROM tuteur ORDER BY prenom ASC";
        return getTuteurFromResultSet(sql);
    }

    public List<Tuteur> trierParProfession() throws SQLException {
        String sql = "SELECT * FROM tuteur ORDER BY profession ASC";
        return getTuteurFromResultSet(sql);
    }



}
=======
=======
>>>>>>> fbee1fb1734da57bf6f5445215b18fdea9e2aefe
}
>>>>>>> 589f2aeeceeb3ef8137b0cec44d486000e851d55
