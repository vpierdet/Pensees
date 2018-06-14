package mvc.dao;

import mvc.model.user;

public interface  userDao {

    user trouver(String username) throws DAOException;

    void bannir(String username) throws DAOException;


}
