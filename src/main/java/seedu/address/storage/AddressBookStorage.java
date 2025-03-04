package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CustomerManager;
import seedu.address.model.legacy.AddressBook;
import seedu.address.model.legacy.ReadOnlyAddressBook;
import seedu.address.model.task.TaskManager;

/**
 * Represents a storage for {@link AddressBook}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyAddressBook}. Returns
     * {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected
     *                                 format.
     * @throws IOException             if there was any problem when reading from
     *                                 the storage.
     */
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     *
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyAddressBook)
     */
    void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} and {@link TaskManager} to the
     * storage.
     *
     * @param addressBook cannot be null.
     * @param taskManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyAddressBook addressBook, TaskManager taskManager,
                         CustomerManager customerManager) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyAddressBook, TaskManager, CustomerManager)
     */
    void saveAddressBook(ReadOnlyAddressBook addressBook, TaskManager taskManager,
                         CustomerManager customerManager, Path filePath) throws IOException;

}
