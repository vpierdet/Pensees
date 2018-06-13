package mvc.dao;

import mvc.model.etatMessage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import static mvc.dao.utilitaireDao.initialisationRequetePreparee;

public class etatMessageDaoImpl implements etatMessageDao {
    private DAOFactory daoFactory;
    private static final String SQL_UPDATE_DEL = "DELETE FROM etatMessage WHERE IdMessage = ? AND IdUser = ?";
    private static final String SQL_UPDATE_ADD = "INSERT INTO etatMessage(IdMessage, IdUser, value) VALUES(?,?,?)";
    private static final String SQL_SELECT_BASE = "SELECT IdMessage, idUser, value FROM etatMessage WHERE IdMessage = ? AND IdUser = ? ";

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
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_BASE, false, idMessage,idUser);
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


    @Override
    public void supprimer(int idUser, int idMessage) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE_DEL, true, idMessage,idUser);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            utilitaireDao.fermeturesSilencieuses( preparedStatement, connexion);
        }
    }

    private static etatMessage map(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            etatMessage em = new etatMessage();
            em.setEtat(resultSet.getInt("value"));
            em.setIdMessage(resultSet.getInt("idMessage"));
            em.setIdUser(resultSet.getInt("idUser"));
            return em;
        }
        else{
           return null;
        }
    }
}
