package BullsAndCows;

public class MethodsForMain {
    /**
     * Prints instructions for Bulls and Cows.
     */
    public static void instructions() {
        System.out.println("Welcome to Bulls and Cows!");
        System.out.println("\nRules:");
        System.out.println("-The computer will randomly generate a secret 4-digit number (each digit appears only once).");
        System.out.println("-You will guess a 4-digit number (each digit appears only once).");
        System.out.println("""
                -The computer will inform you the number of your digits that are:
                     a) located in the right spot (by displaying the number followed by 'bull')
                     b) the right digits in the wrong spots (by displaying the number followed by 'cow')""");
    }
}
