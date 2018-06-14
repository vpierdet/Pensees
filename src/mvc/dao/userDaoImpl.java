package mvc.dao;

import mvc.model.user;

import static mvc.dao.utilitaireDao.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userDaoImpl implements userDao {
    private DAOFactory daoFactory;
    private static final String SQL_SELECT_USERNAME = "SELECT IdUser, Username, password, FlagBan, UserType, Mail FROM User WHERE Username = ?;";
    private static final String SQL_UPDATE_BANNIR = "UPDATE User SET FlagBan = 1 WHERE Username = ?;";

    userDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    @Override
    public user trouver(String username) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        user user1 = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_USERNAME, false, username );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
               user1 = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return user1;
    }

    @Override
    public void bannir(String username) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_BANNIR, true, username);
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if ( statut == 0 ) {
                throw new DAOException( "Échec de la modification de l'utilisateur, aucune ligne ajoutée dans la table." );
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
           /* if ( valeursAutoGenerees.next() ) {

            } else {
                throw new DAOException( "Échec de la modification de l'utilisateur, aucun ID auto-généré retourné." );
            }*/
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
    }


    private static user map(ResultSet resultSet) throws SQLException {
        user user1 = new user();
        user1.setUserType(resultSet.getInt("UserType"));
        user1.setPassword(resultSet.getNString("password"));
        user1.setIdUser(resultSet.getInt("IdUser"));
        user1.setMail(resultSet.getString("Mail"));
        user1.setUsername(resultSet.getString("Username"));
        user1.setFlagBan(resultSet.getBoolean("FlagBan"));
        return user1;
    }



}
