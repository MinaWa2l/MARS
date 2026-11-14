package javaapplication3;


public abstract class Emergency {

    private Location location;
    private boolean resolved;

    public Emergency(Location location) {
        this.location = location;
        this.resolved = false;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    // Emergency identification methods

    public boolean isOxygenLeak() {
        return false;
    }

    public boolean isFire() {
        return false;
    }

    public boolean isRoverBreakdown() {
        return false;
    }

    public boolean isMedicalEmergency() {
        return false;
    }

    public abstract String getDescription();
}

