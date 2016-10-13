import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {
	
	private final static String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			sendGet("http://www.omdbapi.com/?s=" + args[0] +"&y=&r=json");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// HTTP GET request
	private static void sendGet(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		
		String s = response.toString();
		JSONParser parser = new JSONParser();
		
		
        Object jObj = parser.parse(s);
        JSONObject jsonobj = (JSONObject) jObj;
        JSONArray search = (JSONArray) jsonobj.get("Search");
        Iterator<JSONObject> iterator = search.iterator();
        JSONObject info = null;
        if (iterator.hasNext()) {
        	info = iterator.next();
 		}
        System.out.println("Title: " + info.get("Title"));
        System.out.println("Year: " + info.get("Year"));
	}

}
