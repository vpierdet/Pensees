package mvc.dao;

import com.sun.deploy.util.ArrayUtil;
import mvc.model.message;
import static mvc.dao.utilitaireDao.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class messageDaoImpl implements messageDao{

    private DAOFactory daoFactory;
    messageDaoImpl (DAOFactory daoFactory){this.daoFactory = daoFactory;}
    private static final String SQL_UPDATE_ADD = "";
    private static final String SQL_UPDATE_DELETE = "";
    private static final String SQL_UPDATE_MODIFY = "";
    private static final String SQL_SELECT_PERTINENCE = "";
    private static final String SQL_SELECT_DATE = "";
    private static final String SQL_SELECT_AUTHOR = "";
    private static final String SQL_SELECT_IDMES = "";


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
        return null;
    }

    private static message map(ResultSet resultSet) throws SQLException{
        message mes = new message();
        mes.setCategories(resultSet.getString(""));
        mes.setDestinataires(resultSet.getString(""));
        mes.setDisagree(resultSet.getInt("disagree"));
        mes.setAgree(resultSet.getInt("Agree"));
        mes.setFlagModeration(resultSet.getBoolean("FlagModeration"));
        mes.setIdMessage(resultSet.getInt("IdMessage"));
        mes.setIdReponse(resultSet.getInt(""));
        mes.setResolu(resultSet.getBoolean(""));
        mes.setIdUser(resultSet.getInt("IdUser"));
        mes.setFlagNotif(resultSet.getBoolean("FlagNotif"));
        mes.setText(resultSet.getString("TextMessage"));
        mes.setTimestamp(resultSet.getTimestamp("Date"));
        return  mes;
    }




}
