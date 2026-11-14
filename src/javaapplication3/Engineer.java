
package javaapplication3;



public class Engineer extends CrewMember {

    public Engineer(String name, String id, String location,String[] skills) {
        super(name, id, location,skills);
    }

    @Override
    public void performDuty() {
        System.out.println("Repairing equipment.");
    }

    @Override
    public boolean canPerform(Missions mission) {
        return mission instanceof RepairMission ||
               mission instanceof RescueMission;
    }

    @Override
    public boolean canRespondTo(Emergency emergency) {
        return emergency.isOxygenLeak() || emergency.isFire() || emergency.isRoverBreakdown();
    }
}
