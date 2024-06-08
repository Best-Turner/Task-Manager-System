package org.example.service;

import org.example.exeption.TaskNotFoundException;
import org.example.model.Task;
import org.example.storage.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

public class TaskService {

    private final TaskRepository storage;

    public TaskService(TaskRepository storage) {
        this.storage = storage;
    }

    public void saveTask(String name, String description) {
        int countTask = storage.getSizeTasks();
        countTask++;
        storage.addTask(new Task(countTask, name, description, LocalDateTime.now()));
    }

    public Task getTask(int id) throws TaskNotFoundException {
        return existById(id);
    }

    public boolean deleteTaskById(int id) throws TaskNotFoundException {
        List<Task> tasks = storage.allTasks();
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("Список задач пуст. Создайте новую задачу\n");
        }
        existById(id);
        storage.deleteTaskById(id);
        return true;
    }

    public List<Task> allTasks() {
        return storage.allTasks();
    }

    private Task existById(int id) throws TaskNotFoundException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID не может быть отрицательным или равняться 0\n");
        }
        return storage.getTaskById(id).orElseThrow(()
                -> new TaskNotFoundException(String.format("Задачи с ID = %d Не найдено =(\n", id)));
    }
}
