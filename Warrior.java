
public class Warrior extends Adventurer {
    int mana, maxMana;
    String preferredLanguage;
  
    /*the other constructors ultimately call the constructor
      with all parameters.*/
    public Warrior(String name, int hp, String language){
      super(name,hp);
      maxMana = 12;
      mana = maxMana/2;
      preferredLanguage = language;
    }
  
    public Warrior(String name, int hp){
      this(name,hp,"c++");  
    }
  
    public Warrior(String name){
      this(name,12);
    }
  
    public Warrior(){
      this("Carmack");
    }
  
    /*The next 8 methods are all required because they are abstract:*/
    public String getSpecialName(){
      return "mana";
    }
  
    public int getSpecial(){
      return mana;
    }
    
    public void setSpecial(int n){
      mana = n;
    }
  
    public int getSpecialMax(){
      return maxMana;
    }
  
    /*Deal 1-6 damage to opponent, restores 2 mana*/
    public String attack(Adventurer other){
      int damage = (int)(Math.random()*6)+1;
      other.applyDamage(damage);
      restoreSpecial(2);
      return this + " attacked "+ other + " and dealt "+ damage +
      " points of damage. They then take a sip of their coffee.";
    }
    /*Deal 3-12 damage to opponent, only if mana is high enough.
      Reduces mana by 8.
    */
    public String specialAttack(Adventurer other){
      if(getSpecial() >= 8){
        setSpecial(getSpecial()-8);
        int damage = (int)(Math.random()*5+Math.random()*5)+3;
        other.applyDamage(damage);
        return this + " used their "+preferredLanguage+
        " skills to hack the matrix. "+
        " This glitched out "+other+" dealing "+ damage +" points of damage.";
      }else{
        return "Not enough mana to use the ultimate code. Instead "+attack(other);
      }
    }
  
    /*Restores 5 special to other*/
    public String support(Adventurer other){
      return "Gives a coffee to "+other+" and restores " 
      +other.restoreSpecial(5)+" "+other.getSpecialName();
    }
    /*Restores 6 special and 1 hp to self.*/
    public String support(){
      int hp = 1;
      setHP(getHP()+hp);
      return this+" drinks a coffee to restores "+restoreSpecial(6)+" "
      + getSpecialName()+ " and "+hp+" HP";
    }
  }
}
