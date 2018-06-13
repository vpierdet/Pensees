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
import java.util.ArrayList;

@WebServlet(name = "EssaiServlet", urlPatterns = {"/essai"})
public class EssaiServlet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    private messageDao md;

    public void init() throws ServletException {
        this.md = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getMessageDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<message> listeMessage = md.trouverMessagesDate(0,5);
        request.setAttribute("listeMessage" , listeMessage);
        getServletContext().getRequestDispatcher("/FileActu.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<message> listeMessage = md.trouverMessagesPertinence(0,5);
        request.setAttribute("listeMessage" , listeMessage);
        getServletContext().getRequestDispatcher("/FileActu.jsp").forward(request,response);
    }
}
