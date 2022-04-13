package com.nrupay.PaperScissorsRockGame;

import com.nrupay.game.RockPaperScissorGameRules;
import com.nrupay.game.GameSummary;
import com.nrupay.inputadapter.ConsoleInput;
import com.nrupay.inputadapter.InputAdapter;
import com.nrupay.inputadapter.RandomInput;
import com.nrupay.player.AbstractPlayer;
import com.nrupay.player.ComputerPlayer;
import com.nrupay.player.RealPlayer;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissorsGame {

	public static void main(String[] args) {
		beginGame();
	}

	private static void beginGame() {
		// Initialize PlayerList
		Scanner scanner = new Scanner(System.in);
		ArrayList<AbstractPlayer> playerList = new ArrayList<AbstractPlayer>();
		InputAdapter<String> realPlayerInput = new ConsoleInput(scanner);
		InputAdapter<String> randomInput = new RandomInput(new Random());
		
		// Player1 and Player2 are instantiated and added to the list
		playerList.add(new RealPlayer("Human", realPlayerInput));
		playerList.add(new ComputerPlayer("Computer", randomInput));
		
		// Instantiate gameEngine instance for PlayerList and GameSummary as parameters
		try {
			RockPaperScissorGameRules gameEngine = new RockPaperScissorGameRules(playerList, new GameSummary());
			char exitGame = 'N';
			while (exitGame == 'N') {
				int n = inputNumberOfPlays(scanner);
				System.out.println("\n Game started. Each Player will play " + n + " moves");
				gameEngine.playSession(n);
				gameEngine.resetGame(); // Reset game before every new game starts
				System.out.println(
						"\n Do you want to Exit? Press 'N' or 'n' to continue else press any other key to continue");
				exitGame = scanner.nextLine().toUpperCase().charAt(0);
			}
		} catch (Exception ex) {
			System.out.println("Error in Game Rules Parameters! " + ex.getMessage());
		}
	}

	private static int inputNumberOfPlays(Scanner scanner) {
		int n = 0;
		while (true) {
			try {
				System.out.println("How many numbers of move you want in this game<Must be Greater than Zero>");
				n = scanner.nextInt();
				if (n <= 0) {
					throw new NumberFormatException("User input must be positive...");
				}
				scanner.nextLine();
				break;
			} catch (NumberFormatException ex) {
				System.out.println("Invalidated user input! " + ex.getMessage());
			}
		}
		return n;
	}
}
