package mvc.dao;

import mvc.model.categorie;

public class categorieDaoImpl implements categorieDao{
    private DAOFactory daoFactory;
    private static final String SQL_UPDATE_ADD = "";
    private static final String SQL_UPDATE_DELETE = "";
    private static final String SQL_UPDATE_MODIFY = "";
    private static final String SQL_SELECT_NAME= "";


    categorieDaoImpl (DAOFactory daoFactory){this.daoFactory = daoFactory;}




    @Override
    public void ajouter(categorie catego) throws DAOException {

    }

    @Override
    public void supprimer(String nom) throws DAOException {

    }

    @Override
    public void modifier(int id, String nom, String color) throws DAOException {

    }

    @Override
    public categorie trouver(String nom) throws DAOException {
        return null;
    }
}
