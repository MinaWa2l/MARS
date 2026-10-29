
package javaapplication3;


public class Laboratory extends Facility{
    protected boolean equipement;
     public Laboratory(String location,int capacity,Resources R,boolean equipement){
        super(location,capacity,R);
        this.equipement=equipement;
       
    }
     
     public boolean hasEquipment() {
    return equipement;
}
}