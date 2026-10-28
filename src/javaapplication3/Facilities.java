
package javaapplication3;


public abstract class Facilities implements Location{
    protected String location;
    protected int capacity;
    protected boolean status;
    protected Resources R;
    protected boolean equipement;
    
    public Facilities(String location, int capacity, boolean status,Resources R, boolean equipement) {
        this.location = location;
        this.capacity = capacity;
        this.status = status;
        this.R = R;
        this.equipement = equipement;
    }
    
    
}
