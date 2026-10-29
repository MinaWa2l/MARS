/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3;

/**
 *
 * @author HP
 */
public class NormalConditions implements EnvironmentCondition{
    @Override
    public String getName(){
        return "Normal Condition";
    }
    @Override    
    public EnvironmentCheck canStart(Missions mission){
            return EnvironmentCheck.allow();
        }
    @Override
    public EnvironmentCheck canContinue(Missions mission){
            return EnvironmentCheck.allow();
        }
    @Override
    public EnvironmentCheck canControl(Agent agent){
            return EnvironmentCheck.allow();
        }
}
