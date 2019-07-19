package model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestSender {

	public static void main(String[] args) {
		int pokeNo = (int) ((Math.random()*500) + 1);
		String requsetUrl = "https://pokeapi.co/api/v2/pokemon/" + String.valueOf(pokeNo);
		
		System.out.println(requsetUrl);
		
		try {
			HttpClient httpClient = HttpClients.createDefault();
			
			HttpGet httpGet = new HttpGet(requsetUrl);
			
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuilder stb = new StringBuilder();
			
			String temp = null;
			
			while((temp = br.readLine()) != null) {
				stb.append(temp);
			}
			
			JSONObject obj = new JSONObject(stb.toString());
			
			String name = ((JSONObject)((JSONArray)obj.get("forms")).get(0)).getString("name");
			
			System.out.println("name = " + name);
			System.out.println("weight = " + obj.getInt("weight") + " kg");
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
