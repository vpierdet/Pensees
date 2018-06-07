package mvc.dao;

import mvc.model.etatMessage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import static mvc.dao.utilitaireDao.initialisationRequetePreparee;

public class etatMessageDaoImpl implements etatMessageDAO {
    private DAOFactory daoFactory;
    private static final String SQL_UPDATE_ADD = "INSERT INTO etatMessage(idMessage, idUser, value) VALUES(?,?,?)";
    private static final String SQL_SELECT_BASE = "SELECT idMessage, idUser, value FROM etatMessage WHERE idMessage = ? AND idUser = ? ";

    public etatMessageDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouter(int idUser, int idMessage, int etat) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE_ADD, true, idMessage,idUser,etat);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            utilitaireDao.fermeturesSilencieuses( preparedStatement, connexion);
        }
    }

    @Override
    public etatMessage trouver(int idUser, int idMessage) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        etatMessage em ;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_BASE, false);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            em = map(resultSet);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            utilitaireDao.fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }

        return em;
    }


    private static etatMessage map(ResultSet resultSet) throws SQLException {
        if (resultSet==null)return null;
        etatMessage em = new etatMessage();
        em.setEtat(resultSet.getInt("value"));
        em.setIdMessage(resultSet.getInt("idMessage"));
        em.setIdUser(resultSet.getInt("idUser"));
        return em;
    }
}
