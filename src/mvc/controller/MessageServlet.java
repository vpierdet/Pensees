package mvc.controller;

import mvc.dao.DAOFactory;
import mvc.dao.messageDao;
import mvc.model.message;
import mvc.dao.etatMessageDao;
import mvc.model.etatMessage;
import mvc.dao.answerDao;
import mvc.model.answer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MessageServlet", urlPatterns = {"/MessageServlet"})
public class MessageServlet extends HttpServlet {
    private static final String CONF_DAO_FACTORY = "daofactory";
    private static final String VUE_NORMAL_USER = "/FileActu.jsp";
    private static final String VUE_REP = "/FileReponse.jsp";
    private static final String VUE_ADMIN = "/FileActuAdmin.jsp";

    private messageDao md;
    private etatMessageDao emd;
    private answerDao ad;

    public void init() {
        this.md = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getMessageDao();
        this.emd = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getEtatMessageDAO();
        this.ad = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getAnswerDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<message> listeMessage = new ArrayList<>();
        String triPara;
        HttpSession session = request.getSession();
        /**
         * Récupération du mode de tri
         */
        if (request.getParameter("tri") == null) request.setAttribute("tri", "Pertinence");
        else request.setAttribute("tri", request.getParameter("tri"));

        if(request.getParameter("tri") != null)triPara = request.getParameter("tri");
        else triPara = (String) request.getAttribute("tri");

        /**
         * Gestion Page
         */
        int debut = request.getParameter("bouton_page") != null ? Integer.parseInt(request.getParameter("bouton_page")) : 0;
        int fin = debut + 9;
        int nombreMessages = md.Count(triPara.replace("Cat_",""), triPara.contains("Cat"));
        if (debut == 0) request.setAttribute("page", 1);
        request.setAttribute("debut" , debut);
        request.setAttribute("nbrMessage", nombreMessages);

        /**
         * Recuperation message
         */
        if(!triPara.equals("")) {
            triPara = triPara.replace("_", " ");

            switch (triPara) {
                case "Date":
                    listeMessage = md.trouverMessagesDate(debut, fin);
                    break;

                case "Pertinence":
                    listeMessage = md.trouverMessagesPertinence(debut, fin);
                    break;

                default:
                    if (triPara.contains("Cat ")) {
                        triPara = triPara.substring(4);
                        listeMessage = md.trouverMessagesCategorie(triPara, debut, fin);
                    } else listeMessage = md.trouverMessagesPertinence(debut, fin);
                    break;

            }
        }
        if (listeMessage == null){
            listeMessage = md.trouverMessagesPertinence(debut, fin);
        }

        /**
         * Recuperation etat personnel et les réponses du messages
         */
        listeMessage = getEtat(listeMessage, request);
        listeMessage = getResponse(listeMessage);

        /**
         * Renvoi sur le file d'actu
         */

        request.setAttribute("listeMessage", listeMessage);

        int userType = (Integer)session.getAttribute("userType");

        switch (userType){
            case 0 : getServletContext().getRequestDispatcher(VUE_NORMAL_USER).forward(request, response); break;
            case 1 : getServletContext().getRequestDispatcher(VUE_NORMAL_USER).forward(request, response); break;
            case 2 : getServletContext().getRequestDispatcher(VUE_ADMIN).forward(request, response); break;
            default:break;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
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
