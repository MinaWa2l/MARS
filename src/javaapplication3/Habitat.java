
package javaapplication3;


public class Habitat extends Facility{
    protected boolean equipement;
    public Habitat(String location,int capacity,Resources R,boolean equipement){
        super(location,capacity,R);
        this.equipement=equipement;
       
    }
    
}
