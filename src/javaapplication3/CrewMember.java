package javaapplication3;
public abstract class CrewMember implements Agent {

    protected String name;
    protected String id;
    protected int health;
    protected String location;
    protected int fatigue;
    protected boolean available;

    public CrewMember(String name, String id, String location) {
        this.name = name;
        this.id = id;
        this.location = location;
        this.health = 100;
        this.fatigue = 0;
        this.available = true;
    }

    public abstract void performDuty();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public abstract boolean canPerform(Missions mission);

    @Override
    public abstract boolean canRespondTo(Emergency emergency);
}
