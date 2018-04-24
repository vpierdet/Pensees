package mvc.dao;

import mvc.model.answer;

import java.util.ArrayList;

public class answerDaoImpl implements answerDao{
    private DAOFactory daoFactory;
    answerDaoImpl (DAOFactory daoFactory){this.daoFactory = daoFactory;}
    @Override
    public void ajouter(answer ans) throws DAOException {

    }

    @Override
    public void supprimer(answer ans) throws DAOException {

    }

    @Override
    public void modifier(answer ans) throws DAOException {

    }

    @Override
    public ArrayList<answer> trouverReponses(int idUser) throws DAOException {
        return null;
    }

    @Override
    public ArrayList<answer> trouverReponse(int idMessage) throws DAOException {
        return null;
    }
}
