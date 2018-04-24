package mvc.dao;

import mvc.model.answer;

import java.util.ArrayList;

public interface answerDao {

    void ajouter(answer ans)throws DAOException;
    void supprimer(answer ans)throws DAOException;
    void modifier(answer ans)throws DAOException;
    ArrayList<answer> trouverReponses(int idUser)throws DAOException;
    ArrayList<answer> trouverReponse(int idMessage)throws DAOException;


}
