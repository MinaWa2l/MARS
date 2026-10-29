package javaapplication3;
public class Scientist extends CrewMember {

    public Scientist(String name, String id, String location) {
        super(name, id, location);
    }

    @Override
    public void performDuty() {
        System.out.println("Conducting research.");
    }

    @Override
    public boolean canPerform(Missions mission) {
        return mission.isExplorationMission()
                || mission.isResearchMission();
    }

    @Override
    public boolean canRespondTo(Emergency emergency) {
        return false;
    }
}
