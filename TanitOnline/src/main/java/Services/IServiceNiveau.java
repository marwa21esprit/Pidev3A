package Services;

import models.Niveau;

import java.sql.SQLException;
import java.util.List;

public interface IServiceNiveau {
    void add(Niveau niveau) throws SQLException;

    void update(Niveau niveau) throws SQLException;


    void delete(String name) throws SQLException;

    List<Niveau> getAll() throws SQLException;
    Niveau getById(int id) throws  SQLException;
    Niveau getByName(String name) throws  SQLException;
    public List<String> getName() throws  SQLException;

}
