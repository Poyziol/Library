package controllers;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Exemplaire;
import services.ExemplaireService;
import services.ReservationService;

@WebServlet("/reservation-client")
public class ReservationClientServlet extends HttpServlet 
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        // Récupérer les exemplaires disponibles
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        ExemplaireService exemplaireService = ctx.getBean(ExemplaireService.class);
        
        List<Exemplaire> exemplaires = exemplaireService.listDisponibles();
        request.setAttribute("exemplaires", exemplaires);
        
        request.getRequestDispatcher("/WEB-INF/views/reservationClient.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        Integer adherantId = (Integer) session.getAttribute("idAdherant");
        String exemplaireId = request.getParameter("exemplaireId");
        LocalDate dateReservation = LocalDate.parse(request.getParameter("dateReservation"));
        
        if(adherantId == null) 
        {
            session.setAttribute("errorMessage", "Vous devez être connecté pour réserver");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        ReservationService reservationService = ctx.getBean(ReservationService.class);
        
        try 
        {
            reservationService.creerReservation(Integer.parseInt(exemplaireId), adherantId, dateReservation);
            session.setAttribute("successMessage", "Réservation effectuée avec succès! Attendez la confirmation.");
        } 
        catch (Exception e) 
        {
            session.setAttribute("errorMessage", "Erreur: " + e.getMessage());
        }
        
        response.sendRedirect(request.getContextPath() + "/reservation-client");
    }
}