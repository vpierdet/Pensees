package mvc.controller;

import mvc.dao.messageDaoImpl;
import mvc.model.message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

@WebServlet(name = "PostServlet")
public class PostServlet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String VUE              = "/WEB-INF/inscription.jsp";
    private messageDaoImpl mdi;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String textMessage = request.getParameter("user_message");
        String category = request.getParameter("category");
        int idUser = (int)session.getAttribute("idUser");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String check = request.getParameter("anonymat");
        String username;
        if(check == null){
            username = (String)session.getAttribute("username");
        }
        else{
            username = "anonyme";
        }


        if((textMessage != null)&&(!textMessage.isEmpty())){
            message mes = new message();
            mes.setText(textMessage);
            mes.setCategories(category);
            mes.setIdUser(idUser);
            mes.setTimestamp(timestamp);
            mes.setUsername(username);
            this.mdi.ajouter(mes);
            getServletContext().getRequestDispatcher("/FileActu").forward(request,response);

        }
        else{
            getServletContext().getRequestDispatcher("/publier_user").forward(request,response);
            String someMessage = "Erreur, votre post est vide, veuillez recommencer !";
            PrintWriter out = response.getWriter();
            out.print("<script type=\"text/javascript\">alert(" + someMessage + ");</script>");

        }





    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
