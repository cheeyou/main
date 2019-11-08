package seedu.address.model;

import java.util.ArrayList;

/**
 * A (@code CustomerManager) with an undo/redo history
 */
public class VersionedCustomerManager extends CustomerManager {

    private static ArrayList<CustomerManager> customerManagerStateList;
    private static int currentStatePointer;

    public VersionedCustomerManager(CustomerManager customerManager) {
        super();
        customerManagerStateList = new ArrayList<>();
        currentStatePointer = 0;
        customerManagerStateList.add(currentStatePointer, customerManager);
    }

    /**
     * Returns the stored history of (@code CustomerManager)s.
     *
     * @return stored history of (@code CustomerManager)s.
     */
    public static ArrayList<CustomerManager> getCustomerManagerStateList() {
        return customerManagerStateList;
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
     * Saves the current CustomerManager state in (@code customerManagerStateList).
     */
    public static void commit(CustomerManager customerManager) {
        currentStatePointer++;
        customerManagerStateList.add(currentStatePointer, customerManager);
    }

    /**
     * Returns the undone version of the (@code CustomerManager).
     *
     * @return undone version of (@code CustomerManager).
     */
    public static CustomerManager undo() {
        currentStatePointer--;
        CustomerManager previousVersion = customerManagerStateList.get(currentStatePointer);
        return previousVersion;
    }

    /**
     * Returns the redone version of the (@code CustomerManager).
     *
     * @return redone version of (@code CustomerManager).
     */
    public static CustomerManager redo() {
        currentStatePointer++;
        CustomerManager nextVersion = customerManagerStateList.get(currentStatePointer);
        return nextVersion;
    }

    /**
     * Shortens the customerManagerStateList
     */
    public static void truncateList() {
        int cutoff = currentStatePointer;
        for (int i = customerManagerStateList.size() - 1; i > cutoff; i--) {
            customerManagerStateList.remove(i);
        }
    }
}
