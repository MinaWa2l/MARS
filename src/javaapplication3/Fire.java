package javaapplication3;

/**
 *
 * @author HP
 */
public class Fire extends Emergency {
    private Laboratory  affectedLaboratory;
    public Fire(Location location,Laboratory affectedLaboratory){
        super(location);
        this.affectedLaboratory = affectedLaboratory;
    }
    @Override
    public boolean isFire(){
        return true;
    }
    public Laboratory getAffectedLaboratory(){
        return affectedLaboratory;
    }
    @Override
    public String getDescription() {
        return "Fire detected in Laboratory";
    }
    
}
