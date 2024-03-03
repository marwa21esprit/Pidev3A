package services;

import models.Etablissement;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IEtabServices<T> {

    void addSchool(T t) throws SQLException;

    void updateSchool(T t, int id) throws SQLException;

    void deleteSchool(int id) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(int id) throws SQLException;

    List<String> getNoms() throws SQLException;

    int getIDByNom(String nom) throws SQLException;
    String getNameByID(int id) throws SQLException;

    String getAdrByID(int id) throws SQLException;

    Map<String, Integer> getNombreCertificatsParEtablissement() throws SQLException;

     List<Etablissement> getEtablissementList() throws SQLException;
}
