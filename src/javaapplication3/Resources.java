
package javaapplication3;


public class Resources {
    protected int oxygen,water,food,fuel,battery,medical,spare;
    
    
    public int getOxygen(){
        return oxygen;
    }
    public void addOxygen(int amount){
        oxygen+=amount;
    }
    public int removeOxygen(int amount){
        if(amount>=oxygen){
            int removed=oxygen;
            oxygen=0;
            return removed;
        }
        else{
            oxygen-=amount;
            return amount;
        } 
    }
    
    
        public int getWater(){
        return water;
    }
    public void addWater(int amount){
        water+=amount;
    }
    public int removeWater(int amount){
        if(amount>=water){
            int removed=water;
            water=0;
            return removed;
        }
        else{
            water-=amount;
            return amount;
        } 
    }
    
    
    
            public int getFood(){
        return food;
    }
    public void addFood(int amount){
        food+=amount;
    }
    public int removeFood(int amount){
        if(amount>=food){
            int removed=food;
            food=0;
            return removed;
        }
        else{
            food-=amount;
            return amount;
        } 
    }
    
    public int getFuel(){
        return fuel;
    }
    public void addFuel(int amount){
        fuel += amount;
    }
    public int removeFuel(int amount){
        if(amount >= fuel){
            int removed = fuel;
            fuel = 0;
            return removed;
        } else {
            fuel -= amount;
            return amount;
        }
    }

    public int getBattery(){
        return battery;
    }
    public void addBattery(int amount){
        battery += amount;
    }
    public int removeBattery(int amount){
        if(amount >= battery){
            int removed = battery;
            battery = 0;
            return removed;
        } else {
            battery -= amount;
            return amount;
        }
    }

    public int getMedical(){
        return medical;
    }
    public void addMedical(int amount){
        medical += amount;
    }
    public int removeMedical(int amount){
        if(amount >= medical){
            int removed = medical;
            medical = 0;
            return removed;
        } else {
            medical -= amount;
            return amount;
        }
    }

    public int getSpare(){
        return spare;
    }
    public void addSpare(int amount){
        spare += amount;
    }
    public int removeSpare(int amount){
        if(amount >= spare){
            int removed = spare;
            spare = 0;
            return removed;
        } else {
            spare -= amount;
            return amount;
        }
    }
    
    
    
    
}
