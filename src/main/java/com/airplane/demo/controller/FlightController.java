package com.airplane.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airplane.demo.entity.Airplane;
import com.airplane.demo.entity.AirportStatus;
//import com.airplane.demo.repository.AirplaneRepository;

@RestController
@RequestMapping("/v1/api")
public class FlightController {
	@Autowired
//	private AirplaneRepository repository;
	
	/**
	 * Send Airplane for takeoff
	 */
	@PostMapping("/takeoff/airplane/{id}")
	public ResponseEntity<Boolean> sendAirplaneToTakeOff(@PathVariable(value = "id") Long airplaneId) throws Exception {
		//Check to see if airplane exists
//		Airplane airplane = repository.findById(airplaneId).orElseThrow(() -> new Exception("Airplane not found :: " + airplaneId));
		
		//Add yourself to Waiting to TakeOff Queue
		//return true
		return ResponseEntity.ok().body(true);
		
		//while(entries in Waiting to TakeOff Queue)
		//check to see if Runway is empty --> Check Inflight for Landing and TakeOff
			//if both empty
				//if Waiting for Landing is empty
					//add yourself to Inflight TakeOff Queue
					//wait 10 sec 
					//add yourself to TakeOff complete List
					//break of loop
				//else 
					//wait for (10 * #entries in Waiting Queue for Landing + 10 * #entries before you in Wating Queue for TakeOff) seconds
					//check all above conditions again
			//else if Inflight TakeOff Queue or Inflight Landing Queue is not empty
				//wait for 10 sec
				//check all above conditions again
		
		
		
		//Scenario 1: Airplane is first in Waiting, No plane in Inflight TakeOff and No Waiting for Landing and No Inflight Landing --> immediately move to Inflight
		//Scenario 2: Airplane is first in Waiting, a plane in Inflight TakeOff --> wait for 10 seconds , check Scenario 3
		//Scenario 3: Airplane is first in Waiting, No plane in Inflight TakeOff, a plane Waiting for Landing --> wait for 10 sec, check Scenario 4
		//Scenario 4: Airplane is first in Waiting, No plane in Inflight TakeOff, No Waiting for Landing, a plane Inflight Landing --> wait 10 sec, check Scenario 1
		
		
	}
	
	/**
	 * Send Airplane for landing
	 */
	@PostMapping("/landing/airplane/{id}")
	public ResponseEntity<Boolean> sendAirplaneToLanding(@PathVariable(value = "id") Long airplaneId) throws Exception {
		//Check to see if airplane exists
//		Airplane airplane = repository.findById(airplaneId).orElseThrow(() -> new Exception("Airplane not found :: " + airplaneId));
		
		//Add yourself to Waiting to TakeOff Queue
		//return true
		return ResponseEntity.ok().body(true);
		
		//Add yourself to Waiting to Land Queue
		//check to see if Runway is empty --> Check Inflight for Landing and TakeOff
		//Scenario 1: Airplane is first in Waiting, No plane in Inflight TakeOff and No Waiting for Landing and No Inflight Landing --> immediately move to Inflight
		//Scenario 2: Airplane is first in Waiting, a plane in Inflight TakeOff --> wait for 10 seconds , check Scenario 3
		//Scenario 3: Airplane is first in Waiting, No plane in Inflight TakeOff, a plane Waiting for Landing --> wait for 10 sec, check Scenario 4
		//Scenario 4: Airplane is first in Waiting, No plane in Inflight TakeOff, No Waiting for Landing, a plane Inflight Landing --> wait 10 sec, check Scenario 1
	}
	
	/**
	 * Get Status of Runway
	 */
	@GetMapping("/status")
	public ResponseEntity<AirportStatus> statusOfFlights() {
		AirportStatus status = new AirportStatus();
		//get counts from all the Queues and Lists 
		//set the entries in AirportStatus object
		
		status.setInFlightLanding(10);
		status.setInFlightTakeOff(1);
		status.setSuccessfulLanding(10);
		status.setSuccessfulTakeOff(1);
		status.setWaitingForLanding(5);
		status.setWaitingForTakeOff(15);
		status.setTime(5);
		
		//return object
//		return ResponseEntity.ok().body(status);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
}