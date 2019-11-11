package seedu.address.model.task;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;

import seedu.address.model.Model;
import seedu.address.model.person.Driver;

/**
 * Manages the task list.
 * It contains the minimal set of list operations.
 */
public class TaskManager {

    private TaskList tasks;

    public TaskManager() {
        tasks = new TaskList();
    }

    public TaskManager(TaskList savedTasks) {
        this();
        tasks = savedTasks;
    }

    public ObservableList<Task> getList() {
        return tasks.getList();
    }

    public TaskList getTaskList() {
        return tasks;
    }

    public TaskList getDeepCopyTaskList(Model model) {
        TaskList deepCopyTaskList = new TaskList();
        for(Task task : tasks.getList()) {
            deepCopyTaskList.addTask((Task) deepCopy(task));
        }
        return deepCopyTaskList;
    }

    //task list operations

    public void addTask(Task newTask) {
        tasks.addTask(newTask);
    }

    public void deleteTask(Task taskToRemove) {
        tasks.deleteTask(taskToRemove);
    }

    public Task getTask(int taskId) {
        return tasks.getTask(taskId);
    }

    public void setTask(Task taskToEdit, Task editedTask) {
        tasks.setTask(taskToEdit, editedTask);
    }

    public boolean hasTask(Task task) {
        return tasks.hasTask(task);
    }

    public boolean hasTask(int taskId) {
        return tasks.hasTask(taskId);
    }

    public void setTaskList(TaskList taskList) {
        tasks.setTaskList(taskList.getList());
    }

    public static List<Task> getSortedList(List<Task> tasks, Comparator<Task> comparator) {
        return TaskList.getSortedList(tasks, comparator);
    }

    public static List<Task> getFilteredList(List<Task> tasks, Predicate<Task> predicate) {
        return TaskList.getFilteredList(tasks, predicate);
    }

    public static List<Driver> getDriversFromTasks(List<Task> assignedTasks) {
        return TaskList.getDriversFromTasks(assignedTasks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskManager otherObject = (TaskManager) o;
        return getList().equals(otherObject.getList());
    }

    /**
     * Makes a deep copy of any Java object that is passed.
     */
    private static Object deepCopy(Object object) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
            outputStrm.writeObject(object);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
            return objInputStream.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
