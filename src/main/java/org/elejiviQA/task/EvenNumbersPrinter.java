package org.elejiviQA.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

/**
 * Задача 3.
 * Задан массив целых чисел: [1, 2, 3, 4, 5, 6, 7, 8, 9,10]
 * необходимо написать программу, которая выведет в консоль все чётные числа.
 */
public class EvenNumbersPrinter extends Task {

    private static final Logger logger = LoggerFactory.getLogger(EvenNumbersPrinter.class);
    private int[] numbers;

    public EvenNumbersPrinter(String key) {
        super(key);
    }

    @Override
    public void execute() {
        showTask();
        read();
        compare();
        isReadStreamRequired();
    }

    @Override
    protected void read() {
        numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        logger.info("Массив чисел инициализирован");
    }

    @Override
    protected void compare() {
        Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);

        String evenNumbers = Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .mapToObj(String::valueOf)
                .reduce((a, b) -> a + ", " + b)
                .orElse("Нет чётных чисел");
        System.out.println("Все чётные числа: " + evenNumbers);

        logger.info("Набор четных чисел массива: {}", evenNumbers);
    }
}
