package mvc.dao;

import mvc.model.user;

public interface  userDao {

    user trouver(String username) throws DAOException;

    void modifier(String colunm, Object value) throws DAOException;

}
