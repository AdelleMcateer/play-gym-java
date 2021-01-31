import java.util.HashMap;

/**
 * This is the PremiumMember class.
 * It is a  sub-class of the Member class.
 * <p>
 * It provides the concrete implementation for the chosenPackage
 * method. There is no data validation.
 * <p>
 * Fields: email, name, address, gender, height,
 * and start weight.
 *
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */

public class PremiumMember extends Member {
    /**
     * Constructor for objects of the Premium Member Class
     *
     * @param email          member's E-mail address
     * @param name           member's Name
     * @param address        member's Address
     * @param gender         member's Gender (M/F)
     * @param height         member's Height (in meters)
     * @param startingWeight member's startWeight (in kgs)
     * @param chosenPackage  member's chosenPackage (in kgs)
     */

    public PremiumMember(String email, String name, String address, String gender,
                         float height, float startingWeight, String chosenPackage) {
        super(email, name, address, gender, height, startingWeight, chosenPackage);
    }

    /**
     * Overlaoded Constructor for objects of the Premium Member Class. I added this
     * when I was trying to incorporate HashMaps.
     *
     * @param email             member's E-mail address
     * @param name              member's Name
     * @param address           member's Address
     * @param gender            member's Gender (M/F)
     * @param height            member's Height (in meters)
     * @param startingWeight    member's startWeight (in kgs)
     * @param chosenPackage     member's chosenPackage (in kgs)
     * @param assessmentHashMap assessment for the member contained in a Hashmap
     */
    public PremiumMember(String email, String name, String address, String gender,
                         float height, float startingWeight, String chosenPackage,
                         HashMap assessmentHashMap) {
    }

    /**
     * Provides the concrete implementation for this method.
     * The chosenPackage is set to the value passed as a parameter.
     * There is no validation on the entered data
     *
     * @param chosenPackage
     */

    @Override
    public void chosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }
}

