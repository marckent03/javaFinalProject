import java.util.ArrayList;

public class Player extends Character {
    private int eclipsium;
    private ArrayList<Potion> inventory;
    
    public Player(String name, String zodiacType) {
        super(name, zodiacType, getMaxHPForType(zodiacType), getMaxMPForType(zodiacType));
        this.eclipsium = 0;
        this.inventory = new ArrayList<>();
    }
    
    private static int getMaxHPForType(String zodiacType) {
        if (zodiacType.equals("Aries")) return 450;
        else if (zodiacType.equals("Cancer")) return 500;
        else return 400;
    }
    
    private static int getMaxMPForType(String zodiacType) {
        if (zodiacType.equals("Aries")) return 150;
        else if (zodiacType.equals("Cancer")) return 200;
        else return 180;
    }
    
    public String getZodiacType() { return type; }
    public int getEclipsium() { return eclipsium; }
    public ArrayList<Potion> getInventory() { return inventory; }
    
    public void addEclipsium(int amount) {
        eclipsium += amount;
    }
    
    public boolean spendEclipsium(int amount) {
        if (eclipsium >= amount) {
            eclipsium -= amount;
            return true;
        }
        return false;
    }
    
    public void addPotion(Potion potion) {
        for (Potion p : inventory) {
            if (p.getName().equals(potion.getName())) {
                p.addQuantity(potion.getQuantity());
                return;
            }
        }
        inventory.add(potion);
    }
    
    public void restoreFullHealth() {
        this.currentHP = this.maxHP;
    }

    public void restoreFullMana() {
        this.currentMP = this.maxMP;
    }
    
    public void heal(int amount) {
        currentHP = Math.min(currentHP + amount, maxHP);
    }
    
    public void restoreMana(int amount) {
        currentMP = Math.min(currentMP + amount, maxMP);
    }
    
    public void rest() {
        currentHP = Math.min(currentHP + (maxHP / 2), maxHP);
        currentMP = Math.min(currentMP + (maxMP / 2), maxMP);
    }
    
    public void resetCooldowns() {
        skill2Cooldown = 0;
        skill3Cooldown = 0;
    }
    
    @Override
    public String getSkillName(int skillNum) {
        if (type.equals("Aries")) {
            if (skillNum == 1) return "Raging Charge";
            if (skillNum == 2) return "Blazing Strike";
            return "Infernal Cataclysm";
        } else if (type.equals("Cancer")) {
            if (skillNum == 1) return "Tidal Wave";
            if (skillNum == 2) return "Moonlit Barrier";
            return "Eclipse Embrace";
        } else {
            if (skillNum == 1) return "Piercing Arrow";
            if (skillNum == 2) return "Rain of Stars";
            return "Celestial Execution";
        }
    }
    
    @Override
    public int getSkillDamage(int skillNum) {
        if (type.equals("Aries")) {
            if (skillNum == 1) return 80;
            if (skillNum == 2) return 120;
            return 200;
        } else if (type.equals("Cancer")) {
            if (skillNum == 1) return 70;
            if (skillNum == 2) return 0;
            return 180;
        } else {
            if (skillNum == 1) return 90;
            if (skillNum == 2) return 60;
            return 220;
        }
    }
    
    public int getSkillMpCost(int skillNum) {
        if (skillNum == 1) return 0;
        if (skillNum == 2) return 30;
        return 50;
    }
    
    public int getSkillCooldown(int skillNum) {
        if (skillNum == 1) return 0;
        if (skillNum == 2) return skill2Cooldown;
        return skill3Cooldown;
    }
    
    public void displaySkills() {
        System.out.println("\n=== YOUR SKILLS ===");
        for (int i = 1; i <= 3; i++) {
            String cooldownText = "";
            if (i == 2 && skill2Cooldown > 0) {
                cooldownText = " [COOLDOWN: " + skill2Cooldown + " turns]";
            } else if (i == 3 && skill3Cooldown > 0) {
                cooldownText = " [COOLDOWN: " + skill3Cooldown + " turns]";
            }
            
            System.out.println(i + ". " + getSkillName(i) + 
                             " (DMG: " + getSkillDamage(i) + 
                             ", MP: " + getSkillMpCost(i) + ")" + cooldownText);
        }
    }
    
    public int useSkill(int skillNum) {
        int mpCost = getSkillMpCost(skillNum);
        
        if (skillNum == 2 && skill2Cooldown > 0) {
            return -1;
        }
        if (skillNum == 3 && skill3Cooldown > 0) {
            return -1;
        }
        if (currentMP < mpCost) {
            return -2;
        }
        
        currentMP -= mpCost;
        
        if (skillNum == 2) {
            skill2Cooldown = 2;
        } else if (skillNum == 3) {
            skill3Cooldown = 3;
        }
        
        System.out.println("You use " + getSkillName(skillNum) + "!");
        
        if (type.equals("Cancer") && skillNum == 2) {
            int healAmount = 150;
            heal(healAmount);
            System.out.println("You heal for " + healAmount + " HP!");
            return 0;
        }
        
        return getSkillDamage(skillNum);
    }
    
    @Override
    public int attack() {
        return useSkill(1);
    }
    
    @Override
    public String getAttackMessage() {
        return name + " attacks with " + getSkillName(1) + "!";
    }
    
    public void showStats() {
        System.out.println("\n=== " + name.toUpperCase() + " STATS ===");
        System.out.println("Zodiac Type: " + type);
        System.out.println("HP: " + currentHP + "/" + maxHP);
        System.out.println("MP: " + currentMP + "/" + maxMP);
        System.out.println("Eclipsium: " + eclipsium);
        System.out.println("\nInventory:");
        if (inventory.isEmpty()) {
            System.out.println("  Empty");
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println("  " + (i+1) + ". " + inventory.get(i).getName() + 
                                 " x" + inventory.get(i).getQuantity());
            }
        }
        System.out.println();
    }
}