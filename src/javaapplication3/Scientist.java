package javaapplication3;
public class Scientist extends CrewMember {

    public Scientist(String name, String id, String location,String[] skills) {
        super(name, id, location,skills);
    }

    @Override
    public void performDuty() {
        System.out.println("Conducting research.");
    }

    
    @Override
    public boolean canPerform(Missions mission) {
        if (mission instanceof ExplorationMission) {
            return hasSkill("Geology");
        }

        if (mission instanceof ResearchMission) {
            return hasSkill("Research");
        }

        return false;
    }

    @Override
    public boolean canRespondTo(Emergency emergency) {
        return false;
    }
}
