package mvc.dao;

import mvc.model.message;

import java.util.ArrayList;

public interface messageDao {
    void ajouter(message mes)throws DAOException;
    void supprimer(message mes)throws DAOException;
    void modifier(message mes)throws DAOException;
    /* compliquer la fonction ou en faire plus pour choisir ce qu'on récupère (nombre messages, le tri, iduser.....) */
    ArrayList<message> trouverMessages(Object... obj)throws DAOException;
    message trouverMessage(int idMessage)throws DAOException;

}
