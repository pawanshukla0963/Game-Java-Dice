package dice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

//public class DiceGame implements java.io.Serializable{
public class DiceGame implements java.io.Serializable {

	Player player1 = new Player();
	Player player2 = new Player();

	public void playPart1() {

		System.out.println("Please enter your name.");
		Scanner scan = new Scanner(System.in);
		String username = scan.next();
		scan.close();
		System.out.println("Welcome " + username);
		Dice dice = new Dice();

		for (int i = 0; i < 20; i++) {
			dice.roll();
			System.out.println("Dice value is:" + dice.getFaceValue());
		}

	}

	public void playPart2() {
		Dice dice = new Dice(1);
		int score = 5;
		System.out.println("Dice \t Score");
		System.out.println("***** \t *****");
		System.out.println(dice.getFaceValue() + "\t" + (score));

		while (score != 0 && dice.getFaceValue() != 6) {
			score--;
			dice.roll();
			System.out.println(dice.getFaceValue() + "\t" + score);
		}
		if (score == 0) {
			System.out.println("You have LOST!");
		} else {
			System.out.println("You have WON! - your score is: " + score);
		}
	}

	public void playPart3() {

		Dice dice1 = new Dice();
		Dice dice2 = new Dice();
		int score = 0;

		for (int i = 0; i < 3; i++) {
			dice1.roll();
			dice2.roll();
			System.out.println(dice1.getFaceValue() + " \t" + dice2.getFaceValue());
			if (dice1.getFaceValue() == dice2.getFaceValue()) {
				score++;
			}
		}
		System.out.println("Your score is " + score);
	}

	public void playPart4() {

		player1.takeTurn();
		player2.takeTurn();
		
		checkScore();
	}
	
	public void checkScore(){
		
		if (player1.getScore() > player2.getScore()) {
			System.out.println("Player 1 wins!");
		} else if (player1.getScore() < player2.getScore()) {
			System.out.println("Player 2 wins!");
		} else {
			System.out.println("Draw!");
		}
	}

	public static void main(String[] args) {

		DiceGame game = new DiceGame();
		// game.playPart1();
		// game.playPart2();
		// game.playPart3();
		game.playPart4();

		// serialize the game object
		try {
			FileOutputStream fileOut = new FileOutputStream("game.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(game);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in game.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}

		// deserialize the game object
		//DiceGame oldGame = null;
		try {
			FileInputStream fileIn = new FileInputStream("game.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			game = (DiceGame) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("DiceGame class not found");
			c.printStackTrace();
			return;
		}
		game.checkScore();
	}

}
@pawanshukla0963
Comment
