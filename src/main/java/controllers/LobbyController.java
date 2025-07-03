package controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/home")
public class LobbyController extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        HttpSession session = request.getSession(false);
        String typeUsers = (session != null) ? (String) session.getAttribute("type") : null;

        if (typeUsers == null) 
        {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setAttribute("typeUsers", typeUsers);
        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        doGet(request, response);
    }
}
