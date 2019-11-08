package seedu.address.model;

import java.util.ArrayList;

/**
 * A (@code DriverManager) with an undo/redo history
 */
public class VersionedDriverManager extends DriverManager {

    private static ArrayList<DriverManager> driverManagerStateList;
    private static int currentStatePointer;

    public VersionedDriverManager(DriverManager driverManager) {
        super();
        driverManagerStateList = new ArrayList<>();
        currentStatePointer = 0;
        driverManagerStateList.add(currentStatePointer, driverManager);
    }

    /**
     * Returns the stored history of (@code DriverManager)s.
     *
     * @return stored history of (@code DriverManager)s.
     */
    public static ArrayList<DriverManager> getDriverManagerStateList() {
        return driverManagerStateList;
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
     * Saves the current DriverManager state in (@code driverManagerStateList).
     */
    public static void commit(DriverManager driverManager) {
        currentStatePointer++;
        driverManagerStateList.add(currentStatePointer, driverManager);
    }

    /**
     * Returns the undone version of the (@code DriverManager).
     *
     * @return undone version of (@code DriverManager).
     */
    public static DriverManager undo() {
        currentStatePointer--;
        DriverManager previousVersion = driverManagerStateList.get(currentStatePointer);
        return previousVersion;
    }

    /**
     * Returns the redone version of the (@code DriverManager).
     *
     * @return redone version of (@code DriverManager).
     */
    public static DriverManager redo() {
        currentStatePointer++;
        DriverManager nextVersion = driverManagerStateList.get(currentStatePointer);
        return nextVersion;
    }

    /**
     * Shortens the driverManagerStateList
     */
    public static void truncateList() {
        int cutoff = currentStatePointer;
        for (int i = driverManagerStateList.size() - 1; i > cutoff; i--) {
            driverManagerStateList.remove(i);
        }
    }
}
