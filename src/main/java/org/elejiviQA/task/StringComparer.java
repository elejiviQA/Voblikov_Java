package org.elejiviQA.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

/**
 * Задача 2.
 *  Написать программу, которая принимает на вход две строки (a и b) и сравнивает их.
 *  В результате сравнения в консоль должно быть выведено одно из сообщений: "Строки неидентичны" или "Строки идентичны"
 */
public class StringComparer extends Task {

    private static final Logger logger = LoggerFactory.getLogger(StringComparer.class);

    private String a;
    private String b;

    public StringComparer(String key) {
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
        try {
            System.out.print("Введите первую строку: ");
            a = reader.readLine();
            System.out.print("Введите вторую строку: ");
            b = reader.readLine();
        } catch (IOException e) {
            logger.error("Ошибка при чтении строк: ", e);
            System.out.println("Произошла ошибка при чтении. Попробуйте еще раз.");
            read();
        }
    }

    @Override
    protected void compare() {
        if (Objects.equals(a, b)) {
            logger.info("Строки идентичны");
            System.out.println("Строки идентичны");
        } else {
            logger.info("Строки неидентичны");
            System.out.println("Строки неидентичны");
        }
    }
}
