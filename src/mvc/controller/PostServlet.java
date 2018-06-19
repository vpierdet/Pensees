package mvc.controller;

import mvc.dao.DAOFactory;
import mvc.dao.messageDao;
import mvc.model.message;
import mvc.dao.answerDao;
import mvc.model.answer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "PostServlet", urlPatterns = {"/post","/PostServlet"})
public class PostServlet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String SERV_MESADMIN = "/MessageAdminServlet";
    private static final String SERV_NORMAL_USER = "/MessageServlet";
    private static final String VUE_PUBLIER = "/vue/publier.jsp.html";
    private messageDao mdi;
    private answerDao ad;

    public void init() {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.mdi = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getMessageDao();
        this.ad = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getAnswerDao();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int idUser = (int)session.getAttribute("idUser");
        String username = (String)session.getAttribute("username");

        if (request.getParameter("post").equals("message")){
            String textMessage = request.getParameter("user_message");
            String category = request.getParameter("category");
            if (category.equals("Enseignement")) category += " - " + request.getParameter("category2");
            String check = request.getParameter("anonymat");
            System.out.println("ceci est un check : " +check);
            if(check != null) username = "anonyme";
            if((textMessage != null)&&(!textMessage.isEmpty())){
                message mes = new message();
                mes.setUsername(username);
                mes.setText(textMessage);
                mes.setCategories(category);
                mes.setIdUser(idUser);
                mes.setUsername(username);
                this.mdi.ajouter(mes);
                getServletContext().getRequestDispatcher(SERV_NORMAL_USER).forward(request,response);

            }
            else{
                getServletContext().getRequestDispatcher(VUE_PUBLIER).forward(request,response);

            }
        }

        else if (request.getParameter("post").equals("reponse")){
            answer ans = new answer();
            int idMes = Integer.parseInt(request.getParameter("idMes"));
            ans.setUsername(username);
            ans.setIdUser(idUser);
            ans.setTxt(request.getParameter("user_answer"));
            ans.setIdMessage(idMes);
            int idAnswer = this.ad.ajouter(ans);
            this.mdi.Answer(idMes,idAnswer);

            getServletContext().getRequestDispatcher(SERV_MESADMIN).forward(request,response);



        }







    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
