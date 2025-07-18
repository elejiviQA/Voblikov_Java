package org.elejiviQA;

import org.elejiviQA.task.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("\n********************");
        System.out.println("Запуск программы...");
        System.out.println("********************\n");

        TaskLoader.load("/tasks.json");

        Task[] tasks = {
                new NumberProcessor("task1"),
                new StringComparer("task2"),
                new EvenNumberPrinter("task3")
        };

        for (Task task : tasks) {
            try {
                task.execute();
            } catch (Exception e) {
                System.err.println("Ошибка при выполнении задачи: " + task.getClass().getSimpleName());
                logger.error("Исключение при выполнении задачи", e);
            }
        }

        System.out.println("\n********************");
        System.out.println("Завершение программы...");
        System.out.println("********************\n");
    }
}