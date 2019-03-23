//package com.airplane.demo.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "airplane")
//public class Airplane {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;
//	
//	@Column(name = "name", nullable = false)
//	private String name;
//	
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//	
//	public Long getid() {
//		return id;
//	}
//}


package com.airplane.demo.entity;

import com.airplane.demo.utils.GlobalEnums.AirplaneType;



public class Airplane implements Comparable<Airplane> {
    private final long id;
    private final String name;
    private final AirplaneType type;
    private final String location;
    private final int priority;
    
    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public AirplaneType getType() {
        return type;
    }
    
    public String getLocation() {
        return location;
    }
    
    public int getPriority() {
        return priority;
    }
    
    private Airplane(AirplaneBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.location = builder.location;
        this.priority = builder.priority;
    }
    
    @Override
    public String toString() {
        return "Airplane ID = " + this.id + " Name = " + this.name + " Type = " + this.type + " Priority = " + this.priority + " Location = " + this.location;
    }
    
    //check if they are same Type Landing or Takeoff
    //if same then check the priority-level
  		//if same then return 0
      	//else return a.priority - this.priority for descending order
    //else 
  	//if "this" is Landing return -1 for descending order
  	//else return 1
    @Override
    public int compareTo(Airplane a) {
    	if(this.getType() == a.getType()) {
            return a.getPriority() - this.getPriority();
        } else {
            if(this.getType() == AirplaneType.LANDING) return -1;
            else if(a.getType() == AirplaneType.LANDING) return 1;
            else return 0;
        }
    }
    
    public static class AirplaneBuilder {
        private final long id;
        private final String name;
        private final AirplaneType type;
        private String location;
        private int priority;
        
        public AirplaneBuilder(long id, String name, AirplaneType type) {
            this.id = id;
            this.name = name;
            this.type = type;
        }
        
        public AirplaneBuilder whichLocation(String location) {
            this.location = location;
            return this;
        }
        
        public AirplaneBuilder bumpPriority(int priority) {
            this.priority = priority;
            return this;
        }
        
        public Airplane buildAirplane() {
            Airplane airplane = new Airplane(this);
            return airplane;
        }
    }
}
