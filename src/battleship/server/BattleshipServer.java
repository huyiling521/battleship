package battleship.server;

import battleship.server.model.*;

import java.util.Scanner;


/**
 * Main class for a Human vs. Computer version of Battleship.
 * Creates a single instance of OnePlayerBoard.
 * Gets user input(row and column) as instructions
 *  Interact with and play against the Computer
 * @author Yiling Hu &amp; Jiao Du
 *
 */
public class BattleshipServer {

    //Instance variables
    //Initialize the row to store the result from the user
    int row;
    //Initialize the column to store the result from the user
    int column;
    //Initialize a list to store ten ships on board
    Ship[][] ships;
    //Initialize a value to start and end a loop
    boolean playEnd;
    //Initialize the board
    OnePlayerBoard board;


    /**
     * The main method just creates a BattleshipServer object and calls its run method.
     * @param args Not used.
     */
    public static void main(String[] args) {
        //A general method that contain the whole process of game
        new BattleshipServer().run();
    }

    /**
     * Main program for the game
     * Including printing rules, creating new games, asking for human player's instruction, and calculating by computer
     * Prints the result if the game is over and ask if the human player wants to play again
     */
    void run() {
        //Give the player beginning information
        printBegin();
        //Set value for playEnd to start a game
        playEnd = false;
        Scanner scanner = new Scanner(System.in);
        //Game starts
        while (!playEnd){
            //Create a new board
            OnePlayerBoard board = new OnePlayerBoard();
            //Put ten ships on board
            board.placeAllShipsRandomly();
            //Put the ten ships into the ship lists
            ships = board.getShipArray();
            //Print the board for the player
            board.print();
            //Give the player instructions to enter numbers
            System.out.println("\n" + "Where do you want to shoot? Please type row and column number and use comma(,) to seperate.");
            //If the game is not over
            while (!board.isGameOver()) {
                //Let the player enter row and column
                System.out.println("Enter row,column: ");
                //Set the number to column and row variables
                String shootWhere = scanner.nextLine();
                String[] rowAndColumn = shootWhere.split(",");
                row = Integer.parseInt(rowAndColumn[0]);
                column = Integer.parseInt(rowAndColumn[1]);
                //Play the game
                computerCalculate(board);
                //Print the changed board for the player
                board.print();
                System.out.println();
            }
            //Print the result for the player
            printEnd(board);
            //Ask the player if she wants to play again
            System.out.println("Do you want to play again?");
            System.out.println("If yes, enter 'Y'or'y'; If not, enter 'N'or'n'.");
            String playAgain = scanner.next();
            //If the player doesn't, then end
            if (playAgain.equals("n") || playAgain.equals("N")) {
                playEnd = true;
                System.out.print("Thanks for playing our Game!");
            }
        }
        scanner.close();
    }

    /**
     * The process of computer to calculate for each term
     * Including check if the human player hit or miss
     * Check if the ship is sunk and print information
     * Check if the game is over(count the number of sunk ships)
     * @param board for the board to play the game
     */
    void computerCalculate(OnePlayerBoard board) {
        //Determine if the player shoot a ship
        //If she does
        if (board.shootAt(row,column)) {
            //Tell the player she shoots a ship
            System.out.println("hit");
            //Determine if the ship is sunk
            //If the ship is sunk
            if (ships[row][column].isSunk()) {
                //Determine the type of the sunk ship
                ShipType type = ships[row][column].getShipType();
                //Print result for the player
                System.out.println("You just sank a ship: "+ type + ".");
            }
        }
        //If the player doesn't hit a ship
        else {
            //Print the result for the player
            System.out.println("miss");
        }
        System.out.println();
    }

    /**
     * Print the basic rules of this version of Battleship
     */
    void printBegin() {
        System.out.println("---------- WELCOME TO BATTLESHIP----------");
        System.out.println();
        System.out.println("This is a Computer vs. Human version of game Battleship.");
        System.out.println("Here's basic rules for this game!");
        System.out.println();
        System.out.println(">>The computer places the ten ships on the 10 x 10 board.");
        System.out.println(">>1 aircraft carrier(cover 5 units), 2 battleship(cover 4 units), 2 destroyer(cover 3 units), 2 submarine(cover 3 unit), 4 patrol boats(cover 2 unit)");
        System.out.println(">>No ships are immediately adjacent to each other, either horizontally, vertically, or diagonally.");
        System.out.println();
        System.out.println(">>The human player tries to hit the ships, by indicating a specific row and column number (r,c). ");
        System.out.println(">>The computer responds with one bit of information saying, “hit” or “miss”.");
        System.out.println(">>When a ship is hit and sinks, the program prints out a message “You just sank a ship - (type).");
        System.out.println(">>It takes four hits (in four different places) to sink a battleship, three to sink a cruiser, two for a destroyer, and one for a submarine.");
        System.out.println();
        System.out.println(">>After each shot, the computer re-displays the board with the new information.");
        System.out.println(">>When all ship are sunk, the game is over.");
        System.out.println(">>The score will be the number of shots the human player takes in total.");
        System.out.println();
    }

    /**
     * Print Game Over information and the final score of the human player(the shots fired)
     * @param board board for the board to play the game
     */
    void printEnd(OnePlayerBoard board) {
        System.out.println("----------GAME OVER----------");
        System.out.println("Your have shot "+ board.getShotsFired() + " times.");
    }
}
