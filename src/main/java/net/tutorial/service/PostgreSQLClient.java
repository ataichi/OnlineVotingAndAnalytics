package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Candidate;

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
	
	public List<Candidate> getPresidentCandidates(int positionID) throws Exception {
		String selectquery = "SELECT * FROM candidate c, electionlist el WHERE c.ElectionListID = el.ElectionListID and el.PositionID = '" + positionID + "';";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
		try {
            connection = getConnection();
            statement = connection.prepareStatement(selectquery);
            rs = statement.executeQuery();

            Candidate candidate = new Candidate();
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
        String createquery1 = 
		"CREATE TABLE  IF NOT EXISTS candidate  (" +
		"CandidateID  int(11) NOT NULL AUTO_INCREMENT," +
   "FirstName  varchar(45) NOT NULL," +
   "MiddleName  varchar(45) DEFAULT NULL,"+
   "LastName  varchar(45) NOT NULL,"+
   "NickName  varchar(45) NOT NULL,"+
   "Birthday  date DEFAULT NULL,"+
   "Birthplace  varchar(100) DEFAULT NULL,"+
   "Gender  varchar(45) NOT NULL,"+
   "ElectionListID  int(11) NOT NULL,"+
  "PRIMARY KEY (CandidateID),"+
  "UNIQUE KEY  CandidateID_UNIQUE  (CandidateID),"+
  "KEY  ElectionListID_idx  (ElectionListID),"+
  "CONSTRAINT  ElectionListID2  FOREIGN KEY (ElectionListID) REFERENCES  electionlist  (ElectionListID) ON DELETE NO ACTION ON UPDATE NO ACTION)";
  
        String insertquery1 = "INSERT INTO candidate VALUES (1,'Jejomar','C.','Binay','Jojo','1942-11-11','Paco, Manila','Male',1),(2,'Miriam','D.','Santiago','Miriam','1945-06-15','La Paz, Iloilo','Female',2),(3,'Rodrigo','R.','Duterte','Rody','1945-03-28','Maasin, Southern Leyte','Male',3),(4,'Grace',NULL,'Poe','Grace','1968-09-03','Jaro, Iloilo','Female',4),(5,'Manuel','A.','Roxas','Mar','1957-05-13','Manila','Male',5),(6,'Roy','V.','Se�eres','Mr. OFW','1947-07-06','Mambusao, Capiz','Male',6),(7,'Francis Joseph','G.','Escudero','Chiz','1969-10-10','Manila Doctor\'s Hospital, Manila','Male',7),(8,'Gregorio','B.','Honasan','Gringo','1948-03-14','Baguio City','Male',8),(9,'Ferdinand','E.','Marcos Jr.','Bongbong','1957-09-13','Manila, Philippines','Female',9),(10,'Maria Leonor','G.','Robredo','Leni',NULL,NULL,'Female',10),(11,'Antonio','F.','Trillanes IV','Sonny','1971-08-06','Caloocan City','Male',11);";
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
