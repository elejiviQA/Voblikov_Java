package org.elejiviQA.task;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Задача 3.
 * Задан массив целых чисел: [1, 2, 3, 4, 5, 6, 7, 8, 9,10]
 * необходимо написать программу, которая выведет в консоль все чётные числа.
 */
@Getter
@Setter
public class EvenNumberPrinter extends Task {

    private static final Logger logger = LoggerFactory.getLogger(EvenNumberPrinter.class);
    private int[] numbers;

    public EvenNumberPrinter(String key) {
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
    public void read() {
        numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        logger.info("Инициализирован массив чисел по умолчанию");
    }

    public void read(int[] numbers) {
        this.numbers = numbers;
        logger.info("Массив чисел инициализирован");
    }

    /**
     * Метод для получения списка чётных чисел из массива
     */
    public List<Integer> getEvenNumbersList() {
        return Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * Метод для получения строки с чётными числами
     */
    public String getEvenNumbersString() {
        List<Integer> evenNumbers = getEvenNumbersList();
        if (evenNumbers.isEmpty()) {
            return "Нет чётных чисел";
        }
        return evenNumbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }

    @Override
    public void compare() {
        String evenNumbersStr = getEvenNumbersString();
        System.out.println("Все чётные числа: " + evenNumbersStr);

        logger.info("Набор четных чисел массива: {}", evenNumbersStr);
    }
}