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
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MessageServlet", urlPatterns = {"/MessageServlet"})
public class MessageServlet extends HttpServlet {
    private static final String CONF_DAO_FACTORY = "daofactory";
    private messageDao md;
    private etatMessageDao emd;
    private answerDao ad;

    public void init() {
        this.md = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getMessageDao();
        this.emd = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getEtatMessageDAO();
        this.ad = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getAnswerDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("tri") == null) request.setAttribute("tri", "Pertinence");
        else request.setAttribute("tri", request.getParameter("tri"));
        ArrayList<message> listeMessage = new ArrayList<>();
        int debut = 0;
        int fin = 10;
        System.out.println("attr :" + request.getAttribute("tri") + ", Param : " + request.getParameter("tri"));

        if(request.getParameter("tri") != null) {
            String tri = request.getParameter("tri");
            tri = tri.replace("_", " ");

            switch (tri) {
                case "Date":
                    listeMessage = md.trouverMessagesDate(debut, fin);
                    break;

                case "Pertinence":
                    listeMessage = md.trouverMessagesPertinence(debut, fin);
                    break;

                default:
                    if (tri.contains("Cat ")) {
                        tri = tri.substring(4);
                        listeMessage = md.trouverMessagesCategorie(tri, debut, fin);
                    } else listeMessage = md.trouverMessagesPertinence(debut, fin);
                    break;

            }
        }
        if (listeMessage == null){
            listeMessage = md.trouverMessagesPertinence(debut, fin);
        }

        listeMessage = getEtat(listeMessage, request);
        listeMessage = getResponse(listeMessage);


        request.setAttribute("listeMessage", listeMessage);
        getServletContext().getRequestDispatcher("/FileActu.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
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
