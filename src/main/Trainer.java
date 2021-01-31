/**
 * This is the Trainer class.
 * It is a  sub-class of the Person class.
 * <p>
 * Fields: email, name, address, gender and speciality.
 *
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */

public class Trainer extends Person {
    private String speciality;

    /**
     * Constructor for objects of the Student Member Class
     *
     * @param email      the trainer's email
     * @param name       the trainer's name
     * @param address    the trainer's address
     * @param gender     the trainer's gender
     * @param speciality the trainer's speciality e.g weight-training
     */

    public Trainer(String email, String name, String address, String gender,
                   String speciality) {
        super(email, name, address, gender);
        this.speciality = speciality;
    }

    /**
     * Getter for the Trainer's speciality.
     *
     * @return a String with the Trainer's speciality e.g weight-lifting
     */

    public String getSpeciality() {
        return speciality;
    }

    /**
     * Setter for the Trainer's speciality.
     *
     * @param speciality e.g Weight-Lifiting/Training
     */

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    /**
     * @return A string outlining the Trainer's speciality
     */

    @Override
    public String toString() {
        return super.toString() + "/Speciality: " + speciality;
    }
}