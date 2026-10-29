
package javaapplication3;



public class Medical extends CrewMember {

    public Medical(String name, String id, String location) {
        super(name, id, location);
    }

    @Override
    public void performDuty() {
        System.out.println("Providing medical care.");
    }

    @Override
    public boolean canPerform(Missions mission) {
        return mission.isRescueMission();
    }

    @Override
    public boolean canRespondTo(Emergency emergency) {
        return emergency.isMedicalEmergency();
    }
}
