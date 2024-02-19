package ru.spmi.temnov.lab8;

public enum Fridge {
    WHITEF(RandomGenerator.getRandomComp(), "Белый"),
    BLACKF(RandomGenerator.getRandomComp(), "Черный"),
    GREYF(RandomGenerator.getRandomComp(), "Серый");

    private String name;
    private final String color;

    Fridge(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public void show() {
        System.out.printf("Холодильник фирмы %s. Цвет = %s\n", this.name, this.color);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String string){
        name = string;
    }
}
