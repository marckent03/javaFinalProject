import java.util.Scanner;

public class Shop {
    private InputHandler inputHandler;
    private String merchantName;
    
    public Shop(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        this.merchantName = "Merchant";
    }
    
    public void setMerchantName(String name) {
        this.merchantName = name;
    }
    
    public void openShop(Player player, Scanner scanner) {
        System.out.println("\n=== TRAVELING MERCHANT ===");
        
        if (merchantName.equals("Neo")) {
            System.out.println("Neo: 'Before you step into this realm, child of the Zodiac...");
            System.out.println("You may find something useful in my humble wares.'");
        } else if (merchantName.equals("Francis")) {
            System.out.println("Francis: 'Ah, child of the Zodiac... this realm carries the weight of tides and secrets.");
            System.out.println("Before you step forward, perhaps my wares can offer you the protection the stars forgot to give.'");
        } else if (merchantName.equals("Kevin")) {
            System.out.println("Kevin: 'Hoho! A bold traveler guided by the Zodiac's flame.");
            System.out.println("Before charging into the unknown, take a look—my goods might just make your next step a wiser one.'");
        } else if (merchantName.equals("Kent")) {
            System.out.println("Kent: 'You stand at the threshold of Olympus's wrath, child.");
            System.out.println("If you're determined to face a god, glance at my wares. Even the bravest hero needs an edge against Zeus.'");
        } else {
            System.out.println(merchantName + ": 'Greetings, traveler! Care to see my wares?'");
        }
        
        System.out.println("Your Eclipsium: " + player.getEclipsium());
        
        boolean shopping = true;
        while (shopping) {
            System.out.println("\n=== SHOP MENU ===");
            
            int healthPotionCount = player.getPotionCount("Health Potion");
            int manaPotionCount = player.getPotionCount("Mana Potion");
            int fullRestoreCount = player.getPotionCount("Full Restore");
            
            System.out.println("1. Health Potion (50 Eclipsium) - Restores 200 HP" + 
                             (healthPotionCount > 0 ? " [Owned: " + healthPotionCount + "]" : ""));
            System.out.println("2. Mana Potion (40 Eclipsium) - Restores 100 MP" + 
                             (manaPotionCount > 0 ? " [Owned: " + manaPotionCount + "]" : ""));
            System.out.println("3. Full Restore (100 Eclipsium) - Restores all HP and MP" + 
                             (fullRestoreCount > 0 ? " [Owned: " + fullRestoreCount + "]" : ""));
            System.out.println("4. Leave Shop");
            System.out.print("Your choice: ");
            
            try {
                int choice = inputHandler.getIntInput(1, 4);
                
                if (choice == 1) {
                    if (player.spendEclipsium(50)) {
                        player.addPotion(new Potion("Health Potion", "Restores 200 HP", 1, 200, 0));
                        System.out.println("Purchased Health Potion!");
                        System.out.println("Remaining Eclipsium: " + player.getEclipsium());
                    } else {
                        System.out.println("Not enough Eclipsium!");
                    }
                } else if (choice == 2) {
                    if (player.spendEclipsium(40)) {
                        player.addPotion(new Potion("Mana Potion", "Restores 100 MP", 1, 0, 100));
                        System.out.println("Purchased Mana Potion!");
                        System.out.println("Remaining Eclipsium: " + player.getEclipsium());
                    } else {
                        System.out.println("Not enough Eclipsium!");
                    }
                } else if (choice == 3) {
                    if (player.spendEclipsium(100)) {
                        player.addPotion(new Potion("Full Restore", "Restores all HP and MP", 1, 9999, 9999));
                        System.out.println("Purchased Full Restore!");
                        System.out.println("Remaining Eclipsium: " + player.getEclipsium());
                    } else {
                        System.out.println("Not enough Eclipsium!");
                    }
                } else if (choice == 4) {
                    System.out.println(merchantName + ": 'Safe travels, friend!'");
                    shopping = false;
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
    }
}