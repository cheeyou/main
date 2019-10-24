package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;



/**
 * Represents a Customer in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */

public class Customer extends Person {

    public static final String MESSAGE_INVALID_ID = "Invalid customer ID.";

    private static int idCount = 0;
    private int id;

    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */

    public Customer(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        id = ++idCount;
    }

    public int getIdCount() {
        return idCount;
    }

    public int getId() {
        return id;
    }

    /**
     * Returns true if both customers of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two customers.
     */
    public boolean isSameCustomer(Customer otherCustomer) {
        if (otherCustomer == this) {
            return true;
        }

        return otherCustomer != null
                && otherCustomer.getName().equals(getName())
                && (otherCustomer.getPhone().equals(getPhone()) || otherCustomer.getEmail().equals(getEmail()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Customer)) {
            return false;
        }

        Customer otherCustomer = (Customer) other;
        return otherCustomer.getName().equals(getName())
                && otherCustomer.getPhone().equals(getPhone())
                && otherCustomer.getEmail().equals(getEmail())
                && otherCustomer.getAddress().equals(getAddress())
                && otherCustomer.getTags().equals(getTags());
    }
}
