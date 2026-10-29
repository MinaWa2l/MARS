
package javaapplication3;


public class DustStorm implements EnvironmentCondition{
    
    private static String Reason = "Surface operations are not allowed during a dust storm.";
    
    @Override
    public String getName(){
        return "Dust Storm" ;
    }
     @Override    
    public EnvironmentCheck canStart(Missions mission){
            return mission.requiresSurfaceOperations()
                ? EnvironmentCheck.deny(Reason)
                : EnvironmentCheck.allow();
        }
    @Override
    public EnvironmentCheck canContinue(Missions mission){
            return mission.requiresSurfaceOperations()
                ? EnvironmentCheck.deny(Reason)
                : EnvironmentCheck.allow();
        }
    @Override
    public EnvironmentCheck canControl(Agent agent){
            return EnvironmentCheck.allow();
        }
}
