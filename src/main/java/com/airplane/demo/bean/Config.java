package com.airplane.demo.bean;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.airplane.demo.entity.Airplane;
import com.airplane.demo.utils.GlobalEnums.RunwayState;
import com.airplane.demo.utils.RunwayService;


@Configuration
public class Config {
	
	@Bean
	public PriorityQueue<Airplane> waitingQ() {
		return new PriorityQueue<>();
	}
	
	@Bean
	public Queue<Airplane> completedQ() {
		return new LinkedList<>();
	}
	
	@Bean
	public Map<Long, Airplane> saveAirplane() {
		return new HashMap<>();
	}
	
	@Bean
	public RunwayService getRunwayService() {
		return new RunwayService(RunwayState.FREE, 1);
	}
}
