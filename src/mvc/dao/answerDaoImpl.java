package mvc.dao;

import mvc.model.answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static mvc.dao.utilitaireDao.fermeturesSilencieuses;
import static mvc.dao.utilitaireDao.initialisationRequetePreparee;

public class answerDaoImpl implements answerDao{
    private DAOFactory daoFactory;
    answerDaoImpl (DAOFactory daoFactory){this.daoFactory = daoFactory;}

    private static final String SQL_UPDATE_ADD = "INSERT INTO Answer ( IdMessage, IdUSer, TextAnswer, Date, Username) VALUES(?,?,?,NOW(),?)";
    private static final String SQL_UPDATE_DELETE = "";
    private static final String SQL_UPDATE_MODIFY = "";
    private static final String SQL_SELECT_AUTHOR = "";
    private static final String SQL_SELECT_IDMES = "SELECT IdAnswer, IdMessage, IdUSer, TextAnswer, Date, Username FROM Answer WHERE IdMessage = ?";



    @Override
    public void ajouter(answer ans) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE_ADD, true, ans.getIdMessage(), ans.getIdUser(), ans.getTxt(), ans.getUsername());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses( preparedStatement, connexion);
        }
    }

    @Override
    public void supprimer(answer ans) throws DAOException {

    }

    @Override
    public void modifier(answer ans) throws DAOException {

    }

    @Override
    public ArrayList<answer> trouverReponseIdUser(int idUser) throws DAOException {
        return null;
    }

    @Override
    public answer trouverReponseIdMessage(int idMessage) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        answer ans = new answer();
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_IDMES, false, idMessage);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                ans = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            fermeturesSilencieuses(resultSet, preparedStatement, connexion);
        }
        return ans;
    }

    private static answer map (ResultSet resultSet) throws SQLException{
        answer ans = new answer();
        ans.setDate(resultSet.getTimestamp("Date"));
        ans.setIdMessage(resultSet.getInt("IdMessage"));
        ans.setIdReponse(resultSet.getInt("IdAnswer"));
        ans.setIdUser(resultSet.getInt("IdUser"));
        ans.setTxt(resultSet.getString("TextAnswer"));
        ans.setUsername(resultSet.getString("Username"));
        return ans;

    }
}
