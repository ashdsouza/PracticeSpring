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
			config.getRunwayService().handleFlight();
			//return message
			return ResponseEntity.ok().body("Airplane " + airplaneId + " Set to takeoff");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Airplane with ID " + airplaneId + " Does not exist");
		}
		//Check to see if airplane exists
		//Airplane airplane = repository.findById(airplaneId).orElseThrow(() -> new Exception("Airplane not found :: " + airplaneId));	
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
			config.getRunwayService().handleFlight();
			//return true
			return ResponseEntity.ok().body("Airplane " + airplaneId + " Set to land");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Airplane with ID " + airplaneId + " Does not exist");
		}
		//Check to see if airplane exists
		//Airplane airplane = repository.findById(airplaneId).orElseThrow(() -> new Exception("Airplane not found :: " + airplaneId));
	}
	
	/**
	 * Get Status of Runway
	 */
	@GetMapping("/status")
	public ResponseEntity<AirportStatus> statusOfFlights() {
		//get counts from all the Queues and Lists 
		//set the entries in AirportStatus object
		AirportStatus status = config.getRunwayService().flightStatus();
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
}
