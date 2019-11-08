package seedu.address.model.task;

import java.util.ArrayList;

/**
 * A (@code TaskManager) with an undo/redo history
 */
public class VersionedTaskManager extends TaskManager {

    private static ArrayList<TaskManager> taskManagerStateList;
    private static Integer currentStatePointer;

    public VersionedTaskManager(TaskManager taskManager) {
        super();
        taskManagerStateList = new ArrayList<>();
        currentStatePointer = 0;
        taskManagerStateList.add(currentStatePointer, taskManager);
    }

    /**
     * Returns the stored history of (@code TaskManager)s.
     *
     * @return stored history of (@code TaskManager)s.
     */
    public static ArrayList<TaskManager> getTaskManagerStateList() {
        return taskManagerStateList;
    }

    /**
     * Returns the index of the current state pointer.
     *
     * @return index of current state pointer.
     */
    public static int getCurrentStatePointer() {
        return currentStatePointer;
    }

    /**
     * Saves the current TaskManager state in (@code taskManagerStateList).
     */
    public static void commit(TaskManager taskManager) {
        currentStatePointer++;
        taskManagerStateList.add(currentStatePointer, taskManager);
    }

    /**
     * Returns the undone version of the (@code TaskManager).
     *
     * @return undone version of (@code TaskManager).
     */
    public static TaskManager undo() {
        currentStatePointer--;
        TaskManager previousVersion = taskManagerStateList.get(currentStatePointer);
        return previousVersion;
    }

    /**
     * Returns the redone version of the (@code TaskManager).
     *
     * @return redone version of (@code TaskManager).
     */
    public static TaskManager redo() {
        currentStatePointer++;
        TaskManager nextVersion = taskManagerStateList.get(currentStatePointer);
        return nextVersion;
    }

    /**
     * Shortens the taskManagerStateList
     */
    public static void truncateList() {
        int cutoff = currentStatePointer;
        for (int i = taskManagerStateList.size() - 1; i > cutoff; i--) {
            taskManagerStateList.remove(i);
        }
    }
}
