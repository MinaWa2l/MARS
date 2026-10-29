
package javaapplication3;


public abstract class Facility implements Location{
    protected String location;
    protected int capacity;
    protected boolean status;
    protected Resources R;
    
    public Facility(String location, int capacity,Resources R) {
        this.location = location;
        this.capacity = capacity;
        this.status = true;
        this.R = R;
    }
    
    public void setLocation(String location){
        this.location=location;
    }
    
    public  String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity >= 0) {
            this.capacity = capacity;
        }
    }

    public boolean isOperational() {
        return status;
    }

    public void setOperational(boolean status) {
        this.status = status;
    }

    public Resources getResources() {
        return R;
    }

    
    
    
    
    
}
