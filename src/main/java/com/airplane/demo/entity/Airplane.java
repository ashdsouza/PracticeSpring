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

enum AirplaneType {
    LANDING, TAKEOFF;
}

public class Airplane implements Comparable<Airplane> {
    private final long id;
    private final String name;
    private final String type;
    private final String location;
    
    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public String getLocation() {
        return location;
    }
    
    private Airplane(AirplaneBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
        this.location = builder.location;
    }
    
    @Override
    public String toString() {
        return "Airplane ID = " + this.id + " Name = " + this.name + " Type = " + this.type + " Location = " + this.location;
    }
    
    @Override
    public int compareTo(Airplane a) {
        String t1 = this.getType();
        String t2 = a.getType();
        if(t1.equals(t2)) {return 0;}
        else {return t1.equals(AirplaneType.LANDING) ? 1 : -1;}
    }
    
    public static class AirplaneBuilder {
        private final long id;
        private final String name;
        private final String type;
        private String location;
        
        public AirplaneBuilder(long id, String name, String type) {
            this.id = id;
            this.name = name;
            this.type = type;
        }
        
        public AirplaneBuilder whichLocation(String location) {
            this.location = location;
            return this;
        }
        
        public Airplane buildAirplane() {
            Airplane airplane = new Airplane(this);
            return airplane;
        }
    }
}
