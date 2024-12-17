import java.util.Scanner;

public class Game {
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String NO_UNDERLINE = "\u001B[24m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(YELLOW + "Enter a job/role (berserker, mage, alchemist, archer):" + RESET);
        String role = scanner.nextLine();
        Adventurer player = createAdventurer(role);
        Adventurer enemy = createRandomAdventurer();
        System.out.println(UNDERLINE + "Player:" + NO_UNDERLINE + " " + player.getName() + ", HP: " + player.getHP() + ", " + player.getSpecialName() + ": " + player.getSpecial() + "/" + player.getSpecialMax());
        System.out.println(UNDERLINE + "Enemy:" + NO_UNDERLINE + " " + enemy.getName() + ", HP: " + enemy.getHP() + ", " + enemy.getSpecialName() + ": " + enemy.getSpecial() + "/" + enemy.getSpecialMax());
        String action;
        while (true) {
            System.out.println(YELLOW + "Type: attack / special / support / quit" + RESET);
            action = scanner.nextLine();
            if (action.equalsIgnoreCase("quit")) {
                System.out.println(RED + "Game Over. Thanks for playing!" + RESET);
                break;
            }
            if (action.equalsIgnoreCase("attack")) {
                System.out.println(player.attack(enemy));
            } else if (action.equalsIgnoreCase("special")) {
                System.out.println(player.specialAttack(enemy));
            } else if (action.equalsIgnoreCase("support")) {
                System.out.println(player.support());
            } else {
                System.out.println(RED + "Invalid action. Please try again." + RESET);
                continue;
            }
            displayStatus(player, enemy);
            if (enemy.getHP() <= 0) {
                System.out.println(GREEN + player.getName() + " wins!" + RESET);
                break;
            }
            System.out.println(CYAN + enemy.getName() + "'s turn:" + RESET);
            System.out.println(enemy.attack(player));
            displayStatus(player, enemy);
            if (player.getHP() <= 0) {
                System.out.println(RED + enemy.getName() + " wins!" + RESET);
                break;
            }
        }
        scanner.close();
    }
    private static Adventurer createAdventurer(String role) {
        switch (role.toLowerCase()) {
            case "berserker":
                System.out.println(CYAN + "Player will be a berserker." + RESET);
                return new Berserker("Player", 25, "Axe");
            case "mage":
                System.out.println(CYAN + "Player will be a mage." + RESET);
                return new Mage("Player", 21, "Fireball");
            case "archer":
                System.out.println(CYAN + "Player will be an archer." + RESET);
                return new Archer("Player", 23, 10);
            case "alchemist":
                System.out.println(CYAN + "Player will be an alchemist." + RESET);
                return new Alchemist("Player", 21, "Weakness Potion");
            default:
                System.out.println(RED + "Invalid role. Player will be a berserker." + RESET);
                return new Berserker("Player", 25, "Axe");
        }
    }
    private static Adventurer createRandomAdventurer() {
        int random = (int) (Math.random() * 4);

        switch (random) {
            case 0:
                System.out.println(CYAN + "Enemy will be a berserker." + RESET);
                return new Berserker("Enemy", 25, "Axe");
            case 1:
                System.out.println(CYAN + "Enemy will be a mage." + RESET);
                return new Mage("Enemy", 21, "Hail of Ice");
            case 2:
                System.out.println(CYAN + "Enemy will be an archer." + RESET);
                return new Archer("Enemy", 23, 15);
            case 3:
                System.out.println(CYAN + "Enemy will be an alchemist." + RESET);
                return new Alchemist("Enemy", 21, "Weakness Potion");
            default:
                System.out.println(CYAN + "Enemy will be a berserker." + RESET);
                return new Berserker("Enemy", 25, "Axe");
        }
    }
    private static void displayStatus(Adventurer player, Adventurer enemy) {
        System.out.println(UNDERLINE + "Player:" + NO_UNDERLINE + " " + player.getName() + ", HP: " + player.getHP() + ", " + player.getSpecialName() + ": " + player.getSpecial() + "/" + player.getSpecialMax());
        System.out.println(UNDERLINE + "Enemy:" + NO_UNDERLINE + " " + enemy.getName() + ", HP: " + enemy.getHP() + ", " + enemy.getSpecialName() + ": " + enemy.getSpecial() + "/" + enemy.getSpecialMax());
    }
}
