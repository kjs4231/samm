package com.samm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.samm.biz.EmailBiz;
import com.samm.biz.WishBiz;
import com.samm.restapi.TourFestivalAPI;
import com.samm.vo.WishVo;

@Component
public class Scheduler {

	@Autowired
	TourFestivalAPI tour;
	@Autowired
	EmailBiz emailBiz;

	@Scheduled(cron = "0 0 1 * * *")
	public void weeklyMonUpdate() {
		System.out.println("----------- Scheduler Start ------------");
		try {
			System.out.println("Festivalinsertstart.........................................");
			if (tour.insertFestivalApi()) {
				Thread.sleep(5000);
				System.out.println("commonstart.........................................");
				if (tour.DetailCommonInsert()) {
					Thread.sleep(5000);
					if (tour.DetailInfoInsert()) {
						tour.DetailIntroInsert();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("----------- Scheduler End ------------");
	}
	
	@Scheduled(cron = "0 1 0 * * *")
	public void sendDdayFestival() {
		System.out.println("------------ mail Scheduler Start -------------------");
		emailBiz.sendDdayFestivalMail();
		System.out.println("------------ mail Scheduler End-------------------");
	}

}
