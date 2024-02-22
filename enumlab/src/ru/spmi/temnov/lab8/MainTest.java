package ru.spmi.temnov.lab8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {//класс тестов для всех методов и метода расчета вычисляемого показателя со случайными значениями

    private void provideInput(String data){//метод имитации консольного ввода
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
    Main m;
    @BeforeEach
    void testible(){
        m = new Main();
    }

    @Test
    public void testFound1(){//проверка метода found на совпадение с существующей фирмой
        try {
            Method method = Main.class.getDeclaredMethod("found", String.class);
            method.setAccessible(true);
            assertTrue((Boolean) method.invoke(m, "LG"));
            System.out.println("Товар фирмы LG существует\n");

        } catch (NoSuchMethodException e) {
            System.out.println("Нет такого метода! " + e);

        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testFound2(){//проверка метода found на совпадение с несуществующей фирмой
        try {
            Method method = Main.class.getDeclaredMethod("found", String.class);
            method.setAccessible(true);
            assertFalse((Boolean) method.invoke(m, "lG"));
            System.out.println("Товара фирмы lG не существует\n");

        } catch (NoSuchMethodException e) {
            System.out.println("Нет такого метода! " + e);

        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testFound3(){//проверка метода found на совпадение с пустой строкой
        try {
            Method method = Main.class.getDeclaredMethod("found", String.class);
            method.setAccessible(true);
            assertEquals(false, method.invoke(m, ""));
            System.out.println("Товара фирмы без названия не существует\n");

        } catch (NoSuchMethodException e) {
            System.out.println("Нет такого метода! " + e);

        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void inputTest1(){//проверка ввода существующей компании

        provideInput("Haier");//ввод

        try {
            Method method = Main.class.getDeclaredMethod("inputCompany", null);
            method.setAccessible(true);
            String output = (String) method.invoke(m);
            assertEquals("Haier", output);
            System.out.println("Ввод Haier подерживается\n");

        } catch (NoSuchMethodException e) {
            System.out.println("Нет такого метода! " + e);

        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void inputTest2(){//проверка ввода несуществующей комании или пустой строки

        provideInput("");

        try {
            Method method = Main.class.getDeclaredMethod("inputCompany", null);
            method.setAccessible(true);
            String output = (String) method.invoke(m);
            assertNull(output);
            System.out.println("Ввод пустой строки подерживается\n");

        } catch (NoSuchMethodException e) {
            System.out.println("Нет такого метода! " + e);

        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
    private HashMap<String, Integer> countCheck(){//локальный подсчет количества товаров
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (Company comp: Company.values()){
            Integer num = 0;
            for (TV tv: TV.values())
                if (tv.getName().equals(comp.getName()))
                    ++num;

            for (Fridge fr: Fridge.values())
                if (fr.getName().equals(comp.getName()))
                    ++num;

            hashMap.put(comp.getName(), num);
        }

        return hashMap;
    }

    @Test
    public void countTest() throws RuntimeException {//проверка корректности расчета вычисляемого показателя

        try {
            HashMap<String, Integer> countFirm = countCheck();

            Method method = Main.class.getDeclaredMethod("printList");
            method.setAccessible(true);
            method.invoke(m, null);

            method = Main.class.getDeclaredMethod("count", String.class);
            method.setAccessible(true);

            for (Company comp: Company.values()){
                int numOfApps = (int) method.invoke(m, comp.getName());
                assertEquals(countFirm.get(comp.getName()), numOfApps);
                System.out.printf("Количество товаров компании %s равно %d\n", comp.getName(), numOfApps);
            }

            System.out.println();

        } catch (NoSuchMethodException e) {

            System.out.println("Нет такого метода! " + e);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}