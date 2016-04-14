package net.tutorial.service.credential;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PostgreSQLCredential {
	
	public String hostname;
    public String port;
    public String name;
    public String password;
	
	public PostgreSQLCredential(){
        try {
            String envApp = System.getenv("VCAP_APPLICATION");
            String envServices = System.getenv("VCAP_SERVICES");

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(envServices);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray vcapArray = (JSONArray) jsonObject.get("postgresql");
            JSONObject vcap = (JSONObject) vcapArray.get(0);
            JSONObject credentials = (JSONObject) vcap.get("credentials");
            hostname = credentials.get("hostname").toString();
            port = credentials.get("port").toString();
            name = credentials.get("name").toString();
            password = credentials.get("password").toString();
        }catch(Exception e){
            System.err.println("PostgreSQLCredential() Exception: " + e.getMessage());
        }
    }

    public String toString(){
        String str = "";
        str += "hostname: " + hostname + "\n";
        str += "port: " + port + "\n";
        str += "name: " + name + "\n";
        str += "password: " + password + "\n";
        return str;
    }
}
