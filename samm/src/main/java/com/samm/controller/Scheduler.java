package com.samm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.samm.restapi.TourFestivalAPI;

@Component
public class Scheduler {
/*
	@Autowired
	TourFestivalAPI tour;

	@Scheduled(cron = "0 0 9 * * *")
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
*/
}
