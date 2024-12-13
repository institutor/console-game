abstract class Weapon {
    private String name;
    private double damage;
    private int durability;

    public abstract void applyDurabilityDamage(int damage);

    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }

    public int getDurability() {
        return durability;
    }
}
