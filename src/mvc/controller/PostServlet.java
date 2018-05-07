package mvc.controller;

import mvc.dao.DAOFactory;
import mvc.dao.messageDao;
import mvc.model.categorie;
import mvc.model.message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "PostServlet", urlPatterns = {"/PostServlet"})
public class PostServlet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE = "/WEB-INF/succes.jsp";
    private messageDao md;

    public void init() throws ServletException {
        this.md = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getMessageDao();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        md.ajouter(messageCreator(session,request));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(VUE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private static message messageCreator(HttpSession session, HttpServletRequest request){
        message mes = new message();
        categorie cat = new categorie();
        cat.setNom(request.getParameter("categorie"));
        mes.setCategories(cat);
        mes.setDisagree(0);
        mes.setAgree(0);
        mes.setFlagModeration(true);
        mes.setIdUser((int) session.getAttribute("idUser"));
        mes.setFlagNotif(false);
        mes.setText(request.getParameter("txt"));

        java.util.Date utilDate = new java.util.Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(utilDate);
        cal.set(Calendar.MILLISECOND, 0);
        mes.setTimestamp(new java.sql.Timestamp(cal.getTimeInMillis()));


        return mes;
    }


}
