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
    private int restPenaltyCount;
    
    public ZodiacRpg() {
        scanner = new Scanner(System.in);
        inputHandler = new InputHandler(scanner);
        enemies = new ArrayList<>();
        gameRunning = true;
        shop = new Shop(inputHandler);
        worldCounter = 0;
        currentMinionIndex = 0;
        inWorldExploration = false;
        restPenaltyCount = 0;
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
        System.out.println("=".repeat(60));
        System.out.println("        RISE OF THE ZODIAC DEMIGOD");
        System.out.println("=".repeat(60));
        System.out.println();
        System.out.println(" Narrator:");
        System.out.println();
        System.out.println("Three Zodiac-born demigods rose against Zeus.");
        System.out.println("Their rebellion was swift, but Olympus's king struck them");
        System.out.println("down in a storm of lightning. Shackled in divine chains,");
        System.out.println("they awaited judgment.");
        System.out.println();
        System.out.println("But destiny whispers. From the three, only one will awaken");
        System.out.println("and break free of Zeus's grasp. The chosen demigod will rise");
        System.out.println("against Olympus, while the others—still bound to Zeus—will");
        System.out.println("stand as enemies in your path.");
        System.out.println();
        System.out.println("Yet know this:");
        System.out.println("Victory over Zeus means more than rebellion. The one you free");
        System.out.println("today fights not only for themselves, but to avenge their");
        System.out.println("fallen kin and to break the divine chains that bind them.");
        System.out.println("Should Olympus fall, all three demigods shall rise again—united at last.");
        System.out.println();
    }
    
    public void createPlayer() {
        try {
            System.out.println(" Choose your destiny.");
            System.out.println("One will rise as the challenger of Olympus.");
            System.out.println("The other two will remain chained to Zeus, sworn to destroy you.");
            System.out.println();
            
            System.out.println("1. Aresios - Aries Demigod");
            System.out.println("   Weapon: Broadsword");
            System.out.println("   Forged in endless wars, thrives in chaos and bloodshed.");
            System.out.println();
            System.out.println("2. Selinia - Cancer Demigod");
            System.out.println("   Weapon: Staff");
            System.out.println("   Once a healer, compassion betrayed, now hardened by bitterness.");
            System.out.println();
            System.out.println("3. Orionis - Sagittarius Demigod");
            System.out.println("   Weapon: Bow");
            System.out.println("   Master hunter whose arrows pierce like starlight.");
            System.out.println();
            System.out.println("4. View Character Details");
            System.out.println();
            System.out.print("Your choice (1-4): ");
            
            int zodiacChoice = inputHandler.getIntInput(1, 4);
            
            while (zodiacChoice == 4) {
                System.out.println("\nWhich character would you like to learn about?");
                System.out.println("1. Aresios");
                System.out.println("2. Selinia");
                System.out.println("3. Orionis");
                System.out.println("4. Back to selection");
                System.out.print("Your choice: ");
                
                int infoChoice = inputHandler.getIntInput(1, 4);
                if (infoChoice != 4) {
                    Player.displayCharacterInfo(infoChoice);
                    System.out.println("\nPress Enter to continue...");
                    inputHandler.waitForEnter();
                }
                
                System.out.println("\nChoose your destiny:");
                System.out.println("1. Aresios - Aries Demigod");
                System.out.println("2. Selinia - Cancer Demigod");
                System.out.println("3. Orionis - Sagittarius Demigod");
                System.out.println("4. View Character Details");
                System.out.print("Your choice (1-4): ");
                zodiacChoice = inputHandler.getIntInput(1, 4);
            }
            
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
            System.out.println("2. Choose a custom character name");
            System.out.print("Enter your choice (1-2): ");
            
            int nameChoice = inputHandler.getIntInput(1, 2);
            
            String finalCharacterName = "";
            if (nameChoice == 1) {
                finalCharacterName = defaultCharacterName;
            } else {
                System.out.print("Enter your character's name: ");
                finalCharacterName = inputHandler.getStringInput();
            }
            
            player = new Player(finalCharacterName, zodiacType);
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("\nNarrator:");
            System.out.println("The chosen demigod's journey begins.");
            System.out.println("Through war-torn fields, moonlit oceans, and celestial forests,");
            System.out.println("they will confront gods and ghosts alike.");
            System.out.println();
            System.out.println(finalCharacterName + ", the " + zodiacType + " demigod...");
            System.out.println("Your destiny awaits.");
            System.out.println();
            System.out.println("Press Enter to continue...");
            inputHandler.waitForEnter();
        } catch (Exception e) {
            System.out.println("An error occurred during character creation. Please restart.");
            System.exit(0);
        }
    }
    
    public void initialZeusBattle() {
        try {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("\n=== THE AWAKENING ===");
            System.out.println("As your powers awaken, thunder splits the sky...");
            System.out.println("Zeus himself descends from Olympus!");
            System.out.println();
            System.out.println("Zeus: \"Another Zodiac-born dares to challenge my rule?");
            System.out.println("You are but a child playing with powers you do not understand!\"");
            System.out.println();
            System.out.println(player.getName() + ": \"I will not bow to your tyranny, Zeus!\"");
            System.out.println();
            System.out.println("Press Enter to face Zeus...");
            inputHandler.waitForEnter();
            
            Enemy zeus = new Enemy("Zeus", "God", 5000, 1000);
            Battle battle = new Battle(player, zeus, scanner, shop, inputHandler, true);
            battle.startBattle();
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("\n=== DEFEAT ===");
            System.out.println("Zeus raises his hand for the final strike...");
            System.out.println();
            System.out.println("Zeus: \"You are not ready, young one. Return when you");
            System.out.println("have proven yourself worthy. I will be waiting on Olympus.\"");
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
            System.out.println("\n" + "=".repeat(60));
            System.out.println("\n=== RESCUE PATH BEGINS ===");
            System.out.println();
            showRescuePathStory();
            System.out.println();
            System.out.println("You awaken in a small town, your body aching from Zeus's");
            System.out.println("divine lightning.");
            System.out.println();
            System.out.println("A kind healer named Kyle tends to your wounds.");
            System.out.println();
            System.out.println("Kyle: \"Easy... easy. You're safe for now.\"");
            System.out.println();
            System.out.println("Kyle: \"Zeus chained your kin.");
            System.out.println("We have to free them — and you're the only one strong enough to do it.\"");
            System.out.println();
            System.out.println("Your mission is clear: Journey through the demigod realms,");
            System.out.println("free your fallen allies, and grow strong enough to challenge");
            System.out.println("Zeus again!");
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
    
    private void showRescuePathStory() {
        if (player.getZodiacType().equals("Aries")) {
            System.out.println("But as " + player.getName() + " steps through the Gateway,");
            System.out.println("a brutal truth is revealed:");
            System.out.println();
            System.out.println("Zeus has twisted each realm into a prison for the demigods");
            System.out.println("who once stood beside you.");
            System.out.println();
            System.out.println("Selinia is trapped within the Moonlit Bastion,");
            System.out.println("encased in lunar glass, forced to channel her healing");
            System.out.println("into shields for Olympus.");
            System.out.println();
            System.out.println("Orionis roams the Starlight Expanse, bound by celestial chains");
            System.out.println("that compel him to hunt anything that threatens Zeus.");
            System.out.println();
            System.out.println(player.getName() + " grips the blazing blade:");
            System.out.println("\"Zeus broke us... now I'll break his chains.\"");
        } else if (player.getZodiacType().equals("Cancer")) {
            System.out.println("As " + player.getName() + " crosses the Gateway,");
            System.out.println("the tides whisper a terrible revelation:");
            System.out.println();
            System.out.println("Your fellow demigods are not merely enemies—");
            System.out.println("they are captives, their wills shackled by Zeus's dominion.");
            System.out.println();
            System.out.println("Aresios is imprisoned in the Infernal Crucible,");
            System.out.println("bound in molten chains that feed his rage beyond control.");
            System.out.println();
            System.out.println("Orionis is locked within the Constellation Crucible,");
            System.out.println("forced to fire arrow after arrow under Zeus's command.");
            System.out.println();
            System.out.println(player.getName() + "'s light brightens:");
            System.out.println("\"I could not save them before... but I will save them now.\"");
        } else {
            System.out.println("As " + player.getName() + " steps through the Gateway,");
            System.out.println("the stars reveal Zeus's final cruelty:");
            System.out.println();
            System.out.println("Your fellow demigods are not traitors—");
            System.out.println("they are puppets trapped in divine prisons.");
            System.out.println();
            System.out.println("Aresios rages within the War-Bound Citadel,");
            System.out.println("his strength shackled and weaponized by Zeus.");
            System.out.println();
            System.out.println("Selinia drifts inside the Lunar Abyss,");
            System.out.println("her compassion crushed beneath celestial bindings.");
            System.out.println();
            System.out.println(player.getName() + " lowers the bow:");
            System.out.println("\"They were never the prey... Zeus was.\"");
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
                                restPenaltyCount = 0;
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
            System.out.println("Current World Progress: Minion " + (currentMinionIndex + 1) + " of " + (4 + restPenaltyCount));
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
            System.out.println("\n=== ENTERING THE REALM OF ZEUS ===");
            System.out.println();
            System.out.println("Narrator:");
            System.out.println("When the final demigod is freed and the last chain shatters,");
            System.out.println("the Gateway reacts. The skies tremble. The realms align.");
            System.out.println();
            System.out.println("A colossal rift opens—swirling with gold lightning and divine stormfire.");
            System.out.println("This is the World of Olympus. The Domain of Zeus.");
            System.out.println("The Seat of the Immortal Tyrant.");
            System.out.println();
            System.out.println("Kyle stands firm despite his fear:");
            System.out.println("Kyle: \"This... this is it. The World of Zeus.");
            System.out.println("I can go no further—but I'll wait for you.");
            System.out.println("Come back alive. All of us will.\"");
            System.out.println();
            System.out.println("Press Enter to continue...");
            inputHandler.waitForEnter();
            
            System.out.println("\n" + "=".repeat(60));
            shop.setMerchantName("Kent");
            shop.openShop(player, scanner);
            
            player.resetCooldowns();
            
            System.out.println("\n" + "=".repeat(60));
            System.out.println("\nAs you step inside, the world shifts from endless clouds");
            System.out.println("to a silent marble city—a place where gods once walked,");
            System.out.println("now abandoned, hollow, and filled only with echoes.");
            System.out.println();
            System.out.println("At its center stands a lone figure. A man.");
            System.out.println("Human-shaped... but too still. Too perfect.");
            System.out.println("Lightning flickers beneath his skin.");
            System.out.println();
            System.out.println("His back is turned. But you can feel him smiling.");
            System.out.println();
            System.out.println("Press Enter to face Zeus...");
            inputHandler.waitForEnter();
            
            System.out.println("\n" + "=".repeat(60));
            showZeusConfrontation();
            
            Enemy boss = enemies.get(0);
            Battle battle = new Battle(player, boss, scanner, shop, inputHandler, false);
            boolean victory = battle.startBattle();
            
            if (victory) {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("\n=== PHASE 2: GOD FORM ===");
                System.out.println();
                System.out.println("⚔️ Narrator:");
                System.out.println("When his human body collapses, lightning engulfs him.");
                System.out.println("The sky tears open. Winds scream across Olympus.");
                System.out.println();
                System.out.println("He rises—no longer a man, but a colossal figure of storm and gold:");
                System.out.println("- Skin crackling with electricity");
                System.out.println("- Eyes brighter than suns");
                System.out.println("- Hair flowing like a solar flare");
                System.out.println("- Armor forged from thunder itself");
                System.out.println("- Wings of pure lightning unfurl behind him");
                System.out.println();
                System.out.println("Zeus (God Form): \"You wanted a god... Now face one.\"");
                System.out.println();
                System.out.println("Press Enter to continue...");
                inputHandler.waitForEnter();
                
                System.out.println("\n" + "=".repeat(60));
                Enemy zeusPhase2 = new Enemy("Zeus (God Form)", "God_Phase2", 1800, 600);
                Battle finalBattle = new Battle(player, zeusPhase2, scanner, shop, inputHandler, false);
                boolean finalVictory = finalBattle.startBattle();
                
                if (finalVictory) {
                    showVictoryEnding();
                    enemies.clear();
                    gameRunning = false;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred during the Zeus battle.");
        }
    }
    
    private void showZeusConfrontation() {
        System.out.println("Zeus (Human Form) turns, revealing cold, starless eyes.");
        System.out.println();
        System.out.println("Zeus: \"So... you dragged yourself through the worlds I shattered.");
        System.out.println("You freed the ones I chained.");
        System.out.println("Tell me, child... Do you truly believe you deserve a god's throne?\"");
        System.out.println();
        System.out.println("He lifts his hand. Lightning dances between his fingers like puppets on strings.");
        System.out.println();
        System.out.println("Zeus: \"You forget who shaped you. Who blessed you. Who owns you.\"");
        System.out.println();
        
        if (player.getZodiacType().equals("Aries")) {
            System.out.println(player.getName() + " slams the blazing weapon into the marble.");
            System.out.println(player.getName() + ": \"You forged me for war.");
            System.out.println("Now choke on the monster you created!\"");
            System.out.println();
            System.out.println("Zeus: \"Ungrateful whelp. I should have crushed you in your cradle.\"");
            System.out.println();
            System.out.println(player.getName() + ": \"Try it.\"");
        } else if (player.getZodiacType().equals("Cancer")) {
            System.out.println(player.getName() + " raises the glowing staff, moonlight swirling around.");
            System.out.println(player.getName() + ": \"You twisted our compassion, our loyalty, our hope.");
            System.out.println("I walk this path to end your cruelty.\"");
            System.out.println();
            System.out.println("Zeus: \"Compassion is weakness. You were meant to serve.\"");
            System.out.println();
            System.out.println(player.getName() + ": \"That is your greatest lie.\"");
        } else {
            System.out.println(player.getName() + " draws the bow, starlight gathering to a single point.");
            System.out.println(player.getName() + ": \"The hunt ends tonight, Zeus.");
            System.out.println("You're the final quarry.\"");
            System.out.println();
            System.out.println("Zeus: \"You would hunt me, boy?\"");
            System.out.println();
            System.out.println(player.getName() + ": \"I never miss.\"");
        }
        System.out.println();
        System.out.println("Zeus: \"Now kneel... before I remind you what it means");
        System.out.println("to challenge the King of Olympus.\"");
        System.out.println();
        System.out.println("Press Enter to begin battle...");
        inputHandler.waitForEnter();
    }
    
    private void showVictoryEnding() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("\n⚡ FINAL BATTLE ENDING ⚡");
        System.out.println();
        System.out.println("Narrator:");
        System.out.println("When Zeus falls, the storm collapses.");
        System.out.println("Lightning dissolves like dust.");
        System.out.println("The chains choking the realms crumble at once.");
        System.out.println();
        
        String ally1 = "";
        String ally2 = "";
        if (player.getZodiacType().equals("Aries")) {
            ally1 = "Selinia";
            ally2 = "Orionis";
        } else if (player.getZodiacType().equals("Cancer")) {
            ally1 = "Aresios";
            ally2 = "Orionis";
        } else {
            ally1 = "Aresios";
            ally2 = "Selinia";
        }
        
        System.out.println(ally1 + ", " + ally2 + ", and " + player.getName() + "—all freed—");
        System.out.println("stand together for the first time since their rebellion.");
        System.out.println();
        System.out.println("The sky clears. Olympus, once a prison, becomes silent and open.");
        System.out.println("The throne stands empty.");
        System.out.println();
        System.out.println("!!! ULTIMATE VICTORY! !!!");
        System.out.println();
        System.out.println("You have defeated Zeus and freed Olympus!");
        System.out.println("The divine chains are broken!");
        System.out.println("All three demigods rise again—united at last!");
        System.out.println();
        System.out.println("Your legend will be remembered forever!");
        System.out.println("\nWorlds Conquered: " + worldCounter + "/3");
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
            int totalMinions = 4 + restPenaltyCount;
            
            for (int i = currentMinionIndex; i < totalMinions; i++) {
                System.out.println("\n" + "=".repeat(60));
                
                int minionIndex = i % 4;
                String minionName = minionNames[minionIndex];
                if (i >= 4) {
                    minionName += " (Reinforcement)";
                }
                
                Enemy minion = new Enemy(minionName, bossType + "_Minion", 200 + (minionIndex * 30), 80 + (minionIndex * 10));
                
                System.out.println("\n=== ENCOUNTER ===");
                System.out.println(world.getMinionDialogue(minionIndex));
                System.out.println();
                
                String minionCounter = "Minion " + (i + 1) + " of " + totalMinions;
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
                player.addEclipsium(20 + (minionIndex * 5));
                System.out.println("You earned " + (20 + (minionIndex * 5)) + " Eclipsium!");
                
                if (i < totalMinions - 1) {
                    System.out.println("\n=== PATH CHOICE ===");
                    System.out.println("1. Continue forward (Next battle)");
                    System.out.println("2. Rest and return to town (Restore 50% HP/MP)");
                    System.out.println("   WARNING: Choosing rest will add 1 more minion to this world!");
                    System.out.print("Your choice: ");
                    
                    int choice = inputHandler.getIntInput(1, 2);
                    
                    if (choice == 2) {
                        player.rest();
                        restPenaltyCount++;
                        System.out.println("\n" + "=".repeat(60));
                        System.out.println("You rest and recover some strength...");
                        System.out.println("!! Enemy reinforcements have arrived! +1 Minion added!");
                        System.out.println("Returning to town...");
                        currentMinionIndex = i + 1;
                        return;
                    }
                }
            }
            
            currentMinionIndex = 0;
            restPenaltyCount = 0;
            
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
                
                player.boostStats();
                
                enemies.remove(0);
                inWorldExploration = false;
                currentMinionIndex = 0;
                
                showBossDefeatDialogue(boss.getType());
                
                System.out.println("\n=== WORLD CONQUERED ===");
                System.out.println("You have defeated " + boss.getName() + "!");
                System.out.println("The realm is freed from their control...");
                System.out.println("The chains binding them shatter!");
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
    
    private void showBossDefeatDialogue(String bossType) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println();
        if (bossType.equals("Aries")) {
            System.out.println("Aresios falls to one knee, his blazing weapon dimming.");
            System.out.println();
            System.out.println("Aresios: \"You... actually beat me?\"");
            System.out.println();
            System.out.println("The chains binding him begin to crack and dissolve.");
            System.out.println();
            System.out.println("Aresios: \"Zeus... lied to us. All this time...\"");
            System.out.println();
            System.out.println(player.getName() + ": \"We were meant to fight together, not against each other.\"");
            System.out.println();
            System.out.println("Aresios: \"Then let's finish what we started. Zeus will pay.\"");
            System.out.println();
            System.out.println("!! Aresios has been freed! The chains are broken!");
        } else if (bossType.equals("Cancer")) {
            System.out.println("Selinia's staff falls from her hands as she collapses.");
            System.out.println();
            System.out.println("Selinia: \"Is it... finally over?\"");
            System.out.println();
            System.out.println("The lunar glass prison shatters around her, chains dissolving into moonlight.");
            System.out.println();
            System.out.println("Selinia: \"I... I was trapped in my own bitterness. Zeus used my pain.\"");
            System.out.println();
            System.out.println(player.getName() + ": \"You're free now. And we need you.\"");
            System.out.println();
            System.out.println("Selinia: \"Then I will heal what Zeus has broken. Starting with him.\"");
            System.out.println();
            System.out.println("!! Selinia has been freed! The chains are broken!");
        } else {
            System.out.println("Orionis lowers his bow, the celestial chains snapping one by one.");
            System.out.println();
            System.out.println("Orionis: \"My arrows... they were never mine to control.\"");
            System.out.println();
            System.out.println("The starlight bonds dissolve, and he stands free for the first time.");
            System.out.println();
            System.out.println("Orionis: \"Zeus made me hunt my own kind. I won't forget that.\"");
            System.out.println();
            System.out.println(player.getName() + ": \"Then help me hunt him down.\"");
            System.out.println();
            System.out.println("Orionis: \"The final hunt begins. And this time, I choose my prey.\"");
            System.out.println();
            System.out.println("!! Orionis has been freed! The chains are broken!");
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