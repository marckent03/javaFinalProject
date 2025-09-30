public class Potion {
    private String name;
    private String description;
    private int quantity;
    private int hpRestore;
    private int mpRestore;
    
    public Potion(String name, String description, int quantity, int hpRestore, int mpRestore) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.hpRestore = hpRestore;
        this.mpRestore = mpRestore;
    }
    
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getQuantity() { return quantity; }
    
    public void use(Player player) {
        if (quantity > 0) {
            player.heal(hpRestore);
            player.restoreMana(mpRestore);
            quantity--;
            System.out.println("Used " + name + "!");
            if (hpRestore > 0) System.out.println("Restored " + hpRestore + " HP!");
            if (mpRestore > 0) System.out.println("Restored " + mpRestore + " MP!");
        }
    }
}