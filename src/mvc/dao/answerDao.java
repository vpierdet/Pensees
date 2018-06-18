package mvc.dao;

import mvc.model.answer;

import java.util.ArrayList;

public interface answerDao {

    int ajouter(answer ans)throws DAOException;
    void supprimer(answer ans)throws DAOException;
    void modifier(answer ans)throws DAOException;
    ArrayList<answer> trouverReponseIdUser(int idUser)throws DAOException;
    answer trouverReponseIdMessage(int idMessage)throws DAOException;


}
