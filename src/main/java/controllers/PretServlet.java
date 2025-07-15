package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Adherant;
import models.Exemplaire;
import models.Pret;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import services.AdherantService;
import services.ExemplaireService;
import services.PretService;
import services.TypePretService;

@WebServlet("/pret")
public class PretServlet extends HttpServlet 
{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());

        AdherantService adherantService = ctx.getBean(AdherantService.class);
        ExemplaireService exemplaireService = ctx.getBean(ExemplaireService.class);
        PretService pretService = ctx.getBean(PretService.class);
        TypePretService typePretService = ctx.getBean(TypePretService.class);

        List<Adherant> adherants = adherantService.listAll();
        List<Exemplaire> exemplairesDisponibles = exemplaireService.listDisponibles();
        List<Pret> prets = pretService.listAll();

        String dateMin = request.getParameter("dateMin");
        String dateMax = request.getParameter("dateMax");

        request.setAttribute("adherants", adherants);
        request.setAttribute("exemplairesDisponibles", exemplairesDisponibles);
        request.setAttribute("prets", prets);
        List<Pret> historique = pretService.getHistoriqueRetours(dateMin, dateMax);
        request.setAttribute("historique", historique);
        request.setAttribute("typesPret", typePretService.listAll());

        request.setAttribute("today", LocalDate.now().toString());

        request.getRequestDispatcher("/WEB-INF/views/pret.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        WebApplicationContext ctx = WebApplicationContextUtils
            .getRequiredWebApplicationContext(getServletContext());
        PretService pretService = ctx.getBean(PretService.class);
            
        try {
            String action = request.getParameter("action");
            if ("delete".equals(action)) {
                // En fait : retourner le livre (pas supprimer)
                Integer idPret = Integer.valueOf(request.getParameter("idPret"));
                LocalDate dateRetour = LocalDate.parse(request.getParameter("dateRetour"));
                pretService.returnBook(idPret, dateRetour);
            } else {
                // création normale
                Integer idAdherant     = Integer.valueOf(request.getParameter("idAdherant"));
                Integer idExemplaire   = Integer.valueOf(request.getParameter("idExemplaire"));
                LocalDate datePret     = LocalDate.parse(request.getParameter("datePret"));
                LocalDate dateRetourEstime = LocalDate.parse(request.getParameter("dateRetourEstime"));
                pretService.create(idAdherant, idExemplaire, datePret, dateRetourEstime);
            }
        } catch (Exception ex) {
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", ex.getMessage());
            response.sendRedirect(request.getHeader("Referer"));
            return;
        }
    
        request.getSession().removeAttribute("errorMessage");
        response.sendRedirect(request.getContextPath() + "/pret");
    }
}
