package javaapplication3;


public abstract class Emergency {

    private Location location;
    private boolean resolved;
    private String failureReason = "";

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
    
    public void setFailureReason(String reason) {
        failureReason = reason;
    }

    public String getFailureReason() {
        return failureReason;
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

