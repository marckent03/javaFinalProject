import java.util.*;

public class ZodiacRpg {
    private Scanner scanner;
    private InputHandler inputHandler;
    private Player player;
    private ArrayList<Enemy> enemies;
    private boolean gameRunning;
    private Shop shop;
    private int worldCounter;
    private int currentMinionIndex;
    private boolean inWorldExploration;
    
    public ZodiacRpg() {
        scanner = new Scanner(System.in);
        inputHandler = new InputHandler(scanner);
        enemies = new ArrayList<>();
        gameRunning = true;
        shop = new Shop(inputHandler);
        worldCounter = 0;
        currentMinionIndex = 0;
        inWorldExploration = false;
    }
    
    public void startGame() {
        showTitle();
        createPlayer();
        initialZeusBattle();
        showStory();
        createEnemies();
        gameLoop();
    }
    
    public void showTitle() {
        System.out.println("==============================================");
        System.out.println("        RISE OF THE ZODIAC DEMIGOD");
        System.out.println("==============================================");
        System.out.println();
        System.out.println("Three Zodiac-born demigods rose against Zeus.");
        System.out.println("Their rebellion was swift, but Olympus's king");
        System.out.println("struck them down in a storm of lightning.");
        System.out.println("Shackled in divine chains, they awaited judgment.");
        System.out.println();
        System.out.println("But destiny whispers... From the three, only one will");
        System.out.println("awaken and break free of Zeus's grasp...");
        System.out.println("The chosen demigod will rise against Olympus,");
        System.out.println("while the others-still bound to Zeus");
        System.out.println("-will stand as enemies in your path.");
        System.out.println();
    }
    
    public void createPlayer() {
        try {
            System.out.println("Welcome, young demigod! What is your first name?");
            String playerName = inputHandler.getStringInput();
            
            System.out.println("\n~~ Choose your destiny: ~~");
            System.out.println("1. Aresios - Aries Demigod");
            System.out.println("   Weapon: Broadsword");
            System.out.println("   HP: 450     ||      MP: 150");
            System.out.println("   High Attack, Aggressive Fighter");
            System.out.println("   Forged in endless wars, thrives in chaos");
            System.out.println();
            System.out.println("2. Selinia - Cancer Demigod");
            System.out.println("   Weapon: Staff");
            System.out.println("   HP: 500     ||      MP: 200");
            System.out.println("   Balanced Stats, Supportive Abilities");
            System.out.println("   Once a healer, now hardened by betrayal");
            System.out.println();
            System.out.println("3. Orionis - Sagittarius Demigod");
            System.out.println("   Weapon: Bow");
            System.out.println("   HP: 400     ||      MP: 180");
            System.out.println("   High Damage, Precision Strikes");
            System.out.println("   Master hunter with starlight arrows");
            System.out.println();
            System.out.print("Your choice (1-3): ");
            
            int zodiacChoice = inputHandler.getIntInput(1, 3);
            
            String zodiacType = "";
            String defaultCharacterName = "";
            if (zodiacChoice == 1) {
                zodiacType = "Aries";
                defaultCharacterName = "Aresios";
            } else if (zodiacChoice == 2) {
                zodiacType = "Cancer";
                defaultCharacterName = "Selinia";
            } else if (zodiacChoice == 3) {
                zodiacType = "Sagittarius";
                defaultCharacterName = "Orionis";
            }
            
            System.out.println("\nWould you like to:");
            System.out.println("1. Use the default name (" + defaultCharacterName + ")");
            System.out.println("2. Use your name (" + playerName + ")");
            System.out.println("3. Choose a custom character name");
            System.out.print("Enter your choice (1-3): ");
            
            int nameChoice = inputHandler.getIntInput(1, 3);
            
            String finalCharacterName = "";
            if (nameChoice == 1) {
                finalCharacterName = defaultCharacterName;
            } else if (nameChoice == 2) {
                finalCharacterName = playerName;
            } else {
                System.out.print("Enter your character's name: ");
                finalCharacterName = inputHandler.getStringInput();
            }
            
            player = new Player(finalCharacterName, zodiacType);
            
            System.out.println("\n" + finalCharacterName + ", the " + zodiacType + 
                              " demigod, your destiny awaits...");
        } catch (Exception e) {
            System.out.println("An error occurred during character creation. Please restart.");
            System.exit(0);
        }
    }
    
    public void initialZeusBattle() {
        try {
            System.out.println("\n=== THE AWAKENING ===");
            System.out.println("As your powers awaken, thunder splits the sky...");
            System.out.println("Zeus himself descends from Olympus!");
            System.out.println();
            System.out.println("Zeus: 'Another Zodiac-born dares to challenge my rule?'");
            System.out.println("Zeus: 'You are but a child playing with powers you do not understand!'");
            System.out.println();
            System.out.println(player.getName() + ": 'I will not bow to your tyranny, Zeus!'");
            System.out.println();
            System.out.println("Press Enter to face Zeus...");
            inputHandler.waitForEnter();
            
            Enemy zeus = new Enemy("Zeus", "God", 5000, 1000);
            Battle battle = new Battle(player, zeus, scanner, shop, inputHandler, true);
            battle.startBattle();
            
            System.out.println("\n=== DEFEAT ===");
            System.out.println("Zeus raises his hand for the final strike...");
            System.out.println();
            System.out.println("Zeus: 'You are not ready, young one. Return when you");
            System.out.println("have proven yourself worthy. I will be waiting on Olympus.'");
            System.out.println();
            System.out.println("Lightning strikes you down...");
            System.out.println("Everything goes dark...");
            System.out.println();
            System.out.println("Press Enter to continue...");
            inputHandler.waitForEnter();
        } catch (Exception e) {
            System.out.println("An error occurred during the initial Zeus battle.");
        }
    }
    
    public void createEnemies() {
        if (!player.getZodiacType().equals("Aries")) {
            enemies.add(new Enemy("Aresios", "Aries", 600, 150));
        }
        if (!player.getZodiacType().equals("Cancer")) {
            enemies.add(new Enemy("Selinia", "Cancer", 700, 200));
        }
        if (!player.getZodiacType().equals("Sagittarius")) {
            enemies.add(new Enemy("Orionis", "Sagittarius", 550, 180));
        }
        enemies.add(new Enemy("Zeus (Human Form)", "God_Phase1", 1200, 400));
    }
    
    public void showStory() {
        try {
            System.out.println("\n=== PROLOGUE ===");
            System.out.println("You awaken in a small town, your body aching from Zeus's");
            System.out.println("divine lightning. A kind healer named Kyle has tended to your wounds.");
            System.out.println();
            System.out.println("Kyle: 'You're lucky to be alive. Zeus could have");
            System.out.println("destroyed you completely.'");
            System.out.println();
            System.out.println("Your divine powers are weakened, but not gone. You remember");
            System.out.println("Zeus's words: Prove yourself worthy. Two of your former");
            System.out.println("allies have joined Zeus as his champions.");
            System.out.println();
            System.out.println("Kyle: 'If you truly wish to challenge Zeus again, you must");
            System.out.println("journey through the demigod realms. Defeat the champions");
            System.out.println("and grow strong enough to face the King of Gods.'");
            System.out.println();
            System.out.println("Your mission is clear: Grow strong enough to challenge");
            System.out.println("Zeus again and claim your rightful place!");
            System.out.println();
            System.out.println("Kyle hands you a pouch with 50 Eclipsium...");
            player.addEclipsium(50);
            System.out.println();
            System.out.println("Press Enter to begin your journey...");
            inputHandler.waitForEnter();
        } catch (Exception e) {
            System.out.println("An error occurred during the story sequence.");
        }
    }
    
    public void gameLoop() {
        player.restoreFullHealth();
        player.restoreFullMana();
        
        while (gameRunning && player.isAlive()) {
            try {
                showMenu();
                int choice = inputHandler.getIntInput(1, 4);
                
                if (choice == 1) {
                    if (enemies.size() > 0) {
                        Enemy boss = enemies.get(0);
                        
                        if (!boss.getName().contains("Zeus")) {
                            if (!inWorldExploration) {
                                worldCounter++;
                                currentMinionIndex = 0;
                                inWorldExploration = true;
                            }
                            exploreWorld(boss.getType());
                        } else {
                            worldCounter++;
                            handleZeusFinalBattle();
                        }
                    } else {
                        System.out.println("You have defeated all enemies!");
                        gameRunning = false;
                    }
                } else if (choice == 2) {
                    player.showStats();
                } else if (choice == 3) {
                    shop.openShop(player, scanner);
                } else if (choice == 4) {
                    System.out.println("Thanks for playing!");
                    gameRunning = false;
                }
            } catch (Exception e) {
                System.out.println("An error occurred in the game loop. Please try again.");
            }
        }
        
        if (!player.isAlive()) {
            System.out.println("\nGAME OVER");
            System.out.println("Your legend ends here...");
        }
    }
    
    public void showMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("Worlds Conquered: " + (worldCounter - (inWorldExploration ? 1 : 0)) + "/3");
        if (inWorldExploration) {
            System.out.println("Current World Progress: Minion " + (currentMinionIndex + 1) + " of 4");
        }
        if (enemies.size() > 1) {
            System.out.println("1. Journey to " + getWorldName(enemies.get(0).getType()) + 
                             " (Challenge " + enemies.get(0).getName() + ")");
        } else if (enemies.size() == 1) {
            System.out.println("1. Journey to Olympus - Challenge Zeus - FINAL BATTLE!");
        } else {
            System.out.println("1. No enemies left!");
        }
        System.out.println("2. View Stats");
        System.out.println("3. Visit Shop");
        System.out.println("4. Quit Game");
        System.out.print("Your choice: ");
    }
    
    public void handleZeusFinalBattle() {
        try {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("\n=== WORLD " + worldCounter + "/3 ===");
            System.out.println("\n=== JOURNEY TO OLYMPUS ===");
            System.out.println("You ascend the divine mountain, ready for your final battle...");
            System.out.println();
            System.out.println("Press Enter to continue...");
            inputHandler.waitForEnter();
            
            System.out.println("\n" + "=".repeat(60));
            shop.setMerchantName("Kent");
            shop.openShop(player, scanner);
            
            player.resetCooldowns();
            
            System.out.println("\n" + "=".repeat(60));
            Enemy boss = enemies.get(0);
            Battle battle = new Battle(player, boss, scanner, shop, inputHandler, false);
            boolean victory = battle.startBattle();
            
            if (victory) {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("\n=== PHASE 2: GOD FORM ===");
                System.out.println("Zeus's body begins to glow with divine light...");
                System.out.println();
                System.out.println("Zeus: 'Impressive... But now witness my TRUE power!'");
                System.out.println();
                System.out.println("Zeus transforms into his God Form!");
                System.out.println();
                System.out.println("Press Enter to continue...");
                inputHandler.waitForEnter();
                
                System.out.println("\n" + "=".repeat(60));
                Enemy zeusPhase2 = new Enemy("Zeus (God Form)", "God_Phase2", 1800, 600);
                Battle finalBattle = new Battle(player, zeusPhase2, scanner, shop, inputHandler, false);
                boolean finalVictory = finalBattle.startBattle();
                
                if (finalVictory) {
                    enemies.clear();
                    System.out.println("\n" + "=".repeat(60));
                    System.out.println("\n🎉🎉🎉 ULTIMATE VICTORY! 🎉🎉🎉");
                    System.out.println("You have defeated Zeus and freed Olympus!");
                    System.out.println("The divine chains are broken, and all three demigods are free!");
                    System.out.println("Your legend will be remembered forever!");
                    System.out.println("\nWorlds Conquered: " + worldCounter + "/3");
                    gameRunning = false;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the Zeus battle.");
        }
    }
    
    public void exploreWorld(String bossType) {
        try {
            GameWorld world = createWorld(bossType);
            
            if (currentMinionIndex == 0) {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("\n=== WORLD " + worldCounter + "/3 ===");
                System.out.println("\n" + world.getDescription());
                System.out.println();
                System.out.println("Press Enter to explore...");
                inputHandler.waitForEnter();
                
                setMerchantNameForWorld(bossType);
                player.resetCooldowns();
            }
            
            String[] minionNames = world.getMinionNames();
            
            for (int i = currentMinionIndex; i < 4; i++) {
                System.out.println("\n" + "=".repeat(60));
                Enemy minion = new Enemy(minionNames[i], bossType + "_Minion", 200 + (i * 30), 80 + (i * 10));
                
                System.out.println("\n=== ENCOUNTER ===");
                System.out.println(world.getMinionDialogue(i));
                System.out.println();
                
                String minionCounter = "Minion " + (i + 1) + " of 4";
                Battle minionBattle = new Battle(player, minion, scanner, shop, inputHandler, false, minionCounter);
                boolean victory = minionBattle.startBattle();
                
                if (!victory) {
                    System.out.println("\n" + "=".repeat(60));
                    System.out.println("\nYou have been defeated!");
                    System.out.println("Returning to town...");
                    currentMinionIndex = i;
                    return;
                }
                
                System.out.println("\n" + "=".repeat(60));
                player.addEclipsium(20 + (i * 5));
                System.out.println("You earned " + (20 + (i * 5)) + " Eclipsium!");
                
                if (i < 3) {
                    System.out.println("\n=== PATH CHOICE ===");
                    System.out.println("1. Continue forward (Next battle)");
                    System.out.println("2. Rest and return to town (Restore 50% HP/MP)");
                    System.out.print("Your choice: ");
                    
                    int choice = inputHandler.getIntInput(1, 2);
                    
                    if (choice == 2) {
                        player.rest();
                        System.out.println("\n" + "=".repeat(60));
                        System.out.println("You rest and recover some strength...");
                        System.out.println("Returning to town...");
                        currentMinionIndex = i + 1;
                        return;
                    }
                }
            }
            
            currentMinionIndex = 0;
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("\n=== RESTING POINT ===");
            System.out.println("You've cleared all the minions!");
            System.out.println("A traveling merchant appears...");
            System.out.println();
            shop.openShop(player, scanner);
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("\nYou rest before the boss battle...");
            System.out.println("HP and MP fully restored!");
            player.restoreFullHealth();
            player.restoreFullMana();
            player.resetCooldowns();
            System.out.println();
            System.out.println("Press Enter to face the boss...");
            inputHandler.waitForEnter();
            
            System.out.println("\n" + "=".repeat(60));
            Enemy boss = enemies.get(0);
            System.out.println("\n" + world.getBossDialogue(player.getName()));
            System.out.println();
            System.out.println("Press Enter to begin battle...");
            inputHandler.waitForEnter();
            
            System.out.println("\n" + "=".repeat(60));
            Battle bossBattle = new Battle(player, boss, scanner, shop, inputHandler, false);
            boolean bossVictory = bossBattle.startBattle();
            
            if (bossVictory) {
                System.out.println("\n" + "=".repeat(60));
                player.addEclipsium(100);
                System.out.println("You earned 100 Eclipsium!");
                enemies.remove(0);
                inWorldExploration = false;
                currentMinionIndex = 0;
                
                System.out.println("\n=== WORLD CONQUERED ===");
                System.out.println("You have defeated " + boss.getName() + "!");
                System.out.println("The realm is freed from their control...");
                System.out.println();
                System.out.println("You rest and prepare for the next journey...");
                player.restoreFullHealth();
                player.restoreFullMana();
                player.resetCooldowns();
                System.out.println();
                System.out.println("Press Enter to continue...");
                inputHandler.waitForEnter();
            } else {
                currentMinionIndex = 0;
            }
        } catch (Exception e) {
            System.out.println("An error occurred while exploring the world.");
        }
    }
    
    private void setMerchantNameForWorld(String bossType) {
        if (bossType.equals("Aries")) {
            shop.setMerchantName("Neo");
        } else if (bossType.equals("Cancer")) {
            shop.setMerchantName("Francis");
        } else if (bossType.equals("Sagittarius")) {
            shop.setMerchantName("Kevin");
        }
    }
    
    private GameWorld createWorld(String bossType) {
        if (bossType.equals("Aries")) {
            return new AriesWorld();
        } else if (bossType.equals("Cancer")) {
            return new CancerWorld();
        } else {
            return new SagittariusWorld();
        }
    }
    
    public String getWorldName(String enemyType) {
        GameWorld world = createWorld(enemyType);
        return world.getName();
    }
    
    public static void main(String[] args) {
        try {
            ZodiacRpg game = new ZodiacRpg();
            game.startGame();
        } catch (Exception e) {
            System.out.println("A critical error occurred. Please restart the game.");
            e.printStackTrace();
        }
    }
}