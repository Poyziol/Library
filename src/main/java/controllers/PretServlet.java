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

        List<Adherant> adherants = adherantService.listAll();
        List<Exemplaire> exemplairesDisponibles = exemplaireService.listDisponibles();
        List<Pret> prets = pretService.listAll();

        request.setAttribute("adherants", adherants);
        request.setAttribute("exemplairesDisponibles", exemplairesDisponibles);
        request.setAttribute("prets", prets);

        request.setAttribute("today", LocalDate.now().toString());

        request.getRequestDispatcher("/WEB-INF/views/pret.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        PretService pretService = ctx.getBean(PretService.class);

        try {
            String action = request.getParameter("action");
            if ("delete".equals(action)) {
                // Suppression d'un prêt existant
                String sId = request.getParameter("idPret");
                if (sId != null && !sId.isEmpty()) {
                    Integer idPret = Integer.valueOf(sId);
                    pretService.delete(idPret);
                }
            } else {
                Integer idAdherant = Integer.valueOf(request.getParameter("idAdherant"));
                Integer idExemplaire = Integer.valueOf(request.getParameter("idExemplaire"));
                LocalDate datePret = LocalDate.parse(request.getParameter("datePret"));
                LocalDate dateRetourEstime = LocalDate.parse(request.getParameter("dateRetourEstime"));

                pretService.create(idAdherant, idExemplaire, datePret, dateRetourEstime);
            }
        } catch (Exception ex) {
            // Stocker le message d'erreur dans la session
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", ex.getMessage());

            // Rediriger vers la page de prêt en conservant les paramètres
            String referer = request.getHeader("Referer");
            response.sendRedirect(referer != null ? referer : request.getContextPath() + "/pret");
            return;
        }

        // Réinitialiser les messages d'erreur après une opération réussie
        request.getSession().removeAttribute("errorMessage");
        response.sendRedirect(request.getContextPath() + "/pret");
    }
}
