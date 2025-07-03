package controllers;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import models.Livre;
import services.LivreService;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/home")
public class LobbyController extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());

        LivreService livreService = ctx.getBean(LivreService.class);

        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        String ageParam = request.getParameter("ageMin");
        Integer ageMin = (ageParam != null && !ageParam.isEmpty()) ? Integer.valueOf(ageParam) : null;

        List<Livre> livres = livreService.filter(titre, auteur, ageMin);
        request.setAttribute("livres", livres);

        request.setAttribute("auteurs", livreService.listAuteurs());
        request.setAttribute("ages",   livreService.listAgeMins());

        request.setAttribute("filterTitre",  titre);
        request.setAttribute("filterAuteur", auteur);
        request.setAttribute("filterAge",    ageMin);

        HttpSession session = request.getSession(false);
        String typeUsers = (session != null) ? (String) session.getAttribute("type") : null;
        request.setAttribute("typeUsers", typeUsers);

        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        doGet(request, response);
    }
}
