package model;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestSenderZINIO {
	// use getBearerToken() method to get latest token 12 hours
	private static String BEARER_TOKEN = "nTw9C2yk5DwzJxuX8qhqdLdZ5IGpJnEx";
	
	public static void main(String[] args) {
//		getBearerToken();
		getOrderItems();
	}
	
	private static void getBearerToken() {
		String requsetUrl = "https://sbx-api.ziniopro.com/oauth/v2/tokens";
		
		System.out.println(requsetUrl);
		
		try {
			HttpClient httpClient = HttpClients.createDefault();
			
			HttpPost httpPost = new HttpPost(requsetUrl); 
			httpPost.setHeader("Content-Type", "application/json");
			
			// set entity content by JSONObject
			JSONObject entity = new JSONObject();
			entity.put("client_id", "fad300b0d4f0491ea14f78f9b76e3f9a");
			entity.put("client_secret", "c37cb26156c948a180eced9bd27ef868");
			entity.put("grant_type", "client_credentials");
			
			// set entity by JSON String
			httpPost.setEntity(new StringEntity(entity.toString(), "UTF-8"));
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			
			if(httpResponse.getStatusLine().getStatusCode() == 200) {
				JSONObject obj = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
				String accessToken = obj.getString("access_token");
				
				System.out.println("access token = " + accessToken);
			} else {
				System.out.println("Error occurs");
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void getOrderItems() {
		String requsetUrl = "https://sbx-api.ziniopro.com/commerce/v2/orders/821666";
		
		System.out.println(requsetUrl);
		
		try {
			HttpClient httpClient = HttpClients.createDefault();
			
			HttpGet httpGet = new HttpGet(requsetUrl);
			httpGet.setHeader("Authorization", "Bearer " + BEARER_TOKEN);
			
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			
			if(httpResponse.getStatusLine().getStatusCode() == 200) {
				JSONObject obj = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
				int total = ((JSONObject)obj.get("data")).getInt("total");
				String currency = ((JSONObject)((JSONArray)((JSONObject)obj.get("data")).get("payments")).get(0)).getString("currency_code");
				
				System.out.println("total = " + total + " " + currency);
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
