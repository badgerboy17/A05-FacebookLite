package edu.uwp.csci242.assignment.a05.facebooklite;

import java.util.*;

/**
 * Person class holds the values for their name and list of friends.
 * <p>
 *
 * @author Bryce Sulin
 * @edu.uwp.cs.242.course CSCI 242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 5
 * @bugs none
 */
public class Person {

    /**
     * Holds the name of the Person
     */
    private String name;

    /**
     * Holds all the friend names of the Person
     * LinkedHashSet was chosen for its order by insertion & non-duplicate elements
     */
    private LinkedHashSet<String> friends = new LinkedHashSet<>();

    /**
     * Default 2-Argument Constructor for Person
     * @param name Holds name of the Person
     * @param friends Holds all the friend names of the Person
     */
    public Person (String name, LinkedHashSet<String> friends) {
        this.name = name;
        this.friends = friends;
    }

    /**
     * 1-Arg Constructor for Person
     * @param friends Holds all the friend names of the Person
     */
    public Person (LinkedHashSet<String> friends) {
        this.friends = friends;
    }

    /**
     * Set the name value
     * @param name String of the person's name
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * Get the name value
     * @return String of the person's name
     */
    public String getName () {
        return name;
    }

    /**
     * Set the values for list of friends
     * @param friends LinkedHashSet of all friend names
     */
    public void setFriends (LinkedHashSet<String> friends) {
        this.friends = friends;
    }

    /**
     * Get the values for list of friends
     * @return LinkedHashSet friends
     */
    public LinkedHashSet<String> getFriends () {
        return friends;
    }

    /**
     * Add a friend's name to the friend list
     * @param friendName String of the friend's name
     */
    public void addFriends (String friendName) {
        friends.add(friendName);
    }

    /**
     * Remove a friend's name from the friend list
     * @param friendName String of the friend's name
     */
    public void removeFriends (String friendName) {
        friends.remove(friendName);
    }
}