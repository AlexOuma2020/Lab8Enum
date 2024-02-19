package ru.spmi.temnov.lab8;

import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainTestNotRandom {//класс теста для НЕСЛУЧАЙНЫХ значений

    @Test
    public void countTestNotRandom() throws RuntimeException {//метод расчета ВП для неслучайных значений
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