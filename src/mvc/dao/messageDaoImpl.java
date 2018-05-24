package mvc.dao;

import com.sun.deploy.util.ArrayUtil;
import mvc.model.message;
import static mvc.dao.utilitaireDao.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class messageDaoImpl implements messageDao{
    private DAOFactory daoFactory;
    private static final String SQL_UPDATE_ADD = "";
    private static final String SQL_UPDATE_DELETE = "";
    private static final String SQL_UPDATE_MODIFY = "";
    private static final String SQL_SELECT_PERTINENCE = "";
    private static final String SQL_SELECT_DATE = "";
    private static final String SQL_SELECT_AUTHOR = "";
    private static final String SQL_SELECT_IDMES = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Date FROM Message WHERE IdUser = ?;";



    messageDaoImpl (DAOFactory daoFactory){this.daoFactory = daoFactory;}
    /**
     * permet d'ajouter un message a la base de données
     *
     * @param mes le message a jouter à la base de données
     * @throws DAOException
     */
    @Override
    public void ajouter(message mes) throws DAOException {

    }

    /**
     * permet de supprimer un message de la base de données
     *
     * @param mes le message à supprimer
     * @throws DAOException
     */
    @Override
    public void supprimer(message mes) throws DAOException {

    }

    /**
     * permet de modifier un message stocké sur la base de données
     *
     * @param mes le message à modifier
     * @throws DAOException
     */
    @Override
    public void modifier(message mes) throws DAOException {

    }

    /**
     * permet de choisir la tranche de messages a récupérer lorque ceci sont récupérés par ordre de pertinence
     *
     * @param debut debut de la tranche de messages
     * @param fin   fin de la tranche de message
     * @return ArrayList<message> la liste de messages selectionnés
     * @throws DAOException
     */
    @Override
    public ArrayList<message> trouverMessagesPertinence(int debut, int fin) throws DAOException {
        return null;
    }

    /**
     * permet de choisir la tranche de messages a récupérer lorque ceci sont récupérés par date de publication
     *
     * @param debut debut de la tranche de messages
     * @param fin   fin de la tranche de message
     * @return ArrayList<message> la liste de messages selectionnés
     * @throws DAOException
     */
    @Override
    public ArrayList<message> trouverMessagesDate(int debut, int fin) throws DAOException {
        return null;
    }

    /**
     * permet de récupérer les messages publiés par un utilisateur en fonction de son id
     *
     * @param idUser id de l'utilisateur
     * @return ArrayList<message> la liste des messages publiés par cet utilisateur
     * @throws DAOException
     */
    @Override
    public ArrayList<message> trouverMessagesAuteur(int idUser) throws DAOException {
        return null;
    }

    /**
     * per
     *
     * @param idMessage
     * @return
     * @throws DAOException
     */
    @Override
    public message trouverMessage(int idMessage) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        message mes = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_IDMES, false, idMessage );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                mes = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }
        return mes;


    }

    private static message map(ResultSet resultSet) throws SQLException{
        message mes = new message();
        mes.setCategories(resultSet.getString("Categories"));
        mes.setDestinataires(resultSet.getString("Destinataires"));
        mes.setDisagree(resultSet.getInt("disagree"));
        mes.setAgree(resultSet.getInt("Agree"));
        mes.setFlagModeration(resultSet.getBoolean("FlagModeration"));
        mes.setIdMessage(resultSet.getInt("IdMessage"));
        mes.setIdReponse(resultSet.getInt("IdAnswer"));
        mes.setResolu(resultSet.getBoolean("FlagAnswer"));
        mes.setIdUser(resultSet.getInt("IdUser"));
        mes.setFlagNotif(resultSet.getBoolean("FlagNotif"));
        mes.setText(resultSet.getString("TextMessage"));
        mes.setTimestamp(resultSet.getTimestamp("Date"));
        return  mes;
    }




}
