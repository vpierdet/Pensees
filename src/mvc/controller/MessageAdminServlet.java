package mvc.controller;

import mvc.dao.DAOFactory;
import mvc.dao.answerDao;
import mvc.dao.etatMessageDao;
import mvc.dao.messageDao;
import mvc.model.answer;
import mvc.model.etatMessage;
import mvc.model.message;
import mvc.model.categorie;
import mvc.dao.categorieDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MessageAdminServlet", urlPatterns = {"/mas"})
public class MessageAdminServlet extends HttpServlet {

    private static final String CONF_DAO_FACTORY = "daofactory";
    private messageDao md;
    private etatMessageDao emd;
    private answerDao ad;
    private categorieDao cd;

    public void init() {
        this.md = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getMessageDao();
        this.emd = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getEtatMessageDAO();
        this.ad = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getAnswerDao();
        this.cd = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCategorierDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         if (request.getParameter("action").equals("réponse")){

        }

        HttpSession session = request.getSession();
         int idUser = (Integer) session.getAttribute("idUser");
        ArrayList<message> listeMessage = new ArrayList<>();
        categorie cat = cd.trouverUser(idUser);
        int debut = request.getParameter("bouton_page") != null ? Integer.parseInt(request.getParameter("bouton_page")) : 0;
        int fin = debut + 9;
        int nombreMessages = md.Count(cat.getNom(),true);
        if (debut == 0) request.setAttribute("page", 1);
        request.setAttribute("debut" , debut);
        request.setAttribute("nbrMessage", nombreMessages);
        listeMessage = md.trouverMessagesCategorie(cat.getNom(),debut,fin);
        listeMessage = getEtat(listeMessage, request);
        listeMessage = getResponse(listeMessage);
        request.setAttribute("listeMessage", listeMessage);
        getServletContext().getRequestDispatcher("/FileReponse.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idUser = (Integer) session.getAttribute("idUser");
        ArrayList<message> listeMessage = new ArrayList<>();
        categorie cat = cd.trouverUser(idUser);
        int debut = request.getParameter("bouton_page") != null ? Integer.parseInt(request.getParameter("bouton_page")) : 0;
        int fin = debut + 9;
        int nombreMessages = md.Count(cat.getNom(),true);
        if (debut == 0) request.setAttribute("page", 1);
        request.setAttribute("debut" , debut);
        request.setAttribute("nbrMessage", nombreMessages);
        listeMessage = md.trouverMessagesCategorie(cat.getNom(),debut,fin);
        listeMessage = getEtat(listeMessage, request);
        listeMessage = getResponse(listeMessage);
        request.setAttribute("listeMessage", listeMessage);

        getServletContext().getRequestDispatcher("/FileReponse.jsp").forward(request, response);

    }


    private ArrayList<message> getEtat(ArrayList<message> list, HttpServletRequest request) {
        int idUser = (int) request.getSession().getAttribute("idUser");
        for (message mes : list) {
            etatMessage e = emd.trouver(idUser, mes.getIdMessage());
            if (e != null)
                mes.setEtat(e.getEtat());
        }
        return list;
    }

    private ArrayList<message> getResponse(ArrayList<message> list) {
        for (message mes : list) {
            if (mes.isResolu()) {
                answer ans = ad.trouverReponseIdMessage(mes.getIdMessage());
                mes.setUsernameAnswer(ans.getUsername());
                mes.setReponse(ans.getTxt());
            }
        }
        return list;
    }

}
