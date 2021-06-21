package apiJava;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ISSLocator {
	
	public static void main (String[] args) throws IOException {
	String sURL = "http://api.open-notify.org/iss-now.json";
	URL url = new URL(sURL);
	URLConnection request = url.openConnection();
    request.connect();

    JsonParser jp = new JsonParser();
    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
    JsonObject rootobj = root.getAsJsonObject();
    JsonObject loc = rootobj.get("iss_position").getAsJsonObject();
    double lat = loc.get("latitude").getAsDouble();
    double lon = loc.get("longitude").getAsDouble();
    int timestamp = rootobj.get("timestamp").getAsInt();
    
    System.out.println(rootobj);
    
    String message = rootobj.get("message").getAsString();
    System.out.println();
    System.out.println("ISS Locator API");
    System.out.println("Message: " + message);
    System.out.println("ISS Coordinates");
    System.out.println("Latitude: " + lat);
    System.out.println("Longitude: "+ lon);
    System.out.println("Timestamp: "+ timestamp);
    
	}
}
