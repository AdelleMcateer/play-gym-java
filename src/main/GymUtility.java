/**
 * This is the utility class (or helper class) with a "structure" that has
 * only static methods and encapsulates no state (fields).
 *
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */

public class GymUtility {
    /**
     * Returns the BMI for the member based on the calculation:
     * <p>
     * BMI is weight divided by the square of the height.
     *
     * @param member     for who the calculation is being performed
     * @param assessment Assessment details from the members latest assessment
     * @return a double for the member BMI
     */
    public static double calculateBMI(Member member, Assessment assessment) {
        return toTwoDecimalPlaces(assessment.getWeight() / (member.getHeight()
                * member.getHeight()));
    }

    /**
     * Returns the category the BMI belongs to, based on the following values:
     * <p>
     * BMI less than 16 (exclusive) is "SEVERELY UNDERWEIGHT"
     * BMI between 16 (inclusive) and 18.5 (exclusive) is "UNDERWEIGHT"
     * BMI between 18.5 (inclusive) and 25(exclusive) is "NORMAL"
     * BMI between 25 (inclusive) and 30 (exclusive) is "OVERWEIGHT"
     * BMI between 30 (inclusive) and 35 (exclusive) is "MODERATELY OBESE"
     * BMI greater than 35 (inclusive) and is "SEVERELY OBESE"
     *
     * @param bmiValue
     * @return the member's BMI category
     */
    public static String determineBMICategory(double bmiValue) {
        //boundary case added for testing purposes
        if (bmiValue < 14) {
            return "VERY SEVERELY UNDERWEIGHT";
        } else if ((bmiValue >= 14) && (bmiValue < 16)) {
            return "SEVERELY UNDERWEIGHT";
        } else if ((bmiValue >= 16) && (bmiValue < 18.5)) {
            return "UNDERWEIGHT";
        } else if ((bmiValue >= 18.5) && (bmiValue < 25)) {
            return "NORMAL";
        } else if ((bmiValue >= 25) && (bmiValue < 30)) {
            return "OVERWEIGHT";
        } else if ((bmiValue >= 30) && (bmiValue < 35)) {
            return "MODERATELY OBESE";
        } else if ((bmiValue >= 35) && (bmiValue < 40)) {
            return "SEVERELY OBESE";
        } else {
            return "VERY SEVERELY OBESE";
        }
    }

    /**
     * Returns a boolean to indicate if the member has an ideal body weight based on the
     * Devine formula:
     * <p>
     * For males, an ideal body weight is: 50 kg + 2.3 kg for each inch over 5 feet.
     * For females, an ideal body weight is: 45.5 kg + 2.3 kg for each inch over 5 feet.
     * <p>
     * Note: if no gender is specified, return the result of the female calculation.
     * <p>
     * Note: if the member is 5 feet or less, return 50kg for male and 45.5kg for female.
     * <p>
     * To allow for different calculations and rounding, when testing for the ideal
     * body weight, if it is +/- .2 of the devine formula, return true.
     *
     * @param member
     * @param assessment
     * @return a boolean to indicate if the member has an ideal body weight
     */
    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        float heightToInches = convertHeightMetresToInches(member);
        int fiveFeet = 60;
        float idealBodyWeight;

        if (heightToInches <= fiveFeet) {
            if (member.getGender().equals("M")) {
                idealBodyWeight = 50;
            } else {
                idealBodyWeight = 45.5f;
            }
        } else {
            if (member.getGender().equals("M")) {
                idealBodyWeight = 50f + (2.3f * (heightToInches - fiveFeet));
            } else {
                idealBodyWeight = 45.5f + (2.3f * (heightToInches - fiveFeet));
            }
        }
        return (idealBodyWeight >= (assessment.getWeight() - 2)) && (idealBodyWeight <=
                (assessment.getWeight() + 2));
    }

    /**
     * Method which converts the height of the Member to Inches for the BMI calculation
     * and returns it to 2 decimal places. One metre equals 39.37inches.
     *
     * @param member
     * @return a float with the member's height conversion
     */
    public static float convertHeightMetresToInches(Member member) {
        return toTwoDecimalPlaces(member.getHeight() * 39.37f);
    }

    /**
     * Method which truncates a float to 2 decimal places taken from Shop V5.0.
     *
     * @param num float passed to it from other methods
     * @return float truncated to 2 decimal places
     */
    private static float toTwoDecimalPlaces(float num) {
        return (int) (num * 100f) / 100.0f;
    }
}