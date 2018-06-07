package mvc.controller;

import mvc.mail.Mailer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.mail.internet.*;
import javax.mail.*;
import java.util.*;

@WebServlet(name = "MailServlet", urlPatterns = {"/MailServlet"})
public class MailServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageName = request.getParameter("pageName");

        if(pageName.equals("signal")){
            String reason = request.getParameter("reason");
            String nameBan = request.getParameter("usernameBan");
            String text = request.getParameter("user_message");
            String finalMessage = writeMessage(reason, nameBan, text);
            try{
            Mailer.send("matthiasdeconninck@hotmail.fr", "Signalement", finalMessage);}
            catch(java.lang.NoClassDefFoundError  e){
                System.out.println(e);
                e.printStackTrace();
            }

        }
        else if(pageName.equals("post")){

        }
        else{

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected String writeMessage(String reason, String user, String message){

        String finalMessage = " The User " + user + " has been reported for " + reason + ". The informations given are : " + message + "";
        return finalMessage;
    }


}
