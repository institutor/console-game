// Collaborators: Jiewen Huang, Calvin Ye

public abstract class Adventurer{
  // ANSI color codes to make stuff more readable
  public static final String RESET = "\u001B[0m";   // default
  public static final String RED = "\u001B[31m";     // For damage messages
  public static final String GREEN = "\u001B[32m";   // For healing or support
  public static final String YELLOW = "\u001B[33m";  // For warnings
  public static final String CYAN = "\u001B[36m";    // For general info
  public static final String UNDERLINE = "\u001B[4m";
  public static final String NO_UNDERLINE = "\u001B[24m";
  private String name;
  private int HP,maxHP;
  private double damageMultiplier = 1.0;


  /*There is no no-arg constructor. Be careful with your subclass constructors.*/
  
  public Adventurer(String name){
      this(name, 25);
  }

  public Adventurer(String name, int hp){
      this.name = name;
      this.HP = hp;
      this.maxHP = hp;
  }

  //concrete method written using abstract methods.
  //refill special resource by amount, but only up to at most getSpecialMax()
  public int restoreSpecial(int n){
      if( n > getSpecialMax() - getSpecial()){
              n = getSpecialMax() - getSpecial();
      }
      setSpecial(getSpecial()+n);
      return n;
  }

  //Abstract methods are meant to be implemented in child classes.

  /*
    all adventurers must have a custom special
    consumable resource (mana/rage/money/witts etc)
  */
  //give it a short name (fewer than 13 characters)
  public abstract String getSpecialName();
  //accessor methods
  public abstract int getSpecial();
  public abstract void setSpecial(int n);
  public abstract int getSpecialMax();

  /*
    all adventurers must have a way to attack enemies and
    support their allys
  */
  //hurt or hinder the target adventurer
  public abstract String attack(Adventurer other);

  //heal or buff the target adventurer
  public abstract String support(Adventurer other);

  //heal or buff self
  public abstract String support();

  //hurt or hinder the target adventurer, consume some special resource
  public abstract String specialAttack(Adventurer other);


  /*
    standard methods
  */
  public void applyDamage(int amount){
    int actualDamage = (int) (amount * damageMultiplier);
        this.HP -= actualDamage;
        if (this.HP <= 0) {
            this.HP = 0;
            System.out.println(this.name + " has been defeated!");
        }
  }

  

  //toString method
  public String toString(){
    return this.getName();
  }

  //Get Methods
  public String getName(){
    return name;
  }

  public int getHP(){
      return HP;
  }

  public int getmaxHP(){
      return maxHP;
  }
  public void setmaxHP(int newMax){
        maxHP = newMax;
  }

  //Set Methods
  public void setHP(int health){
      this.HP = health;
  }

  public void setName(String s){
      this.name = s;
  }
  public void setDamageMultiplier(double multiplier) {
    this.damageMultiplier = multiplier;
}

  public double getDamageMultiplier() {
    return damageMultiplier;
  }
}