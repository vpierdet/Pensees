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

@WebServlet(name = "MessageAdminServlet", urlPatterns = {"/mas","/MessageAdminServlet"})
public class MessageAdminServlet extends HttpServlet {

    private static final String CONF_DAO_FACTORY = "daofactory";
    private static final String VUE_FILEREP = "/vue/FileReponse.jsp";
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

        HttpSession session = request.getSession();
         int idUser = (Integer) session.getAttribute("idUser");
        ArrayList<message> listeMessage = new ArrayList<>();
        categorie cat = cd.trouverUser(idUser);
        String tri = request.getParameter("tri") != null ? request.getParameter("tri") : "date" ;
        String catS = cat.getNom();

        /**
         * Gestion page
         */
        int debut = request.getParameter("bouton_page") != null ? Integer.parseInt(request.getParameter("bouton_page")) : 0;
        int fin = debut + 9;
        int nombreMessages;
        if (tri.equals("date") || tri.equals("pertinence"))
            nombreMessages = md.Count(cat.getNom(),true);
        else{
            boolean repondu = tri.equals("repondu") ? true : false;
            nombreMessages = md.Count(cat.getNom(),true, repondu);
        }
        if (debut == 0) request.setAttribute("page", 1);
        request.setAttribute("debut" , debut);
        request.setAttribute("nbrMessage", nombreMessages);

        /**
         * Récupération messages
         */
        if (tri.equals("date")) listeMessage = md.trouverMessagesCategorie(catS,debut,fin);
        if (tri.equals("pertinence")) listeMessage = md.trouverMessagesCategoriePertinence(catS,debut, fin);
        if (tri.equals("repondu")) listeMessage = md.trouverMessagesCategorieRepondu(catS, debut ,fin, true);
        if (tri.equals("nonrep")) listeMessage = md.trouverMessagesCategorieRepondu(catS, debut ,fin, false);

        /**
         * Récupérations autres infos messages
         */
        listeMessage = getEtat(listeMessage, request);
        listeMessage = getResponse(listeMessage);

        /**
         * Renvoi sur la vue
         */
        System.out.println("nombre message : " + nombreMessages + "  methode tri : " + tri);
        request.setAttribute("tri", tri);
        request.setAttribute("listeMessage", listeMessage);

        getServletContext().getRequestDispatcher(VUE_FILEREP).forward(request, response);

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

        getServletContext().getRequestDispatcher(VUE_FILEREP).forward(request, response);

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
            if (mes.getIdReponse() != -1) {
                answer ans = ad.trouverReponseIdMessage(mes.getIdMessage());
                mes.setUsernameAnswer(ans.getUsername());
                mes.setReponse(ans.getTxt());
            }
        }
        return list;
    }

}
