import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.* ;

@WebServlet(name="essai",  urlPatterns={"/login"})
public class Servletessai extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Chargement du driver JDBC pour MySQL */
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            /* Gérer les éventuelles erreurs ici. */
        }
        String url ="jdbc:mysql://localhost:3306/testjdbc";
        String utilisateur = "java";
        String motDePasse = "essai";
        Connection connexion = null;

        try {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );

            /* Ici, nous placerons nos requêtes vers la BDD */
            /* ... */

        } catch ( SQLException e ) {
            /* Gérer les éventuelles erreurs ici */
        } finally {
            if ( connexion != null )
                try {
                    /* Fermeture de la connexion */
                    connexion.close();
                } catch ( SQLException ignore ) {
                    /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
                }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch ( ClassNotFoundException e ) {
            /* Gérer les éventuelles erreurs ici. */
        }
        String url ="jdbc:mysql://localhost:3306/testjdbc";
        String utilisateur = "java";
        String motDePasse = "essai";
        Connection connexion = null;

        try {
            connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
            out.print("yes    \n");
            /* Création de l'objet gérant les requêtes */

            Statement statement = connexion.createStatement();
            /* Exécution d'une requête de lecture */

            ResultSet resultat = statement.executeQuery( "SELECT * FROM testjdbc.Message;" );
            resultat.next();
             String txt = resultat.getString("txt");
            out.print(txt);


        } catch ( SQLException e ) {
            out.print("erreur");
        } finally {
            if ( connexion != null )
                try {
                    /* Fermeture de la connexion */
                    connexion.close();
                } catch ( SQLException ignore ) {
                    /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
                }
        }
    }
}
