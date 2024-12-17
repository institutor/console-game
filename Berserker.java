public class Berserker extends Adventurer {
  int rage, maxRage;
  String preferredWeapon;

  public Berserker(String name, int hp, String weapon) {
      super(name, hp);
      maxRage = 100;
      rage = maxRage / 2;
      preferredWeapon = weapon;
  }

  public Berserker(String name, int hp) {
      this(name, hp, "Axe");
  }

  public Berserker(String name) {
      this(name, 30);
  }

  public Berserker() {
      this("Berserker");
  }

  public String getSpecialName() {
      return "Rage";
  }

  public int getSpecial() {
      return rage;
  }

  public void setSpecial(int n) {
      rage = n;
  }

  public int getSpecialMax() {
      return maxRage;
  }

  public String attack(Adventurer other) {
      int damage = (int) (Math.random() * 6) + 1;
      other.applyDamage(damage);
      rage += damage;
      if (getSpecial() <= 50) {
        System.out.println(YELLOW + "Ultimate ability is ready! Type 'special' on your next attack to use it!" + RESET);
      }
      return RED + this + " attacked " + other + " with " + preferredWeapon + " and dealt " + UNDERLINE + damage + " points of damage. " + NO_UNDERLINE + this + " also gains some Rage. Current Rage: " + rage + "/" + maxRage + RESET;
  }

  public String specialAttack(Adventurer other) {
      if (getSpecial() >= 50) {
          setSpecial(getSpecial() - 50);
          int damage = (int) (Math.random() * 15) + 6;
          other.applyDamage(damage);
          return CYAN + this + " used their rage to perform a fierce attack on " + other + ", dealing " + UNDERLINE + damage + " points of damage." + NO_UNDERLINE + " Rage: " + rage + "/" + maxRage + RESET;
      } else {
          return RED + "Not enough rage to perform a special move. Instead " + attack(other) + RESET;
      }
  }

  public String support(Adventurer other) {
      return RED + "Berserker does not support others." + RESET;
  }

  public String support() {
      int hp = 5;
      setHP(getHP() + hp);
      rage += 3;
      return GREEN + "Using their incredible regeneration abilities, " + this + " regenerates " + UNDERLINE + hp + " health points. They also gain some rage." + NO_UNDERLINE + " HP: " + getHP() + RESET;
  }
}
