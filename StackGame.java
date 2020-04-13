package PyramidStack;

import java.util.*;

import java.util.LinkedList;
import java.util.HashMap;

public class StackGame {

	private static String playerNameTwo;
	private static String playerNameOne;
	private static int numberA; // integers inputed into the pile A by the player
	private static int numberB; // integer inputed into the pile B by the player
	private static int numberC; // integer inputed into the pile C by the player
	private static LinkedList<String> pile = new LinkedList<String>();
	private static HashMap<String, Integer> pilesAndNumbers = new HashMap<String, Integer>(); // to store pile and
																								// numbers.
	private static StackPlayer playerOne = new StackPlayer(); // initializing player one
	private static StackPlayer playerTwo = new StackPlayer(); // initializing player two
	private static boolean isPlayerOne = true; // setting the player toggle
	private static int counter = 0;// to ensure we know when to set the differentrmb19@leicester.ac.uk numbers
	// making it a global field to ensure the counter does not always go back to
	// zero after every call.
	private static String playerDisplayName; // to be able to display the player names while the game is going on
	private static String currentPile; // display pile is to ensure the pile names A, B or C is choosen always
	private static int currentNumber; // current number selected by the user
	private static Scanner sc = new Scanner(System.in);

	// main method.
	public static void main(String[] args) {
		System.out.println("### Welcome to the Stack Game ###");
		System.out.println("-----------------------");
		setAndPrintPlayerNames(); // CALLING THE METHOD IN THE MAIN METHOD
		setPile();
		callAndPrintPileAndInputtingIntegers();

		setPilesAndNumbers();
		System.out.println("--------------Start game----------------");
		getPilesAndNumbers();

		// this while loop to check if the game is still on

		printGameRunning();

		System.out.println(playerDisplayName + ": you picked up the last counters, so you WIN!");

		// Print player history
		System.out.println("Recorded history of # Player 1: " + playerOne.getPlayerName());
		playerOne.printPlayerHistory();

		printEmptySpace();

		System.out.println("Recorded history of # Player 2: " + playerTwo.getPlayerName());
		playerTwo.printPlayerHistory();

		// Close scanner
		sc.close();

	}

	// to check if the game is to be exited using String q
	private static void checkIfToExitGameIfQIsInputted(String pileValue) {
		if (pileValue.equals("q")) {
			System.out.println("You have quit the game");
			System.exit(0);
		}
	}

	// to check if the game is to be exited using integer 0
	private static void checkIfToExitGameIfNegativeValueIsInputted(int number) {
		if (number < 0) {
			System.out.println("You have quit the game");
			System.exit(0);
		}
	}

	// to ensure the validity of the pile choosen by the users.
	private static boolean chosenPileIsNotValid(String chosenPile) {
		switch (chosenPile) {
		case "A":
			return false;
		case "B":
			return false;
		case "C":
			return false;
		default:
			return true;
		}
	}

	private static void printEmptySpace() {
		System.out.println();
	}

	// setting the name of the pile method
	private static void setPileNumbers(int value) {
		// setting the method setPileNumbers to return a value because of the check
		// system of tempA to ensure values less than 0 or equal to 0 are not accepted
		// the check system is to ensure the values are put right.

		if (counter == 0) {
			numberA = value;
			counter++; //// the counter is to ensure when to know when to set numberA, numberB, numberC

		}

		else if (counter == 1) {
			numberB = value;
			counter++; //

		} else {

			numberC = value;

		}
	}

	// making it final because the piles are constants that cannot be changed.
	private final static void setPile() {

		pile.add("A");
		pile.add("B");
		pile.add("C");
	}

	// getting the pile name
	private static String getPile(int i) {
		return pile.get(i);

	}

	// setting the numbers set to the pile and the pile using a hashmap
	private static void setPilesAndNumbers() {
		pilesAndNumbers.put(pile.get(0), numberA);
		pilesAndNumbers.put(pile.get(1), numberB);
		pilesAndNumbers.put(pile.get(2), numberC);

	}

	// a method/function to get the numbers and the pile values to display after
	// start game
	private static void getPilesAndNumbers() {
		// Get max number of lines
		int maxPile = 0; /// to find the pile with the maximum integer so as to be able to identify where
							/// the level it starts reading
							// top down from
		for (String key : pilesAndNumbers.keySet()) { // a for loop created to find the maximum pile .i.e the pile with
														// the highest integer value
			if (pilesAndNumbers.get(key) > maxPile) {

				maxPile = pilesAndNumbers.get(key);
			}
		}
		// the first occurrence of a pile tells me about the remainder of a pile
		// so basically the boolean is to know if this is the first time we are printing
		// for this pile
		boolean firstOccurenceA = true;
		boolean firstOccurenceB = true;
		boolean firstOccurenceC = true;
		System.out.println();
		int maxPileCeil = (int) java.lang.Math.ceil(maxPile / 3f);
		// creating a threshold value for each letter, this is done by
		// categorizing each pile to different levels.

		// for instance 7 , should be long to level 3 in the pile display
		// 7 8 9
		// * ** *** ->3
		// *** *** *** ->2
		// *** *** *** ->1
		// looking at it from each level enables the printing of it from up to down
		// the use of java lang math is to be able to know when to displaying
		// information about a pile when we start printing
		// from top to down because the console works from top to down.
		int aDisplayTreshold = pilesAndNumbers.get(pile.get(0)) % 3 == 0 ? pilesAndNumbers.get(pile.get(0)) / 3
				: (3 - pilesAndNumbers.get(pile.get(0)) % 3 + pilesAndNumbers.get(pile.get(0))) / 3;

		int bDisplayTreshold = pilesAndNumbers.get(pile.get(1)) % 3 == 0 ? pilesAndNumbers.get(pile.get(1)) / 3
				: (3 - pilesAndNumbers.get(pile.get(1)) % 3 + pilesAndNumbers.get(pile.get(1))) / 3;
		// variable DisplayThreshold is to know when to start printing information about
		// the pile
		int cDisplayTreshold = pilesAndNumbers.get(pile.get(2)) % 3 == 0 ? pilesAndNumbers.get(pile.get(2)) / 3
				: (3 - pilesAndNumbers.get(pile.get(2)) % 3 + pilesAndNumbers.get(pile.get(2))) / 3;

		for (int i = maxPileCeil; i > 0; i--) {
			// print for A
			System.out.print("\t\t");

			if (aDisplayTreshold >= i) {

				int remainderStars = pilesAndNumbers.get(pile.get(0)) % 3;

				if (firstOccurenceA) {
					if (remainderStars == 0) {
						System.out.print("***");
					} else {
						for (int k = 0; k < 3; k++) {
							if (k < remainderStars) {
								System.out.print("*");
							} else {
								System.out.print(" ");
							}
						}
					}
				} else {
					System.out.print("***");
				}
				firstOccurenceA = false;
			} else {
				System.out.print("   ");
			}

			// Print B
			System.out.print("\t\t");
			if (bDisplayTreshold >= i) {

				int remainderStarsB = pilesAndNumbers.get(pile.get(1)) % 3;

				if (firstOccurenceB) {
					if (remainderStarsB == 0) {// the theory is if the pile is divisible by 3 then the firstOccurence of
												// the pile
												// is set to 3(applies to all pile)
						System.out.print("***");
					} else {
						for (int k = 0; k < 3; k++) {
							if (k < remainderStarsB) {
								System.out.print("*"); // the theory of the else statement is to tell the program to
														// print stars for the remainder
							} else { // this theory applies to all piles
								System.out.print(" ");
							}
						}
					}
				} else {
					System.out.print("***");
				}
				firstOccurenceB = false;
			} else {
				System.out.print("   ");
			}
			System.out.print("\t\t"); // this is to ensure formatting
			// print C
			if (cDisplayTreshold >= i) {

				int remainderStarsC = pilesAndNumbers.get(pile.get(2)) % 3;

				if (firstOccurenceC) {
					if (remainderStarsC == 0) {
						System.out.print("***");
					} else {
						for (int k = 0; k < 3; k++) {
							if (k < remainderStarsC) {
								System.out.print("*");
							} else {
								System.out.print(" ");
							}
						}
					}
				} else {
					System.out.print("***");
				}
				firstOccurenceC = false;
			} else {
				System.out.print("   ");
			}
			System.out.println();

		}

		System.out.println("\t\t" + pile.get(0) + ":" + pilesAndNumbers.get(pile.get(0)) + "\t\t" + pile.get(1) + ":"
				+ pilesAndNumbers.get(pile.get(1)) + "\t\t" + pile.get(2) + ":" + pilesAndNumbers.get(pile.get(2)));
	}

	// to check if the values are positive in order to decide if the game keeps
	// running
	// A METHOD TO CONFIRM IF THE GAME KEEPS RUNNING
	private static boolean pileValuesArePositive() {
		if (pilesAndNumbers.get(pile.get(0)) <= 0 && pilesAndNumbers.get(pile.get(1)) <= 0
				&& pilesAndNumbers.get(pile.get(2)) <= 0) {
			return false;
		} else {
			return true;
		}
	}

	// deduct numbers inputed by the user in the game from the pile till all pile
	// reaches 0
	private static void deductNumberFromPile(String pile, int value) {
		int newNumber = pilesAndNumbers.get(pile) - value;
		pilesAndNumbers.replace(pile, newNumber);
	}

	// setting and getting, printing playerNames and the output for the users
	private static void setAndPrintPlayerNames() {
		System.out.println("Player 1 - Enter your name ");
		playerNameOne = sc.nextLine();
		while (playerNameOne.length() <= 0) // while is to ensure the player's name is not empty
		{
			System.out.println("Name cannot be empty, please input a name");
			playerNameOne = sc.nextLine();// asking for the input of player name one
		}

		playerOne.setPlayerName(playerNameOne); // setting player name one

		System.out.println("Player 2 - Enter your name");
		playerNameTwo = sc.nextLine();
		while (playerNameTwo.length() <= 0) {
			System.out.println("Name cannot be empty, please input a name");
			playerNameTwo = sc.nextLine();// asking for the input of player name two
			// THE WHILE STATEMENT IS TO ENSURE AN EMPTY NAME IS NOT ENTERED
		}
		playerTwo.setPlayerName(playerNameTwo); // setting player name two

	}

	private static void callAndPrintPileAndInputtingIntegers() {
		System.out.println("Enter a positive integer for [" + pile.get(0) + "]:");
		int tempA = Integer.parseInt(sc.nextLine()); // making a temporary local variable in order to be able to check
														// the integer being inputed is greater than 0
		while (tempA <= 0) {
			System.out.println("Please provide a positive integer input ");
			tempA = Integer.parseInt(sc.nextLine());
		}
		setPileNumbers(tempA);

		System.out.println("Enter a positive integer for [" + pile.get(1) + "]:");
		int tempB = Integer.parseInt(sc.nextLine());
		while (tempB <= 0) { // making a temporary local variable in order to be able to check the integer
								// being inputed is greater than 0
			System.out.println("Please provide a positive integer input ");
			tempB = Integer.parseInt(sc.nextLine());
		}
		setPileNumbers(tempB);

		System.out.println("Enter a positive integer for [" + pile.get(2) + "]:");
		int tempC = Integer.parseInt(sc.nextLine());
		while (tempC <= 0) { // making a temporary local variable in order to be able to check the integer
								// being inputed is greater than 0
			System.out.println("Please provide non negative integer input ");
			tempC = Integer.parseInt(sc.nextLine());

		}
		setPileNumbers(tempC); // setting the numbers temporarily to enable a check to be done

	}

	private static void printGameRunning() {
		while (pileValuesArePositive()) {
			if (isPlayerOne) {
				playerDisplayName = playerNameOne; // to equate the names of the player to be displayed
			} else {
				playerDisplayName = playerNameTwo; // to equate the names of the player to be displayed
			}

			System.out.println(playerDisplayName + ", choose a pile: or enter q to exit ");

			String currentPile = sc.nextLine(); // current pile refers to the present pile chosen by the user

			// Check to exit the game if q is entered
			checkIfToExitGameIfQIsInputted(currentPile);
			// i put this in a while loop to ensure the gameplayer choice is not q before
			// the game continues
			while (chosenPileIsNotValid(currentPile)) {
				checkIfToExitGameIfQIsInputted(currentPile); // a check comfirming the game was not exited by the user.

				System.out.println("Please check your choice and try again! remember a choosen pile must not be empty");
				printEmptySpace();
				System.out.println(playerDisplayName + ", choose a pile: or enter q to exit ");
				currentPile = sc.nextLine();
			}

			System.out.println(
					"How many to remove from pile" + " " + currentPile + ": (enter any negative integer to exit)");

			int currentNumber = Integer.parseInt(sc.nextLine()); // variable current number set to be able to pass the
																	// value to be deducted from the pile

			checkIfToExitGameIfNegativeValueIsInputted(currentNumber); // confirming if game should be exited

			while (currentNumber > pilesAndNumbers.get(currentPile)) {
				// the while statement is to check if the current number inputted is greater
				// than the numbers in the pile choosen
				checkIfToExitGameIfNegativeValueIsInputted(currentNumber);

				System.out.println("Wrong: no enough counters please try again");
				printEmptySpace();
				System.out.println(
						"How many to remove from pile" + " " + currentPile + ": (enter any negative integer to exit)");

				currentNumber = Integer.parseInt(sc.nextLine());
			}

			deductNumberFromPile(currentPile, currentNumber);
			// To print out the number left in each pile after every play
			getPilesAndNumbers();
			if (isPlayerOne) { // method setPlayerHistory from StackPlayer
				playerOne.setPlayerHistory(currentPile + currentNumber); // to equate the names of the player to be
				// displayed
			} else {
				playerTwo.setPlayerHistory(currentPile + currentNumber);
			}
			isPlayerOne = !isPlayerOne; // to allow two players to play the game
		}
	}

}
