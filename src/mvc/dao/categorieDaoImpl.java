package mvc.dao;

import mvc.model.categorie;
import mvc.model.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static mvc.dao.utilitaireDao.fermeturesSilencieuses;
import static mvc.dao.utilitaireDao.initialisationRequetePreparee;

public class categorieDaoImpl implements categorieDao{
    private DAOFactory daoFactory;
    private static final String SQL_UPDATE_ADD = "";
    private static final String SQL_UPDATE_DELETE = "";
    private static final String SQL_UPDATE_MODIFY = "";
    private static final String SQL_SELECT_NAME= "";
    private static  final String SQL_SELECT_USER = "SELECT idUser1, idUser2, Name, IdCategory FROM Category WHERE idUser1 = ? OR idUser2 = ? ";


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

    @Override
    public categorie trouverUser(int idUser) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        categorie cat = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_USER, false, idUser, idUser);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                cat = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return cat;
    }


    private static categorie map(ResultSet resultSet) throws SQLException {
        categorie cat = new categorie();
        cat.setNom(resultSet.getString("Name"));
        cat.setIdUser1(resultSet.getInt("idUser1"));
        cat.setIdUser2(resultSet.getInt("idUser2"));
        cat.setIdCategorie(resultSet.getInt("IdCategory"));
        return cat;
    }
}
