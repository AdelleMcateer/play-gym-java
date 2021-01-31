import java.util.*;

/**
 * This is the member class.
 * <p>
 * It is a  sub-class of the person class.
 * Fields: person’s height, starting weight, chosenPackage and a hashmap to record all
 * the member's progress i.e. assessments performed by trainers.
 * <p>
 * The map key will be the date (as a string) the assessment was performed, in the format
 * YY/MM/DD, e.g.18/05/04. The map values will be an assessment's detail.
 *
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */

public class Member extends Person {
    public HashMap<String, Assessment> assessmentHashMap = new HashMap<>();
    protected String chosenPackage;
    private float height;
    private float startingWeight;
    private SortedSet<String> sortedAssessmentDates;

    //I added these constructirs as it allows me to run the unit tests without error.
    //empty constructor
    public Member() {
    }

    //Default constructor with the Parameters from the Super class
    public Member(String name, String email, String address, String gender) {
    }

    /**
     * Constructor for objects of the Member Class
     *
     * @param email          Member's Email
     * @param name           Member's Name
     * @param address        Member's Address
     * @param gender         Member's Gender (M/F)
     * @param height         Member's Height (in meters)
     * @param staringtWeight Member's starting weight in kgs
     * @param chosenPackage  Member's Chosen Package e.g Premium Student
     */
    public Member(String email, String name, String address, String gender,
                  float height, float staringtWeight, String chosenPackage) {
        super(email, name, address, gender);
        setHeight(height);
        setStartWeight(staringtWeight);
        setChosenPackage(chosenPackage);
    }

    /**
     * Overloaded constructor to include the Assessment HashMap parameter
     *
     * @param email             Member's Email
     * @param name              Member's Name
     * @param address           Member's Address
     * @param gender            Member's Gender (M/F)
     * @param height            Member's Height (in meters)
     * @param startingWeight    Member's starting weight in kgs
     * @param chosenPackage     Member's Chosen Package e.g Premium Student
     * @param assessmentHashMap Member assessment added by the trainer and stored in a HashMap
     */

    public Member(String email, String name, String address, String gender,
                  float height, float startingWeight, String chosenPackage,
                  HashMap assessmentHashMap) {
        super(email, name, address, gender);
        setHeight(height);
        setStartWeight(startingWeight);
        setChosenPackage(chosenPackage);
        this.assessmentHashMap = assessmentHashMap;
    }

    /**
     * Returns the latest assessment based on last entry (by calendar date)
     *
     * @return the latest Assessment
     */
    //TODO: I have been trying to implent the SortedSet and TreeSet based on the labs and slack
    // but not sure I fully understand it yet. Try to revise and update.
    public Assessment latestAssessment() {
        SortedSet<String> latest = sortedAssessmentDates();
        {
            Iterator<String> it = latest.iterator();
            while (it.hasNext()) {
                // returns the latest assessment in the HashMap
                return assessmentHashMap.get(latestAssessment());
            }
            return null;
        }
    }

    /**
     * Returns the assessments dates (as strings) sorted in date order.
     *
     * @return returns sorted set by Date
     */
    public SortedSet<String> sortedAssessmentDates() {
        SortedSet<String> sortedSet = new TreeSet<>();
        System.out.println(assessmentHashMap);
        return sortedAssessmentDates;
    }

    /**
     * @param packageChoice for the member's chosen package
     */
    public void chosenPackage(String packageChoice) {
    }

    //Accessors & Mutator methods//

    /**
     * Sets the Hashmap
     *
     * @param hashMap Has a hashmap key of Date and a value of Assessment
     */

    public void setHashMap(HashMap<String, Assessment> hashMap) {
        this.assessmentHashMap = hashMap;
    }

    /**
     *
     * @return
     */
    public HashMap<String, Assessment> getAssessments() {
        return getAssessments();
    }

    /**
     * Height is measured in metres and must be between
     * 1 and 3 inclusive.
     *
     * @return returns the member's height
     */
    public float getHeight() {
        if ((height >= 1) && (height <= 3))
            this.height = height;
        return height;
    }

    /**
     * Sets the member's height field.
     *
     * @param height The member's height is measured in Meters. A minimum height of
     *               one meter is allowed and a maximum height of three meters.
     */
    public void setHeight(float height) {
        if ((height >= 1) && (height <= 3))
            this.height = height;
    }

    /**
     * Starting Weight is measured in kgs and must be
     * between 35 and 250.
     *
     * @return returns the member's starting weight
     */
    public float getStartWeight() {
        if ((startingWeight >= 35) && (startingWeight <= 250))
            this.startingWeight = startingWeight;
        return startingWeight;
    }

    /**
     * Updates the member starting weight field.
     *
     * @param startWeight The member's weight upon joining the gym (in kgs). A minimum
     *                    weight of 35kg and a max of 250kg is permitted in the gym.
     */
    public void setStartWeight(float startWeight) {
        if ((startWeight >= 35) && (startWeight <= 250))
            this.startingWeight = startWeight;
    }

    /**
     * @return returns the member's chosen package
     */
    public String getChosenPackage() {
        return chosenPackage;
    }

    /**
     * @return returns the hashmap
     */

    /**
     * Sets the Members Chosen Package
     *
     * @param chosenPackage Members chosen package - either Premium or Student
     */

    public void setChosenPackage(String chosenPackage) {
        this.chosenPackage = chosenPackage;
    }

    /**
     * @param hashMap
     */
    public void getHashMap(HashMap<String, Assessment> hashMap) {
        this.assessmentHashMap = hashMap;
    }

    /**
     * String method that formats the printing of the object state
     * and returns it.
     *
     * @return a string representation
     */
    public String toString() {
        return super.toString() + "/ Height: " + height + "m"
                + "/ Starting Weight: " + startingWeight + "kg"
                + "/ Chosen Package: " + chosenPackage;
    }

}

