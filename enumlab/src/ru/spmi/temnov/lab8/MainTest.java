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
    void companyGetRandomTest(){ //проверка корректности получения случайного значения
        try {
            Method method = Main.class.getDeclaredMethod("found", String.class);//для привтного метода
            method.setAccessible(true);
            assertTrue((Boolean) method.invoke(m, RandomGenerator.getRandomComp()));
            System.out.println("Случайное значение массива company работает корректно\n");

        } catch (NoSuchMethodException e) {
            System.out.println("Нет такого метода! " + e);

        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void companyGetAllTest(){//проверка возврата массива названий-фирм
        assertArrayEquals(new String[]{"LG", "Haier", "Sharp", "Samsung", "Bosch", "Siemens", "Hitachi"}, RandomGenerator.getAll());
        System.out.println("Получение массива фирм работает корректно\n");
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
    public void countTest() throws RuntimeException {//проверка корректности расчета вычисляемого показателя
        System.out.println("Случайные значения!\n");

        try {
            HashMap<String, Integer> countFirm = countCheck();

            Method method = Main.class.getDeclaredMethod("printList");
            method.setAccessible(true);
            method.invoke(m, null);

            method = Main.class.getDeclaredMethod("count", String.class);
            method.setAccessible(true);

            for (String st: RandomGenerator.getAll()){
                int numOfApps = (int) method.invoke(m, st);
                assertEquals(countFirm.get(st), numOfApps);
                System.out.printf("Количество товаров компании %s равно %d\n", st, numOfApps);
            }

            System.out.println();

        } catch (NoSuchMethodException e) {

            System.out.println("Нет такого метода! " + e);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}