package com.samm.tourapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

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

import com.samm.mapper.AreaMapper;
import com.samm.mapper.FestivalMapper;

@SpringBootTest
class TourFestivalInsert {

	// tag값의 정보를 가져오는 함수
	public static String getTagValue(String tag, Element eElement) {

		// 결과를 저장할 result 변수 선언
		String result = "";

		if (eElement.getElementsByTagName(tag).item(0) == null) {
			result = null;
		} else {
			NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
			result = nlList.item(0).getTextContent();
		}
		return result;
	}

	@Autowired
	AreaMapper dao;
	
	@Autowired
	FestivalMapper fdao;

	@Test
	void contextLoads() throws IOException {

			StringBuilder urlBuilder = new StringBuilder(
					"http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival"); /* 행사정보 URL */
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
					+ "=tfve0pUOnMXCq5%2F%2Fm0wkmz%2BoE2%2BWsgFwlecdUgsklVSER1UoydTgZG1ZaUOK%2FtsetQnRNi1TuOiEjM%2BHncD9qw%3D%3D"); // servicekey
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("10000", "UTF-8")); /* 한 페이지 결과수 */
			urlBuilder.append(
					"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 현재 페이지 번호 */
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "="
					+ URLEncoder.encode("ETC", "UTF-8")); /* IOS (아이폰), AND (안드로이드), WIN (원도우폰), ETC */
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "="
					+ URLEncoder.encode("AppTest", "UTF-8")); /* 서비스명=어플명 */
			urlBuilder.append("&" + URLEncoder.encode("arrange", "UTF-8") + "=" + URLEncoder.encode("A", "UTF-8")); 
																													
			urlBuilder.append("&" + URLEncoder.encode("listYN", "UTF-8") + "="
					+ URLEncoder.encode("Y", "UTF-8")); /* listYN 목록구분(Y=목록,N=개수) */

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
				
				int cnt = 0;
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					Element eElement = (Element) nNode;
					HashMap<String, String> hashmap = new HashMap<String, String>();

					hashmap.put("contentid", getTagValue("contentid", eElement));
					hashmap.put("title", getTagValue("title", eElement));
					hashmap.put("areacode", getTagValue("areacode", eElement));
					hashmap.put("eventstartdate", getTagValue("eventstartdate", eElement));
					hashmap.put("eventenddate", getTagValue("eventenddate", eElement));
					hashmap.put("tel", getTagValue("tel", eElement));
					hashmap.put("addr1", getTagValue("addr1", eElement));
					hashmap.put("mapx", getTagValue("mapx", eElement));
					hashmap.put("mapy", getTagValue("mapy", eElement));
					hashmap.put("firstimage", getTagValue("firstimage", eElement));
					hashmap.put("firstimage2", getTagValue("firstimage2", eElement));
					hashmap.put("createdtime", getTagValue("createdtime", eElement));
					cnt++;
					
					try {
						fdao.apiinsert(hashmap);
					
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					System.out.println("cnt ::" + cnt);
					System.out.println(hashmap);

				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			rd.close();
			conn.disconnect();

		}

}
