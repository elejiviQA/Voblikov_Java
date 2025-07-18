package org.elejiviQA.task;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Getter
public abstract class Task {

    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    protected static final BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Поток для чтения был открыт\n");
        logger.info("Поток для чтения был открыт");
    }

    private static int currentId = 1; // статический счетчик
    private static int totalTasksLoaded = 0;

    private final int taskId;
    private int taskNumber;
    private String taskHeader;
    private String taskDescription;

    public Task(String key) {
        this.taskId = currentId++;
        loadFromJson(key);
        totalTasksLoaded = TaskLoader.getTotalTasksLoaded();
    }

    private void loadFromJson(String taskKey) {
        TaskLoader.TaskData data = TaskLoader.getTask(taskKey);
        if (data != null) {
            this.taskNumber = data.number;
            this.taskHeader = data.header;
            this.taskDescription = data.description;
        } else {
            throw new RuntimeException("Данные по ключу '" + taskKey + "' не найдены");
        }
    }

    public abstract void execute();

    protected abstract void read();

    protected abstract void compare();

    protected void isReadStreamRequired() {
        if (this.taskId == totalTasksLoaded) {
            try {
                reader.close();
                logger.info("Поток для чтения был закрыт");
                System.out.println("\nПоток для чтения был закрыт\n");
            } catch (IOException e) {
                logger.error("Ошибка при закрытии BufferedReader", e);
            }
        }
    }

    public void showTask() {
        System.out.println("\n********************");
        System.out.printf("*     %s     *\n", taskHeader);
        System.out.print(taskDescription);
        System.out.println("********************\n");
    }
}