package mvc.dao;

import mvc.model.answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class answerDaoImpl implements answerDao{
    private DAOFactory daoFactory;
    answerDaoImpl (DAOFactory daoFactory){this.daoFactory = daoFactory;}

    private static final String SQL_UPDATE_ADD = "";
    private static final String SQL_UPDATE_DELETE = "";
    private static final String SQL_UPDATE_MODIFY = "";
    private static final String SQL_SELECT_AUTHOR = "";
    private static final String SQL_SELECT_IDMES = "";



    @Override
    public void ajouter(answer ans) throws DAOException {

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
        return null;
    }

    private static answer map (ResultSet resultSet) throws SQLException{
        answer ans = new answer();
        ans.setDate(resultSet.getTimestamp("Date"));
        ans.setIdMessage(resultSet.getInt("IdMessage"));
        ans.setIdReponse(resultSet.getInt("IdAnswer"));
        ans.setIdUser(resultSet.getInt("IdUser"));
        ans.setTxt(resultSet.getString("TextAnswer"));
        return ans;

    }
}
