package ru.spmi.temnov.lab8;

import java.io.*;

public class Main {
    private boolean found(String needed){
        for (String firm: RandomGenerator.getAll()){
            if (needed.equals(firm))
                return true;
        }
        return false;
    }

    private void printList(){
        System.out.println("Список телевизоров: ");
        for (TV tv: TV.values()){
            System.out.print(tv + " -> ");
            tv.show();
        }

        System.out.println("\nСписок холодильников: ");
        for (Fridge fridge: Fridge.values()){
            System.out.print(fridge + " -> ");
            fridge.show();
        }
        System.out.println();
    }
    private String inputCompany() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
    private String menu(){//без понятия как сделать тест
        String needed = null;
        boolean excep;
        do{
            excep = true;
            System.out.print("Введите название фирмы, количество товаров которой хотите узнать {LG, Haier, Sharp, Samsung, Bosch, Siemens, Hitachi}: ");
            try {
                needed = inputCompany();
            } catch (IOException e) {
                excep = false;
                System.out.println("Неверный формат ввода!");
            }
            if (!found(needed)){
                System.out.println("Несуществующая фирма " + needed);
                excep = false;
            }
        }while(!excep);
        return needed;
    }
    private int count(String str){
        int num = 0;
        for (TV tv: TV.values()) {
            if (tv.getName().equals(str))
                ++num;
        }
        for (Fridge fridge: Fridge.values()){
            if (fridge.getName().equals(str))
                ++num;
        }
        return num;
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.printList();
        System.out.printf("Количество товара заданной фирмы = %d", m.count(m.menu()));
    }
}
