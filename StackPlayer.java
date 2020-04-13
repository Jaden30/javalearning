package PyramidStack;
import java.util.ArrayList;

public class StackPlayer {

	private static String playerName; // name of the players
	private ArrayList<String> playerHistory; // using an arraylist to add numbers selected by the players // and the pile
											// selected

// constructor
	public StackPlayer() {
		playerName = "";
		playerHistory = new ArrayList<String>(); // calling a new Arraylist

	}

//setName method to set the method to input name
	public void setPlayerName(String name) {
	
			playerName = name;
		}


//getName method to call setName method to return the result of the Names
	public String getPlayerName() {
		return playerName;

	}
// set value of the number of the piles

	// TO SET THE HISTORY OF THE PLAYER'S VALUE AND PILE CHOICE
	public void setPlayerHistory(String pileAndValue) {
		playerHistory.add(pileAndValue);
	}

	// PRINTING PLAYER HISTORY BUT SPLITTING THE PILE AND VALUE TO GIVE ME THE
	// PLAYER HISTORY
	public void printPlayerHistory() {
		String pile;
		int value;
		for (int i = 0; i < playerHistory.size(); i++) {
			// splitting pile from values
			pile = playerHistory.get(i).substring(0, 1);
			value = Integer.parseInt(playerHistory.get(i).substring(1, playerHistory.get(i).length()));
			System.out.println("           chosen pile  [" + pile + "] and removed value [" + value + "]");
		}

	}

}

