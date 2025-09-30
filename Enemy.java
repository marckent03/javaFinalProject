public class Enemy {
    private String name;
    private String type;
    private int currentHP;
    private int maxHP;
    private int maxMP;
    private int currentMP;
    private int skill2Cooldown;
    private int skill3Cooldown;
    
    public Enemy(String name, String type, int hp, int mp) {
        this.name = name;
        this.type = type;
        this.maxHP = hp;
        this.currentHP = hp;
        this.maxMP = mp;
        this.currentMP = mp;
        this.skill2Cooldown = 0;
        this.skill3Cooldown = 0;
    }
    
    public String getName() { return name; }
    public String getType() { return type; }
    public int getCurrentHP() { return currentHP; }
    public int getMaxHP() { return maxHP; }
    public int getCurrentMP() { return currentMP; }
    public int getMaxMP() { return maxMP; }
    
    public boolean isAlive() {
        return currentHP > 0;
    }
    
    public void takeDamage(int damage) {
        currentHP = currentHP - damage;
        if (currentHP < 0) {
            currentHP = 0;
        }
    }
    
    public void reduceCooldowns() {
        if (skill2Cooldown > 0) skill2Cooldown--;
        if (skill3Cooldown > 0) skill3Cooldown--;
    }
    
    public int attack() {
        reduceCooldowns();
        
        int skillChoice = (int)(Math.random() * 3) + 1;
        
        if (skillChoice == 2 && skill2Cooldown > 0) skillChoice = 1;
        if (skillChoice == 3 && skill3Cooldown > 0) skillChoice = 1;
        
        int mpCost = (skillChoice == 1) ? 0 : (skillChoice == 2) ? 20 : 40;
        if (currentMP < mpCost) skillChoice = 1;
        
        currentMP -= mpCost;
        
        if (skillChoice == 2) skill2Cooldown = 2;
        if (skillChoice == 3) skill3Cooldown = 3;
        
        return getSkillDamage(skillChoice);
    }
    
    public String getSkillName(int skillNum) {
        if (type.equals("Aries")) {
            if (skillNum == 1) return "Raging Charge";
            if (skillNum == 2) return "Blazing Strike";
            return "Infernal Cataclysm";
        } else if (type.equals("Cancer")) {
            if (skillNum == 1) return "Tidal Wave";
            if (skillNum == 2) return "Moonlit Barrier";
            return "Eclipse Embrace";
        } else if (type.equals("Sagittarius")) {
            if (skillNum == 1) return "Piercing Arrow";
            if (skillNum == 2) return "Rain of Stars";
            return "Celestial Execution";
        } else if (type.contains("God")) {
            if (skillNum == 1) return "Thunder Wrath";
            if (skillNum == 2) return "Divine Judgement";
            return "Storm Overload";
        }
        return "Attack";
    }
    
    public int getSkillDamage(int skillNum) {
        if (type.equals("Aries")) {
            if (skillNum == 1) return 90;
            if (skillNum == 2) return 130;
            return 210;
        } else if (type.equals("Cancer")) {
            if (skillNum == 1) return 80;
            if (skillNum == 2) return 110;
            return 190;
        } else if (type.equals("Sagittarius")) {
            if (skillNum == 1) return 95;
            if (skillNum == 2) return 140;
            return 220;
        } else if (type.equals("God_Phase1")) {
            if (skillNum == 1) return 120;
            if (skillNum == 2) return 170;
            return 250;
        } else if (type.equals("God_Phase2")) {
            if (skillNum == 1) return 150;
            if (skillNum == 2) return 200;
            return 300;
        } else if (type.contains("_Minion")) {
            return 40 + (int)(Math.random() * 20);
        }
        return 50;
    }
    
    public String getAttackMessage() {
        if (type.equals("Aries")) {
            return name + " charges with blazing fury!";
        } else if (type.equals("Cancer")) {
            return name + " strikes with tidal force!";
        } else if (type.equals("Sagittarius")) {
            return name + " fires a starlight arrow!";
        } else if (type.contains("God")) {
            return name + " unleashes divine power!";
        } else if (type.contains("_Minion")) {
            return name + " attacks fiercely!";
        }
        return name + " attacks!";
    }
}