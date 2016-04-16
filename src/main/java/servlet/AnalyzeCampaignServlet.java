package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.TradeoffAnalytics;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.Dilemma;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.Option;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.Problem;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.column.Column;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.column.Column.Goal;
import com.ibm.watson.developer_cloud.tradeoff_analytics.v1.model.column.NumericColumn;

import service.TradeoffAnalyticsConnector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = {"/AnalyzeCampaignServlet"})
public class AnalyzeCampaignServlet extends HttpServlet {

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
			
			TradeoffAnalyticsConnector connector = new TradeoffAnalyticsConnector();
			TradeoffAnalytics service = new TradeoffAnalytics();
			service.setUsernameAndPassword(connector.getUsername(), connector.getPassword());
			
			Problem problem = new Problem("campaign");
			
			String registeredvoters = "registeredvoters";
			String votes = "votes";
			String budget = "budget";
			
			List<Column> columns = new ArrayList<Column>();
			problem.setColumns(columns);
			
			columns.add(new NumericColumn().withKey(registeredvoters).withGoal(Goal.MAX));
			columns.add(new NumericColumn().withKey(votes).withGoal(Goal.MIN).withObjective(true));
			columns.add(new NumericColumn().withKey(budget).withGoal(Goal.MIN).withObjective(true));
			
			List<Option> option = new ArrayList<Option>();
			problem.setOptions(option);
			
			HashMap<String, Object> NCR = new HashMap<String, Object>();
			NCR.put(registeredvoters, 6253249);
			NCR.put(votes, 1188117);
			NCR.put(budget, 62532490);
			option.add(new Option("1", "NCR").withValues(NCR));
			
			HashMap<String, Object> CAR = new HashMap<String, Object>();
			CAR.put(registeredvoters, 906162);
			CAR.put(votes, 172170);
			CAR.put(budget, 9061620);
			option.add(new Option("2", "CAR").withValues(CAR));
			
			HashMap<String, Object> Region1 = new HashMap<String, Object>();
			Region1.put(registeredvoters, 2950775);
			Region1.put(votes, 560647);
			Region1.put(budget, 29507750);
			option.add(new Option("3", "Region1").withValues(Region1));
			
			HashMap<String, Object> Region2 = new HashMap<String, Object>();
			Region2.put(registeredvoters, 6253249);
			Region2.put(votes, 364980);
			Region2.put(budget, 19209520);
			option.add(new Option("4", "Region2").withValues(Region2));
			
			HashMap<String, Object> Region3 = new HashMap<String, Object>();
			Region3.put(registeredvoters, 6055869);
			Region3.put(votes, 1150615);
			Region3.put(budget, 60558690);
			option.add(new Option("5", "Region3").withValues(Region3));
			
			HashMap<String, Object> Region4a = new HashMap<String, Object>();
			Region4a.put(registeredvoters, 7619278);
			Region4a.put(votes, 14447663);
			Region4a.put(budget, 76192780);
			option.add(new Option("6", "Region4a").withValues(Region4a));
			
			HashMap<String, Object> Region4b = new HashMap<String, Object>();
			Region4b.put(registeredvoters, 1589326);
			Region4b.put(votes, 301971);
			Region4b.put(budget, 15893260);
			option.add(new Option("7", "Region4b").withValues(Region4b));
			
			HashMap<String, Object> Region5 = new HashMap<String, Object>();
			Region5.put(registeredvoters, 3121661);
			Region5.put(votes, 593115);
			Region5.put(budget, 31216610);
			option.add(new Option("8", "Region5").withValues(Region5));
			
			HashMap<String, Object> Region6 = new HashMap<String, Object>();
			Region6.put(registeredvoters, 4242153);
			Region6.put(votes, 806009);
			Region6.put(budget, 42421530);
			option.add(new Option("9", "Region6").withValues(Region6));
			
			HashMap<String, Object> Region7 = new HashMap<String, Object>();
			Region7.put(registeredvoters, 4375756);
			Region7.put(votes, 831393);
			Region7.put(budget, 43757560);
			option.add(new Option("10", "Region7").withValues(Region7));
			
			HashMap<String, Object> Region8 = new HashMap<String, Object>();
			Region8.put(registeredvoters, 2698883);
			Region8.put(votes, 512787);
			Region8.put(budget, 26988830);
			option.add(new Option("11", "Region9").withValues(Region8));
			
			HashMap<String, Object> Region9 = new HashMap<String, Object>();
			Region9.put(registeredvoters, 1931795);
			Region9.put(votes, 367041);
			Region9.put(budget, 19317950);
			option.add(new Option("12", "Region9").withValues(Region9));
			
			HashMap<String, Object> Region10 = new HashMap<String, Object>();
			Region10.put(registeredvoters, 2541331);
			Region10.put(votes, 482852);
			Region10.put(budget, 25413310);
			option.add(new Option("13", "Region10").withValues(Region10));
			
			HashMap<String, Object> Region11 = new HashMap<String, Object>();
			Region11.put(registeredvoters, 2659704);
			Region11.put(votes, 505343);
			Region11.put(budget, 26597040);
			option.add(new Option("14", "Region11").withValues(Region11));
			
			HashMap<String, Object> Region12 = new HashMap<String, Object>();
			Region12.put(registeredvoters, 2086112);
			Region12.put(votes, 396361);
			Region12.put(budget, 20861120);
			option.add(new Option("15", "Region12").withValues(Region12));
			
			HashMap<String, Object> CARAGA = new HashMap<String, Object>();
			CARAGA.put(registeredvoters, 1547093);
			CARAGA.put(votes, 293947);
			CARAGA.put(budget, 15470930);
			option.add(new Option("16", "CARAGA").withValues(CARAGA));
			
			HashMap<String, Object> ARMM = new HashMap<String, Object>();
			ARMM.put(registeredvoters, 1863230);
			ARMM.put(votes, 354013);
			ARMM.put(budget, 18632300);
			option.add(new Option("17", "ARMM").withValues(ARMM));
			
			Dilemma dilemma = service.dilemmas(problem);
			
			out.println("<html>");
        	out.println("<head>");
            out.println("<title>Results</title>");
       		out.println("</head>");
           	out.println("<body>");
			
			String jsonstring = dilemma.toString();
			JSONParser parser = new JSONParser();
            Object obj = parser.parse(sample);
            JSONObject json1 = (JSONObject) obj;
            JSONObject json2 = (JSONObject) json1.get("resolution");
            JSONArray json3 = (JSONArray) json2.get("solutions");
            
            JSONObject json4;
            String region, status;
            for(int i=0; i<json3.size(); i++) {
                json4 = (JSONObject) json3.get(i);
                region = json4.get("solution_ref").toString();
                status = json4.get("status").toString();
                
                System.out.println(region);
                System.out.println(status);
            }
			
			if(region.matches("1")) {
				out.println("<h2>You need to focus on NCR.");
			} else if(region.matches("2")){
				out.println("<h2>You need to focus on CAR</h2>");
			} else if(region.matches("3")){
				out.println("<h2>You need to focus on Region I</h2>");
			}
			
       		out.println("</body>");
       		out.println("</html>");
			
			//request.setAttribute("dilemma", dilemma);
			
			//response.setContentType("text/html");
			//response.setStatus(200);
			//request.getRequestDispatcher("analyze.jsp").forward(request, response);
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
