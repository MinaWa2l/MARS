
package javaapplication3;



public class Engineer extends CrewMember {

    public Engineer(String name, String id, String location) {
        super(name, id, location);
    }

    @Override
    public void performDuty() {
        System.out.println("Repairing equipment.");
    }

    @Override
    public boolean canPerform(Missions mission) {
        return mission.isRepairMission();
    }

    @Override
    public boolean canRespondTo(Emergency emergency) {
        return emergency.isOxygenLeak();
    }
}
