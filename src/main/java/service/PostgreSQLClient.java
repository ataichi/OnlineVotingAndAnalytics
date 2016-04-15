package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.CandidateBean;
import bean.EducationalBGBean;

public class PostgreSQLClient {

    public PostgreSQLClient() {
        try {
            createTables();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

	public 
	
	public String getPoliticalParty(int candidateID) throws Exception {
		String selectquery = "SELECT pp.PoliticalPartyName FROM politicalparty pp, electionlist el, candidate c WHERE c.CandidateID = el.ElectionList and el.PoliticalPartyID = pp.PoliticalPartyID and c.candidateID = '" + candidateID +"';";
		Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		try
		{
            ps = connection.prepareStatement(selectquery);
            rs = ps.executeQuery();
            while (rs.next()) {
             	return rs.getString(1);
            }
            connection.close();
		}
	}
	
	public String getPositionOfCandidate(int candidateID) throws Exception {
		String selectquery = "SELECT p.PositionName FROM position p, candidate c, electionlist el WHERE c.CandidateID = el.CandidateID and el.PositionID = p.PositionID and c.CandidateID = '" + candidateID + "';";
		Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		try
		{
            ps = connection.prepareStatement(selectquery);
            rs = ps.executeQuery();
            while (rs.next()) {
             	return rs.getString(1);
            }
            connection.close();
		}
	}
	
	public List<CandidateBean> getCandidatesPerPosition(int positionID) throws Exception {
		String selectquery = "SELECT * FROM candidate c, electionlist el WHERE c.ElectionListID = el.ElectionListID and el.PositionID = '" + positionID + "';";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
		try {
            connection = getConnection();
            statement = connection.prepareStatement(selectquery);
            rs = statement.executeQuery();

            CandidateBean candidate = new CandidateBean();
			//candidate.setTheresareturnedvalue(0);
            if ( rs.next() ) {
                candidate.setFirstName(rs.getString(1));
				candidate.setMiddleName(rs.getString(2));
				candidate.setLastName(rs.getString(3));
				candidate.setNickname(rs.getString(4));
				candidate.setEducationalBGID(rs.getString(8));
				//candidate.setTheresareturnedvalue(1);
            }
            return candidate;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
	}

    public boolean doesVoterExist(String email, String password) throws Exception {
        String selectquery = "SELECT * FROM voter WHERE EmailAddress = '" + email + "' and Password = '" + password + "';";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		try
		{
            ps = connection.prepareStatement(selectquery);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            connection.close();
        } catch (Exception ex) {
            
        }
        return false;
    }
	
	public boolean doesCandidateExist(String email, String password)throws Exception {
        String selectquery = "SELECT * FROM candidate WHERE EmailAddress = '" + email + "' and Password = '" + password + "';";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		try
		{
            ps = connection.prepareStatement(selectquery);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
            connection.close();
        } catch (Exception ex) {
            
        }
        return false;
    }
	
	public CandidateBean getCandidate(int candidateID) {
		String selectquery = "SELECT * FROM candidate c WHERE c.CandidateID";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
		try {
            connection = getConnection();
            statement = connection.prepareStatement(selectquery);
            rs = statement.executeQuery();

            CandidateBean candidate = new CandidateBean();
            if ( rs.next() ) {
				candidate.setCandidateID(rs.getInt("CandidateID"));
                candidate.setFirstName(rs.getString("FirstName"));
				candidate.setMiddleName(rs.getString("MiddleName"));
				candidate.setLastName(rs.getString("LastName"));
				candidate.setNickname(rs.getString("Nickname"));
				candidate.setBirthday(rs.getDate("Birthday"));
				candidate.setBirthplace(rs.getString("Birthplace"));
				candidate.setGender(rs.getString("Gender"));
				candidate.setElectionListID(rs.getInt("ElectionListID"));
            }
            return candidate;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
	}
	
	public List<EducationalBGBean> getEducBGPerCandidate(int candidateID) {
		
	}
	
	public boolean voteForCandidate(int CandidateID) {
		
	}
	
	public List<CandidateBean> getVotedCandidatesPerUser(String email, String password) {
		
	}

    public static Connection getConnection() throws Exception {
        Map<String, String> env = System.getenv();
        if (env.containsKey("VCAP_SERVICES")) {
            JSONParser parser = new JSONParser();
            JSONObject vcap = (JSONObject) parser.parse(env.get("VCAP_SERVICES"));
            JSONObject service = null;
            for (Object key : vcap.keySet()) {
                String keyStr = (String) key;
                if (keyStr.toLowerCase().contains("postgresql")) {
                    service = (JSONObject) ((JSONArray) vcap.get(keyStr)).get(0);
                    break;
                }
            }
            if (service != null) {
                JSONObject creds = (JSONObject) service.get("credentials");
                String name = (String) creds.get("name");
                String host = (String) creds.get("host");
                Long port = (Long) creds.get("port");
                String user = (String) creds.get("user");
                String password = (String) creds.get("password");
                String url = "jdbc:postgresql://" + host + ":" + port + "/" + name;
                return DriverManager.getConnection(url, user, password);
            }
        }
        throw new Exception("No PostgreSQL binded with your app.");
    }

     private void createTables() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        String createquery1 = "CREATE TABLE electiondate (" +  
"ElectionDateID INT NOT NULL PRIMARY KEY," +
  "ElectionDate date NOT NULL" +
");";
		String insertquery1 = "INSERT INTO electiondate(ElectionDateID, ElectionDate) VALUES (1,'2016-05-09');";
		
		String createquery2 = "CREATE TABLE position (" +
  "PositionID INT NOT NULL PRIMARY KEY, " +
  "PositionName varchar(45) NOT NULL" +
");";
		String insertquery2 = "INSERT INTO position(PositionID, PositionName) VALUES (1,'President'),(2,'Vice President'),(3,'Senator');";
  
		String createquery3 = "CREATE TABLE educationalbglevel ("+
  "EducationalBGLevelID INT NOT NULL PRIMARY KEY,"+
  "LevelName varchar(100) NOT NULL"+
"); ";

		String insertquery3 = "INSERT INTO educationalbglevel(EducationalBGLevelID, LevelName) VALUES (1,'Elementary'),(2,'Secondary'),(3,'College'),(4,'Graduate Studies');";

		String createquery4 = "CREATE TABLE `educationalbg` ( "+
  "EducationalBGID INT NOT NULL PRIMARY KEY,"+
  "CandidateID INT NOT NULL,"+
  "EducationalBGLevelID INT NOT NULL,"+
  "SchoolName varchar(100) NOT NULL,"+
  "FromYear year(4) DEFAULT NULL,"+
  "ToYear year(4) DEFAULT NULL,"+
  "AcademicHonors varchar(100));";
  
		String insertquery4 = "INSERT INTO educationalbg(EducationalBGID, CandidateID, EducationalBGLevelID, SchoolName, FromYear, ToYear, AcademicHonors) VALUES (1,1,1,'Philippine Normal College, Training Department',NULL,NULL,NULL),(2,1,2,'University of the Philippines Preparatory High School',NULL,NULL,NULL),(3,1,3,'University of the Philippines',NULL,1962,NULL),(4,1,4,'University of the Philippines',NULL,NULL,'with honors'),(5,1,4,'Palawan State University',NULL,2012,NULL),(6,2,1,'La Paz Elementary School',1951,1957,'Valedictorian'),(7,2,2,'Iloilo Provincial National High School',1957,1961,'Valedictorian; All-Around Girl Medallion Awardee'),(8,2,3,'Bachelor of Arts in Political Science, UP Iloilo',1961,1965,'Magna Cum Laude'),(9,2,4,'Bachelor of Laws, UP Diliman',1965,1969,'Cum Laude'),(10,2,4,'Master of Arts in Religious Studies (without thesis), Maryhill School of Theology, Quezon City',NULL,1996,''),(11,2,4,'Master of Laws (DeWitt Fellow), University of Michigan',NULL,1975,'Finished with \"A\" average'),(12,2,4,'Doctor of Judicial Science (Barbour Scholar and DeWitt Fellow), University of Michigan',NULL,1976,'Requirements fulfilled in 6 months, with \"A\" average'),(13,3,1,'Sta. Ana Elementary School',NULL,1956,'Graduate'),(14,3,2,'Holy Cross of Digos',NULL,1966,'Graduate'),(15,3,3,'Lyceum of the Philippines University',NULL,1968,'Graduate'),(16,3,4,'San Beda College',NULL,1972,'LLB'),(17,4,1,'St. Paul College Pasig',1975,1982,'Leadership Award'),(18,4,2,'Assumption College',1982,1986,NULL),(19,4,3,'University of the Philippines',1986,1988,'College Scholar'),(20,4,3,'Boston College',1988,1991,NULL),(21,5,1,'Ateneo de Manila University',1962,1970,NULL),(22,5,2,'Ateneo de Manila University',1970,1974,NULL),(23,5,3,'Wharton School, University of Pennsylvania',1975,1979,NULL),(24,6,1,'Nabunturan Central Elementary School, Davao',1953,1956,NULL),(25,6,1,'Butuan Central Elementary, Butuan City',1957,1959,NULL),(26,6,2,'Agusan National High School, Butuan City',1959,1963,NULL),(27,6,3,'University of Sto. Tomas, Manila',1963,1967,NULL),(28,6,3,'Bachelor of Arts Major in Political Science',1967,NULL,NULL),(29,6,4,'Bachelor of Laws, San Beda College Manila',1967,1971,NULL),(30,6,4,'Passed the Bar Exams of October 1971',NULL,NULL,NULL),(31,7,1,'University of the Philippines integrated School',1975,1981,NULL),(32,7,2,'University of the Philippines integrated School',1981,1985,NULL),(33,7,3,'University of the Philippines - Diliman (Pol. Sci.)',1985,1989,NULL),(34,7,3,'University of the Philippines College of Law',1989,1993,'Order of the Purple Feather'),(35,7,4,'Georgetown University (Master in Internationall and Comparative Law)',1995,1996,NULL),(36,8,1,'San Beda College',1953,1959,NULL),(37,8,1,'Dominican School (Taipei)',1959,1961,NULL),(38,8,2,'Don Bosco Technical Institute',1959,1961,NULL),(39,8,3,'University of the Philippines',1961,1965,NULL),(40,8,3,'Philippine Military Academy',1965,1967,'Baron'),(41,8,4,'Asian Institute of Academy',1967,1971,'Thesis with Distinction'),(42,9,1,'La Salle Greenhills, Mandaluyong City',1963,1969,''),(43,9,2,'Worth School, England',1970,1974,NULL),(44,9,3,'Special Diploma in Social Studies',NULL,NULL,NULL),(45,9,3,'Oxford University',1975,1974,NULL),(46,9,4,'Wharton School of Business',NULL,NULL,NULL),(47,9,4,'University of Pennsylvania, USA',1979,1981,NULL),(48,11,1,'Sienna College, Quezon City',1975,1983,NULL),(49,11,2,'Angelicum School, Quezon City',1983,1987,NULL),(50,11,3,'De La Salle University, Manila',1987,1991,NULL),(51,11,3,'Philippine Military Academy',1991,1995,'Cum Laude'),(52,11,4,'University of the Philippines, Diliman, Quezon City',2002,2005,'College Scholar Award'),(53,11,4,'Harvard Kennedy School, Cambridge, Massachussets',2014,2005,NULL),(54,11,4,'Harvard Kennedy School, Cambridge, Massachussets',2015,NULL,NULL);";
  
  
  
  
  
  
  
        String insertquery13 = "INSERT INTO candidate VALUES (1,'Jejomar','C.','Binay','Jojo','1942-11-11','Paco, Manila','Male',1),(2,'Miriam','D.','Santiago','Miriam','1945-06-15','La Paz, Iloilo','Female',2),(3,'Rodrigo','R.','Duterte','Rody','1945-03-28','Maasin, Southern Leyte','Male',3),(4,'Grace',NULL,'Poe','Grace','1968-09-03','Jaro, Iloilo','Female',4),(5,'Manuel','A.','Roxas','Mar','1957-05-13','Manila','Male',5),(6,'Roy','V.','Señeres','Mr. OFW','1947-07-06','Mambusao, Capiz','Male',6),(7,'Francis Joseph','G.','Escudero','Chiz','1969-10-10','Manila Doctor\'s Hospital, Manila','Male',7),(8,'Gregorio','B.','Honasan','Gringo','1948-03-14','Baguio City','Male',8),(9,'Ferdinand','E.','Marcos Jr.','Bongbong','1957-09-13','Manila, Philippines','Female',9),(10,'Maria Leonor','G.','Robredo','Leni',NULL,NULL,'Female',10),(11,'Antonio','F.','Trillanes IV','Sonny','1971-08-06','Caloocan City','Male',11);";
		/*
		String createquery2 = "";
        String insertquery2 = "";
		String createquery3 = "";
        String insertquery3 = "";
		String createquery4 = "";
        String insertquery4 = "";
		String createquery5 = "";
        String insertquery5 = "";
		String createquery6 = "";
        String insertquery6 = "";
		String createquery7 = "";
        String insertquery7 = "";
		String createquery8 = "";
        String insertquery8 = "";
		String createquery9 = "";
        String insertquery9 = "";
		String createquery10 = "";
        String insertquery10 = "";
		String createquery11 = "";
        String insertquery11 = "";
		String createquery12 = "";
        String insertquery12 = "";
		String createquery13 = "";
        String insertquery13 = "";
		*/
        try {
            connection = getConnection();
            statement = connection.prepareStatement(createquery1);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery1);
            statement.executeUpdate();
			/*statement = connection.prepareStatement(createquery2);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery2);
            statement.executeUpdate();
			statement = connection.prepareStatement(createquery3);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery3);
            statement.executeUpdate();
			statement = connection.prepareStatement(createquery4);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery4);
            statement.executeUpdate();
			statement = connection.prepareStatement(createquery5);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery5);
            statement.executeUpdate();
			statement = connection.prepareStatement(createquery6);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery6);
            statement.executeUpdate();
			statement = connection.prepareStatement(createquery7);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery7);
            statement.executeUpdate();
			statement = connection.prepareStatement(createquery8);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery8);
            statement.executeUpdate();
			statement = connection.prepareStatement(createquery9);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery9);
            statement.executeUpdate();
			statement = connection.prepareStatement(createquery10);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery10);
            statement.executeUpdate();
			statement = connection.prepareStatement(createquery11);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery11);
            statement.executeUpdate();
			statement = connection.prepareStatement(createquery12);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery12);
            statement.executeUpdate();
			statement = connection.prepareStatement(createquery13);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery13);
            statement.executeUpdate();*/
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
