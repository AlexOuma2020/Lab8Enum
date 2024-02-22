package ru.spmi.temnov.lab8;

public enum Fridge {//класс холодильник
    HaierCF2(Company.HAIER, "Белый"),
    LG_GCB509(Company.LG, "Черный"),
    Bosch_49KGNXL(Company.BOSCH, "Серый");

    Fridge(Company name, String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Холодильник фирмы " + name + ". Цвет = " + color + '\n';
    }

    public String getName() {
        return this.name.toString();
    }

    private Company name;//название фирмы
    private final String color;//цвет
}
