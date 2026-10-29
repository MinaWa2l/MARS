
package javaapplication3;


public class EnvironmentCheck {
    private boolean allowed;
    private String reason;
    private EnvironmentCheck( boolean allowed , String reason){
    this.allowed=allowed;
    this.reason=reason;
    }
     
    public static EnvironmentCheck allow(){
            return new EnvironmentCheck(true,"");
        }
    public static EnvironmentCheck deny(String reason){
            return new EnvironmentCheck(false,reason);
        }
    public boolean isAllowed(){
        return this.allowed;
    }
    public String getReason(){
        return this.reason;
    }
    
    }
