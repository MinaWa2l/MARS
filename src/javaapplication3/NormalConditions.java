
package javaapplication3;


public class NormalConditions implements EnvironmentCondition{
    @Override
    public String getName(){
        return "Normal Condition";
    }
    @Override    
    public EnvironmentCheck canStart(Missions mission){
            return EnvironmentCheck.allow();
        }
    @Override
    public EnvironmentCheck canContinue(Missions mission){
            return EnvironmentCheck.allow();
        }
    @Override
    public EnvironmentCheck canControl(Agent agent){
            return EnvironmentCheck.allow();
        }
}
