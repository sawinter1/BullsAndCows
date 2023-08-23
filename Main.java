package BullsAndCows;

import static BullsAndCows.MethodsForMain.*;
import static BullsAndCows.GamePlay.*;

public class Main {
    public static void main(String[] args) {
        instructions(); //display at beginning

        //start game loop
        while(true) { //continue to play until the user enters STOP
            if(game()) break; //if the player enters stop then break
        }

        //inform user of high score
        System.out.println("Congratulations! Your high score was " + getHighScore() + " turns.");

        //thank user for playing
        System.out.println("Thank you for playing Bulls and Cows!");
    }
}