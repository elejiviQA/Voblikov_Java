package org.elejiviQA;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.elejiviQA.task.EvenNumberPrinter;
import org.elejiviQA.task.TaskLoader;
import org.junit.jupiter.api.*;
import io.qameta.allure.Description;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Функциональность фильтрации четных чисел")
@Story("Тестирование методов класса EvenNumberPrinter")
public class EvenNumberPrinterTest {

    private static EvenNumberPrinter printer;

    @BeforeAll
    public static void setUpOnce() {
        TaskLoader.load("/test_tasks.json");
        printer = new EvenNumberPrinter("evenNumberPrinterTest");
    }

    @BeforeEach
    public void setUp() {
        int[] testArray = {0, 5, 2, 7, 6, 4, 12, 8, 9, 10, 25};
        printer.read(testArray);
    }

    @Test
    @Tag("positive")
    @DisplayName("Получение списка четных чисел")
    @Description("Фильтрация четных чисел из массива")
    public void testGetEvenNumbersList() {
        List<Integer> expected = List.of(0, 2, 6, 4, 12, 8, 10);
        List<Integer> actual = printer.getEvenNumbersList();

        assertAll(
                () -> assertEquals(expected.size(), actual.size(), "Размер списков не совпадает"),
                () -> assertEquals(expected, actual, "Списки четных чисел не совпадают")
        );
    }

    @Test
    @Tag("positive")
    @DisplayName("Строковое представление четных чисел")
    @Description("Формирование строки с четными числами")
    public void testGetEvenNumbersString() {
        String expected = "0, 2, 6, 4, 12, 8, 10";
        String actual = printer.getEvenNumbersString();

        assertEquals(expected, actual);
    }

    @Test
    @Tag("positive")
    @DisplayName("compare() с массивом с четными числами")
    @Description("Вывод compare() при наличии четных чисел в массиве")
    public void testCompareOutputs() {
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        try {
            int[] testArray = {1,2,3};
            printer.read(testArray);
            printer.compare();

            String output = outContent.toString().trim();

            assertAll(
                    () -> assertTrue(output.contains("Все чётные числа:"), "Вывод содержит 'Все чётные числа:'"),
                    () -> assertTrue(output.contains("2"), "Вывод содержит число '2'"),
                    () -> assertFalse(output.contains("Нет чётных чисел"), "Вывод не должен содержать 'Нет чётных чисел'")
            );
        } finally {
            System.setOut(System.out);
        }
    }

    @Test
    @Tag("positive")
    @DisplayName("Массив только из четных чисел")
    @Description("Все числа четные, проверка фильтрации")
    public void testAllEvens() {
        int[] allEvens = {2, 4, 6, 8};
        printer.read(allEvens);

        List<Integer> expectedList = List.of(2,4,6,8);
        List<Integer> actualList = printer.getEvenNumbersList();

        String expectedStr = "2, 4, 6, 8";

        assertAll(
                () -> assertEquals(expectedList.size(), actualList.size(), "Размер списков не совпадает"),
                () -> assertEquals(expectedList, actualList),
                () -> assertEquals(expectedStr, printer.getEvenNumbersString())
        );
    }


    @Test
    @Tag("negative")
    @DisplayName("Обработка пустого массива")
    @Description("Проверка поведения при пустом массиве")
    public void testEmptyArray() {
        printer.read(new int[]{});
        String result = printer.getEvenNumbersString();

        assertEquals("Нет чётных чисел", result);
    }

    @Test
    @Tag("negative")
    @DisplayName("Отсутствие четных чисел в массиве")
    @Description("Поведение метода при массиве без четных чисел")
    public void testGetEvenNumbersString_NoEvens() {
        int[] noEvens = {1,3,5};
        printer.read(noEvens);
        String expected = "Нет чётных чисел";
        String actual = printer.getEvenNumbersString();
        assertEquals(expected, actual);
    }

    @Test
    @Tag("negative")
    @DisplayName("Массив только из нечетных чисел")
    @Description("Все числа нечетные; проверка вывода")
    public void testNoEvens() {
        int[] noEvens = {1, 3, 5};
        printer.read(noEvens);

        String result = printer.getEvenNumbersString();

        assertEquals("Нет чётных чисел", result);
    }

    // Доп метод для генерации случайного массива
    private int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100); // числа от 0 до 99
        }
        return arr;
    }

    @Test
    @Tag("performance")
    @DisplayName("Обработка большого массива случайных чисел")
    @Description("Работа метода с большим объемом данных")
    public void testLargeRandomArray() {
        int[] largeArray = generateRandomArray(1000);
        printer.read(largeArray);
        List<Integer> evens = printer.getEvenNumbersList();

        for (Integer num : evens) {
            assertTrue(num % 2 == 0);
        }
    }
}