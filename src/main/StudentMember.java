/**
 * This is the StudentMember class.
 * It is a  sub-class of the Member class.
 * <p>
 * It provides the concrete implementation for the chosenPackage method.
 * There is no data validation.
 * <p>
 * Fields: email, name, address, gender, height
 * starting weight, chosen package and college name.
 *
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */

public class StudentMember extends Member {

    private String collegeName;

    /**
     * Constructor for objects of the Student Member Class
     *
     * @param email          the student's email
     * @param name           the student's name
     * @param address        the student's address
     * @param gender         the student's gender
     * @param height         the student's height
     * @param startingWeight the student's start weight
     * @param chosenPackage  the student's chosen package
     * @param collegeName    the student's college name
     */

    public StudentMember(String email, String name, String address, String gender,
                         float height, float startingWeight,
                         String chosenPackage, String collegeName) {
        super(email, name, address, gender, height, startingWeight, chosenPackage);

        this.collegeName = collegeName;
    }

    /**
     * Provides the concrete implementation for this method.
     * <p>
     * The chosenPackage is set to the package associated with their collegeName.
     * If there is no package associated with their college, default to “Package 3”.
     *
     * @param packageChoice
     */
    public void chosenPackage(String packageChoice) {
        if (getCollegeName().toUpperCase().equals("WIT")) {
            setChosenPackage("WIT");
        } else setChosenPackage("Package 3");
    }

    //Accessors & Mutator methods//

    /**
     * @return get collegeName
     */
    public String getCollegeName() {
        return collegeName;
    }

    /**
     * @param collegeName set college name
     */
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    /**
     * @return String for the current Student Member
     */
    @Override
    public String toString() {
        return super.toString() + "/ College Name: " + collegeName;
    }
}


