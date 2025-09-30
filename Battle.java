import java.util.Scanner;

public class Battle {
    private Player player;
    private Enemy enemy;
    private Scanner scanner;
    private Shop shop;
    private boolean isInitialZeusBattle;
    private int roundNumber;
    
    public Battle(Player player, Enemy enemy, Scanner scanner, Shop shop, boolean isInitialZeus) {
        this.player = player;
        this.enemy = enemy;
        this.scanner = scanner;
        this.shop = shop;
        this.isInitialZeusBattle = isInitialZeus;
        this.roundNumber = 1;
    }
    
    public boolean startBattle() {
        if (isInitialZeusBattle) {
            return initialZeusBattle();
        }
        
        System.out.println("\n=== BATTLE START ===");
        System.out.println("You face " + enemy.getName() + "!");
        System.out.println();
        
        while (player.isAlive() && enemy.isAlive()) {
            showBattleStatus();
            if (!playerTurn()) {
                return false;
            }
            if (!enemy.isAlive()) {
                victory();
                return true;
            }
            enemyTurn();
            roundNumber++;
            player.reduceCooldowns();
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
        
        if (!player.isAlive()) {
            defeat();
            return false;
        }
        
        return true;
    }
    
    public boolean initialZeusBattle() {
        while (player.isAlive() && roundNumber <= 4) {
            System.out.println("\n=== Round " + roundNumber + " ===");
            System.out.println(player.getName() + ": " + player.getCurrentHP() + "/" + player.getMaxHP() + " HP");
            System.out.println("Zeus: Overwhelmingly Powerful");
            
            System.out.println("\n=== YOUR TURN ===");
            System.out.println("1. Attack with all your might!");
            System.out.print("Choice: ");
            scanner.nextInt();
            scanner.nextLine();
            
            int damage = player.getSkillDamage(1);
            System.out.println("You strike Zeus for " + damage + " damage!");
            System.out.println("But it barely scratches the King of Gods...");
            
            System.out.println("\n=== ZEUS'S TURN ===");
            int zeusDamage = 150 + (int)(Math.random() * 100);
            player.takeDamage(zeusDamage);
            System.out.println("Zeus unleashes divine lightning!");
            System.out.println("You take " + zeusDamage + " damage!");
            
            roundNumber++;
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
        return false;
    }
    
    public void showBattleStatus() {
        System.out.println("\n=== Round " + roundNumber + " ===");
        System.out.println("--- BATTLE STATUS ---");
        System.out.println(player.getName() + ": " + player.getCurrentHP() + "/" + player.getMaxHP() + " HP, " +
                         player.getCurrentMP() + "/" + player.getMaxMP() + " MP");
        System.out.println(enemy.getName() + ": " + enemy.getCurrentHP() + "/" + enemy.getMaxHP() + " HP");
    }
    
    public boolean playerTurn() {
        System.out.println("\n=== YOUR TURN ===");
        player.displaySkills();
        System.out.println("4. Use Potion");
        System.out.println("5. Try to Run");
        System.out.print("Your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice >= 1 && choice <= 3) {
            int result = player.useSkill(choice);
            
            if (result == -1) {
                System.out.println("That skill is on cooldown!");
                return playerTurn();
            } else if (result == -2) {
                System.out.println("Not enough MP!");
                return playerTurn();
            } else if (result == 0) {
                return true;
            } else {
                enemy.takeDamage(result);
                System.out.println(enemy.getName() + " takes " + result + " damage!");
            }
        } else if (choice == 4) {
            if (player.getInventory().isEmpty()) {
                System.out.println("You have no potions!");
                return playerTurn();
            }
            
            System.out.println("\n=== SELECT POTION ===");
            for (int i = 0; i < player.getInventory().size(); i++) {
                Potion p = player.getInventory().get(i);
                System.out.println((i + 1) + ". " + p.getName() + " x" + p.getQuantity() + 
                                 " (" + p.getDescription() + ")");
            }
            System.out.print("Choose potion (0 to cancel): ");
            
            int potionChoice = scanner.nextInt();
            scanner.nextLine();
            
            if (potionChoice == 0) {
                return playerTurn();
            } else if (potionChoice > 0 && potionChoice <= player.getInventory().size()) {
                Potion potion = player.getInventory().get(potionChoice - 1);
                potion.use(player);
                
                if (potion.getQuantity() <= 0) {
                    player.getInventory().remove(potionChoice - 1);
                }
            } else {
                System.out.println("Invalid choice!");
                return playerTurn();
            }
        } else if (choice == 5) {
            System.out.println("You cannot run from this battle!");
            return playerTurn();
        }
        
        return true;
    }
    
    public void enemyTurn() {
        System.out.println("\n=== " + enemy.getName().toUpperCase() + "'S TURN ===");
        int enemyDamage = enemy.attack();
        player.takeDamage(enemyDamage);
        System.out.println(enemy.getAttackMessage());
        System.out.println("You take " + enemyDamage + " damage!");
    }
    
    public void victory() {
        System.out.println("\n=== VICTORY! ===");
        System.out.println(enemy.getName() + " is defeated!");
    }
    
    public void defeat() {
        System.out.println("\nYou have been defeated...");
        player.rest();
    }
}