public class Mage extends Adventurer {
    int mana, maxMana;
    String preferredSpell;

    public Mage(String name, int hp, String spell) {
        super(name, hp);
        maxMana = 20;
        mana = maxMana / 2;
        preferredSpell = spell;
    }

    public Mage(String name, int hp) {
        this(name, hp, "Fireball");
    }

    public Mage(String name) {
        this(name, 21);
    }

    public Mage() {
        this("Merlin");
    }

    public String getSpecialName() {
        return "Mana";
    }

    public int getSpecial() {
        return mana;
    }

    public void setSpecial(int n) {
        mana = n;
    }

    public int getSpecialMax() {
        return maxMana;
    }

    public String attack(Adventurer other) {
        int damage = (int) (Math.random() * 4) + 1;
        other.applyDamage(damage);
        restoreSpecial(3);
        return RED + this + " casted a " + preferredSpell + " at " + other + ", dealing " + UNDERLINE + damage + " points of damage." + NO_UNDERLINE + " Mana restored by 3 points." + RESET;
    }

    public String specialAttack(Adventurer other) {
        if (getSpecial() >= 10) {
            setSpecial(getSpecial() - 10);
            int damage = (int) (Math.random() * 10) + 10;
            other.applyDamage(damage);
            return CYAN + this + " used their ultimate spell, " + preferredSpell + ", hitting " + other + " for " + UNDERLINE + damage + " points of damage." + NO_UNDERLINE + RESET;
        } else {
            return RED + "Not enough mana to cast the ultimate spell. Instead " + attack(other) + RESET;
        }
    }

    public String support(Adventurer other) {
        return GREEN + this + " shares some tea with " + other + " and restores " +UNDERLINE + other.restoreSpecial(5) + " " + other.getSpecialName() + "." + NO_UNDERLINE + RESET;
    }

    public String support() {

        int hp = 2;
        setHP(getHP() + hp);
        return GREEN + this + " drinks tea to restore " + UNDERLINE + restoreSpecial(8) + " Mana" + NO_UNDERLINE + " and " + hp + " HP." + RESET;
    }
}
