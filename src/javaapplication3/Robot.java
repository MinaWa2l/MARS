

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;
import java.util.Objects;
/**
 *
 * @author lenovo
 */
abstract class Robot {
   protected final String ID;
   protected final String Name;
   // FINAL BEC SINCE IT CREATED IT NEVER CHANGES
   protected int batteryLevel;
   protected String currentLocation;
   protected boolean availability;
   protected boolean healthy;
   public Robot(String currentLocation,String Name,String ID){
        this.Name=Objects.requireNonNull(Name, "Robot Name cannot be null");
        //We use Objects.requireNonNull(Name, "Robot Name cannot be null"); in the constructor to guard against null values
        this.ID=Objects.requireNonNull(ID, "Robot ID cannot be null");
        this.currentLocation=currentLocation;
        healthy=true;
        availability=true;
        batteryLevel=100;
    }
    public String getId() { 
        return ID; }
    public String getName() {
        return Name; }
    public double getBatteryLevel() {
        return batteryLevel; }
    public boolean ishealthy() {
        return healthy; }
    public void sethealthy(boolean healthy) {
        this.healthy = healthy; }
    public boolean isAvailable() {
        return availability; }
    public void setAvailable(boolean availability) {
        this.availability = availability; }
    public String getCurrentLocation() {
        return currentLocation; }
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation; }
    public boolean isOperational() {
        return healthy && batteryLevel > 0.0 && availability;
    }

    public void consumeBattery(double amount) {
        if (amount < 0) return;
        this.batteryLevel = (int) Math.max(0.0, this.batteryLevel - amount);
    }
//Math.max(0.0, this.batteryLevel - amount) to prevent the battery level from ever dropping below zero
    public void rechargeBattery(double amount) {
        if (amount < 0) return;
        this.batteryLevel = (int) Math.min(100.0, this.batteryLevel + amount);
    }
    public abstract boolean canPerformMission(String missionType);
    public abstract boolean canHandleEmergency(String emergencyType);
}
