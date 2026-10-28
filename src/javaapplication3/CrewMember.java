
package javaapplication3;



 public abstract class CrewMember implements Agent {
  protected String name;
  protected String location;
  protected String Skills;
  protected int id;
  protected int health;
  protected int fatigue;
  protected boolean available;
  
  CrewMember(String name,String location,String Skills,int id,int health,int fatigue){
      this.available=true;
      this.name=name;
      this.location=location;
      this.Skills=skills;
      this.id=id;
      this.health=health;
      this.fatigue=fatigue;
      
  }
  
  
    
}
