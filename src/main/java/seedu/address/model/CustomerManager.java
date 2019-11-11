package seedu.address.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.collections.ObservableList;

import seedu.address.model.person.Customer;
import seedu.address.model.person.UniqueEntityList;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Manages the customer list.
 * It contains the minimal set of list operations.
 */
public class CustomerManager extends EntityManager<Customer> {

    public CustomerManager() {
        super();
    }

    /**
     * Retrieve customer using its unique customer id.
     *
     * @param customerId customer unique id.
     * @return Customer with the specified unique id.
     */
    public Customer getCustomer(int customerId) {
        return getPersonList()
                .stream()
                .filter(customer -> customer.getId() == customerId)
                .findFirst()
                .orElseThrow(PersonNotFoundException::new);
    }

    /**
     * Returns an unmodifiable view of the customer list.
     * This list will not contain any duplicate customers.
     *
     * @return Customer list without duplicate customers.
     */
    public ObservableList<Customer> getCustomerList() {
        return super.getPersonList();
    }

    public ObservableList<Customer> getDeepCopyCustomerList() {
        UniqueEntityList<Customer> customers= super.getPersons();
        UniqueEntityList<Customer> deepCopyCustomers = new UniqueEntityList<>();
        for(Customer customer: customers) {
            deepCopyCustomers.add((Customer) deepCopy(customer));
        }
        return deepCopyCustomers.asUnmodifiableObservableList();
    }

    /**
     * Checks if the customer list has a customer with {@code Customer customer}.
     *
     * @param customer customer to be checked
     */
    public boolean hasCustomer(Customer customer) {
        return super.hasPerson(customer);
    }

    /**
     * Checks if the customer list has a customer with {@code int customerId}.
     *
     * @param customerId customer unique id.
     */
    public boolean hasCustomer(int customerId) {
        return getPersonList()
                .stream()
                .anyMatch(customer -> customer.getId() == customerId);
    }

    public void setCustomer(Customer customerToEdit, Customer editedCustomer) {
        super.setPerson(customerToEdit, editedCustomer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerManager otherObject = (CustomerManager) o;
        return getCustomerList().equals(otherObject.getCustomerList());
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
