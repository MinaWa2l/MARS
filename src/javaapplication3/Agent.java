package javaapplication3;
public interface Agent {

    String getName();

    String getLocation();

    void setLocation(String location);

    boolean isAvailable();

    void setAvailable(boolean available);

    boolean canPerform(Missions mission);

    boolean canRespondTo(Emergency emergency);
}
