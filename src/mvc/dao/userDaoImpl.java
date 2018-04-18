package mvc.dao;

import mvc.model.user;
import static mvc.dao.utilitaireDao.*;

import java.sql.ResultSet;

public class userDaoImpl implements userDao {
    private DAOFactory          daoFactory;

    userDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public user trouver(String username) throws DAOException {
        return null;
    }

    @Override
    public void modifier(String colunm, Object value) throws DAOException {

    }



}
