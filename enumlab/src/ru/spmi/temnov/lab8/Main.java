package ru.spmi.temnov.lab8;

import java.io.*;
import java.util.StringJoiner;

public class Main {//основной класс
    private boolean found(String needed){//поиск совпадения с названием фирмы - соотвествие сроки одному из названий
        for (Company comp: Company.values()){
            if (comp.getName().equals(needed))
                return true;
        }
        return false;
    }

    private void printList(){//вывод списка товаров
        System.out.println("Список телевизоров: ");
        for (TV tv: TV.values()){
            System.out.print(tv);
        }

        System.out.println("\nСписок холодильников: ");
        for (Fridge fridge: Fridge.values()){
            System.out.print(fridge);
        }

        System.out.println();
    }
    private String inputCompany() throws IOException{//ввод строки
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
    private String menu(){//без понятия как сделать тест, меню ввода строки-названия компании
        String needed = null;
        boolean excep;

        do{
            excep = true;
            System.out.print("Введите название фирмы, количество товаров которой хотите узнать {");
            StringJoiner sj = new StringJoiner(", ");
            for (Company comp: Company.values()){
                sj.add(comp.getName());
            }
            System.out.println(sj.toString() + "} :");

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
    private int count(String str){//расчет вычисляемого показателя
        int num = 0;

        for (TV tv: TV.values()) {
            if(str.equals(tv.getName()))
                ++num;
        }

        for (Fridge fridge: Fridge.values()){
            if (str.equals(fridge.getName()))
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
