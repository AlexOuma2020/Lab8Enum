package ru.spmi.temnov.lab8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private void provideInput(String data){
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
    Main m;
    @BeforeEach
    void testible(){
        m = new Main();
    }

    @Test
    void companyGetRandomTest(){
        try {
            Method method = Main.class.getDeclaredMethod("found", String.class);
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
    void companyGetAllTest(){
        assertArrayEquals(new String[]{"LG", "Haier", "Sharp", "Samsung", "Bosch", "Siemens", "Hitachi"}, RandomGenerator.getAll());
        System.out.println("Получение массива фирм работает корректно\n");
    }

    @Test
    public void testFound1(){
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
    public void testFound2(){
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
    public void testFound3(){
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
    public void inputTest1(){
        provideInput("Haier");
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
    public void inputTest2(){
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
    public void countTest() throws RuntimeException {
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