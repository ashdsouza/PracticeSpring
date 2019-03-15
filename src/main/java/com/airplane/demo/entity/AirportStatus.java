package com.airplane.demo.entity;

public class AirportStatus {
	
	private Integer time;
	private Integer inFlightTakeOff;
	private Integer inFlightLanding;
	private Integer waitingForTakeOff;
	private Integer waitingForLanding;	
	private Integer successfulTakeOff;
	private Integer successfulLanding;
	
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
	public Integer getWaitingForTakeOff() {
		return waitingForTakeOff;
	}
	public void setWaitingForTakeOff(Integer waitingForTakeOff) {
		this.waitingForTakeOff = waitingForTakeOff;
	}
	public Integer getWaitingForLanding() {
		return waitingForLanding;
	}
	public void setWaitingForLanding(Integer waitingForLanding) {
		this.waitingForLanding = waitingForLanding;
	}
	public Integer getSuccessfulTakeOff() {
		return successfulTakeOff;
	}
	public void setSuccessfulTakeOff(Integer successfulTakeOff) {
		this.successfulTakeOff = successfulTakeOff;
	}
	public Integer getSuccessfulLanding() {
		return successfulLanding;
	}
	public void setSuccessfulLanding(Integer successfulLanding) {
		this.successfulLanding = successfulLanding;
	}
}
