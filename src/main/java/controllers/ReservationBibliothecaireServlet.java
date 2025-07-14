package controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import DTO.ReservationDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.PretService;
import services.ReservationService;

@WebServlet("/reservation-biblio")
public class ReservationBibliothecaireServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        ReservationService reservationService = ctx.getBean(ReservationService.class);
        
        // Utilisez la nouvelle méthode
        List<ReservationDTO> reservations = reservationService.getReservationsEnAttenteWithExemplaires();
        request.setAttribute("reservations", reservations);
        
        request.getRequestDispatcher("/WEB-INF/views/reservationBibliothecaire.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        String reservationId = request.getParameter("reservationId");
        Integer adherantId = Integer.parseInt(request.getParameter("adherantId"));

        HttpSession session = request.getSession();
        
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        ReservationService reservationService = ctx.getBean(ReservationService.class);
        PretService pretService = ctx.getBean(PretService.class);
        
        int quota_actuel = pretService.getQuotaActuel(adherantId);

        try {
            reservationService.confirmerReservation(Integer.parseInt(reservationId), quota_actuel);
            session.setAttribute("successMessage", "Réservation confirmée et prêt créé!");
        } catch (Exception e) {
            session.setAttribute("errorMessage", "Erreur: " + e.getMessage());
        }
        
        response.sendRedirect(request.getContextPath() + "/reservation-biblio");
    }
}