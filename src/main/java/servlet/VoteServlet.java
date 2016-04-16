package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import bean.CandidateBean;
import service.PostgreSQLClient;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import service.*;

@WebServlet(urlPatterns = {"/VoteServlet"})
public class VoteServlet extends HttpServlet {

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
<<<<<<< HEAD
			
			HttpSession session = request.getSession();
			
=======
			HttpSession session = request.getSession();
>>>>>>> ffb9085b98495c2361ea44a21f718834ed539cae
			//get selected candidate
            String pres = (String) request.getParameter("selectpres");
			String vicepres = (String) request.getParameter("selectvicepres");
			String sen = (String) request.getParameter("selectsen");
            
			//retrieve candidatelist from postgre
			PostgreSQLClient client = new PostgreSQLClient();
<<<<<<< HEAD
			List<CandidateBean> presidentlist = client.getCandidatesPerPosition(1);
			List<CandidateBean> vicepresidentlist = client.getCandidatesPerPosition(2);
			List<CandidateBean> senatorlist = client.getCandidatesPerPosition(3);
=======
			List<CandidateBean> presidentlist = client.getPresidentCandidates(1);
			List<CandidateBean> vicepresidentlist = client.getPresidentCandidates(2);
			List<CandidateBean> senatorlist = client.getPresidentCandidates(3);
>>>>>>> ffb9085b98495c2361ea44a21f718834ed539cae
			
			//vote
			for(int i=0; i<presidentlist.size(); i++) {
				if(pres.matches(Integer.toString(i++))) { //selected president
					client.voteForCandidate(presidentlist.get(i).getCandidateID());
				}
			}
			
			for(int i=0; i<vicepresidentlist.size(); i++) {
				if(vicepres.matches(Integer.toString(i++))) { //selected vice president
					client.voteForCandidate(vicepresidentlist.get(i).getCandidateID());
				}
			}
			
			for(int i=0; i<senatorlist.size(); i++) {
				if(sen.matches(Integer.toString(i++))) { //selected senators 
					
				}
			}
			
			//Retrieve Selected Candidates
			List<CandidateBean> ballot = client.getBallotPerUser(email, password);
			session.setAttribute("ballot", ballot);
			
			response.setContentType("text/html");
			response.setStatus(200);
			request.getRequestDispatcher("home.jsp").forward(request, response);
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
