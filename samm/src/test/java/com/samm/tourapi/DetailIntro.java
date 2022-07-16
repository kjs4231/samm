package com.samm.tourapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.samm.biz.FestivalBiz;
import com.samm.mapper.FestivalDetailMapper;

@SpringBootTest
class DetailIntro {

	// tag값의 정보를 가져오는 함수
	public static String getTagValue(String tag, Element eElement) {

		// 결과를 저장할 result 변수 선언
		String result = "";
		if (eElement.getElementsByTagName(tag).item(0) == null) {
			result = null;
		} else {
			NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
			if(nlList.item(0) == null) {
				result = null;
			}else {
				result = nlList.item(0).getTextContent();
			}
		}
		return result;
	}

	@Autowired
	FestivalBiz fbiz;
	@Autowired
	FestivalDetailMapper dao;
	
	@Test
	void contextLoads() throws IOException {
		
		List<String> festivalID = null;
		try {
			festivalID = fbiz.getContentId();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int cnt = 0;
		for (String contentId : festivalID) {
		cnt++;	
		System.out.println("contentId:: "+contentId);
		System.out.println("count::" + cnt);
		StringBuilder urlBuilder = new StringBuilder(
				"http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro"); /* 소개정보 URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=tfve0pUOnMXCq5%2F%2Fm0wkmz%2BoE2%2BWsgFwlecdUgsklVSER1UoydTgZG1ZaUOK%2FtsetQnRNi1TuOiEjM%2BHncD9qw%3D%3D"); // servicekey
		urlBuilder.append(
				"&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과수 */
		urlBuilder.append(
				"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 현재 페이지 번호 */
		urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "="
				+ URLEncoder.encode("ETC", "UTF-8")); /* IOS (아이폰), AND (안드로이드), WIN (원도우폰), ETC */
		urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "="
				+ URLEncoder.encode("AppTest", "UTF-8")); /* 서비스명=어플명 */
		urlBuilder.append("&" + URLEncoder.encode("listYN", "UTF-8") + "="
				+ URLEncoder.encode("Y", "UTF-8")); /* listYN 목록구분(Y=목록,N=개수) */
		urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "="
				+ URLEncoder.encode(contentId, "UTF-8")); /* contentID */
		urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "="
				+ URLEncoder.encode("15", "UTF-8")); /* 축제 콘텐츠타입 = 15 */

		URL url = new URL(urlBuilder.toString());
		String strURL = urlBuilder.toString();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(strURL);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("item");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				HashMap<String, String> hashmap = new HashMap<String, String>();

				hashmap.put("contentid", getTagValue("contentid", eElement));
				hashmap.put("agelimit", getTagValue("agelimit", eElement));
				hashmap.put("eventplace", getTagValue("eventplace", eElement));
				hashmap.put("eventstartdate", getTagValue("eventstartdate", eElement));
				hashmap.put("eventenddate", getTagValue("eventenddate", eElement));
				hashmap.put("festivaltype", getTagValue("festivaltype", eElement));
				hashmap.put("bookingplace", getTagValue("bookingplace", eElement));
				hashmap.put("placeinfo", getTagValue("placeinfo", eElement));
				hashmap.put("playtime", getTagValue("playtime", eElement));
				hashmap.put("program", getTagValue("program", eElement));
				hashmap.put("progresstype", getTagValue("progresstype", eElement));
				hashmap.put("spendtimefestival", getTagValue("spendtimefestival", eElement));
				hashmap.put("sponsor1", getTagValue("sponsor1", eElement));
				hashmap.put("sponsor1tel", getTagValue("sponsor1tel", eElement));
				hashmap.put("sponsor2", getTagValue("sponsor2", eElement));
				hashmap.put("sponsor1tel2", getTagValue("sponsor1tel2", eElement));
				hashmap.put("subevent", getTagValue("subevent", eElement));
				hashmap.put("usetimefestival", getTagValue("usetimefestival", eElement));
				System.out.println("festival ::"+hashmap);
//				try {
//					dao.infoInsert(hashmap);
//					
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
			

			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		rd.close();
		conn.disconnect();
		
		}
		System.out.println("END----------------------------------");

	}

}