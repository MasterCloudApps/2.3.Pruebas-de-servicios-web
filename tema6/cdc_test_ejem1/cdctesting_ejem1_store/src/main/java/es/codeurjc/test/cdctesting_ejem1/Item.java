package es.codeurjc.test.cdctesting_ejem1;

public class Item {

    private String name;

    public Item() {}
    public Item(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
