package javaapplication3;

/**
 *
 * @author HP
 */
public class RoverBreakdown extends Emergency {
    private ExplorationRover affectedRover;
    public RoverBreakdown(Location location, ExplorationRover affectedRover){
        super(location);
        this.affectedRover=affectedRover;
    }
    @Override
    public boolean isRoverBreakdown(){
        return true;
    }
    public ExplorationRover getAffectedRover(){
        return affectedRover;
    }
    @Override
    public String getDescription(){
        return "Rover Breakdown detected!";
    }
}
