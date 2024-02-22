package services;
import java.sql.SQLException;
import java.util.List;
public interface IEtabServices <T>{

    void addSchool(T t) throws SQLException;

    void updateSchool(T t,int id) throws SQLException;

    void deleteSchool(int id) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(int id) throws  SQLException;
}


