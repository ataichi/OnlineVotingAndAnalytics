<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <title> Candidate Statistics </title>
    </head>
    <body>
		<%
			<form method="post" action="AnalyzeCampaignServlet">
			<table>
				<tr>
					<td>
						<h2>Region</h2>
					</td>
					<td>
						<h2>Registered Voters</h2>
					</td>
					<td>
						<h2>Votes</h2>
					</td>
					<td>
						<h2>Budget</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>National Capital Region</h2>
					</td>
					<td>
						<h2>6,253,249</h2>
					</td>
					<td>
						<h2>1,188,117</h2>
					</td>
					<td>
						<h2>62,532,490</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>Cordillera Administrative Region</h2>
					</td>
					<td>
						<h2>906,162</h2>
					</td>
					<td>
						<h2>172,170</h2>
					</td>
					<td>
						<h2>9,061,620</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>Region I - Ilocos Region</h2>
					</td>
					<td>
						<h2>2,950,775</h2>
					</td>
					<td>
						<h2>560,647</h2>
					</td>
					<td>
						<h2>29,507,750</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>Region II - Cagayan Valley</h2>
					</td>
					<td>
						<h2>1,920,952</h2>
					</td>
					<td>
						<h2>364,980</h2>
					</td>
					<td>
						<h2>19,209,520</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>Region III - Central Luzon</h2>
					</td>
					<td>
						<h2>6,055,869</h2>
					</td>
					<td>
						<h2>1,150,615</h2>
					</td>
					<td>
						<h2>60,558,690</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>Region IV-A - Calabarzon</h2>
					</td>
					<td>
						<h2>7,619,278</h2>
					</td>
					<td>
						<h2>1,447,663</h2>
					</td>
					<td>
						<h2>76,192,780</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>Region IV-B - Mimaropa</h2>
					</td>
					<td>
						<h2>1,589,326</h2>
					</td>
					<td>
						<h2>301,971</h2>
					</td>
					<td>
						<h2>15,893,260</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>Region V - Bicol Region</h2>
					</td>
					<td>
						<h2>3,121,661</h2>
					</td>
					<td>
						<h2>593,115</h2>
					</td>
					<td>
						<h2>31,216,610</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>Region VI - Western Visayas</h2>
					</td>
					<td>
						<h2>4,242,153</h2>
					</td>
					<td>
						<h2>806,009</h2>
					</td>
					<td>
						<h2>42,421,530</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>Region VII - Central Visayas</h2>
					</td>
					<td>
						<h2>4,375,756</h2>
					</td>
					<td>
						<h2>831,393</h2>
					</td>
					<td>
						<h2>43,757,560</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>Region VIII - Eastern Visayas</h2>
					</td>
					<td>
						<h2>2,698,883</h2>
					</td>
					<td>
						<h2>512,787</h2>
					</td>
					<td>
						<h2>26,988,830</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>Region IX - Zamboanga Peninsula</h2>
					</td>
					<td>
						<h2>1,931,795</h2>
					</td>
					<td>
						<h2>367,041</h2>
					</td>
					<td>
						<h2>19,317,950</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>Region X - Northern Mindanao</h2>
					</td>
					<td>
						<h2>2,541,331</h2>
					</td>
					<td>
						<h2>482,852</h2>
					</td>
					<td>
						<h2>25,413,310</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>Region XI - Davao Region</h2>
					</td>
					<td>
						<h2>2,659,704</h2>
					</td>
					<td>
						<h2>505,343</h2>
					</td>
					<td>
						<h2>26,597,040</h2>
					</td>
				</tr><tr>
					<td>
						<h2>Region XII - Soccsksargen</h2>
					</td>
					<td>
						<h2>2,086,112</h2>
					</td>
					<td>
						<h2>396,361</h2>
					</td>
					<td>
						<h2>20,861,120</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>Caraga Region</h2>
					</td>
					<td>
						<h2>1,547,093</h2>
					</td>
					<td>
						<h2>293,947</h2>
					</td>
					<td>
						<h2>15,470,930</h2>
					</td>
				</tr>
				<tr>
					<td>
						<h2>Autonomous Region in Muslim Mindanao</h2>
					</td>
					<td>
						<h2>1,863,230</h2>
					</td>
					<td>
						<h2>354,013</h2>
					</td>
					<td>
						<h2>18,632,300</h2>
					</td>
				</tr>
			</table>
            <input type="submit" name="analyze" value="Analyze" />
        </form>
		
	<% 
		if(request.getAttribute("dilemma")!=null) {
			out.println("<h1>" + (String) request.getAttribute("dilemma") + "</h1>");
		}
	%>
		
		%>
    </body>
</html>