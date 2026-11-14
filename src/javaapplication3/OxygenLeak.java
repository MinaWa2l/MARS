
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
     this.oxygenloss=oxygenloss;
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
        return "oxygen Leak detected!";
    }
}
