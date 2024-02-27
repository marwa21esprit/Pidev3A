package Services;
import java.sql.SQLException;
import java.util.List;
public interface ITuteur<T> {



    public interface ITuteurServices <T>{
        void addTuteur(T t) throws SQLException;

        void updateTuteur(T t) throws SQLException;

        void deleteTuteur(int id_tuteur) throws SQLException;

        List<T> getAll() throws SQLException;

        T getById(int id_tuteur) throws  SQLException;
    }
}
