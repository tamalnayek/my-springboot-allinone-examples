package com.sample.file.dwnld.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss");

	@Scheduled(fixedRate = 10000)
	public void performTask() {

		System.out.println("Regular task performed every 10 sec at "
				+ dateFormat.format(new Date()));

	}

	@Scheduled(initialDelay = 1000, fixedRate = 10000)
	public void performDelayedTask() {

		System.out.println("Delayed 1 sec Regular task performed every 10 sec at "
				+ dateFormat.format(new Date()));

	}

	@Scheduled(cron = "*/5 * * * * *")
	public void performTaskUsingCron() {

		System.out.println("Regular task performed every 5 sec interval using Cron at "
				+ dateFormat.format(new Date()));

	}
}
