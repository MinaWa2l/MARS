package javaapplication3;
public abstract class CrewMember implements Agent {

    protected String name;
    protected String id;
    protected int health;
    protected String location;
    protected int fatigue;
    protected boolean available;
    protected String[] skills;

    public CrewMember(String name, String id, String location, String[] skills) {
        this.name = name;
        this.id = id;
        this.location = location;
        this.skills = skills;
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
        return available && health > 0 && fatigue < 100;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public abstract boolean canPerform(Missions mission);

    @Override
    public abstract boolean canRespondTo(Emergency emergency);
    
    public boolean hasSkill(String skill) {
    if (skills == null) return false;

    for (String s : skills) {
        if (s != null && s.equalsIgnoreCase(skill)) {
            return true;
        }
    }

    return false;
    }

    public void heal(int amount) {
        if (amount > 0) {
            health = (int) Math.min(100, (long) health + amount);
        }
    }
}
