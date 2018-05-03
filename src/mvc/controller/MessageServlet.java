package mvc.controller;

import mvc.dao.DAOFactory;
import mvc.dao.messageDao;
import mvc.model.message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MessageServlet", urlPatterns = {"/MessageServlet"})
public class MessageServlet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    private messageDao md;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.md = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getMessageDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        message mes = MessageFinder(request);
        System.out.println(mes.getText());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private  message MessageFinder(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("idMes"));
        return md.trouverMessage(id);
    }

}
