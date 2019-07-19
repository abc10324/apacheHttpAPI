package model;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestSender1 {

	public static void main(String[] args) {
		int pokeNo = (int) ((Math.random()*500) + 1);
		String requsetUrl = "https://pokeapi.co/api/v2/pokemon/" + String.valueOf(pokeNo);
//		String requsetUrl = "https://pokeapi.co/api/v2/pokemon/" + "AA";
		
		System.out.println(requsetUrl);
		
		try {
			HttpClient httpClient = HttpClients.createDefault();
			
			HttpGet httpGet = new HttpGet(requsetUrl);
			
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			
			if(httpResponse.getStatusLine().getStatusCode() == 200) {
				JSONObject obj = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
				String name = ((JSONObject)((JSONArray) obj.get("forms")).get(0)).getString("name");
				
				System.out.println("name = " + name);
				System.out.println("weight = " + obj.getInt("weight") + " kg");
			} else {
				System.out.println("Error occurs");
			}
			
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
