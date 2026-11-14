
package javaapplication3;


public class Resources {
    protected int oxygen,water,food,fuel,battery,medical,spare;
    
    
    public int getOxygen(){
        return oxygen;
    }
    public void addOxygen(int amount){
        if(amount > 0) oxygen += amount;
    }
    public int removeOxygen(int amount){
        if (amount <= 0) return 0;
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
        if(amount > 0) water += amount;
    }
    public int removeWater(int amount){
        if (amount <= 0) return 0;
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
        if(amount > 0) food += amount;
    }
    public int removeFood(int amount){
        if (amount <= 0) return 0;
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
        if(amount > 0) fuel += amount;
    }
    public int removeFuel(int amount){
        if (amount <= 0) return 0;
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
       if(amount > 0) battery += amount;
    }
    public int removeBattery(int amount){
        if (amount <= 0) return 0;
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
        if(amount > 0) medical += amount;
    }
    public int removeMedical(int amount){
        if (amount <= 0) return 0;
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
        if(amount > 0) spare += amount;
    }
    public int removeSpare(int amount){
        if (amount <= 0) return 0;
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
