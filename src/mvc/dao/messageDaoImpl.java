package mvc.dao;

import mvc.model.message;

import static mvc.dao.utilitaireDao.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class messageDaoImpl implements messageDao {
    private DAOFactory daoFactory;
    private static final String SQL_UPDATE_ADD = "INSERT INTO Message(TextMessage, IdUser, FlagModeration, Agree, Disagree, FlagAnswer, Date, Destinataires,Categories) VALUES(?,?,?,?,?,?,NOW(),?,?)";
    private static final String SQL_UPDATE_DELETE = "";
    private static final String SQL_UPDATE_MODIFY = "";
    private static final String SQL_SELECT_CATEGORIE = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Date FROM Message WHERE IdUser = ? ORDER BY Date DESC";;
    private static final String SQL_SELECT_PERTINENCE = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Date FROM Message ORDER BY Agree DESC";
    private static final String SQL_SELECT_DATE = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Date FROM Message ORDER BY Date DESC";;
    private static final String SQL_SELECT_AUTHOR = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Date FROM Message WHERE IdUser = ?;";;
    private static final String SQL_SELECT_IDMES = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Date FROM Message WHERE IdUser = ?;";
    private static final String SQL_SELECT_MODER = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Date FROM Message WHERE FlagModeration = 1;";;

    messageDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    @Override
    public void ajouter(message mes) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE_ADD, true, mes.getText(), mes.getIdUser(), mes.isFlagModeration(), mes.getAgree(), mes.getDisagree(), mes.isResolu(), 2, mes.getCategories());
            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
    }


    @Override
    public void supprimer(int id) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE_DELETE, true, id);
            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
    }


    @Override
    public void modifier(message mes) throws DAOException {

    }


    @Override
    public ArrayList<message> trouverMessagesPertinence(int debut, int fin) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        message mes = null;
        ArrayList<message> listeMessages = new ArrayList<>();
        int i = 0;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_PERTINENCE, false);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                if (i < debut) i++;
                else if (i > fin) break;
                else {
                    listeMessages.add(map(resultSet));
                    i++;
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return listeMessages;
    }


    @Override
    public ArrayList<message> trouverMessagesDate(int debut, int fin) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        message mes = null;
        ArrayList<message> listeMessages = new ArrayList<>();
        int i = 0;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_DATE, false);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                if (i < debut) i++;
                else if (i > fin) break;
                else {
                    listeMessages.add(map(resultSet));
                    i++;
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return listeMessages;

    }


    @Override
    public ArrayList<message> trouverMessagesAuteur(int idUser, int debut, int fin) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        message mes = null;
        ArrayList<message> listeMessages = new ArrayList<>();
        int i = 0;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_AUTHOR, false, idUser);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                if (i < debut) i++;
                else if (i > fin) break;
                else {
                    listeMessages.add(map(resultSet));
                    i++;
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return listeMessages;
    }

    @Override
    public message trouverMessage(int idMessage) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        message mes = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_IDMES, false, idMessage);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                mes = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return mes;
    }


    @Override
    public ArrayList<message> trouverMessagesCategorie(String categorie, int debut, int fin) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        message mes = null;
        ArrayList<message> listeMessages = new ArrayList<>();
        int i = 0;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_CATEGORIE, false, categorie);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                if (i < debut) i++;
                else if (i > fin) break;
                else {
                    listeMessages.add(map(resultSet));
                    i++;
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return listeMessages;

    }

    /**
     * @return
     * @throws DAOException
     */
    @Override
    public ArrayList<message> trouverMessageFlagModération(int debut, int fin) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        message mes = null;
        ArrayList<message> listeMessages = new ArrayList<>();
        int i = 0;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_MODER, false);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            while (resultSet.next()) {
                if (i < debut) i++;
                else if (i > fin) break;
                else {
                    listeMessages.add(map(resultSet));
                    i++;
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return listeMessages;
    }

    private static message map(ResultSet resultSet) throws SQLException {
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
        mes.setUsername(resultSet.getString("Username"));
        return mes;
    }


}
