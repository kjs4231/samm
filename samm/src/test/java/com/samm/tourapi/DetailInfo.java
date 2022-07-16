package com.samm.tourapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
class DetailInfo {

	// tag값의 정보를 가져오는 함수
	public static String getTagValue(String tag, Element eElement) {

		// 결과를 저장할 result 변수 선언
		String result = "";
		if (eElement.getElementsByTagName(tag).item(0) == null) {
			result = null;
		} else {
			NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
			if (nlList.item(0) == null) {
				result = null;
			} else {
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
					"http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailInfo"); /* 반복정보 URL */
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
					+ "=tfve0pUOnMXCq5%2F%2Fm0wkmz%2BoE2%2BWsgFwlecdUgsklVSER1UoydTgZG1ZaUOK%2FtsetQnRNi1TuOiEjM%2BHncD9qw%3D%3D"); // servicekey
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과수 */
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

//			StringBuilder sb = new StringBuilder();
//			String line;
//			while ((line = rd.readLine()) != null) {
//				sb.append(line);
//			}

			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			try {
				dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(strURL);

				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("item");
				
				Node nNode = nList.item(0);
				System.out.println("nNode::" +nNode);
				Node nNode2 = nList.item(1);
				System.out.println("nNode2::" +nNode2);
				Element eElement = (Element) nNode;
				Element eElement2 = (Element) nNode2;
				HashMap<String, String> hashmap = new HashMap<String, String>();

				if(nNode2 == null) {
					hashmap.put("contentid", getTagValue("contentid", eElement));
					hashmap.put("infoname", getTagValue("infoname", eElement));
					hashmap.put("infotext", getTagValue("infotext", eElement));
				}else {
					hashmap.put("contentid", getTagValue("contentid", eElement));
					hashmap.put("infoname", getTagValue("infoname", eElement));
					hashmap.put("infotext", getTagValue("infotext", eElement));
					hashmap.put("infoname2", getTagValue("infoname", eElement2));
					hashmap.put("infotext2", getTagValue("infotext", eElement2));
				}

				
				try {
					dao.infoInsert(hashmap);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("festival::"+hashmap);

			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
			rd.close();
			conn.disconnect();
//			System.out.println(sb.toString());

		}
		System.out.println("END----------------------------------");
	}

}