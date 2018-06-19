package mvc.dao;

import mvc.model.categorie;

import java.sql.ResultSet;

public interface categorieDao {

    void ajouter (categorie catego)throws DAOException;
    void supprimer (String nom)throws DAOException;
    void modifier (int id, String nom, String color)throws DAOException;
    void lier(int idUser, String catego) throws DAOException;
    categorie trouver(String nom)throws DAOException;
    categorie trouverUser(int idUser) throws DAOException;


}
