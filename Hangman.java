import java.util.Scanner;
import java.util.Random;

public class Hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] words = {"code", "hack", "tangible", "program", "javascript"};
        boolean playing = true;
        while (playing) {
            System.out.println("Let's play Hangman!");
            int randomIndex = random.nextInt(words.length);
            char[] randomWord = words[randomIndex].toCharArray();
            int wordLength = randomWord.length;
            char[] playerGuess = new char[wordLength];
            for (int i = 0; i < playerGuess.length; i++) {
                playerGuess[i] = '_';
            }
            boolean wordIsGuessed = false;
            int tries = 0;
            while (!wordIsGuessed && tries != wordLength) {
                System.out.println("Current guesses: ");
                printArray(playerGuess);
                System.out.printf("You have %d tries left.\n", wordLength - tries);
                System.out.println("Enter a single character: ");
                char input = scanner.nextLine().charAt(0);
                tries++;
                if (input == '-') {
                    playing = false;
                    wordIsGuessed = true;
                } else {
                    for (int i = 0; i < randomWord.length; i++) {
                        if (randomWord[i] == input) {
                            playerGuess[i] = input;
                        }
                    }
                    if (isTheWordGuessed(playerGuess)) {
                        wordIsGuessed = true;
                        System.out.println("Congratulations! You won!");
                    }
                }
            }
            if (!wordIsGuessed) {
                System.out.println("You ran out of guesses.");
            }
            System.out.println("Do you want to play again? (yes/no)");
            String anotherGame = scanner.nextLine();
            if (anotherGame.equals("no")) playing = false;
        }
        System.out.println("Game over.");
    }

    public static void printArray(char[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static boolean isTheWordGuessed(char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '_') return false;
        }
        return true;
    }
}