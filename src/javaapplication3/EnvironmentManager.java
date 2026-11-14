package javaapplication3;
/**
 *
 * @author HP
 */
public class EnvironmentManager {
    private EnvironmentCondition currentCondition;
     public EnvironmentManager() {
        currentCondition = new NormalConditions();
    }
public void setCondition(EnvironmentCondition condition) {
        currentCondition = condition;

        System.out.println(
            "Environment changed to: " + condition.getName()
        );
    }
 public EnvironmentCheck canStart(Missions mission) {
        return currentCondition.canStart(mission);
    }
 public EnvironmentCheck canContinue(Missions mission) {
        return currentCondition.canContinue(mission);
    }
  public EnvironmentCheck canControl(Agent agent) {
        return currentCondition.canControl(agent);
    }
    public EnvironmentCondition getCurrentCondition() {
        return currentCondition;
    }
}
