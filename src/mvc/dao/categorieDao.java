package mvc.dao;

import mvc.model.categorie;

public interface categorieDao {

    void ajouter (categorie catego)throws DAOException;
    void supprimer (String nom)throws DAOException;
    void modifier (int id, String nom, String color)throws DAOException;
    categorie trouver(String nom)throws DAOException;

}
