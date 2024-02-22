package ru.spmi.temnov.lab8;

public enum TV {//класс телевизор
    LG_43UR(Company.LG, 43),
    Samsung_QN85QN(Company.SAMSUNG, 85),
    Sharp_LC55(Company.SHARP, 55);

    TV(Company name, int screen) {
        this.name = name;
        this.screen = screen;
    }

    @Override
    public String toString() {
        return "Телевизор фирмы " + name + " с диагональю = " + screen + '\n';
    }

    public String getName() {
        return this.name.toString();
    }

    private final int screen;//диагональ экрана
    private Company name;//название фирмы
}
