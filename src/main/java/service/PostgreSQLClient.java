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
	
	public List<CandidateBean> getBallotPerUser(String email, String password) throws Exception{
		//List<CandidateBean> candidates = new ArrayList<CandidateBean>();
		String selectquery = "SELECT * FROM vote v";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
		try {
            connection = getConnection();
            statement = connection.prepareStatement(selectquery);
            rs = statement.executeQuery();

            CandidateBean candidate = new CandidateBean();
			List<CandidateBean> candidates = new ArrayList<CandidateBean>();
			
            if ( rs.next() ) {
                candidate.setFirstName(rs.getString(1));
				candidate.setMiddleName(rs.getString(2));
				candidate.setLastName(rs.getString(3));
				candidate.setNickname(rs.getString(4));
				candidates.add(candidate);
				//candidate.setEducationalBGID(rs.getString(8));
				//candidate.setTheresareturnedvalue(1);
            }
            return candidates;
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
	
	public String getPoliticalParty(int candidateID) throws Exception {
		String selectquery = "SELECT pp.PoliticalPartyName FROM politicalparty pp, electionlist el, candidate c WHERE c.CandidateID = el.ElectionList and el.PoliticalPartyID = pp.PoliticalPartyID and c.candidateID = '" + candidateID +"';";
		Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		String temp = null;
		try
		{
            ps = connection.prepareStatement(selectquery);
            rs = ps.executeQuery();
            while (rs.next()) {
             	temp =  rs.getString(1);
				return temp;
            }
            connection.close();
		}catch(Exception e){
		
		}
		return temp;
	}
	
	public String getPositionOfCandidate(int candidateID) throws Exception {
		String selectquery = "SELECT p.PositionName FROM position p, candidate c, electionlist el WHERE c.CandidateID = el.CandidateID and el.PositionID = p.PositionID and c.CandidateID = '" + candidateID + "';";
		Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
		String temp = null;
		try
		{
            ps = connection.prepareStatement(selectquery);
            rs = ps.executeQuery();
            while (rs.next()) {
             	temp = rs.getString(1);
				return temp;
            }
            connection.close();
		}catch(Exception e){
		
		}
		return temp;
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

			List<CandidateBean> listcandidate = new ArrayList<CandidateBean>();
			//candidate.setTheresareturnedvalue(0);
            while (rs.next()) {
				
				candidate = new CandidateBean();
				
                candidate.setCandidateID(rs.getInt("CandidateID"));
                candidate.setFirstName(rs.getString("FirstName"));
				candidate.setMiddleName(rs.getString("MiddleName"));
				candidate.setLastName(rs.getString("LastName"));
				candidate.setNickname(rs.getString("Nickname"));
				candidate.setBirthday(rs.getDate("Birthday"));
				candidate.setBirthplace(rs.getString("Birthplace"));
				candidate.setGender(rs.getString("Gender"));
				candidate.setElectionListID(rs.getInt("ElectionListID"));
				
				listcandidate.add(candidate);
            }
            return listcandidate;

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
	
	public CandidateBean getCandidate(int candidateID) throws Exception{
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
	/*
	public List<EducationalBGBean> getEducBGPerCandidate(int candidateID) {
		return ;
	}
	
	public boolean voteForCandidate(int CandidateID) {
		return ;
	}
	
	public List<CandidateBean> getVotedCandidatesPerUser(String email, String password) {
		return ;
	}
*/
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

		String createquery4 = "CREATE TABLE educationalbg ( "+
  "EducationalBGID INT NOT NULL PRIMARY KEY,"+
  "CandidateID INT NOT NULL,"+
  "EducationalBGLevelID INT NOT NULL,"+
  "SchoolName varchar(100) NOT NULL,"+
  "FromYear year(4) DEFAULT NULL,"+
  "ToYear year(4) DEFAULT NULL,"+
  "AcademicHonors varchar(100));";
  
		String insertquery4 = "INSERT INTO educationalbg(EducationalBGID, CandidateID, EducationalBGLevelID, SchoolName, FromYear, ToYear, AcademicHonors) VALUES (1,1,1,'Philippine Normal College, Training Department',NULL,NULL,NULL),(2,1,2,'University of the Philippines Preparatory High School',NULL,NULL,NULL),(3,1,3,'University of the Philippines',NULL,1962,NULL),(4,1,4,'University of the Philippines',NULL,NULL,'with honors'),(5,1,4,'Palawan State University',NULL,2012,NULL),(6,2,1,'La Paz Elementary School',1951,1957,'Valedictorian'),(7,2,2,'Iloilo Provincial National High School',1957,1961,'Valedictorian All-Around Girl Medallion Awardee'),(8,2,3,'Bachelor of Arts in Political Science, UP Iloilo',1961,1965,'Magna Cum Laude'),(9,2,4,'Bachelor of Laws, UP Diliman',1965,1969,'Cum Laude'),(10,2,4,'Master of Arts in Religious Studies (without thesis), Maryhill School of Theology, Quezon City',NULL,1996,''),(11,2,4,'Master of Laws (DeWitt Fellow), University of Michigan',NULL,1975,'Finished with \"A\" average'),(12,2,4,'Doctor of Judicial Science (Barbour Scholar and DeWitt Fellow), University of Michigan',NULL,1976,'Requirements fulfilled in 6 months, with \"A\" average'),(13,3,1,'Sta. Ana Elementary School',NULL,1956,'Graduate'),(14,3,2,'Holy Cross of Digos',NULL,1966,'Graduate'),(15,3,3,'Lyceum of the Philippines University',NULL,1968,'Graduate'),(16,3,4,'San Beda College',NULL,1972,'LLB'),(17,4,1,'St. Paul College Pasig',1975,1982,'Leadership Award'),(18,4,2,'Assumption College',1982,1986,NULL),(19,4,3,'University of the Philippines',1986,1988,'College Scholar'),(20,4,3,'Boston College',1988,1991,NULL),(21,5,1,'Ateneo de Manila University',1962,1970,NULL),(22,5,2,'Ateneo de Manila University',1970,1974,NULL),(23,5,3,'Wharton School, University of Pennsylvania',1975,1979,NULL),(24,6,1,'Nabunturan Central Elementary School, Davao',1953,1956,NULL),(25,6,1,'Butuan Central Elementary, Butuan City',1957,1959,NULL),(26,6,2,'Agusan National High School, Butuan City',1959,1963,NULL),(27,6,3,'University of Sto. Tomas, Manila',1963,1967,NULL),(28,6,3,'Bachelor of Arts Major in Political Science',1967,NULL,NULL),(29,6,4,'Bachelor of Laws, San Beda College Manila',1967,1971,NULL),(30,6,4,'Passed the Bar Exams of October 1971',NULL,NULL,NULL),(31,7,1,'University of the Philippines integrated School',1975,1981,NULL),(32,7,2,'University of the Philippines integrated School',1981,1985,NULL),(33,7,3,'University of the Philippines - Diliman (Pol. Sci.)',1985,1989,NULL),(34,7,3,'University of the Philippines College of Law',1989,1993,'Order of the Purple Feather'),(35,7,4,'Georgetown University (Master in Internationall and Comparative Law)',1995,1996,NULL),(36,8,1,'San Beda College',1953,1959,NULL),(37,8,1,'Dominican School (Taipei)',1959,1961,NULL),(38,8,2,'Don Bosco Technical Institute',1959,1961,NULL),(39,8,3,'University of the Philippines',1961,1965,NULL),(40,8,3,'Philippine Military Academy',1965,1967,'Baron'),(41,8,4,'Asian Institute of Academy',1967,1971,'Thesis with Distinction'),(42,9,1,'La Salle Greenhills, Mandaluyong City',1963,1969,''),(43,9,2,'Worth School, England',1970,1974,NULL),(44,9,3,'Special Diploma in Social Studies',NULL,NULL,NULL),(45,9,3,'Oxford University',1975,1974,NULL),(46,9,4,'Wharton School of Business',NULL,NULL,NULL),(47,9,4,'University of Pennsylvania, USA',1979,1981,NULL),(48,11,1,'Sienna College, Quezon City',1975,1983,NULL),(49,11,2,'Angelicum School, Quezon City',1983,1987,NULL),(50,11,3,'De La Salle University, Manila',1987,1991,NULL),(51,11,3,'Philippine Military Academy',1991,1995,'Cum Laude'),(52,11,4,'University of the Philippines, Diliman, Quezon City',2002,2005,'College Scholar Award'),(53,11,4,'Harvard Kennedy School, Cambridge, Massachussets',2014,2005,NULL),(54,11,4,'Harvard Kennedy School, Cambridge, Massachussets',2015,NULL,NULL);";
  
		String createquery5 = "CREATE TABLE citymunicipality ( "+
  "CityMunicipalityID INT NOT NULL PRIMARY KEY, "+
  "CitymunicipalityName varchar(45) NOT NULL, "+
  "ProvinceID INT NOT NULL "+
");";
		String insertquery5 = 
		"INSERT INTO citymunicipality(CityMunicipalityID, CitymunicipalityName) VALUES (1,'BACOLOD CITY (Capital)',1),(2,'CALATRAVA',1),(3,'CITY OF ESCALANTE',1),(4,'SAN CARLOS CITY',1),(5,'AYUNGON',2),(6,'BACONG',2),(7,'BANGUED (Capital)',3),(8,'BOLINEY',3),(9,'FLORA',4),(10,'LUNA',4),(11,'ATOK',5),(12,'BAGUIO CITY',5),(13,'BANAUE',6),(14,'HUNGDUAN',6),(15,'BALBALAN',7),(16,'LUBUAGAN',7),(17,'BARLIG',8),(18,'BAUKO',8),(19,'ADAMS',9),(20,'BACARRA',9),(21,'ALILEM',10),(22,'BANAYOYO',10),(23,'AGOO',11),(24,'ARINGAY',11),(25,'AGNO',12),(26,'AGUILAR',12),(27,'BASCO (Capital)',13),(28,'ITBAYAT',13),(29,'ABULUG',14),(30,'ALCALA',14),(31,'ALICIA',15),(32,'ANGADANAN',15),(33,'ARITAO',16),(34,'BAGABAG',16),(35,'AGLIPAY',17),(36,'CABARROGUIS (Capital)',17),(37,'BALER (Capital)',18),(38,'CASIGURAN',18),(39,'ABUCAY',19),(40,'BAGAC',19),(41,'ANGAT',20),(42,'BALAGTAS (BIGAA)',20),(43,'ALIAGA',21),(44,'BONGABON',21),(45,'ANGELES CITY',22),(46,'APALIT',22),(47,'ANAO',23),(48,'BAMBAN',23),(49,'BOTOLAN',24),(50,'CABANGAN',24),(51,'AGONCILLO',25),(52,'BALETE',25),(53,'ALFONSO',26),(54,'AMADEO',26),(55,'ALAMINOS',27),(56,'BAY',27),(57,'AGDANGAN',28),(58,'ALABAT',28),(59,'ANGONO',29),(60,'CITY OF ANTIPOLO',29),(61,'BOAC (Capital)',30),(62,'BUENAVISTA',30),(63,'ABRA DE ILOG',31),(64,'CALINTAAN',31),(65,'BACO',32),(66,'BANSUD',32),(67,'ABORLAN',33),(68,'AGUTAYA',33),(69,'ALCANTARA',34),(70,'BANTON',34),(71,'BACACAY',35),(72,'CAMALIG',35),(73,'BASUD',36),(74,'CAPALONGA',36),(75,'BALATAN',37),(76,'BATO',37),(77,'BAGAMANOC',38),(78,'BARAS',38),(79,'AROROY',39),(80,'BALENO',39),(81,'BARCELONA',40),(82,'BULAN',40),(83,'CITY OF LAMITAN',41),(84,'LANTAWAN',41),(85,'BACOLOD-KALAWI (BACOLOD GRANDE)',42),(86,'BALABAGAN',42),(87,'AMPATUAN',43),(88,'BULDON',43),(89,'INDANAN',44),(90,'JOLO (Capital)',44),(91,'PANGLIMA SUGALA (BALIMBING)',45),(92,'BONGAO (Capital)',45);";
  
		String createquery7 = "CREATE TABLE region ( "+
  "RegionID INT NOT NULL PRIMARY KEY, "+
  "RegionName varchar(100) NOT NULL "+
"); ";
		String insertquery7 = "INSERT INTO region(RegionID, RegionName) VALUES (1,'NIR - Negros IslandRegion'),(2,'NCR - National Capital Region'),(3,'CAR - Cordillera Administrative Region'),(4,'REGION I (Ilocos Region)'),(5,'REGION II (Cagayan Valley)'),(6,'REGION III (Central Luzon)'),(7,'REGION IV-A (CALABARZON)'),(8,'REGION IV-B (MIMAROPA)'),(9,'REGION V (Bicol Region)'),(10,'ARMM - Autonomous Region in Muslim Mindanao');";
		String createquery8 = "CREATE TABLE province ( " +
  "ProvinceID INT NOT NULL PRIMARY KEY, " +
  "ProvinceName varchar(100) NOT NULL," +
  "RegionID INT NOT NULL" +  
"); ";
		String insertquery8 = "INSERT INTO province(ProvinceID, ProvinceName, RegionID) VALUES (1,'NEGROS OCCIDENTAL',1),(2,'NEGROS ORIENTAL',1),(3,'ABRA',3),(4,'APAYAO',3),(5,'BENGUET',3),(6,'IFUGAO',3),(7,'KALINGA',3),(8,'MOUNTAIN PROVINCE',3),(9,'ILOCOS NORTE',4),(10,'ILOCOS SUR',4),(11,'LA UNION',4),(12,'PANGASINAN',4),(13,'BATANES',5),(14,'CAGAYAN',5),(15,'ISABELA',5),(16,'NUEVA VIZCAYA',5),(17,'QUIRINO',5),(18,'AURORA',6),(19,'BATAAN',6),(20,'BULACAN',6),(21,'NUEVA ECIJA',6),(22,'PAMPANGA',6),(23,'TARLAC',6),(24,'ZAMBALES',6),(25,'BATANGAS',7),(26,'CAVITE',7),(27,'LAGUNA',7),(28,'QUEZON',7),(29,'RIZAL',7),(30,'MARINDUQUE',8),(31,'OCCIDENTAL MINDORO',8),(32,'ORIENTAL MINDORO',8),(33,'PALAWAN',8),(34,'ROMBLON',8),(35,'ALBAY',9),(36,'CAMARINES NORTE',9),(37,'CAMARINES SUR',9),(38,'CATANDUANES',9),(39,'MASBATE',9),(40,'SORSOGON',9),(41,'BASILAN',10),(42,'LANAO DEL SUR',10),(43,'MAGUINDANAO',10),(44,'SULU',10),(45,'TAWI-TAWI',10);";
		
		String createquery9 = "CREATE TABLE voter ( "+
  "VoterID INT NOT NULL PRIMARY KEY,"+
  "FirstName varchar(45) NOT NULL,"+
  "MiddleName varchar(45) NOT NULL,"+
  "LastName varchar(45) NOT NULL,"+
  "EmailAddress varchar(45) NOT NULL,"+
  "Password varchar(45) NOT NULL,"+
  "CityMunicipality varchar(45) NOT NULL, "+
  "Province varchar(45) NOT NULL,"+
  "Region varchar(45) NOT NULL"+
"); ";
		String insertquery9 = "INSERT INTO voter(VoterID, FirstName, MiddleName, LastName, EmailAddress, Password, CityMunicipality, Province, Region) VALUES (1,'Patricia','A.','Nieva','tricianieva@gmail.com','heh','1','1','1'),(2,'Danica','D.','Corpuz','danicacorpuz@gmail.com','ant','2','1','1'),(3,'Kaye','S.','Solomon','ky@gmail.com','ran','3','1','1');";
		
		String createquery10 = "CREATE TABLE candidate ( " + 
  "CandidateID INT NOT NULL PRIMARY KEY," + 
  "FirstName varchar(45) NOT NULL," + 
  "MiddleName varchar(45) DEFAULT NULL," + 
  "LastName varchar(45) NOT NULL," + 
  "NickName varchar(45) NOT NULL," + 
  "Birthday date DEFAULT NULL," + 
  "Birthplace varchar(100) DEFAULT NULL," + 
  "Gender varchar(45) NOT NULL," + 
  "ElectionListID INT NOT NULL," + 
  "EmailAddress varchar(100) NOT NULL," + 
  "Password varchar(45) NOT NULL" + 
"); ";
		
        String insertquery10 = "INSERT INTO candidate(CandidateID,FirstName,MiddleName,LastName,NickName,Birthday,Gender,ElectionListID,EmailAddress, Password) VALUES (1,'Jejomar','C.','Binay','Jojo','1942-11-11','Paco, Manila','Male',1,'binay@gmail.com','jojo'),(2,'Miriam','D.','Santiago','Miriam','1945-06-15','La Paz, Iloilo','Female',2,'miriam@gmail.com','miriam'),(3,'Rodrigo','R.','Duterte','Rody','1945-03-28','Maasin, Southern Leyte','Male',3,'rody@gmail.com','rody'),(4,'Grace',NULL,'Poe','Grace','1968-09-03','Jaro, Iloilo','Female',4,'grace@gmail.com','gracr'),(5,'Manuel','A.','Roxas','Mar','1957-05-13','Manila','Male',5,'mar@gmail.com','mar'),(6,'Roy','V.','Señeres','Mr. OFW','1947-07-06','Mambusao, Capiz','Male',6,'roy@gmail.com','mrofw'),(7,'Francis Joseph','G.','Escudero','Chiz','1969-10-10','Manila Doctor\'s Hospital, Manila','Male',7,'chiz@gmail.com','chiz'),(8,'Gregorio','B.','Honasan','Gringo','1948-03-14','Baguio City','Male',8,'gringo@gmail.com','gringo'),(9,'Ferdinand','E.','Marcos Jr.','Bongbong','1957-09-13','Manila, Philippines','Female',9,'bong@gmail.com','bongbong'),(10,'Maria Leonor','G.','Robredo','Leni',NULL,NULL,'Female',10,'leni@gmail.com','leni'),(11,'Antonio','F.','Trillanes IV','Sonny','1971-08-06','Caloocan City','Male',11,'sonny@gmail.com','sonny');";
		
		String querysingit = "CREATE TABLE politicalparty ("+
  "PoliticalPartyID INT NOT NULL PRIMARY KEY,"+
  "PoliticalPartyName varchar(100) NOT NULL "+
");";
		String querysingit2 = "INSERT INTO politicalparty(PoliticalPartyID, PoliticalPartyName) VALUES (1,'UNA'),(2,'PRP'),(3,'PDP-Laban'),(4,'IND'),(5,'LP'),(6,'WPPPMM'),(7,'PMP'),(8,'MKBYN'),(9,'KBL'),(10,'NPC'),(11,'AKBYN'),(12,'AKSYON'),(13,'LAKAS'),(14,'NP');";
		
		String createquery11 = "CREATE TABLE electionlist ( "+
  "ElectionListID INT NOT NULL PRIMARY KEY,"+
  "ElectionDateID INT NOT NULL,"+
  "PositionID INT NOT NULL,"+
  "CandidateID INT NOT NULL,"+
  "PoliticalPartyID INT NOT NULL"+
" ); "; 
		String insertquery11 = "INSERT INTO electionlist(ElectionListID, ElectionDateID, PositionID, CandidateID, PoliticalPartyID) VALUES (1,1,1,1,1),(2,1,1,2,2),(3,1,1,3,3),(4,1,1,4,4),(5,1,1,5,5),(6,1,1,6,6),(7,1,2,7,4),(8,1,2,8,1),(9,1,2,9,4),(10,1,2,10,5),(11,1,2,11,4),(12,1,3,12,4),(13,1,3,13,6),(14,1,3,14,4),(15,1,3,15,5),(16,1,3,16,4),(17,1,3,17,4),(18,1,3,18,7),(19,1,3,19,6),(20,1,3,20,5),(21,1,3,21,5),(22,1,3,22,10),(23,1,3,23,5),(24,1,3,24,11),(25,1,3,25,1),(26,1,3,26,1),(27,1,3,27,4),(28,1,3,28,1),(29,1,3,29,12),(30,1,3,30,4),(31,1,3,31,13);";
		
		String createquery12 = "CREATE TABLE vote ("+
  "VoteID INT NOT NULL PRIMARY KEY,"+
  "ElectionDateID INT NOT NULL,"+
  "VoterID INT NOT NULL,"+
  "ElectionListID INT NOT NULL,"+
  "LastModified timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)"+  
"); ";
		
        String insertquery12 = "INSERT INTO vote(VoteID, ElectionDateID, VoterID, ElectionListID, LastModified) VALUES (1,1,1,3,'2016-04-15 10:40:26.879720'),(2,1,2,3,'2016-04-15 10:40:26.915049'),(3,1,3,2,'2016-04-15 10:40:26.915049'),(4,1,4,3,'2016-04-15 10:40:27.025233'),(5,1,5,4,'2016-04-15 10:40:27.025233'),(6,1,6,5,'2016-04-15 10:40:27.025233'),(7,1,7,5,'2016-04-15 10:40:27.026226'),(8,1,8,4,'2016-04-15 10:40:27.026226'),(9,1,9,4,'2016-04-15 10:40:27.026226'),(10,1,10,7,'2016-04-15 10:40:27.027244'),(11,1,11,7,'2016-04-15 10:40:27.027244'),(12,1,12,9,'2016-04-15 10:40:27.027244'),(13,1,13,10,'2016-04-15 10:40:27.027244'),(14,1,14,10,'2016-04-15 10:40:27.028239'),(15,1,15,10,'2016-04-15 10:40:27.028481'),(16,1,16,15,'2016-04-15 10:40:27.028481'),(17,1,17,20,'2016-04-15 10:40:27.028481'),(18,1,18,24,'2016-04-15 10:40:27.028481'),(19,1,19,23,'2016-04-15 10:40:27.028481'),(20,1,20,27,'2016-04-15 10:40:27.029483'),(21,1,21,28,'2016-04-15 10:40:27.029483'),(22,1,22,30,'2016-04-15 10:40:27.029483'),(23,1,23,3,'2016-04-15 10:40:27.029483'),(24,1,24,3,'2016-04-15 10:40:27.029483'),(25,1,25,4,'2016-04-15 10:40:27.029483'),(26,1,26,4,'2016-04-15 10:40:27.029483'),(27,1,27,5,'2016-04-15 10:40:27.029483'),(28,1,28,5,'2016-04-15 10:40:27.030483'),(29,1,29,7,'2016-04-15 10:40:27.030483'),(30,1,30,10,'2016-04-15 10:40:27.030483'),(31,1,31,10,'2016-04-15 10:40:27.030483'),(32,1,32,10,'2016-04-15 10:40:27.030483'),(33,1,33,3,'2016-04-15 10:40:27.030483'),(34,1,34,3,'2016-04-15 10:40:27.030483'),(35,1,35,3,'2016-04-15 10:40:27.030483'),(36,1,36,4,'2016-04-15 10:40:27.031484'),(37,1,37,4,'2016-04-15 10:40:27.031484'),(38,1,38,4,'2016-04-15 10:40:27.031484'),(39,1,39,2,'2016-04-15 10:40:27.031484'),(40,1,40,1,'2016-04-15 10:40:27.031484');";
		
		
        try {
            connection = getConnection();
            statement = connection.prepareStatement(createquery1);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery1);
            statement.executeUpdate();
			statement = connection.prepareStatement(createquery2);
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
			//statement = connection.prepareStatement(createquery6);
           // statement.executeUpdate();
           // statement = connection.prepareStatement(insertquery6);
            //statement.executeUpdate();
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
			statement = connection.prepareStatement(querysingit);
            statement.executeUpdate();
            statement = connection.prepareStatement(querysingit2);
            statement.executeUpdate();
			statement = connection.prepareStatement(createquery11);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery11);
            statement.executeUpdate();
			statement = connection.prepareStatement(createquery12);
            statement.executeUpdate();
            statement = connection.prepareStatement(insertquery12);
            statement.executeUpdate();
			
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
