package org.elejiviQA.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

@Getter
public class TaskLoader {

    private static final Logger logger = LoggerFactory.getLogger(TaskLoader.class);

    @Getter
    public static class TaskData {
        public int number;
        public String header;
        public String description;
    }

    private static Map<String, TaskData> tasksMap;

    static {
        loadTasks();
    }

    private static void loadTasks() {
        Gson gson = new Gson();
        try (InputStreamReader reader = new InputStreamReader(
                Objects.requireNonNull(getResourceAsStream(),
                        "Resource /task.json not found"))) {
            Type type = new TypeToken<Map<String, TaskData>>(){}.getType();
            tasksMap = gson.fromJson(reader, type);
            if (tasksMap == null) {
                logger.warn("JSON файл пустой или неправильно сформатирован");
            } else {
                logger.info("Задачи успешно загружены: {}", tasksMap.keySet());
            }
        } catch (NullPointerException e) {
            logger.error("Не удалось найти ресурс /task.json", e);
        } catch (Exception e) {
            logger.error("Ошибка при чтении JSON файла", e);
        }
    }

    private static InputStream getResourceAsStream() {
        return TaskLoader.class.getResourceAsStream("/task.json");
    }

    public static TaskData getTask(String key) {
        if (tasksMap == null) {
            logger.warn("Мапа задач не инициализирована");
            return null;
        }
        return tasksMap.get(key);
    }

    public static int getTotalTasksLoaded() {
        if (tasksMap == null) {
            return 0;
        }
        return tasksMap.size();
    }
}