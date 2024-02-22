package ru.spmi.temnov.lab8;

public enum Company {
    LG("LG"), SAMSUNG("Samsung"), SHARP("Sharp"), HAIER("Haier"), BOSCH("Bosch");
    Company(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return name;
    }
    private final String name;

}
