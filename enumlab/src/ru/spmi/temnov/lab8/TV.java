package ru.spmi.temnov.lab8;

public enum TV {//класс телевизор
    SMALLTV(RandomGenerator.getRandomComp(), 24),
    MIDTV(RandomGenerator.getRandomComp(), 45),
    BIGTV(RandomGenerator.getRandomComp(), 65);

    private final int screen;//диагональ экрана
    private String name;//название фирмы

    TV(String name, int screen) {
        this.name = name;
        this.screen = screen;
    }

    public void show() {
        System.out.printf("Телевизор фирмы %s с диагональю %d\n", this.name, this.screen);
    }

    public String getName() {
        return this.name;
    }
    public void setName(String string){
        name = string;
    }
}
