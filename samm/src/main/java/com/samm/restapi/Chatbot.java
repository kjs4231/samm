package com.samm.restapi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;

@Controller
public class Chatbot {
	private String secretKey = "eE9XQkhNV2dvc2dVS1REbEhpTlVScVBSSEp2UUZHZ2o=";
	private String apiUrl = "https://046qrl25c9.apigw.ntruss.com/custom/v1/7260/2c30b83e73b9d71df4605a4b7b12d7c139ab4e1cb94476199b340fb66b27b726";
	
	
	public List<String> getMessage(String txt) throws IOException {
		URL url = new URL(apiUrl);
		String chatMessage = txt;
		String urlstr = txt;
		ArrayList<String> chat = new ArrayList<String>();
		String message =  getReqMessage(chatMessage);
		String encodeBase64String = makeSignature(message, secretKey);
		System.out.println(message);
		System.out.println(encodeBase64String);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json;UTF-8");
		con.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", encodeBase64String);

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());

		wr.write(message.getBytes("UTF-8"));
		wr.flush();
		wr.close();
		int responseCode = con.getResponseCode();
		System.out.println("responseCode:"+responseCode);

		BufferedReader br;

		if(responseCode==200) { // 정상 호출

			
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							con.getInputStream()));
			String decodedString;
			String jsonString = "";
			while ((decodedString = in.readLine()) != null) {
				jsonString = decodedString;
			}
			//chatbotMessage = decodedString;
			
			JSONParser jsonparser = new JSONParser();
			try {

				JSONObject json = (JSONObject)jsonparser.parse(jsonString);
				JSONArray bubblesArray = (JSONArray)json.get("bubbles");
				JSONObject bubbles = (JSONObject)bubblesArray.get(0);
				JSONObject data = (JSONObject)bubbles.get("data");
				String description = "";
				String urlstrr = "";
				description = (String)data.get("description");
				urlstrr = (String)data.get("url");
				urlstr = urlstrr;
				chatMessage = description;
				chat.add(0, chatMessage);
				chat.add(1, urlstr);
				System.out.println("url::"+urlstr);
			} catch (Exception e) {
				System.out.println("error");
				e.printStackTrace();
			}

			in.close();

		} else {  // 에러 발생
			System.out.println("Error");

			chatMessage = con.getResponseMessage();
		}
		System.out.println("REsult:"+chatMessage);
		
		return chat;
	}	
	
	public String getReqMessage(String voiceMessage) {

		String requestBody = "";

		try {

			JSONObject obj = new JSONObject();

			long timestamp = new Date().getTime();

			System.out.println("##"+timestamp);

			obj.put("version", "v2");
			obj.put("userId", "U47b00b58c90f8e47428af8b7bddc1231heo2");
			obj.put("timestamp", timestamp);

			JSONObject bubbles_obj = new JSONObject();

			bubbles_obj.put("type", "text");

			JSONObject data_obj = new JSONObject();
			data_obj.put("description", voiceMessage);

			bubbles_obj.put("type", "text");
			bubbles_obj.put("data", data_obj);

			JSONArray bubbles_array = new JSONArray();
			bubbles_array.add(bubbles_obj);

			obj.put("bubbles", bubbles_array);
			obj.put("event", "send");

			requestBody = obj.toString();

		} catch (Exception e){
			System.out.println("## Exception : " + e);
		}

		return requestBody;

	}
	public String makeSignature(String message, String secretKey) {

		 String encodeBase64String = "";

	        try {
	            byte[] secrete_key_bytes = secretKey.getBytes("UTF-8");

	            SecretKeySpec signingKey = new SecretKeySpec(secrete_key_bytes, "HmacSHA256");
	            Mac mac = Mac.getInstance("HmacSHA256");
	            mac.init(signingKey);

	            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
	            encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);

	            return encodeBase64String;

	        } catch (Exception e){
	            System.out.println(e);
	        }

	        return encodeBase64String;

	}
}
