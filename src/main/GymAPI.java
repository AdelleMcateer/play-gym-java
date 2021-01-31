import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * <p>
 * This is the GymAPI class which operates between the model classes and menu driver class.
 * It stores: an ArrayList of Member and an ArrayList of Trainer.
 *
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 */

public class GymAPI {
    //public instance fields
    public ArrayList<Member> members;
    public ArrayList<Trainer> trainers;
    public Assessment assessment;
    public String nameEntered;

    public GymAPI() {
        this.members = new ArrayList<Member>();
        this.trainers = new ArrayList<Trainer>();
    }

    /**
     * Adds a member object to the gym.
     *
     * @param member instance
     */
    public void addMember(Member member) {
        members.add(member);
    }

    /**
     * Adds a trainer object to the Gym.
     *
     * @param trainer instance
     */
    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
    }

    /**
     * The number of Members currently stored in the Gym.
     *
     * @return
     */
    public int numberOfMembers() {
        return members.size();
    }

    /**
     * The number of Trainers currently stored in the Gym.
     *
     * @return
     */
    public int numberOfTrainers() {
        return trainers.size();
    }

    /**
     * The members currently stored within an Arraylist.
     *
     * @return
     */
    public ArrayList<Member> getMembers() {
        return members;
    }

    /**
     * The trainers currently stored within an Arraylist.
     *
     * @return
     */
    public ArrayList<Trainer> getTrainers() {
        return trainers;
    }

    /**
     * Returns a boolean indicating if the index passed as a parameter is
     * a valid index for the member’s array list.
     *
     * @param index
     * @return boolean
     */
    public boolean isValidMemberIndex(int index) {
        return index >= 0 && index < members.size();
    }

    /**
     * Returns a boolean indicating if the index passed as a
     * parameter is a valid index for the trainer’s array list.
     *
     * @param index
     * @return .
     */
    public boolean isValidTrainerIndex(int index) {
        return index >= 0 && index < trainers.size();
    }

    /**
     * Returns the member object that matches the email entered.
     * If no member matches, return null.
     *
     * @param emailEntered
     * @return
     */
    public Member searchMembersByEmail(String emailEntered) {
        if (members.size() != 0) {
            Member memberFound = null;
            for (Member member : members) {
                if (member.getEmail().equals(emailEntered)) memberFound = member;
            }
            return memberFound;
        } else {
            return null;
        }
    }

    /**
     * Returns a list of member names that partially or entirely matches
     * the entered name. An empty array is returned when their are no matches.
     *
     * @param nameEntered
     * @return
     */

    public ArrayList searchMembersByName(String nameEntered) {
        ArrayList<String> memberNames = new ArrayList<>();
        {
            for (Member member : members) {
                if (member.getName().contains(nameEntered))
                    memberNames.add(member.getName());
            }
            return memberNames;
        }
    }

    /**
     * Returns the trainer object that matches the email entered.
     * If no trainer matches, return null.
     *
     * @param emailEntered
     * @return
     */
    public Trainer searchTrainersByEmail(String emailEntered) {
        if (trainers.size() != 0) {
            Trainer trainerFound = null;
            for (Trainer trainer : trainers) {
                if (trainer.getEmail().equals(emailEntered)) trainerFound = trainer;
            }
            return trainerFound;
        } else {
            return null;
        }
    }

    /**
     * Returns a list of trainer names that partially or entirely matches
     * the entered name. An empty array is returned when their are no matches.
     *
     * @param nameEntered
     * @return
     */
    public ArrayList<String> searchTrainersByName(String nameEntered) {
        ArrayList<String> trainerName = new ArrayList<>();
        for (Trainer trainer : trainers) {
            if (trainer.getName().contains(nameEntered))
                trainerName.add(trainer.getName());
        }
        return trainerName;
    }

    /**
     * Returns a list containing all the members in the gym.
     * Returns an empty list if none are found.
     *
     * @return
     */
    public ArrayList<Member> listMembers() {
        ArrayList<String> listMember = new ArrayList<>();
        if (members.size() != 0) {
            String listMembers = "";
            for (int i = 0; i < numberOfMembers(); i++) {
                listMembers += i + ": " + members.get(i) + "\n";
            }
        }
        return members;
    }

    /**
     * Returns a list containing all the members details
     * in the gym whose latest assessment weight is an ideal weight
     * (based on the devine formula).
     * <p>
     * Returns an empty list if none are found.
     * <p>
     * I replicated the code I used for previous methods here and applied what I could
     * to try and pass the Outstanding tests.
     *
     * @return
     */
    public ArrayList<Member> listMembersWithIdealWeight() {
        ArrayList<Member> listOfMembersWithIdealWeight = new ArrayList<>();
        for (Member member : members) {
            Assessment latestAssessment = new Assessment(member.getStartWeight(),
                    0, 0, "");
            if (member.getAssessments().size() > 0) {
                latestAssessment = member.latestAssessment();
            }
            if (GymUtility.isIdealBodyWeight(member, latestAssessment)) {
                listOfMembersWithIdealWeight.add(member);
            }
        }
        return listOfMembersWithIdealWeight;
    }


    /**
     * Returns a string containing all the members details in the gym whose BMI category
     * (based on the latest assessment weight) partially or entirely matches the entered
     * category. Returns an empty list if none are found.
     * <p>
     * I replicated the code I used for previous methods here and applied what I could
     * to pass the Outstanding tests.
     *
     * @param category
     * @return a String of members within a specific BMI category
     */
    public ArrayList<Member> listMembersBySpecificBMICategory(String category) {
        ArrayList<Member> listMembersBySpecificBMICategory = new ArrayList<>();
        for (Member member : members) {
            Assessment latestAssessment = new Assessment(member.getStartWeight(),
                    0, 0, "");
            if (member.getAssessments().size() > 0) {
                latestAssessment = member.latestAssessment();
            }
            if (GymUtility.isIdealBodyWeight(member, latestAssessment)) {
                listMembersBySpecificBMICategory.add(member);
            }
        }
        return listMembersBySpecificBMICategory;
    }

    /**
     * List, for each member, their latest assessment weight and their height
     * both imperially and metrically. The format of the output is like so:
     * Joe Soap: xx kg (xxx lbs) x.x metres (xx inches).
     * Joan Soap: xx kg (xxx lbs) x.x metres (xx inches).
     * If there are no members in the gym, the message
     * "No registered members" should be return
     * <p>
     * Again, I replicated the code I used for previous methods here and applied what I could
     * to try and pass the Outstanding tests.
     *
     * @return a String with the Member's details imperially and metrically.
     */
    public String listMemberDetailsImperialAndMetric() {
        String listOfMembers = "";
        if (members.size() != 0) {
            for (Member member : members) {
                Assessment assessment = member.latestAssessment();
            }
        } else {
            return "No registered members";
        }
        return listOfMembers;
    }


    @SuppressWarnings("unchecked")

    /*
     * Push the members and trainers array lists out to the associated XML file.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        // ------------------ PREVENT SECURITY WARNINGS-----------------------------
        // The  class is what we are reading in.
        // Modify to include others if needed by modifying the next line,
        // add additional classes inside the braces, comma separated
        Class<?>[] classes = new Class[]{Member.class, Trainer.class,
                PremiumMember.class, StudentMember.class, Assessment.class};
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        // -------------------------------------------------------------------------
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter
                ("gym.xml"));
        out.writeObject(members);
        out.writeObject(trainers);
        out.writeObject(assessment);
        out.close();
    }

    /*
     * Pull the members and trainers array lists from the associated XML file.
     *
     * @throws Exception
     */
    public void load() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        // ------------------ PREVENT SECURITY WARNINGS-----------------------------
        // The class is what we are reading in.
        // Modify to include others if needed by modifying the next line,
        // add additional classes inside the braces, comma separated
        Class<?>[] classes = new Class[]{Member.class, Trainer.class,
                PremiumMember.class, StudentMember.class, Assessment.class};
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        // -------------------------------------------------------------------------
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader
                ("gym.xml"));
        members = (ArrayList<Member>) is.readObject();
        trainers = (ArrayList<Trainer>) is.readObject();
        is.close();
    }
}



