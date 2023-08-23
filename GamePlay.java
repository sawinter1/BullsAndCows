package BullsAndCows;

import java.util.ArrayList;
import java.util.Scanner;

public class GamePlay {
    static int highScore = Integer.MAX_VALUE;
    static Scanner input = new Scanner(System.in);
    /**
    * Runs one game of Bulls and Cows. A 4-digit number with no repeating digits is randomly generated and the user guesses numbers until
    * they guess the correct number. Their guesses are responded to with the number of bulls (how many digits are correct and in the same
    * place as the digits in the secret number) and the number of cows (how many digits are correct but not in the same place as the digits
    * in the secret number). Returns a boolean: true meaning the user wants to stop the game, false meaning the user wants to play another
    * round.
    * @return a boolean true: the user does not want to play another game false: the user does want to play another game
    */
   public static boolean game() {//runs one game

       //generate a secret number
       String secretNum = generate();
       //create a 2d array of guesses and their bulls and cows
       ArrayList<String[]> pastGuesses = new ArrayList<>();

       //until 'stop' or user guesses the number
       while(true) {
           //ask for guess or 'stop'
           System.out.print("\nEnter a guess (or 'STOP' to end the game): ");
           String guess = input.nextLine();

           //check if guess is stop and return true if it is
           if(guess.equalsIgnoreCase("stop")) return true;

           //check if guess is valid
           if(isValid(guess)) {
               //check if it is the answer
               if(guess.equals(secretNum)) break;

               //Otherwise:
               //print the bulls and cows
               String bullsAndCows = bullsAndCows(guess, secretNum);
               System.out.println("\nResponse to your guess '" + guess + "': " + bullsAndCows);

               //add guess and bulls and cows to pastGuesses
               pastGuesses.add(new String[]{guess, bullsAndCows});

               //print all past guesses and their bulls and cows
               pastGuesses(pastGuesses);
           }else {
               //inform that guess is not valid
               System.out.println("\nYour guess '" + guess + "' is not valid. Please enter a guess that" +
                       " is 4-digits none of which repeat or enter 'STOP'.");
           }
       }

       //congratulate user
       congratulations(pastGuesses.size());

       //ask if they want to play again
       return playAgain();
   }

    /**
     * Returns the user's high score.
     * @return an int that represents the "high score" or least number of turns taken before the user
     * guessed the number
     */
   public static int getHighScore() {
       return (highScore == Integer.MAX_VALUE) ? 0 : highScore;
   }

    /**
     * Generates a random 4-digit number with no repeating digits.
     * @return a random 4-digit number with no repeating digits
     */
   static String generate() {
        String secretNum;
        int digOne, digTwo, digThree, digFour;

        //generate digit 1
        digOne = (int) (1 + (Math.random() * 9));

        //generate digit 2 (cannot be the same as digOne)
        do{
            digTwo = (int) (1 + (Math.random() * 9));
        }while(digTwo == digOne);

        //generate digit 3 (cannot be the same as digOne or digTwo)
        do{
            digThree = (int) (1 + (Math.random() * 9));
        }while(digThree == digOne || digThree == digTwo);

        //generate digit 4 (cannot be the same as digOne or digTwo or digThree)
        do{
            digFour = (int) (1 + (Math.random() * 9));
        }while(digFour == digOne || digFour == digTwo || digFour == digThree);

        //put all the digits together
        secretNum = Integer.toString(digOne) + digTwo + digThree + digFour;

        //return the secret number
        return secretNum;
   }

    /**
     * Checks if the entered guess is valid by the rules of Bulls and Cows:
     * <p>     -4-digits
     * <p>     -no repeating digits
     * @param guess a String that represents a guess entered by the user
     * @return a boolean representing whether the guess is valid by the rules of Bulls and Cows
     */
   static boolean isValid(String guess) {
       if(guess.length() != 4) return false;

       for(int i = 0; i < 3; i++) { //for the first 3 digits
           //if the rest of the number contains the current digit return false
           if(guess.substring(i+1).contains(guess.substring(i, i+1))) return false;
       }

       //if it has not returned false then it is valid
       return true;
   }

    /**
     * Returns a string formatted "Bulls: " + the number of digits from the guess in the same place
     * as those in the secret number + "Cows: " + the number of digits from the guess that are the
     * same as but not in the same place as those from the secret number.
     * @param guess a String representing a 4-digit number that follows the rules of Bulls and Cows
     * @param secretNum a String representing the 4-digit secret number generated by the randomizer
     * @return a String informing the user of how many digits were correct and correctly placed and
     * how many digits were correct but incorrectly placed
     */
   static String bullsAndCows(String guess, String secretNum) {
       int bull = 0;
       int cow = 0;

       //for each digit of the guess
       for(int i = 0; i < 4; i++) {
           String digit = guess.substring(i, i+1);
           String secretDigit = secretNum.substring(i, i+1);

           //does the digit appear in the same place; if yes: bull++
           if(digit.equals(secretDigit)) {
               bull++;
           }else {//otherwise: does the digit appear at all; if yes: cow++
               if(secretNum.contains(digit)) {
                   cow++;
               }
           }
       }

       return "Bull(s): " + bull + "\tCow(s): " + cow;
   }

    /**
     * Prints all the user-entered (valid) guesses and their corresponding bulls and cows as
     * determined by the bullsAndCows(guess, secretNum) method.
     * @param pastGuesses an ArrayList of String arrays containing: a user entered guess, the corresponding
     *                    result as determined by bullsAndCows(guess, secretNum)
     */
   static void pastGuesses(ArrayList<String[]> pastGuesses) {
       System.out.println("\nPast guesses and their responses:");
       System.out.println("Guess | Response");

       for(String[] guessAndResponse : pastGuesses) {
           System.out.println(guessAndResponse[0] + "  | " + guessAndResponse[1]);
       }
   }

    /**
     * Prints a congratulations message to the user, informs them of the number of guesses it took
     * for them to win, checks if they set a new high score and if they did, sets it.
     * @param numOfRounds an int representing the number of guesses the user made before winning
     * @return a boolean which is always false
     */

   static void congratulations(int numOfRounds) {
       //inform the user of number of turns they won in (store number if lowest)
       System.out.println("Congratulations! You won in: " + numOfRounds + " guesses.");
       if(numOfRounds < highScore) {
           highScore = numOfRounds;
           System.out.println("That is a high score.");
       }else {System.out.println("Your current high score is: " + highScore + " guesses.");}
   }

   static boolean playAgain() {
       while(true) {
           System.out.println("\nWould you like to play again? (type 'y' for yes, or 'n' for no.)");
           String answer = input.nextLine();

           if (answer.equalsIgnoreCase("y")) return false;
           if (answer.equalsIgnoreCase("n")) return true;

           //if they did not enter "y" or "n"
           System.out.println("Please enter 'y' if you want to play again and 'n' if you do not want to play again.");
       }
   }
}
