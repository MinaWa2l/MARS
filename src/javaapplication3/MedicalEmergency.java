package javaapplication3;

/**
 *
 * @author HP
 */
public class MedicalEmergency extends Emergency {
    private CrewMember patient;
    public MedicalEmergency(Location location,CrewMember patient){
        super(location);
        this.patient=patient;    
    }
    @Override
    public boolean isMedicalEmergency(){
        return true;}
    public CrewMember getPatient(){
        return patient;
    }
    @Override 
    public String getDescription(){
        return "Medical Emergency detected!";
    }
}
