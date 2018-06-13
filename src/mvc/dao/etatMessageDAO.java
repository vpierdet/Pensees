package mvc.dao;

import mvc.model.etatMessage;

public interface etatMessageDao {

    void ajouter(int idUser, int idMessage, int etat);

    etatMessage trouver(int idUser, int idMessage);

    void supprimer(int idUser, int idMessage);
}
