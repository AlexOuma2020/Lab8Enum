package ru.spmi.temnov.lab8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainTestNotRandom {

    private HashMap<String, Integer> countCheck(){
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String st: RandomGenerator.getAll()){
            Integer num = 0;
            for (TV tv: TV.values())
                if (tv.getName().equals(st))
                    ++num;
            for (Fridge fr: Fridge.values())
                if (fr.getName().equals(st))
                    ++num;
            hashMap.put(st, num);
        }
        return hashMap;
    }
    @Test
    public void countTestNotRandom() throws RuntimeException {
        Main m = new Main();
        System.out.println("Заданные значения!\n");
        try {
            TV tv = TV.SMALLTV;
            tv.setName("LG");
            tv = TV.MIDTV;
            tv.setName("Haier");
            tv = TV.BIGTV;
            tv.setName("Siemens");
            Fridge fr = Fridge.BLACKF;
            fr.setName("Haier");
            fr = Fridge.WHITEF;
            fr.setName("Samsung");
            fr = Fridge.GREYF;
            fr.setName("LG");
            Method method = Main.class.getDeclaredMethod("printList");
            method.setAccessible(true);
            method.invoke(m, null);
            method = Main.class.getDeclaredMethod("count", String.class);
            method.setAccessible(true);
            int numOfApps = (int) method.invoke(m, "LG");
            assertEquals(2, numOfApps);
            System.out.printf("Количество товаров компании LG равно %d\n", numOfApps);
            numOfApps = (int) method.invoke(m, "Siemens");
            assertEquals(1, numOfApps);
            System.out.printf("Количество товаров компании Siemens равно %d\n", numOfApps);
            numOfApps = (int) method.invoke(m, "Haier");
            assertEquals(2, numOfApps);
            System.out.printf("Количество товаров компании Haier равно %d\n", numOfApps);
            numOfApps = (int) method.invoke(m, "Samsung");
            assertEquals(1, numOfApps);
            System.out.printf("Количество товаров компании Samsung равно %d\n", numOfApps);
            numOfApps = (int) method.invoke(m, "Bosch");
            assertEquals(0, numOfApps);
            System.out.printf("Количество товаров компании Bosch равно %d\n", numOfApps);
            numOfApps = (int) method.invoke(m, "Sharp");
            assertEquals(0, numOfApps);
            System.out.printf("Количество товаров компании Sharp равно %d\n", numOfApps);
            numOfApps = (int) method.invoke(m, "Hitachi");
            assertEquals(0, numOfApps);
            System.out.printf("Количество товаров компании Hitachi равно %d\n", numOfApps);
            System.out.println();
        } catch (NoSuchMethodException e) {
            System.out.println("Нет такого метода! " + e);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}