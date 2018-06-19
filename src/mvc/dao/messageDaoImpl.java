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
    private static final String SQL_UPDATE_ADD = "INSERT INTO Message(TextMessage, IdUser, FlagModeration, Agree, Disagree, FlagAnswer, Date, Destinataires,Categories, Username) VALUES(?,?,?,?,?,?,NOW(),?,?,?)";
    private static final String SQL_UPDATE_DELETE = "DELETE FROM Message WHERE condition";
    private static final String SQL_UPDATE_AG_PLUS ="UPDATE Message SET Agree = Agree + 1 WHERE IdMessage = ?";
    private static final String SQL_UPDATE_AG_MOIN ="UPDATE Message SET Agree = Agree - 1 WHERE IdMessage = ?";
    private static final String SQL_UPDATE_DAG_PLUS ="UPDATE Message SET Disagree = Disagree + 1 WHERE IdMessage = ?";
    private static final String SQL_UPDATE_DAG_MOIN ="UPDATE Message SET Disagree = Disagree - 1 WHERE IdMessage = ?";
    private static final String SQL_COUNT_CAT = "SELECT COUNT(*) FROM Message WHERE Categories LIKE  ? ";
    private static final String SQL_COUNT_ALL = "SELECT COUNT(*) FROM Message";
    private static final String SQL_COUNT_CAT_REPONDU = "SELECT COUNT(*) FROM Message WHERE Categories LIKE  ? AND IdAnswer > -1";
    private static final String SQL_COUNT_CAT_NONREP = "SELECT COUNT(*) FROM Message WHERE Categories LIKE  ? AND IdAnswer = -1";
    private static final String SQL_UPDATE_ANS = "UPDATE Message SET IdAnswer = ? WHERE IdMessage = ?";

    private static final String SQL_SELECT_CATEGORIE = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Username ,Date FROM Message WHERE Categories LIKE  ?  ORDER BY Date DESC";
    private static final String SQL_SELECT_CATEGORIE_PERTINENCE = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Username ,Date FROM Message WHERE Categories LIKE  ?  ORDER BY Agree DESC";
    private static final String SQL_SELECT_CATEGORIE_REPONDU = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Username ,Date FROM Message WHERE Categories LIKE  ? AND IdAnswer > 0 ORDER BY Date DESC";
    private static final String SQL_SELECT_CATEGORIE_NONREP = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Username ,Date FROM Message WHERE Categories LIKE  ? AND IdAnswer = -1  ORDER BY Date DESC";
    private static final String SQL_SELECT_PERTINENCE = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Date, Username  FROM Message ORDER BY Agree DESC";
    private static final String SQL_SELECT_DATE = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Date, Username  FROM Message ORDER BY Date DESC";
    private static final String SQL_SELECT_AUTHOR = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Date, Username  FROM Message WHERE IdUser = ?;";
    private static final String SQL_SELECT_IDMES = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Date, Username  FROM Message WHERE IdUser = ?;";
    private static final String SQL_SELECT_MODER = "SELECT TextMessage, Categories, Destinataires, disagree,Agree, FlagModeration,IdMessage,IdAnswer, FlagAnswer, IdUser, FlagNotif, Date, Username  FROM Message WHERE FlagModeration = 1;";

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
            preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE_ADD, true, mes.getText(), mes.getIdUser(), mes.isFlagModeration(), mes.getAgree(), mes.getDisagree(), mes.isResolu(), 2, mes.getCategories(), mes.getUsername());
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
    public void modifierAGDAG(int idMessage, boolean agree, boolean plus) throws DAOException {
        String SQL_ADAG;
        if (agree){
            if (plus){
                SQL_ADAG = SQL_UPDATE_AG_PLUS;
            }
            else{
                SQL_ADAG = SQL_UPDATE_AG_MOIN;
            }
        }
        else{
            if (plus){
                SQL_ADAG = SQL_UPDATE_DAG_PLUS;
            }
            else{
                SQL_ADAG = SQL_UPDATE_DAG_MOIN;
            }
        }
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_ADAG, true ,  idMessage);
            preparedStatement.executeUpdate();
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }

    }



    @Override
    public void Answer(int idMessage, int idAnswer) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE_ANS, true , idAnswer, idMessage);
            preparedStatement.executeUpdate();
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
        }
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
    public int Count(String tri, boolean catego) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int total = 0;
        String SQL = !catego ? SQL_COUNT_ALL : SQL_COUNT_CAT ;
        tri+= "%";

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();

            if (catego) preparedStatement = initialisationRequetePreparee(connexion, SQL, false,tri);
            else preparedStatement = initialisationRequetePreparee(connexion, SQL, false);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                total = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return total;
    }

    @Override
    public int Count(String tri, boolean catego, boolean repondu) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int total = 0;
        String SQL = repondu ? SQL_COUNT_CAT_REPONDU : SQL_COUNT_CAT_NONREP ;
        tri+= "%";

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();

            if (catego) preparedStatement = initialisationRequetePreparee(connexion, SQL, false,tri);
            else preparedStatement = initialisationRequetePreparee(connexion, SQL, false);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                total = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return total;
    }


    @Override
    public ArrayList<message> trouverMessagesCategorie(String categorie, int debut, int fin) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<message> listeMessages = new ArrayList<>();
        categorie += "%";
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

    @Override
    public ArrayList<message> trouverMessagesCategoriePertinence(String categorie, int debut, int fin) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<message> listeMessages = new ArrayList<>();
        categorie += "%";
        int i = 0;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_CATEGORIE_PERTINENCE, false, categorie);
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
    public ArrayList<message> trouverMessagesCategorieRepondu(String categorie, int debut, int fin, boolean repondu) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<message> listeMessages = new ArrayList<>();
        categorie += "%";
        String SQL = repondu ? SQL_SELECT_CATEGORIE_REPONDU : SQL_SELECT_CATEGORIE_NONREP;
        int i = 0;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL, false, categorie);
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
