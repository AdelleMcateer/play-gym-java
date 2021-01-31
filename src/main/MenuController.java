import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Adelle McAteer
 * @version 1.0
 * @date 07/06/2020
 * <p>
 * The menu driver class (MenuController) uses the console I/O to interact with the user.
 * It creates an instance  of the GymApi class and allows the user to navigate
 * (a subset of) the system's features through a series of menus.
 * <p>
 * On app start-up, the gym data (trainers and members) is automatically loaded
 * from an XML file.
 * <p>
 * The Menu System asks the user to either Login(L) or Register(R).
 * <p>
 * If the user selects login, the email entered will be verified by checking it
 * is stored in the appropriate arraylist i.e. the members or trainers list.
 * <p>
 * If the email doesn’t exist, “access is denied” and the program returns to
 * the login screen.
 * <p>
 * If the user selects register, they are asked to enter the required details
 * for the member/trainer. If they entered an email that is already used in
 * the system (for either trainers/members), “access is denied” due to an
 * invalid email and they are asked to enter a new one.
 * <p>
 * Whether they select Login or Register the next menu asks whether they
 * are a Member(M) or Trainer(T).
 * <p>
 * Once logged in, the appropriate menu (trainer or member)
 * displays for the member.
 * <p>
 * The trainer menu should allow the trainer to:
 * Add a new member
 * List all members
 * Search for a member by email
 * Assessment sub-menu:
 * Add an assessment for a member
 * Update comment on an assessment for a member
 * <p>
 * The member menu should allow the member to:
 * View their profile
 * Update their profile
 * Progress sub-menu:
 * View progress by weight
 * View progress by waist measurement
 * <p>
 * On app exit, the gym data  (trainers and members) automatically
 * saves to an XML file.
 */

public class MenuController {

    private Scanner input = new Scanner(System.in);
    //creating an instance of the GymApi class called gym
    private GymAPI gym;
    private Member member;
    private Trainer trainer;

    private Assessment assessment;
    private HashMap<String, String> chosenPackage;

    public MenuController() {
        gym = new GymAPI();
        chosenPackage = new HashMap<String, String>();
        member = new Member();
        gym = new GymAPI();

        fillPackagesMap();

        //On app start-up, automatically load the gym data
        // (trainers and members) from an XML file.
        try {
            gym.load();
        } catch (Exception e) {
            System.err.println("Error loading from file: " + e);
        }
    }

    public static void main(String[] args) {
        //declaring the Main method to allow the Menu Controller run as the Main Menu
        MenuController app = new MenuController();
        app.mainMenu();
    }

    //Method to populate the Mackages HashMap, I struggled to include this in the menu
    //so it is redundant
    private void fillPackagesMap() {
        chosenPackage.put(" 1", "Allowed access anytime to gym.\nFree access to " +
                "+ all classes.\nAccess to all changing areas including deluxe changing rooms.");
        chosenPackage.put("Package 2", "Allowed access anytime to gym.\n€3 fee for all classes." +
                "\nAccess to all changing areas including deluxe changing rooms.");
        chosenPackage.put("Package 3", "Allowed access to gym at off-peak times. " +
                "€5 fee for all classes. \nNo access to deluxe changing rooms.");
        chosenPackage.put("WIT", "Allowed access to gym during term time." +
                "\n€4 fee for all classes.  \nNo access to deluxe changing rooms.");
    }

    //This is the main menu the user sees on start up. I used character's and
    //if statements. I attempted to change it but I didn't like the layout and
    //it was causing issues as I had gone too far with other elements of the menu.
    private void mainMenu() {
        System.out.println("------------------------------");
        System.out.println(">-----Welcome to PlayGym-----<");
        System.out.println("------------------------------");
        System.out.println(">-What would you like to do?-<");
        System.out.println("------------------------------");
        System.out.println(">-----Press (L) to Login-----<");
        System.out.println("--------------OR--------------");
        System.out.println(">----Press (R) to Register---<");
        System.out.println("------------------------------");
        System.out.print("==>>");
        // returns the next character option the user enters
        char option = input.next().charAt(0);
        boolean nextOption = false;

        //If statement for uppercase or Lower case input
        if ((option == 'L') || (option == 'l')) {
            System.out.println("------------------------------");
            System.out.println(">-----------LOGIN------------<");
            System.out.println("------------------------------");
            System.out.println(">----Are you a Member (M)----<");
            System.out.println("--------------OR--------------");
            System.out.println(">----Are you a Trainer (T)---<");
            System.out.println("------------------------------");
            System.out.print("==>>");
            option = input.next().charAt(0);

            String email;
            if ((option == 'M') || (option == 'm')) {
                System.out.print("\nEnter your email address: ");
                email = input.next();

                //search the Gym for current members with email entered and welcome
                //the user back if the email is stored in the Gym
                if (gym.searchMembersByEmail(email) != null) {
                    member = gym.searchMembersByEmail(email);
                    System.out.println("\n Welcome Back " + member.getName());
                    //display the menu for the member
                    memberMenu();
                    //if the email cannot be verified access is denied and the program exits
                } else {
                    System.out.println("--------------------------");
                    System.out.println("x------Acess Denied------x");
                    System.out.println("__________________________");
                    //exit the program if incorrect details entered
                    System.exit(0);
                }
            }
            if ((option == 'T') || (option == 't')) {
                System.out.print("Enter your email address: ");
                email = input.next();

                //similarly for trainers search for email entered and welcome
                //the user back if the email is stored in the Gym or exit the program
                if (gym.searchTrainersByEmail(email) != null) {
                    trainer = gym.searchTrainersByEmail(email);
                    System.out.println("\n Welcome Back " + trainer.getName());
                    trainerMenu();
                } else {
                    System.out.println("--------------------------");
                    System.out.println("x------Acess Denied------x");
                    System.out.println("__________________________");
                    //exit the program if incorrect details entered
                    System.exit(0);
                }
            }
        }

        //If the Register(R) option is selected the below is displayed on the console
        //and the user is asked if they are a Member(M) or a Trainer (T)
        if ((option == 'R') || (option == 'r')) {
            System.out.println("-------------------------------");
            System.out.println(">----------Register-----------<");
            System.out.println("-------------------------------");
            System.out.println(">-------As a Member (M)-------<");
            System.out.println("--------------OR---------------");
            System.out.println(">-------As a Trainer (T)------<");
            System.out.println("-------------------------------");
            System.out.print("==>>");
            option = input.next().charAt(0);
        }
        //If statements for uppercase and lower case
        if ((option == 'M') || (option == 'm')) {
            addMember();
        } else if ((option == 'T') || (option == 't')) {
            addTrainer();
        }
    }

    // Displays the Menu options when the user is logged in as a Member.
    private void memberMenu() {
        System.out.println("\n------------------------------");
        System.out.println("           MEMBER MENU          ");
        System.out.println(" ------------------------------ ");
        System.out.println(" 1) View your profile           ");
        System.out.println(" 2) Update your profile         ");
        System.out.println(" ------------------------------ ");
        System.out.println("      PROGRESS SUB MENU:        ");
        System.out.println(" ------------------------------ ");
        System.out.println(" 3) By Weight                   ");
        System.out.println(" 4) By Waist Measurement        ");
        System.out.println(" 0) Exit	      	            ");
        System.out.println(" ______________________________ ");
        System.out.println("==>>");
        int option = input.nextInt();

        //Switch statements to navigate the menu depending on the integer input
        switch (option) {
            case 1:
                System.out.println(member.toString());
                break;
            case 2:
                updateProfile();
                break;
            case 3:
                //progressByWeight();
                break;
            case 4:
                //progressByWaist();
                break;
            case 5:
                addAssessment();
                break;
            case 6:
                try {
                    gym.load();
                } catch (Exception e) {
                    System.err.println("Error loading from file: " + e);
                }
            case 0:
                System.out.println("See you soon " + member.getName() + "\n");
                System.exit(0);
                break;
            default:
                System.out.println("--Invalid Option--" + option);
        }
        //pause the program so that the user can read what we just printed
        // to the terminal window
        input.nextLine();
        System.out.println("\nPress the Enter Key to return to the menu.");
        //this second read is required - bug in Scanner class;
        // a String read is ignored straight after reading an int.
        input.nextLine();
        //return to the main menu
        memberMenu();
    }

    // Displays the Menu options when the user is logged in as a Trainer
    private void trainerMenu() {
        System.out.println("\n---------------------------");
        System.out.println("        TRAINER MENU         ");
        System.out.println("-----------------------------");
        System.out.println(" 1) Add a new Member         ");
        System.out.println(" 2) List all Gym Members     ");
        System.out.println(" 3) Search Member by email   ");
        System.out.println(" 4) Search Member by name    ");
        System.out.println(" --------------------------- ");
        System.out.println("     PROGRESS SUB MENU:      ");
        System.out.println(" --------------------------- ");
        System.out.println(" 5) Add a member assessment  ");
        System.out.println(" 6) Comment on an assessment ");
        System.out.println(" 0) Exit				     ");
        System.out.println("_____________________________");
        int option = input.nextInt();

        //Switch statements to navigate the menu depending on the integer input
        switch (option) {
            case 1:
                addMember();
                break;
            case 2:
                System.out.println("List of gym Members: \n");
                System.out.println(gym.getMembers());
                break;
            case 3:
                System.out.println("Members by email: \n");
                searchMemberEmail();
                break;
            case 4:
                System.out.println("Members by Name: \n");
                searchMemberName();
                break;
            case 5:
                System.out.println("Add a Member Assessment: \n");
                addAssessment();
                break;
            case 6:
                System.out.println("Add a Member Comment: \n");
                addComment();
                break;
            case 7:
                try {
                    gym.save();
                } catch (Exception e) {
                    System.err.println("Error writing to file: " + e);
                }
                break;
            case 8:
                //Load the details from the XML
                try {
                    gym.load();
                } catch (Exception e) {
                    System.err.println("Error loading from file: " + e);
                }
                break;
            case 0:
                System.out.println("See you soon " + trainer.getName() + "\n");
                System.exit(0);
                break;
            default:
                System.out.println("--Invalid Option--" + option);
        }
        //pause the program so that the user can read what we just printed
        // to the terminal window
        System.out.println("\nPress Enter to return to the menu.");
        input.nextLine();
        //System.out.println("\nPress Enter Key to return to the main menu.");
        input.nextLine();
        trainerMenu();
    }

    //Adds a new member to the xml file
    private void addMember() {
        String option;
        String email;
        String chosenPackage = null;
        float height = 0;
        float startingWeight = 0;
        boolean goodInput = false;

        //Request email from the user and use a while loop to check if the email
        //is already registered.
        System.out.print("Enter email: ");
        option = input.next();
        while ((gym.searchMembersByEmail(option) != null) ||
                (gym.searchTrainersByEmail(option) != null)) {
            System.out.println("This email is already in use. Please Try Again.");
            mainMenu();
        }
        email = option;

        System.out.print("Enter your name: ");
        String name = input.nextLine();
        name = input.nextLine();

        System.out.print("Enter your address: ");
        String address = input.nextLine();

        System.out.print("Enter your gender (M/F): ");
        String gender = input.nextLine();

        //Use of Try/Catch blocks here for the user measurement input
        System.out.print("Enter height (in meters): ");
        do {
            try {
                height = input.nextFloat();
                goodInput = true;
            } catch (Exception e) {
                input.nextLine();
                System.err.println("\tPlease enter a valid number.");
            }
        } while (!goodInput);

        do {
            try {
                System.out.print("Enter your weight (in kgs): ");
                startingWeight = input.nextFloat();
                goodInput = true;
            } catch (Exception e) {
                input.nextLine();
                System.err.println("\tEnter a number please.");
            }
        } while (!goodInput);

        //Printed to the console to ask the user which package they wish to select
        //Premium (P) or Student (S)
        System.out.println("-------------------------------");
        System.out.println(">-----Choose your Package-----<");
        System.out.println("-------------------------------");
        System.out.println(">------Premium Member (P)-----<");
        System.out.println("--------------OR---------------");
        System.out.println(">------Student Member (S)-----<");
        System.out.println("-------------------------------");
        System.out.print("==>>");
        option = input.next();

        //Use of if statements here and equalsIgnorecase to accept upper/lower case input
        if (!"p".equalsIgnoreCase(option) || !"s".equalsIgnoreCase(option)) {
            if ("p".equalsIgnoreCase(option)) {
                //adds a new Premium member to the xml file
                gym.addMember(new PremiumMember(email, name, address, gender,
                        height, startingWeight, chosenPackage));

                //Exception handling in the event the details cannot be saved to the xml
                try {
                    gym.save();
                } catch (Exception e) {
                    System.err.println("Error writing to file: " + e);
                }
                System.out.println("You now have a Premium Membership");
                System.out.println("\nPress the Enter Key to Login." +
                        "");
                input.nextLine();
                input.nextLine();
                // display the main menu again
                mainMenu();

            } else if ("S".equalsIgnoreCase(option)) {
                System.out.print("Enter your College Name: ");
                option = input.next();
                String collegeName = option;
                //adds a new Student member to the xml file
                gym.addMember(new StudentMember(email, name, address, gender,
                        height, startingWeight, chosenPackage, collegeName));

                //Exception handling in the event the details cannot be saved to the xml
                try {
                    gym.save();
                } catch (Exception e) {
                    System.err.println("Error writing to file: " + e);
                }
                System.out.println("\nYou now have a Student Membership");
                System.out.println("\nPress the Enter Key to Login.");
                input.nextLine();
                input.nextLine();
                // display the main menu again
                mainMenu();
            }
            //In the event  of Invalid input the following message is printed to the console
            System.out.println("---------------------------");
            System.out.println("x------INVALID INPUT------x");
            System.out.println("___________________________");
            System.out.println(">---Please Try Again!---<\n");
            System.out.println("---------------------------");
        }
    }

    /**
     * Lets the member update their profile by removing the member
     * and calling the addMember() method again
     */
    //TODO Update code as currently I need to remove the current member and add
    // a new member as this doesn't make sense
    private void updateProfile() {
        gym.getMembers().remove(member);
        addMember();
    }

    // Adds a new trainer to the xml file
    private void addTrainer() {
        String email;
        String option;

        //Request an email from the user check if it is already stored
        System.out.print("Enter email: ");
        option = input.next();
        while ((gym.searchMembersByEmail(option) != null) ||
                (gym.searchTrainersByEmail(option) != null)) {
            System.out.println("This email is already in use. " +
                    "\n Please Try Again.");
            mainMenu();
        }
        email = option;

        //Trainer details to be input by the user
        System.out.print("Enter name: ");
        String name = input.nextLine();
        name = input.nextLine();

        System.out.print("Enter address: ");
        String address = input.nextLine();

        System.out.print("Enter gender (M/F): ");
        String gender = input.nextLine();

        System.out.print("Enter speciality: ");
        String speciality = input.nextLine();

        //Add the new trainer to the xml and use of Exception Handling in the event
        //the information cannot be saved to the file
        gym.addTrainer(new Trainer(email, name, address, gender, speciality));

        try {
            gym.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }

        System.out.println("\nYou are now registered as a Trainer.");
        System.out.println("\nPress the Enter Key to Login.");
        input.nextLine();
        // display the Login menu again
        mainMenu();
    }

    // Lets trainer search for members by email
    private void searchMemberEmail() {
        System.out.print("Enter email: ");
        String email = input.next();
        while (true) {
            if (gym.searchMembersByEmail(email) != null) {
                System.out.println(gym.searchMembersByEmail(email).toString());
                break;
            }
            System.out.println("There is no user associated with this email.");
            break;
        }
    }

    // Lets trainer search for members by name
    //TODO: Currently only the name from the Arraylist is returned. Need to update.
    private void searchMemberName() {
        System.out.print("Enter name: ");
        String name = input.next();
        while (true) {
            if (gym.searchMembersByName(name) != null) {
                System.out.println(gym.searchMembersByName(name).toString());
                break;
            }
            System.out.println("There is no user associated with this email.");
            break;
        }
    }

    // Lets the trainer add assessments of the members
    // I used the labs and code in Slack here  as I was struggling to get it working.
    private void addAssessment() {
        //Searching the list of members arraylist and allowing the trainer to select the
        //member based on the index selected
        System.out.println("Member List: ");
        String listOfMembers = "";
        for (int i = 0; i < gym.numberOfMembers(); i++) {
            listOfMembers = listOfMembers + "Index" + i + ": " + gym.getMembers().
                    get(i).getName();
        }
        System.out.println(listOfMembers);
        System.out.println("Select member by index");
        int memberIndex = input.nextInt();
        input.nextLine();

        System.out.print("Enter member's weight: ");
        Float weight = input.nextFloat();

        System.out.print("Enter member's thigh: ");
        Float thigh = input.nextFloat();

        System.out.print("Enter member's waist: ");
        Float waist = input.nextFloat();

        System.out.print("Enter trainer's comment: ");
        String comment = input.nextLine();
        input.nextLine();

        System.out.print("Enter member's trainer: ");
        String personalTrainer = input.nextLine();

        System.out.print("Enter assessment date: YY/MM/DD");
        String assessmentDate = input.nextLine();

        //Get the member index and update the corresponding member hashmap with the assessment
        // details.
        System.out.print(gym.members.get(memberIndex));
        System.out.print(gym.getMembers().get(memberIndex));
        Assessment assessment1 = new Assessment(weight, thigh, waist, comment);
        gym.members.get(memberIndex).assessmentHashMap.put(assessmentDate, assessment);
    }

    // Lets the trainer add comments to assessments of the members
    //TODO Update this as currently doesn't function correctly
    //Similar to addAssessment method the listMembers ArrayList is contained within a for loop
    //to search for a member and allows the trainer to select the corresponding member's index
    private void addComment() {
        System.out.println("Member List: ");
        String listOfMembers = "";
        for (int i = 0; i < gym.numberOfMembers(); i++) {
            listOfMembers = listOfMembers + "Index" + i + ": " + gym.getMembers().
                    get(i).getName();
        }

        String comment = input.nextLine();
        System.out.println(listOfMembers);
        System.out.println("Select member by index");
        int memberIndex = input.nextInt();
        input.nextLine();

        System.out.println("\n --------------------------- ");
        System.out.println("    Enter Comments to update   ");
        System.out.println("  ---------------------------- ");
        System.out.println(" \nPlease enter your comments: ");
        input.nextLine();

        System.out.print(gym.members.get(memberIndex));
        System.out.print(gym.getMembers().get(memberIndex));
        Assessment assessment1 = new Assessment(comment);

        //Get the member index and update the corresponding member hashmap with the assessment
        // details
        gym.members.get(memberIndex).assessmentHashMap.put("", assessment1);
        try {
            gym.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }
}

