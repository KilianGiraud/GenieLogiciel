package re.forestier.edu.rpg;

public class Item {

    private final String name;
    private final String description;
    private final int weight;
    private final int value;

    public Item(String name, String description, int weight, int value) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.value = value;
    }

    public String getItemName() {
        return name;
    }

    public String getItemDescription() {
        return description;
    }

    public int getItemWeight() {
        return weight;
    }

    public int getItemValue() {
        return value;
    }

    public String toStringItem() {
        return name + " (" + weight + "kg, " + value + "g)";
    }
}

