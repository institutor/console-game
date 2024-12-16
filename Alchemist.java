public class Alchemist extends Adventurer {
    int potion, maxPotion;
    String preferredPotion;

    public Alchemist(String name, int hp, String potion) {
        super(name, hp);
        maxPotion = 10;
        this.potion = maxPotion / 2;
        preferredPotion = potion;
    }

    public Alchemist(String name, int hp) {
        this(name, hp, "Weakness Potion");
    }

    public Alchemist(String name) {
        this(name, 21);
    }

    public Alchemist() {
        this("Alchemist");
    }

    public String getSpecialName() {
        return "Potion";
    }

    public int getSpecial() {
        return potion;
    }

    public void setSpecial(int n) {
        potion = n;
    }

    public int getSpecialMax() {
        return maxPotion;
    }

    public String attack(Adventurer other) {
        int damage = (int) (Math.random() * 4) + 1;
        other.applyDamage(damage);
        restoreSpecial(2);
        if (getSpecial()>= 5) {
            System.out.println(YELLOW + "Special Attack is ready! Type special on next move to use special attack." + RESET);
        }
        return RED + this + " used a " + UNDERLINE + preferredPotion + NO_UNDERLINE + " on " + other + " dealing " + UNDERLINE + damage + " points of damage" + NO_UNDERLINE +". They then continuing brewing their special potion, getting closer to their special attack." + RESET;
    }

    public String specialAttack(Adventurer other) {
        if (getSpecial() >= 5) {
            setSpecial(getSpecial() - 5);
            other.setDamageMultiplier(2);
            return CYAN + this + " used their ultimate potion, " + UNDERLINE + preferredPotion + NO_UNDERLINE +"! It weakened " + other + ", making them take " + UNDERLINE + "twice as much damage" + NO_UNDERLINE + "." + RESET;
        } else {
            return RED + "Not enough potions to use the ultimate potion. Instead, " + attack(other) + RESET;
        }
    }

    public String support(Adventurer other) {
        return GREEN + this + " gives a potion to " + other + ", restoring " + UNDERLINE + other.restoreSpecial(5) + " " + other.getSpecialName() + NO_UNDERLINE + "." + RESET;
    }

    public String support() {
        int hp = 2;
        setHP(getHP() + hp);
        return GREEN + this + " drinks a potion to restore " + UNDERLINE + restoreSpecial(8) + " " + getSpecialName() +NO_UNDERLINE + " and " + UNDERLINE + hp + " HP" + NO_UNDERLINE + "." + RESET;
    }
}
