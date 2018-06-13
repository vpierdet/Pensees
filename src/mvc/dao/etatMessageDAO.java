package mvc.dao;

import mvc.model.etatMessage;

public interface etatMessageDAO {
    void ajouter(int idUser, int idMessage, int etat);
    etatMessage trouver(int idUser, int idMessage);
}
