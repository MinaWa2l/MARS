
package javaapplication3;


public class StorageFacility extends Facility{
    protected boolean equipement;
     public StorageFacility(String location,int capacity,Resources R,boolean equipement){
        super(location,capacity,R);
        this.equipement=equipement;
       
    }
     
     public boolean hasEquipment() {
    return equipement;
}
    
}