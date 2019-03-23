package com.airplane.demo.entity;

public class AirportStatus {
	
	private Integer time;
	private Integer inFlightTakeOff;
	private Integer inFlightLanding;
	private String waitingForTakeOff;
	private String waitingForLanding;	
	private String successfulTakeOff;
	private String successfulLanding;
	
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public Integer getInFlightTakeOff() {
		return inFlightTakeOff;
	}
	public void setInFlightTakeOff(Integer inFlightTakeOff) {
		this.inFlightTakeOff = inFlightTakeOff;
	}
	public Integer getInFlightLanding() {
		return inFlightLanding;
	}
	public void setInFlightLanding(Integer inFlightLanding) {
		this.inFlightLanding = inFlightLanding;
	}
	public String getWaitingForTakeOff() {
		return waitingForTakeOff;
	}
	public void setWaitingForTakeOff(String waitingForTakeOff) {
		this.waitingForTakeOff = waitingForTakeOff;
	}
	public String getWaitingForLanding() {
		return waitingForLanding;
	}
	public void setWaitingForLanding(String waitingForLanding) {
		this.waitingForLanding = waitingForLanding;
	}
	public String getSuccessfulTakeOff() {
		return successfulTakeOff;
	}
	public void setSuccessfulTakeOff(String successfulTakeOff) {
		this.successfulTakeOff = successfulTakeOff;
	}
	public String getSuccessfulLanding() {
		return successfulLanding;
	}
	public void setSuccessfulLanding(String successfulLanding) {
		this.successfulLanding = successfulLanding;
	}
}
