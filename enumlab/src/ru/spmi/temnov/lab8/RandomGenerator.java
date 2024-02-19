package ru.spmi.temnov.lab8;

public class RandomGenerator {//класс случайной генерации
    private static final String[] company = new java.lang.String[]{"LG", "Haier", "Sharp", "Samsung", "Bosch", "Siemens", "Hitachi"};
    public static String getRandomComp(){//случайное имя фирмы-производителя
        return company[(int)(Math.random() * company.length)];
    }

    public static String[] getAll(){
        return company;
    }
}
