package org.elejiviQA.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

/**
 * Задача 1. Написать программу, которая принимает на вход два целых числа (a и b) и совершает с ними следующие действия:
 * - сравнивает эти два числа и возвращает результат сравнения путем вывода в консоль одного из вариантов: "a > b", "a < b" или "a = b";
 * - совершает с этими числами операции сложения, вычитания, деления и умножения и результат выводит в консоль.
 */
public class NumberProcessor extends Task {

    private static final Logger logger = LoggerFactory.getLogger(NumberProcessor.class);

    private int a;
    private int b;

    public NumberProcessor(String taskKey) {
        super(taskKey);
    }

    @Override
    public void execute() {
            showTask();
            read();
            compare();
            performArithmeticOperations();
            isReadStreamRequired();
    }

    @Override
    public void read() {
        System.out.print("Введите два целых числа (a и b), разделенные пробелом: ");
        String input;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            logger.error("Ошибка при чтении ввода", e);
            System.out.println("Ошибка ввода: " + e.getMessage());
            return;
        }
        if (input == null) {
            logger.warn("Ввод равен null");
            return;
        }
        String[] parts = Objects.requireNonNull(input).trim().split("\\s+");
        if (parts.length != 2) {
            logger.warn("Некорректный ввод: ожидается два числа, получено {}", parts.length);
            System.out.println("Пожалуйста, введите ровно два числа.");
            read();
            return;
        }
        try {
            a = Integer.parseInt(parts[0]);
            b = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            logger.warn("Некорректный формат чисел: {}", input);
            System.out.println("Некорректный формат числа. Пожалуйста, вводите целые числа.");
            read();
        }
    }

    @Override
    public void compare() {
        String result;
        if (a > b) {
            result = String.format("%d > %d", a, b);
        } else if (a < b) {
            result = String.format("%d < %d", a, b);
        } else {
            result = String.format("%d = %d", a, b);
        }
        logger.info("Сравнение: {}", result);
        System.out.println("Сравнение: " + result);
    }

    private void performArithmeticOperations() {
        logger.info(" Сложение: {} + {} = {}", a, b, a + b);
        logger.info("Вычитание: {} - {} = {}", a, b, a - b);
        logger.info("Умножение: {} * {} = {}", a, b, a * b);
        System.out.println(" Сложение: " + (a + b));
        System.out.println("Вычитание: " + (a - b));
        System.out.println("Умножение: " + (a * b));

        if (b != 0) {
            double divisionResult = (double) a / b;
            String formattedResult = String.format("%.2f", divisionResult);
            logger.info("  Деление: {} / {} = {}", a, b, formattedResult);
            System.out.println("  Деление: " + formattedResult);
        } else {
            logger.warn("Попытка деления на ноль");
            System.out.println("Деление на ноль невозможно.");
        }
    }
}