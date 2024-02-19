package ru.spmi.temnov.lab8;

public class RandomGenerator {
    private static final String[] company = new java.lang.String[]{"LG", "Haier", "Sharp", "Samsung", "Bosch", "Siemens", "Hitachi"};
    public static String getRandomComp(){
        return company[(int)(Math.random() * company.length)];
    }

    public static String[] getAll(){
        return company;
    }
}
