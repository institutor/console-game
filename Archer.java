public class Archer extends Adventurer {
    int arrows, maxArrows;

    public Archer(String name, int hp, int arrows) {
        super(name, hp);
        maxArrows = 20;
        this.arrows = arrows;
    }

    public Archer(String name, int hp) {
        this(name, hp, 10);
    }

    public Archer(String name) {
        this(name, 23);
    }

    public Archer() {
        this("Legolas");
    }

    public String getSpecialName() {
        return "Arrows";
    }

    public int getSpecial() {
        return arrows;
    }

    public void setSpecial(int n) {
        arrows = n;
    }

    public int getSpecialMax() {
        return maxArrows;
    }

    public String attack(Adventurer other) {
        if (arrows <= 0) {
            return RED + this + " has no arrows left to shoot!" + "instead " + this + support();
        }
        int damage = (int) (Math.random() * 6) + 1;
        other.applyDamage(damage);
        arrows--;
        return RED + this + " shot an arrow at " + UNDERLINE + other + NO_UNDERLINE +", dealing " + UNDERLINE + damage + " points of damage" + NO_UNDERLINE + " and losing an arrow." + RESET;
    }

    public String specialAttack(Adventurer other) {
        if (getSpecial() >= 5) {
            setSpecial(getSpecial() - 5);
            int damage = (int) (Math.random() * 10) + 10;
            other.applyDamage(damage);
            return CYAN + this + " used their ultimate skill, " + UNDERLINE + RED + "REAAALLY REALLY BIG" + CYAN + " Arrow" + NO_UNDERLINE + "! It hit " + UNDERLINE + other + NO_UNDERLINE + ", dealing " + UNDERLINE + damage + " points of damage" + NO_UNDERLINE + "." + RESET;
        } else {
            return RED + "Not enough arrows to use the ultimate skill. Instead, " + attack(other) + RESET;
        }
    }

    public String support(Adventurer other) {
        return GREEN + this + " shares their grape juice with " + UNDERLINE + other + NO_UNDERLINE +", restoring " + UNDERLINE + other.restoreSpecial(3) + " " + other.getSpecialName() + NO_UNDERLINE + "." + RESET;
    }

    public String support() {
        setHP(getHP() + 1);
        return GREEN + this + " refills their quiver and drinks some grape juice and takes a quick nap (in the middle of the fight?!?!), restoring " + UNDERLINE + restoreSpecial(5) + " " + getSpecialName() + NO_UNDERLINE + " and " + UNDERLINE + " 1 HP" + NO_UNDERLINE + "." + RESET;
    }
}
