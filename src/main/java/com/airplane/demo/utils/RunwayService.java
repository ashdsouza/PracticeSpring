package com.airplane.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.airplane.demo.bean.Config;
import com.airplane.demo.entity.Airplane;
import com.airplane.demo.entity.AirportStatus;
import com.airplane.demo.utils.GlobalEnums.AirplaneType;
import com.airplane.demo.utils.GlobalEnums.RunwayState;

public class RunwayService {
	
	@Autowired
	private Config config;
	private RunwayState state;
	private int nums;
	
	public RunwayService() {}
	
	public RunwayService(RunwayState state, int nums) {
		this.state = state;
		this.nums = nums;
	}
	
	public AirportStatus flightStatus() {
		AirportStatus status = new AirportStatus();
		
		if(!config.waitingQ().isEmpty()) {
			String waitingLanding = "Waiting for landing ";
			String waitingTakeoff = "Waiting for takeoff ";
			for (Airplane item: config.waitingQ()) {
				if(item.getType() == AirplaneType.LANDING) {
					waitingLanding = waitingLanding + item.getId() + " ";
				} else {
					waitingTakeoff = waitingTakeoff + item.getId() + " ";
				}
			}
			status.setWaitingForLanding(waitingLanding);
			status.setWaitingForTakeOff(waitingTakeoff);
		} else {
			status.setWaitingForLanding("Waiting for landing none");
			status.setWaitingForTakeOff("Waiting for takeoff none");
		}
		
		if(!config.completedQ().isEmpty()) {
			String completedLanding = "Successful landing ";
			String completedTakeoff = "Successful takeoff ";
			for (Airplane item: config.completedQ()) {
				if(item.getType() == AirplaneType.LANDING) {
					completedLanding = completedLanding + item.getId() + " ";
				} else {
					completedTakeoff = completedTakeoff + item.getId() + " ";
				}
			}
			status.setSuccessfulLanding(completedLanding);
			status.setSuccessfulTakeOff(completedTakeoff);
		}else {
			status.setSuccessfulLanding("Successful landing none");
			status.setSuccessfulTakeOff("Successful takeoff none");
		}
		return status;
	}
	
	synchronized public void handleFlight() {
		
        try {
//            System.out.println("Runways state = " + state);
//            semaphore.acquire();
//            System.out.println(airplane.getName() + " : got lock...");
//        	if(!config.waitingQ().isEmpty()) config.waitingQ().add(airplane);
//        	else { 
        		try {
        			System.out.println("Runways state = " + state + " queue size = " + config.waitingQ().size());
        			if(state.equals(RunwayState.FREE) && !config.waitingQ().isEmpty()) {
        				Airplane airplane = config.waitingQ().poll();
        				System.out.println("Flight of " + airplane.getId());
        				
        				state = RunwayState.BUSY;
        				Thread.sleep(10000);
        				state = RunwayState.FREE;
        				
        				System.out.println("Flight of " + airplane.getId() + " completed");
        				config.completedQ().add(airplane);
        			}
//	                for(int i=0; i<runways.size(); i++) {
//	                    //check if runways is free
//	                    if(runways.get(i).state == RunwayState.FREE) {
//	                        //set it to busy
//	                        System.out.println(airplane.getName() + " : got free runway...# " + i);
//	                        runways.get(i).state = RunwayState.BUSY;
//	                        Thread.sleep(10000);
//	
//	                        System.out.println(airplane.getName() + " : completed and added to completed List");
//	                        completedQ.add(this.airplane);
//	                        //set this runway to free once done
//	                        runways.get(i).state = RunwayState.FREE;
//	                    }
//	                }
	            } finally {
	//                System.out.println(airplane.getName() + " : releasing lock...");
	//                semaphore.release();
	//                System.out.println(airplane.getName() + " : released lock...DONE");
	            }
//        	}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}
