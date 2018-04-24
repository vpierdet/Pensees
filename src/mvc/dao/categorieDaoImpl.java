package mvc.dao;

import mvc.model.categorie;

public class categorieDaoImpl implements categorieDao{
    private DAOFactory daoFactory;
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
