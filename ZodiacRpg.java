import java.util.*;

public class ZodiacRpg {
    private Scanner scanner;
    private Player player;
    private ArrayList<Enemy> enemies;
    private boolean gameRunning;
    private Shop shop;
    
    public ZodiacRpg() {
        scanner = new Scanner(System.in);
        enemies = new ArrayList<>();
        gameRunning = true;
        shop = new Shop();
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
        System.out.println("Welcome, young demigod! What is your first name?");
        String playerName = scanner.nextLine();
        
        System.out.println("\n~~ Choose your destiny: ~~");
        System.out.println("1. Aresios - Aries Demigod");
        System.out.println("   Weapon: Broadsword");
        System.out.println("   High Attack, Aggressive Fighter");
        System.out.println("   Forged in endless wars, thrives in chaos");
        System.out.println();
        System.out.println("2. Selinia - Cancer Demigod");
        System.out.println("   Weapon: Staff");
        System.out.println("   Balanced Stats, Supportive Abilities");
        System.out.println("   Once a healer, now hardened by betrayal");
        System.out.println();
        System.out.println("3. Orionis - Sagittarius Demigod");
        System.out.println("   Weapon: Bow");
        System.out.println("   High Damage, Precision Strikes");
        System.out.println("   Master hunter with starlight arrows");
        System.out.println();
        System.out.print("Your choice (1-3): ");
        
        int zodiacChoice = scanner.nextInt();
        scanner.nextLine();
        
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
        } else {
            System.out.println("Invalid choice. Defaulting to Aries.");
            zodiacType = "Aries";
            defaultCharacterName = "Aresios";
        }
        
        System.out.println("\nWould you like to:");
        System.out.println("1. Use the default name (" + defaultCharacterName + ")");
        System.out.println("2. Use your name (" + playerName + ")");
        System.out.println("3. Choose a custom character name");
        System.out.print("Enter your choice (1-3): ");
        
        int nameChoice = scanner.nextInt();
        scanner.nextLine();
        
        String finalCharacterName = "";
        if (nameChoice == 1) {
            finalCharacterName = defaultCharacterName;
        } else if (nameChoice == 2) {
            finalCharacterName = playerName;
        } else {
            System.out.print("Enter your character's name: ");
            finalCharacterName = scanner.nextLine();
        }
        
        player = new Player(finalCharacterName, zodiacType);
        
        System.out.println("\n" + finalCharacterName + ", the " + zodiacType + 
                          " demigod, your destiny awaits...");
    }
    
    public void initialZeusBattle() {
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
        scanner.nextLine();
        
        Enemy zeus = new Enemy("Zeus", "God", 5000, 1000);
        Battle battle = new Battle(player, zeus, scanner, shop, true);
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
        scanner.nextLine();
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
        scanner.nextLine();
    }
    
    public void gameLoop() {
        player.restoreFullHealth();
        player.restoreFullMana();
        
        while (gameRunning && player.isAlive()) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) {
                if (enemies.size() > 0) {
                    Enemy boss = enemies.get(0);
                    
                    if (!boss.getName().contains("Zeus")) {
                        explorerWorld(boss.getType());
                    } else {
                        System.out.println("\n=== JOURNEY TO OLYMPUS ===");
                        System.out.println("You ascend the divine mountain, ready for your final battle...");
                        System.out.println();
                        System.out.println("Press Enter to continue...");
                        scanner.nextLine();
                        
                        shop.openShop(player, scanner);
                        
                        Battle battle = new Battle(player, boss, scanner, shop, false);
                        boolean victory = battle.startBattle();
                        
                        if (victory) {
                            System.out.println("\n=== PHASE 2: GOD FORM ===");
                            System.out.println("Zeus's body begins to glow with divine light...");
                            System.out.println();
                            System.out.println("Zeus: 'Impressive... But now witness my TRUE power!'");
                            System.out.println();
                            System.out.println("Zeus transforms into his God Form!");
                            System.out.println();
                            System.out.println("Press Enter to continue...");
                            scanner.nextLine();
                            
                            Enemy zeusPhase2 = new Enemy("Zeus (God Form)", "God_Phase2", 1800, 600);
                            Battle finalBattle = new Battle(player, zeusPhase2, scanner, shop, false);
                            boolean finalVictory = finalBattle.startBattle();
                            
                            if (finalVictory) {
                                enemies.clear();
                                System.out.println("\n=== VICTORY! ===");
                                System.out.println("You have defeated Zeus and freed Olympus!");
                                System.out.println("Your legend will be remembered forever!");
                                gameRunning = false;
                            }
                        }
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
        }
        
        if (!player.isAlive()) {
            System.out.println("\nGAME OVER");
            System.out.println("Your legend ends here...");
        }
    }
    
    public void showMenu() {
        System.out.println("\n=== MAIN MENU ===");
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
    
    public void explorerWorld(String bossType) {
        System.out.println("\n" + getWorldDescription(bossType));
        System.out.println();
        System.out.println("Press Enter to explore...");
        scanner.nextLine();
        
        String[] minionNames = getMinionNames(bossType);
        
        for (int i = 0; i < 4; i++) {
            Enemy minion = new Enemy(minionNames[i], bossType + "_Minion", 200 + (i * 30), 80 + (i * 10));
            
            System.out.println("\n=== ENCOUNTER ===");
            System.out.println(getMinionDialogue(bossType, i));
            System.out.println();
            
            Battle minionBattle = new Battle(player, minion, scanner, shop, false);
            boolean victory = minionBattle.startBattle();
            
            if (!victory) {
                System.out.println("\nYou have been defeated!");
                System.out.println("Returning to town...");
                player.rest();
                return;
            }
            
            player.addEclipsium(20 + (i * 5));
            System.out.println("You earned " + (20 + (i * 5)) + " Eclipsium!");
            
            if (i < 3) {
                System.out.println("\n=== PATH CHOICE ===");
                System.out.println("1. Continue forward (Next battle)");
                System.out.println("2. Rest (Restore 50% HP/MP)");
                System.out.print("Your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                if (choice == 2) {
                    player.rest();
                    System.out.println("You rest and recover some strength...");
                }
            }
        }
        
        System.out.println("\n=== RESTING POINT ===");
        System.out.println("You've cleared all the minions!");
        System.out.println("A traveling merchant appears...");
        System.out.println();
        shop.openShop(player, scanner);
        
        System.out.println("\nYou rest before the boss battle...");
        System.out.println("HP and MP fully restored!");
        player.restoreFullHealth();
        player.restoreFullMana();
        System.out.println();
        System.out.println("Press Enter to face the boss...");
        scanner.nextLine();
        
        Enemy boss = enemies.get(0);
        System.out.println("\n" + getBossDialogue(bossType));
        System.out.println();
        System.out.println("Press Enter to begin battle...");
        scanner.nextLine();
        
        Battle bossBattle = new Battle(player, boss, scanner, shop, false);
        boolean bossVictory = bossBattle.startBattle();
        
        if (bossVictory) {
            player.addEclipsium(100);
            System.out.println("You earned 100 Eclipsium!");
            enemies.remove(0);
            
            System.out.println("\n=== WORLD CONQUERED ===");
            System.out.println("You have defeated " + boss.getName() + "!");
            System.out.println("The realm is freed from their control...");
            System.out.println();
            System.out.println("You rest and prepare for the next journey...");
            player.restoreFullHealth();
            player.restoreFullMana();
            System.out.println();
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }
    }
    
    public String[] getMinionNames(String type) {
        if (type.equals("Aries")) {
            return new String[]{"Scorched Warrior", "Flame Berserker", "Inferno Guardian", "War Titan"};
        } else if (type.equals("Cancer")) {
            return new String[]{"Tidal Sprite", "Lunar Guardian", "Coral Sentinel", "Ocean Leviathan"};
        } else {
            return new String[]{"Star Wolf", "Nebula Hawk", "Astral Hunter", "Cosmic Beast"};
        }
    }
    
    public String getMinionDialogue(String type, int index) {
        if (type.equals("Aries")) {
            String[] dialogues = {
                "A Scorched Warrior blocks your path!\nWarrior: 'None shall pass through Pyroxis!'",
                "A Flame Berserker emerges from the flames!\nBerserker: 'Your journey ends in fire!'",
                "An Inferno Guardian stands before you!\nGuardian: 'Aresios will crush you, weakling!'",
                "A massive War Titan appears!\nTitan: 'Face the might of eternal war!'"
            };
            return dialogues[index];
        } else if (type.equals("Cancer")) {
            String[] dialogues = {
                "A Tidal Sprite rises from the water!\nSprite: 'The depths will claim you!'",
                "A Lunar Guardian blocks your way!\nGuardian: 'Selinia's moon shall eclipse your hope!'",
                "A Coral Sentinel emerges!\nSentinel: 'Turn back before the tide drowns you!'",
                "An Ocean Leviathan surfaces!\nLeviathan: 'The abyss hungers for challengers!'"
            };
            return dialogues[index];
        } else {
            String[] dialogues = {
                "A Star Wolf prowls nearby!\nWolf: 'You're prey in these hunting grounds!'",
                "A Nebula Hawk descends from above!\nHawk: 'Orionis never misses his mark!'",
                "An Astral Hunter tracks you!\nHunter: 'The stars have marked you for death!'",
                "A Cosmic Beast roars!\nBeast: 'Your constellation ends here!'"
            };
            return dialogues[index];
        }
    }
    
    public String getBossDialogue(String type) {
        if (type.equals("Aries")) {
            return "=== BOSS ENCOUNTER: ARESIOS ===\n" +
                   "Aresios stands in the center of burning battlefield...\n\n" +
                   "Aresios: 'So you've survived my warriors. Impressive.'\n" +
                   "Aresios: 'But I am war incarnate! Zeus promised me eternal battle,\n" +
                   "and I will not let you take that from me!'\n\n" +
                   player.getName() + ": 'You've lost yourself to violence, Aresios!\n" +
                   "This isn't who you were meant to be!'\n\n" +
                   "Aresios: 'ENOUGH TALK! Show me your strength in combat!'";
        } else if (type.equals("Cancer")) {
            return "=== BOSS ENCOUNTER: SELINIA ===\n" +
                   "Selinia waits beneath the eternal moon...\n\n" +
                   "Selinia: 'You've come far, " + player.getName() + ".'\n" +
                   "Selinia: 'Once, I would have healed your wounds. But mortals taught me\n" +
                   "that kindness is weakness. Zeus showed me true strength.'\n\n" +
                   player.getName() + ": 'Selinia, this bitterness isn't strength!\n" +
                   "We can still fight Zeus together!'\n\n" +
                   "Selinia: 'Together? No. Only the strongest survive. Let the tide decide!'";
        } else {
            return "=== BOSS ENCOUNTER: ORIONIS ===\n" +
                   "Orionis stands atop a celestial peak, bow drawn...\n\n" +
                   "Orionis: 'My arrows never miss, " + player.getName() + ".\n" +
                   "You've been in my sights since you entered Stellaros.'\n\n" +
                   player.getName() + ": 'Why serve Zeus? We were meant to be free!'\n\n" +
                   "Orionis: 'Free? Zeus offered me the ultimate hunt - immortal prey,\n" +
                   "eternal challenge. And you... you're the greatest prey of all.\n" +
                   "Let's see if you can dodge starlight!'";
        }
    }
    
    public String getWorldName(String enemyType) {
        if (enemyType.equals("Aries")) {
            return "Pyroxis (War-torn Fields)";
        } else if (enemyType.equals("Cancer")) {
            return "Thalassic Depths (Moonlit Realm)";
        } else if (enemyType.equals("Sagittarius")) {
            return "Stellaros (Celestial Hunting Grounds)";
        } else {
            return "Olympus (Storm Throne)";
        }
    }
    
    public String getWorldDescription(String enemyType) {
        if (enemyType.equals("Aries")) {
            return "=== ENTERING PYROXIS - THE WAR-TORN FIELDS ===\n" +
                   "Endless battlefields stretch before you. The sky burns red,\n" +
                   "scorched earth cracks beneath your feet. The air smells of smoke\n" +
                   "and steel. War cries echo in the distance...";
        } else if (enemyType.equals("Cancer")) {
            return "=== ENTERING THALASSIC DEPTHS - THE MOONLIT REALM ===\n" +
                   "You descend into vast oceans lit by eternal moonlight.\n" +
                   "Glowing coral palaces shimmer in the deep. Tides shift with\n" +
                   "lunar rhythm. The water is both beautiful and treacherous...";
        } else {
            return "=== ENTERING STELLAROS - CELESTIAL HUNTING GROUNDS ===\n" +
                   "Starry wilderness extends infinitely around you. Constellations\n" +
                   "flow like rivers through the night sky. Celestial beasts roam\n" +
                   "among crystalline trees. You feel watched from above...";
        }
    }
    
    public static void main(String[] args) {
        ZodiacRpg game = new ZodiacRpg();
        game.startGame();
    }
}