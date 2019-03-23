package com.airplane.demo.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airplane.demo.bean.Config;
import com.airplane.demo.entity.Airplane;
import com.airplane.demo.entity.AirportStatus;
import com.airplane.demo.utils.GlobalEnums.AirplaneType;
//import com.airplane.demo.repository.AirplaneRepository;

@Component
@RestController
@RequestMapping("/v1/api")
public class FlightController {

//	@Autowired
//	private AirplaneRepository repository;
	
	@Autowired
	private Config config;
	
	//convert String to Airplane object using Builder Pattern 
	public Airplane getObjectFromString(String str) throws JSONException {
		JSONObject jsonObj=new JSONObject(str);
		long id = jsonObj.getLong("id");
		String name=jsonObj.getString("name");
		String type=jsonObj.getString("type");
		AirplaneType typeVal = type.equals("LANDING") ? AirplaneType.LANDING : AirplaneType.TAKEOFF;
		String loc = jsonObj.has("location") ? jsonObj.getString("location") : null;
		int priority = jsonObj.has("priority") ? jsonObj.getInt("priority") : 0;
		
		Airplane air = new Airplane.AirplaneBuilder(id, name, typeVal)
						.whichLocation(loc)
						.bumpPriority(priority)
						.buildAirplane();
		
		return air;
	}
	
	/**
	 * Basic CRUD for Airplanes
	 * @return
	 */
	@GetMapping("/airplanes")
	public ResponseEntity<Object> getAirplanes() {
		return ResponseEntity.ok().body(config.saveAirplane().values());
	}
	
	@PostMapping("/airplanes/add")
	public ResponseEntity<Object> addAirplanes(@RequestBody String airplaneString) throws JSONException {
		Airplane airplane = getObjectFromString(airplaneString);
		config.saveAirplane().put(airplane.getId(), airplane);
		return ResponseEntity.ok().body("Airplane information added");
	}
	
	@PutMapping("/airplanes/update/{id}")
	public ResponseEntity<Object> updateAirplane(@PathVariable(value = "id") Long airplaneId, @RequestBody String airplaneString) throws JSONException {
		config.saveAirplane().remove(airplaneId);
		Airplane airplane = getObjectFromString(airplaneString);
		config.saveAirplane().put(airplaneId, airplane);
		return ResponseEntity.ok().body("Airplane information updated");
	}
	
	@DeleteMapping("/airplanes/delete/{id}")
	public ResponseEntity<Object> deleteAirplane(@PathVariable(value = "id") Long airplaneId) {
		config.saveAirplane().remove(airplaneId);
		return ResponseEntity.ok().body("Airplane information deleted");
	}
	
	/**
	 * Send Airplane for takeoff
	 */
	@PostMapping("/takeoff/airplane/{id}")
	public ResponseEntity<Object> sendAirplaneToTakeOff(@PathVariable(value = "id") Long airplaneId) throws Exception {
		Airplane airplane = config.saveAirplane().get(airplaneId);
		
		if(airplane != null && airplane.getType() == AirplaneType.TAKEOFF) {
			//Add Airplane to Waiting Queue
			config.waitingQ().add(airplane);
			config.getRunwayService().flightStatus();
			config.getRunwayService().handleFlight();
			//return message
			return ResponseEntity.ok().body("Airplane " + airplaneId + " Set to takeoff");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Airplane with ID " + airplaneId + " Does not exist");
		}
		//Check to see if airplane exists
		//Airplane airplane = repository.findById(airplaneId).orElseThrow(() -> new Exception("Airplane not found :: " + airplaneId));
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
	public ResponseEntity<Object> sendAirplaneToLanding(@PathVariable(value = "id") Long airplaneId) throws Exception {
		Airplane airplane = config.saveAirplane().get(airplaneId);
		
		if(airplane != null && airplane.getType() == AirplaneType.LANDING) {
			//Add Airplane to Waiting Queue
			config.waitingQ().add(airplane);
			System.out.println("Added to queue called handleFlight()");
			config.getRunwayService().flightStatus();
			config.getRunwayService().handleFlight();
			//return true
			return ResponseEntity.ok().body("Airplane " + airplaneId + " Set to land");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Airplane with ID " + airplaneId + " Does not exist");
		}
		//Check to see if airplane exists
		//Airplane airplane = repository.findById(airplaneId).orElseThrow(() -> new Exception("Airplane not found :: " + airplaneId));
				
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
