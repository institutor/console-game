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
            System.out.println(YELLOW + "Type: attack (or a) / special (or sp) / support (or su) / quit" + RESET);
            action = scanner.nextLine();
            if (action.equalsIgnoreCase("quit")) {
                System.out.println(RED + "Game Over. Thanks for playing!" + RESET);
                break;
            }
            if ((action.equalsIgnoreCase("attack")) || action.equalsIgnoreCase("a")) {
                System.out.println(player.attack(enemy));
            } else if ((action.equalsIgnoreCase("special")) || action.equalsIgnoreCase("sp")) {
                System.out.println(player.specialAttack(enemy));
            } else if ((action.equalsIgnoreCase("support")) || action.equalsIgnoreCase("su")) {
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
            enemyTurn(enemy, player);
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
                System.out.println(RED + "Invalid role. Please enter a valid role (berserker, mage, alchemist, archer)." + RESET);
                Scanner scanner = new Scanner(System.in);
                role = scanner.nextLine();
                return createAdventurer(role);
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
    private static void enemyTurn(Adventurer enemy, Adventurer player) {
        int action = (int)(Math.random()*3);
        switch (action) {
            case 0: // Regular attack
                System.out.println(CYAN + enemy.getName() + " attacks:" + RESET);
                System.out.println(enemy.attack(player));
                break;
            case 1: // for sp attack
                if (enemy.getSpecial() >= 5) { 
                    System.out.println(CYAN + enemy.getName() + " attempts to use their special attack:" + RESET);
                    System.out.println(enemy.specialAttack(player));
                } else { // regular attack from enemy
                    System.out.println(CYAN + enemy.getName() + " doesn't have enough special points for a special attack and attacks instead:" + RESET);
                    System.out.println(enemy.attack(player));
                }
                break;
            case 2: // for supporting
                System.out.println(CYAN + enemy.getName() + " supports themselves:" + RESET);
                System.out.println(enemy.support());
                break;
            default:
                System.out.println(CYAN + enemy.getName() + " attacks:" + RESET);
                System.out.println(enemy.attack(player));
                break;
        }
    }
}
