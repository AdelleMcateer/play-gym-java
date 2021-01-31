/**
 * This is the person or 'super' class and contains the
 * following details common to the sub-classes:
 * <p>
 * Fields: email, name, address and gender.
 * <p>
 * It's subclasses (Member & Trainer) override the superclass method
 * String toString(), call the superclass to String method and also
 * report on the new fields defined in these subclasses.
 *
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */

public class Person {

    public String name;
    public String email;
    public String address;
    public String gender;

    /**
     * Constructor for the Super class
     *
     * @param email   the person's email
     * @param name    the person's name
     * @param address the person's address
     * @param gender  the person's gender
     */
    public Person(String email, String name, String address, String gender) {
        setName(name);
        this.email = email;
        this.address = address;
        setGender(gender);
    }

    //Empty constructor added as I was trying to add a Mock member test
    public Person() {
    }

    /**
     * @return a String for the person's name
     */

    //Accessors & Mutator methods//
    public String getName() {
        return name;
    }

    /**
     * The name is maximum 30 characters;
     * Any name entered will be truncated to 30 characters.
     *
     * @param name
     */

    public void setName(String name) {
        if (name.length() <= 30) {
            this.name = name;
        } else {
            this.name = name.substring(0, 30);
        }
    }

    /**
     * @return a String for the person's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return a String for the person's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the person's gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * The gender can be either “M” or “F”. If it is not
     * either of these then apply a default value of “Unspecified”.
     *
     * @param gender
     */
    public void setGender(String gender) {
        if ((gender.toUpperCase().equals("M")) || (gender.toUpperCase().equals("F"))) {
            this.gender = gender.toUpperCase();
        } else {
            this.gender = "Unspecified";
        }
    }

    /**
     * String method that formats the printing of the object state and returns it.
     *
     * @return a string with the Person details.
     */
    @Override
    public String toString() {
        return "Email: " + email + " Name: " + name + " Address: " + address +
                " Gender: " + gender;
    }
}
