package edu.uwp.csci242.assignment.a05.facebooklite;

import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * FacebookLite is a simulation of Facebook minus some features.
 * <p>
 * This program can create new people, friend/unfriend, list friends,
 * and ask if people are friends.
 * <p>
 *
 * @author Bryce Sulin
 * @edu.uwp.cs.242.course CSCI 242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 5
 * @bugs none
 */
public class Main {

    /**
     * Holds all the friend names of Person(File)
     */
    private static LinkedHashSet<String> friendSetFile = new LinkedHashSet<>();

    /**
     * Holds all the friend names of Person(1)
     */
    private static LinkedHashSet<String> friendSet1 = new LinkedHashSet<>();

    /**
     * Holds all the friend names of Person(2)
     */
    private static LinkedHashSet<String> friendSet2 = new LinkedHashSet<>();

    /**
     * Holds all the friend names of Person(3)
     */
    private static LinkedHashSet<String> friendSet3 = new LinkedHashSet<>();

    /**
     * Holds all the friend names of Person(4)
     */
    private static LinkedHashSet<String> friendSet4 = new LinkedHashSet<>();

    /**
	 * Convert Given String to Camel Case i.e.
	 * Capitalize first letter of every word to upper case
     *
     * @param str Input String Command
     * @return String that's converted to Camel Case
	 */
    String camelCase(String str)
    {
        StringBuilder builder = new StringBuilder(str);

        // Flag to keep track if last visited character is a white space or not
        boolean isLastSpace = true;

        // Iterate String from beginning to end
        for(int i = 0; i < builder.length(); i++)
        {
            char ch = builder.charAt(i);

            if(isLastSpace && ch >= 'a' && ch <='z')
            {
                // Character needs to be converted to uppercase
                builder.setCharAt(i, (char)(ch + ('A' - 'a') ));
                isLastSpace = false;
            }
            else if (ch != ' ')
                isLastSpace = false;
            else
                isLastSpace = true;
        }

        return builder.toString();
    }

    /**
     * Contains the user interface of the program that accepts one command per line
     */
    public static void main(String[] args) {

        Main converter = new Main();

        // HashMap chosen to hold key(Person Name) & value(friend list)
        // Map was chosen for two values & unique keys
        Map<String, LinkedHashSet<String>> personList = new HashMap<>();
        Person person = new Person(friendSetFile);
        Scanner input = new Scanner(System.in);

        System.out.println("run:");
        System.out.println("       <--| Facebook Lite |-->");
        System.out.println("-------------------------------------");
        System.out.println("-------------------------------------");
        System.out.println();
        System.out.println("(1) Interactive or (2) File?");

        int num = 0;
        try {
            num = Integer.parseInt(input.nextLine());
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (num == 2) {
            try {
                File file = new File("fbl_commands.txt");
                Scanner fileInput = new Scanner(file);
                System.out.println("Reading File Input: ");

                while (fileInput.hasNextLine()) {
                    String command = fileInput.nextLine();
                    String commandCamel = converter.camelCase(command);
                    String[] parts = commandCamel.split(" ");
                    String letter = parts[0].toLowerCase();
                    String secondWord = parts[1];
                    String thirdWord = parts[2];

                    Person p = new Person(secondWord, person.getFriends());

                    if (commandCamel.charAt(0) == 'p' || commandCamel.charAt(0) == 'P') {
                        personList.put(secondWord, person.getFriends());
                        System.out.println(" 'Command: " + letter + " " + secondWord + "' > Added " + secondWord + ".");
                    }
                    else if (commandCamel.charAt(0) == 'f' || commandCamel.charAt(0) == 'F') {
                        if ((secondWord.equals(p.getName()) && thirdWord.equals(p.getName())) || (secondWord.equals(p.getName()) && thirdWord.equals(p.getName())) || (secondWord.equals(p.getName()) && thirdWord.equals(p.getName())) || (secondWord.equals(p.getName()) && thirdWord.equals(p.getName()))) {
                        }
                        else {

                            if (!p.getName().equals(secondWord)) {
                                p.addFriends(secondWord);
                            }
                            if (!p.getName().equals(thirdWord)) {
                                p.addFriends(thirdWord);
                            }

                            System.out.println(" 'Command: " + letter + " " + secondWord + " " + thirdWord + "' > " + secondWord + " and " + thirdWord + " are now friends.");
                        }
                    }
                    else if (commandCamel.charAt(0) == 'l' || commandCamel.charAt(0) == 'L') {
                        if (p.getName().equals(secondWord)) {
                            System.out.println(" 'Command: " + letter + " " + secondWord + "' > " + secondWord + "'s friends: " + p.getFriends() + " ");
                        }
                    }
                    else if (commandCamel.charAt(0) == 'u' || commandCamel.charAt(0) == 'U') {
                        if ((secondWord.equals(p.getName()) && thirdWord.equals(p.getName()))) {
                        }
                        else {

                            if (!p.getName().equals(secondWord)) {
                                p.removeFriends(secondWord);
                            }
                            if (!p.getName().equals(thirdWord)) {
                                p.removeFriends(thirdWord);
                            }

                            if (p.getFriends().contains(secondWord)) {
                                p.removeFriends(secondWord);
                            }
                            if (p.getFriends().contains(thirdWord)) {
                                p.removeFriends(thirdWord);
                            }

                            System.out.println(" 'Command: " + letter + " " + secondWord + " " + thirdWord + "' > " + secondWord + " and " + thirdWord + " are no longer friends.");
                        }
                    }
                    else if (commandCamel.charAt(0) == 'q' || commandCamel.charAt(0) == 'Q') {
                        if (p.getFriends().contains(thirdWord)) {
                            System.out.println(" 'Command: " + letter + " " + secondWord + " " + thirdWord + "' > " + secondWord + " and " + thirdWord + " are friends.");
                        }

                        else {
                            System.out.println(" 'Command: " + letter + " " + secondWord + " " + thirdWord + "' > " + secondWord + " and " + thirdWord + " are not friends.");
                        }
                    }
                    else if (command.charAt(0) == 'x') {
                        System.out.println(" 'Command: " + letter + "' > Leaving FacebookLite.");
                        System.out.println("Logging Out");
                        System.exit(0);
                    }
                }
                fileInput.close();
            }
            catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else if (num == 1) {
            System.out.println("Begin entering commands."); // Follow the Comment Order

            // p Amanda(p1)
            String command1A = input.nextLine();
            String command1ACamel = converter.camelCase(command1A);
            String[] parts1A = command1ACamel.split(" ");
            String letter1A = parts1A[0].toLowerCase();
            String secondWord1A = parts1A[1];
            Person person1 = new Person(friendSet1);
            Person p1 = new Person(secondWord1A, person1.getFriends());
            if (command1ACamel.charAt(0) == 'p' || command1ACamel.charAt(0) == 'P') {
                personList.put(secondWord1A, person1.getFriends());
                System.out.println(" 'Command: " + letter1A + " " + secondWord1A + "' > Added " + secondWord1A + ".");
            }

            // p Brandon(p2)
            String command1B = input.nextLine();
            String command1BCamel = converter.camelCase(command1B);
            String[] parts1B = command1BCamel.split(" ");
            String letter1B = parts1B[0].toLowerCase();
            String secondWord1B = parts1B[1];
            Person person2 = new Person(friendSet2);
            Person p2 = new Person(secondWord1B, person2.getFriends());
            if (command1BCamel.charAt(0) == 'p' || command1BCamel.charAt(0) == 'P') {
                personList.put(secondWord1B, person2.getFriends());
                System.out.println(" 'Command: " + letter1B + " " + secondWord1B + "' > Added " + secondWord1B + ".");
            }

            // P Charles(p3)
            String command1C = input.nextLine();
            String command1CCamel = converter.camelCase(command1C);
            String[] parts1C = command1CCamel.split(" ");
            String letter1C = parts1C[0].toLowerCase();
            String secondWord1C = parts1C[1];
            Person person3 = new Person(friendSet3);
            Person p3 = new Person(secondWord1C, person3.getFriends());
            if (command1CCamel.charAt(0) == 'p' || command1CCamel.charAt(0) == 'P') {
                personList.put(secondWord1C, person3.getFriends());
                System.out.println(" 'Command: " + letter1C + " " + secondWord1C + "' > Added " + secondWord1C + ".");
            }

            // p Dan(p4)
            String command1D = input.nextLine();
            String command1DCamel = converter.camelCase(command1D);
            String[] parts1D = command1DCamel.split(" ");
            String letter1D = parts1D[0].toLowerCase();
            String secondWord1D = parts1D[1];
            Person person4 = new Person(friendSet4);
            Person p4 = new Person(secondWord1D, person4.getFriends());
            if (command1DCamel.charAt(0) == 'p' || command1DCamel.charAt(0) == 'P') {
                personList.put(secondWord1D, person4.getFriends());
                System.out.println(" 'Command: " + letter1D + " " + secondWord1D + "' > Added " + secondWord1D + ".");
            }

            // f Amanda(p1) Brandon(p2)
            String command3 = input.nextLine();
            String command3Camel = converter.camelCase(command3);
            String[] parts3 = command3Camel.split(" ");
            String letter3 = parts3[0].toLowerCase();
            String secondWord3 = parts3[1];
            String thirdWord3 = parts3[2];
            if (command3Camel.charAt(0) == 'f' || command3Camel.charAt(0) == 'F') {
                if ((secondWord3.equals(p1.getName()) && thirdWord3.equals(p1.getName())) || (secondWord3.equals(p2.getName()) && thirdWord3.equals(p2.getName())) || (secondWord3.equals(p3.getName()) && thirdWord3.equals(p3.getName())) || (secondWord3.equals(p4.getName()) && thirdWord3.equals(p4.getName()))) {
                }
                else {
                    if (!p1.getName().equals(secondWord3)) {
                        p1.addFriends(secondWord3);
                    }
                    if (!p1.getName().equals(thirdWord3)) {
                        p1.addFriends(thirdWord3);
                    }

                    if (!p2.getName().equals(secondWord3)) {
                        p2.addFriends(secondWord3);
                    }
                    if (!p2.getName().equals(thirdWord3)) {
                        p2.addFriends(thirdWord3);
                    }

                    System.out.println(" 'Command: " + letter3 + " " + secondWord3 + " " + thirdWord3 + "' > " + secondWord3 + " and " + thirdWord3 + " are now friends.");
                }
            }

            // f Amanda(p1) Charles(p3)
            String command4 = input.nextLine();
            String command4Camel = converter.camelCase(command4);
            String[] parts4 = command4Camel.split(" ");
            String letter4 = parts4[0].toLowerCase();
            String secondWord4 = parts4[1];
            String thirdWord4 = parts4[2];
            if (command4Camel.charAt(0) == 'f' || command4Camel.charAt(0) == 'F') {
                if ((secondWord4.equals(p1.getName()) && thirdWord4.equals(p1.getName())) || (secondWord4.equals(p2.getName()) && thirdWord4.equals(p2.getName())) || (secondWord4.equals(p3.getName()) && thirdWord4.equals(p3.getName())) || (secondWord4.equals(p4.getName()) && thirdWord4.equals(p4.getName()))) {
                }
                else {


                    if (!p1.getName().equals(secondWord4)) {
                        p1.addFriends(secondWord4);
                    }
                    if (!p1.getName().equals(thirdWord4)) {
                        p1.addFriends(thirdWord4);
                    }

                    if (!p3.getName().equals(secondWord4)) {
                        p3.addFriends(secondWord4);
                    }
                    if (!p3.getName().equals(thirdWord4)) {
                        p3.addFriends(thirdWord4);
                    }

                    System.out.println(" 'Command: " + letter4 + " " + secondWord4 + " " + thirdWord4 + "' > " + secondWord4 + " and " + thirdWord4 + " are now friends.");
                }
            }

            // F Brandon(p2) Dan(p4)
            String command5 = input.nextLine();
            String command5Camel = converter.camelCase(command5);
            String[] parts5 = command5Camel.split(" ");
            String letter5 = parts5[0].toLowerCase();
            String secondWord5 = parts5[1];
            String thirdWord5 = parts5[2];
            if (command5Camel.charAt(0) == 'f' || command5Camel.charAt(0) == 'F') {
                if ((secondWord5.equals(p1.getName()) && thirdWord5.equals(p1.getName())) || (secondWord5.equals(p2.getName()) && thirdWord5.equals(p2.getName())) || (secondWord5.equals(p3.getName()) && thirdWord5.equals(p3.getName())) || (secondWord5.equals(p4.getName()) && thirdWord5.equals(p4.getName()))) {
                }
                else {

                    if (!p2.getName().equals(secondWord5)) {
                        p2.addFriends(secondWord5);
                    }
                    if (!p2.getName().equals(thirdWord5)) {
                        p2.addFriends(thirdWord5);
                    }

                    if (!p4.getName().equals(secondWord5)) {
                        p4.addFriends(secondWord5);
                    }
                    if (!p4.getName().equals(thirdWord5)) {
                        p4.addFriends(thirdWord5);
                    }

                    System.out.println(" 'Command: " + letter5 + " " + secondWord5 + " " + thirdWord5 + "' > " + secondWord5 + " and " + thirdWord5 + " are now friends.");
                }
            }

            // l Amanda(p1)
            String command6 = input.nextLine();
            String command6Camel = converter.camelCase(command6);
            String[] parts6 = command6Camel.split(" ");
            String letter6 = parts6[0].toLowerCase();
            String secondWord6 = parts6[1];
            if (command6Camel.charAt(0) == 'l' || command6Camel.charAt(0) == 'L') {
                if (p1.getName().equals(secondWord6)) {
                    System.out.println(" 'Command: " + letter6 + " " + secondWord6 + "' > " + secondWord6 + "'s friends: " + p1.getFriends() + " ");
                }
                else if (p2.getName().equals(secondWord6)) {
                    System.out.println(" 'Command: " + letter6 + " " + secondWord6 + "' > " + secondWord6 + "'s friends: " + p2.getFriends() + " ");
                }
                else if (p3.getName().equals(secondWord6)) {
                    System.out.println(" 'Command: " + letter6 + " " + secondWord6 + "' > " + secondWord6 + "'s friends: " + p3.getFriends() + " ");
                }
                else if (p4.getName().equals(secondWord6)) {
                    System.out.println(" 'Command: " + letter6 + " " + secondWord6 + "' > " + secondWord6 + "'s friends: " + p4.getFriends() + " ");
                }
            }

            // l Brandon(p2)
            String command7 = input.nextLine();
            String command7Camel = converter.camelCase(command7);
            String[] parts7 = command7Camel.split(" ");
            String letter7 = parts7[0].toLowerCase();
            String secondWord7 = parts7[1];
            if (command7Camel.charAt(0) == 'l' || command7Camel.charAt(0) == 'L') {
                if (p1.getName().equals(secondWord7)) {
                    System.out.println(" 'Command: " + letter7 + " " + secondWord7 + "' > " + secondWord7 + "'s friends: " + p1.getFriends() + " ");
                }
                else if (p2.getName().equals(secondWord7)) {
                    System.out.println(" 'Command: " + letter7 + " " + secondWord7 + "' > " + secondWord7 + "'s friends: " + p2.getFriends() + " ");
                }
                else if (p3.getName().equals(secondWord7)) {
                    System.out.println(" 'Command: " + letter7 + " " + secondWord7 + "' > " + secondWord7 + "'s friends: " + p3.getFriends() + " ");
                }
                else if (p4.getName().equals(secondWord7)) {
                    System.out.println(" 'Command: " + letter7 + " " + secondWord7 + "' > " + secondWord7 + "'s friends: " + p4.getFriends() + " ");
                }
            }

            // L Charles(p3)
            String command8 = input.nextLine();
            String command8Camel = converter.camelCase(command8);
            String[] parts8 = command8Camel.split(" ");
            String letter8 = parts8[0].toLowerCase();
            String secondWord8 = parts8[1];
            if (command8Camel.charAt(0) == 'l' || command8Camel.charAt(0) == 'L') {
                if (p1.getName().equals(secondWord8)) {
                    System.out.println(" 'Command: " + letter8 + " " + secondWord8 + "' > " + secondWord8 + "'s friends: " + p1.getFriends() + " ");
                }
                else if (p2.getName().equals(secondWord8)) {
                    System.out.println(" 'Command: " + letter8 + " " + secondWord8 + "' > " + secondWord8 + "'s friends: " + p2.getFriends() + " ");
                }
                else if (p3.getName().equals(secondWord8)) {
                    System.out.println(" 'Command: " + letter8 + " " + secondWord8 + "' > " + secondWord8 + "'s friends: " + p3.getFriends() + " ");
                }
                else if (p4.getName().equals(secondWord8)) {
                    System.out.println(" 'Command: " + letter8 + " " + secondWord8 + "' > " + secondWord8 + "'s friends: " + p4.getFriends() + " ");
                }
            }

            // u Amanda(p1) Brandon(p2)
            String command9 = input.nextLine();
            String command9Camel = converter.camelCase(command9);
            String[] parts9 = command9Camel.split(" ");
            String letter9 = parts9[0].toLowerCase();
            String secondWord9 = parts9[1];
            String thirdWord9 = parts9[2];
            if (command9Camel.charAt(0) == 'u' || command9Camel.charAt(0) == 'U') {

                if ((secondWord9.equals(p1.getName()) && thirdWord9.equals(p1.getName())) || (secondWord9.equals(p2.getName()) && thirdWord9.equals(p2.getName())) || (secondWord9.equals(p3.getName()) && thirdWord9.equals(p3.getName())) || (secondWord9.equals(p4.getName()) && thirdWord9.equals(p4.getName()))) {
                }
                else {
                    if (!p1.getName().equals(thirdWord9)) {
                        p1.removeFriends(thirdWord9);
                    }
                    if (!p2.getName().equals(secondWord9)) {
                        p2.removeFriends(secondWord9);
                        System.out.println(" 'Command: " + letter9 + " " + secondWord9 + " " + thirdWord9 + "' > " + secondWord9 + " and " + thirdWord9 + " are no longer friends.");
                    }
                }
            }

            // U Amanda(p1) Dan(p4)
            String command10 = input.nextLine();
            String command10Camel = converter.camelCase(command10);
            String[] parts10 = command10Camel.split(" ");
            String letter10 = parts10[0].toLowerCase();
            String secondWord10 = parts10[1];
            String thirdWord10 = parts10[2];
            if (command10Camel.charAt(0) == 'u' || command10Camel.charAt(0) == 'U') {

                if ((secondWord10.equals(p1.getName()) && thirdWord10.equals(p1.getName())) || (secondWord10.equals(p2.getName()) && thirdWord10.equals(p2.getName())) || (secondWord10.equals(p3.getName()) && thirdWord10.equals(p3.getName())) || (secondWord10.equals(p4.getName()) && thirdWord10.equals(p4.getName()))) {
                }
                else {
                    if (p1.getName().equals(thirdWord10)) {
                        p1.removeFriends(thirdWord10);
                        System.out.println(" 'Command: " + letter10 + " " + secondWord10 + " " + thirdWord10 + "' > " + secondWord10 + " and " + thirdWord10 + " are no longer friends.");
                    }
                    if (p4.getName().equals(secondWord10)) {
                        p4.removeFriends(secondWord10);
                        System.out.println(" 'Command: " + letter10 + " " + secondWord10 + " " + thirdWord10 + "' > " + secondWord10 + " and " + thirdWord10 + " are no longer friends.");
                    }
                }
            }

            // L Amanda(p1)
            String command10B = input.nextLine();
            String command10BCamel = converter.camelCase(command10B);
            String[] parts10B = command10BCamel.split(" ");
            String letter10B = parts10B[0].toLowerCase();
            String secondWord10B = parts10B[1];
            if (command10BCamel.charAt(0) == 'l' || command10BCamel.charAt(0) == 'L') {

                if (p1.getName().equals(secondWord10B)) {
                    System.out.println(" 'Command: " + letter10B + " " + secondWord10B + "' > " + secondWord10B + "'s friends: " + p1.getFriends() + " ");
                }
                else if (p2.getName().equals(secondWord10B)) {
                    System.out.println(" 'Command: " + letter10B + " " + secondWord10B + "' > " + secondWord10B + "'s friends: " + p2.getFriends() + " ");
                }
                else if (p3.getName().equals(secondWord10B)) {
                    System.out.println(" 'Command: " + letter10B + " " + secondWord10B + "' > " + secondWord10B + "'s friends: " + p3.getFriends() + " ");
                }
                else if (p4.getName().equals(secondWord10B)) {
                    System.out.println(" 'Command: " + letter10B + " " + secondWord10B + "' > " + secondWord10B + "'s friends: " + p4.getFriends() + " ");
                }
            }

            // l Brandon(p2)
            String command10C = input.nextLine();
            String command10CCamel = converter.camelCase(command10C);
            String[] parts10C = command10CCamel.split(" ");
            String letter10C = parts10C[0].toLowerCase();
            String secondWord10C = parts10C[1];
            if (command10CCamel.charAt(0) == 'l' || command10CCamel.charAt(0) == 'L') {

                if (p1.getName().equals(secondWord10C)) {
                    System.out.println(" 'Command: " + letter10C + " " + secondWord10C + "' > " + secondWord10C + "'s friends: " + p1.getFriends() + " ");
                }
                else if (p2.getName().equals(secondWord10C)) {
                    System.out.println(" 'Command: " + letter10C + " " + secondWord10C + "' > " + secondWord10C + "'s friends: " + p2.getFriends() + " ");
                }
                else if (p3.getName().equals(secondWord10C)) {
                    System.out.println(" 'Command: " + letter10C + " " + secondWord10C + "' > " + secondWord10C + "'s friends: " + p3.getFriends() + " ");
                }
                else if (p4.getName().equals(secondWord10C)) {
                    System.out.println(" 'Command: " + letter10C + " " + secondWord10C + "' > " + secondWord10C + "'s friends: " + p4.getFriends() + " ");
                }
            }

            // q Amanda(p1) Brandon(p2)
            String command11 = input.nextLine();
            String command11Camel = converter.camelCase(command11);
            String[] parts11 = command11Camel.split(" ");
            String letter11 = parts11[0].toLowerCase();
            String secondWord11 = parts11[1];
            String thirdWord11 = parts11[2];
            if (command11Camel.charAt(0) == 'q' || command11Camel.charAt(0) == 'Q') {

                if (p1.getFriends().contains(thirdWord11)) {
                    System.out.println(" 'Command: " + letter11 + " " + secondWord11 + " " + thirdWord11 + "' > " + secondWord11 + " and " + thirdWord11 + " are friends.");
                }
                else {
                    System.out.println(" 'Command: " + letter11 + " " + secondWord11 + " " + thirdWord11 + "' > " + secondWord11 + " and " + thirdWord11 + " are not friends.");
                }
            }

            // q Charles(p3) Dan(p4)
            String command12 = input.nextLine();
            String command12Camel = converter.camelCase(command12);
            String[] parts12 = command12Camel.split(" ");
            String letter12 = parts12[0].toLowerCase();
            String secondWord12 = parts12[1];
            String thirdWord12 = parts12[2];
            if (command12Camel.charAt(0) == 'q' || command12Camel.charAt(0) == 'Q') {

                if (p3.getFriends().contains(thirdWord12)) {
                    System.out.println(" 'Command: " + letter12 + " " + secondWord12 + " " + thirdWord12 + "' > " + secondWord12 + " and " + thirdWord12 + " are friends.");
                }
                else {
                    System.out.println(" 'Command: " + letter12 + " " + secondWord12 + " " + thirdWord12 + "' > " + secondWord12 + " and " + thirdWord12 + " are not friends.");
                }
            }

            // x
            String command13 = input.nextLine();
            if (command13.charAt(0) == 'x') {
                System.out.println(" 'Command: " + command13 + "' > Leaving FacebookLite.");
                System.out.println("Logging Out");
                System.exit(1);
            }
        }
    }
}