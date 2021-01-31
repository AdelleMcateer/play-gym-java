/**
 * The is the Assessment Class.
 * <p>
 * It stores weight, thigh, waist, comment and a Trainer that entered
 * the member’s assessment (i.e. a personal trainer).
 * <p>
 * This class just has the standard constructor, accessor and mutator method
 * with no validation on any fields
 * <p>
 *
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */

public class Assessment {
    private float weight;
    private float thigh;
    private float waist;
    private String comment;
    private Trainer trainer;

    /**
     * Default Constructor included to run the Outstanding level tests
     *
     * @param weight
     * @param thigh
     * @param waist
     * @param comment
     */
    public Assessment(float weight, float thigh, float waist, String comment) {
        setWeight(weight);
        setThigh(thigh);
        setWaist(waist);
        setComment(comment);
    }

    /**
     * This is an overloaded constructor for the Assessment to include the Trainer
     *
     * @param weight  the member's weight for a current assessment
     * @param thigh   the member's thigh measurement for a current assessment
     * @param waist   the member's waist measurement for a current assessment
     * @param comment the comment added by the trainer for a current assessment
     * @param Trainer the trainer associated with a Member
     */
    public Assessment(float weight, float thigh, float waist, String comment, String Trainer) {
        setWeight(weight);
        setThigh(thigh);
        setWaist(waist);
        setComment(comment);
        setTrainer();
    }

    public Assessment(String comment) {
    }

    //Accessors & Mutator methods//

    /**
     * Returns the member's weight from a particular assessment
     *
     * @return float for the member's weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * Sets the member's weight from a particular assessment
     *
     * @param weight float for the member's weight
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * Returns the member's thigh measurement from a particular assessment
     *
     * @return loat for the member's thigh measurement
     */
    public float getThigh() {
        return thigh;
    }

    /**
     * Sets the member's thigh measurement from a particular assessment
     *
     * @param thigh float for the member's thigh measurement
     */
    public void setThigh(float thigh) {
        this.thigh = thigh;
    }

    /**
     * Returns the member's waist measurement from a particular assessment
     *
     * @return float for the member's waist measurement
     */
    public float getWaist() {
        return waist;
    }

    /**
     * Sets the member's waist measurement from a particular assessment
     *
     * @param waist the member's waist measurement
     */
    public void setWaist(float waist) {
        this.waist = waist;
    }

    /**
     * Returns the Trainer comment from a particular assessment
     *
     * @return String for the Trainer comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the Trainer comment from a particular assessment
     *
     * @param comment String for the Trainer comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Returns the Trainer that added a particular assessment
     *
     * @return the Trainer who added the assessment
     */
    public Trainer getTrainer() {
        return trainer;
    }

    /**
     * Sets the Trainer that added a particular assessment
     *
     * @return the Trainer who added the assessment
     */
    public Trainer setTrainer() {
        return trainer;
    }

    /**
     * String method that formats the printing of the object state
     * and returns it.
     *
     * @return String of the Assessment details
     */

    @Override
    public String toString() {
        return "Weight: " + getWeight()
                + "\nThigh: " + getThigh()
                + "\nWaist: " + getWaist()
                + "\nTrainer's Comment: " + getComment()
                + "\nTrainer: " + getTrainer().getName() + " .";
    }
}