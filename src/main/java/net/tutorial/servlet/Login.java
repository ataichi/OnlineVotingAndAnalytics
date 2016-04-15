package net.tutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.tutorial.service.PostgreSQLClient;

@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
			
			HttpSession session = request.getSession();
			
            String email = (String) request.getParameter("email");
			String password = (String) request.getParameter("password");
            
			PostgreSQLClient client = new PostgreSQLClient();
			if(client.doesVoterExist(email, password)) {
				List<CandidateBean> presidentlist = client.getCandidatesPerPosition(1);
				List<CandidateBean> vicepresidentlist = client.getCandidatesPerPosition(2);
				List<CandidateBean> senatorlist = client.getCandidatesPerPosition(3);
				
				session.setAttribute("presidentlist", presidentlist);
				session.setAttribute("vicepresidentlist", vicepresidentlist);
				session.setAttribute("senatorlist", senatorlist);
				
				List<CandidateBean> ballot = client.getBallotPerUser(email, password);
				session.setAttribute("ballot", ballot);
				
				response.setContentType("text/html");
				response.setStatus(200);
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
			else {
				response.sendRedirect("login.jsp");
			}
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
