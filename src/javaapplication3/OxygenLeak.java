
package javaapplication3;

/**
 *
 * @author HP
 */
public class OxygenLeak extends Emergency{
    private Habitat affectedHabitat;
    private double oxygenloss;
    
    public OxygenLeak(Location location, Habitat affectedHabitat, double oxygenLoss){
     super(location);
     this.affectedHabitat = affectedHabitat;
     this.oxygenloss=oxygenLoss;
    }
    @Override
    public boolean isOxygenLeak(){
        return true;
    }
    public Habitat getAffectedHabitat(){
        return affectedHabitat;
    }
    public double getOxygenLoss(){
        return oxygenloss;
    }
    @Override
    public String getDescription(){
        return "Oxygen Leak detected in " + affectedHabitat.getLocation() + "!";
    }
}
