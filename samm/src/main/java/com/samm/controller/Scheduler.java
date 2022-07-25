package com.samm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.samm.restapi.TourFestivalAPI;

@Component
public class Scheduler {

	@Autowired
	TourFestivalAPI tour;

	@Scheduled(cron = "0 0 9 ? * 1")
	public void weeklyMonUpdate() {
		System.out.println("----------- Scheduler Start ------------");
		try {
			if (tour.insertFestivalApi()) {
				if (tour.DetailCommonInsert()) {
					if (tour.DetailInfoInsert()) {
						tour.DetailIntroInsert();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("----------- Scheduler Start ------------");
	}

}
