import java.util.Scanner;

public class Shop {
    public void openShop(Player player, Scanner scanner) {
        System.out.println("\n=== TRAVELING MERCHANT ===");
        System.out.println("Merchant: 'Greetings, traveler! Care to see my wares?'");
        System.out.println("Your Eclipsium: " + player.getEclipsium());
        
        boolean shopping = true;
        while (shopping) {
            System.out.println("\n=== SHOP MENU ===");
            System.out.println("1. Health Potion (50 Eclipsium) - Restores 200 HP");
            System.out.println("2. Mana Potion (40 Eclipsium) - Restores 100 MP");
            System.out.println("3. Full Restore (100 Eclipsium) - Restores all HP and MP");
            System.out.println("4. Leave Shop");
            System.out.print("Your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) {
                if (player.spendEclipsium(50)) {
                    player.addPotion(new Potion("Health Potion", "Restores 200 HP", 1, 200, 0));
                    System.out.println("Purchased Health Potion!");
                } else {
                    System.out.println("Not enough Eclipsium!");
                }
            } else if (choice == 2) {
                if (player.spendEclipsium(40)) {
                    player.addPotion(new Potion("Mana Potion", "Restores 100 MP", 1, 0, 100));
                    System.out.println("Purchased Mana Potion!");
                } else {
                    System.out.println("Not enough Eclipsium!");
                }
            } else if (choice == 3) {
                if (player.spendEclipsium(100)) {
                    player.addPotion(new Potion("Full Restore", "Restores all HP and MP", 1, 9999, 9999));
                    System.out.println("Purchased Full Restore!");
                } else {
                    System.out.println("Not enough Eclipsium!");
                }
            } else if (choice == 4) {
                System.out.println("Merchant: 'Safe travels, friend!'");
                shopping = false;
            }
        }
    }
}