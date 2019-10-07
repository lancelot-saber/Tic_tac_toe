import Objects.AIPlayer;
import Objects.Boards;
import Objects.HumanPlayer;
import java.io.IOException;
import java.util.Scanner;

public class First {
    private static String[] pos = new String[]{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}; // Array that contains board positions
    private static Boolean FinishFlag = false;// finish game flag
    private static Boolean DrawFlag = false;// draw game flag
    private static Boolean PlayingFlag = true;;// on game flag
    private static Boolean CPUFlag = false;;// CPU Mode flag
    private static Boolean CorrectInput = false;;// inputcheck flag
    private static int Choice = 0;// position number
    private static int turn = 1; // whose round
    private static int score1 = 0;// player1's win time
    private static int score2 = 0;// player2's win time

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static boolean CheckSelectionInput(int choice)
    {
        if (choice > 0 && choice < 3)
        { return true; }
        else
        {
            System.out.println("Please input the valid number.");
            return false;
        }
    }

    public static void main(String[] args)throws IOException
    {
        Scanner sc = new Scanner(System.in); //To read input
        System.out.println("Hello! Welcome to Tic Tac Toe.");
        System.out.println("Please select the MODE");
        System.out.println("1. Play with CPU");
        System.out.println("2. Play with your friends");
        var player1 = new HumanPlayer();
        var player2 = new HumanPlayer();
        var AIplayer = new AIPlayer();
        var board = new Boards();

        while (!CorrectInput)
        {
            if (sc.hasNextInt())
            {
                Choice = sc.nextInt();
                CorrectInput = CheckSelectionInput(Choice);
            }else
            {
                System.out.println("Please input the valid number.");
            }
        }
        CorrectInput = false; // Reset
        switch (Choice)
        {
            case 1:
                clearScreen();
                CPUFlag = true; //CPU Mode
                break;
            case 2:
                clearScreen(); //Friends Mode
                break;
        }
        System.out.println("What is the name of player 1?");
        player1.Playername = sc.nextLine();
        if (!CPUFlag)
        {
            System.out.println("Very good. What is the name of player 2?");
            player2.Playername = sc.nextLine();
        }else
        {
            AIplayer.Playername = "Mr.CPU";
        }
        String secondPlayer = CPUFlag ?AIplayer.Playername:player2.Playername;
        System.out.println(String.format("Okay good. %s is O and %s is X.", player1, secondPlayer));
        System.out.println(String.format("%s goes first.", player1));
        sc.nextLine();
        clearScreen();
        while (PlayingFlag)
        {
            while (!FinishFlag)
            {
                board.DrawBoard(pos);
                board.Showhistory(player1.Playername, secondPlayer, score1, score2);
                if (turn == 1)
                {
                    System.out.println(String.format("%s's (O) turn", player1.Playername));
                    System.out.println("Which position would you like to take?");
                        if (sc.hasNextInt())
                        {
                            boolean correcting = player1.Inputposition(sc.nextInt(), pos, "O", FinishFlag);
                            if(!correcting)
                            {
                                continue;
                            }
                        } else
                        {
                            System.out.println("Please input the valid number.");
                            continue;
                        }
                        FinishFlag = CheckWin();
                }
                if (turn == 2 && !CPUFlag)
                {
                    System.out.println(String.format("%s's (X) turn", player2.Playername));
                    System.out.println("Which position would you like to take?");
                    if (sc.hasNextInt())
                    {
                        boolean correcting = player2.Inputposition(sc.nextInt(), pos, "X", FinishFlag);
                        if(!correcting)
                        {
                            continue;
                        }
                    } else
                    {
                        System.out.println("Please input the valid number.");
                        continue;
                    }
                    FinishFlag = CheckWin();
                } else if(turn == 2 && CPUFlag)
                    {
                        System.out.println(String.format("%s's (O) turn", AIplayer.Playername));
                        AIplayer.Inputposition(pos,"X", FinishFlag);
                        FinishFlag = CheckWin();
                    }
                if (!FinishFlag)
                {
                    turn = turn == 1 ? 2 : 1;
                   clearScreen();
                }
            }
            clearScreen();
            //Show Results
            board.DrawBoard(pos);
            for (int i = 1; i < 10; i++) // Resets board
            {
                pos[i] = Integer.toString(i);
            }
            if (FinishFlag && DrawFlag) // No one won
            {
                System.out.println("It's a draw!");

                board.Showhistory(player1.Playername, secondPlayer, score1, score2);
                System.out.println("");
                PlayAgainOrQuit(sc, Choice);
            }
            else if (FinishFlag) // Someone won
            {
                if (turn == 1)
                {
                    score1++;
                    System.out.println(String.format("%s wins!", player1));
                    PlayAgainOrQuit(sc, Choice);
                }
                else if (turn == 2)
                {
                    score2++;
                    System.out.println(String.format("%s wins!", secondPlayer));
                    PlayAgainOrQuit(sc, Choice);
                }
            }
        }
    }

    static void PlayAgainOrQuit(Scanner sc, int choice)
    {
        System.out.println("What would you like to do now?");
        System.out.println("1. Play again");
        System.out.println("2. Leave");

        while (!CorrectInput)
        {
            System.out.println("Enter your option: ");
            if (sc.hasNextInt())
            {
                Choice = sc.nextInt();
                CorrectInput = CheckSelectionInput(Choice);
            }else
            {
                System.out.println("Please input the valid number.");
            }
        }
        CorrectInput = false; // Reset
        switch (choice)
        {
            case 1:
                clearScreen();
                FinishFlag = false;
                break;
            case 2:
                clearScreen();
                System.out.println("Thanks for playing!");
                sc.nextLine();
                PlayingFlag = false;
                break;
        }
    }

    private static Boolean CheckWin() //Winner checking method
    {
        if (pos[1].equals("O") && pos[2].equals("O") && pos[3].equals("O")) // Horizontal
        { return true; }
        else if (pos[4].equals("O") && pos[5].equals("O") && pos[6].equals("O"))
        { return true; }
        else if (pos[7].equals("O") && pos[8].equals("O") && pos[9].equals("O"))
        { return true; }
        else if (pos[1].equals("O") && pos[5].equals("O") && pos[9].equals("O")) // Diagonal
        { return true; }
        else if (pos[7].equals("O") && pos[5].equals("O") && pos[3].equals("O"))
        { return true; }
        else if (pos[1].equals("O") && pos[4].equals("O") && pos[7].equals("O"))// Coloumns
        { return true; }
        else if (pos[2].equals("O") && pos[5].equals("O") && pos[8].equals("O"))
        { return true; }
        else if (pos[3].equals("O") && pos[6].equals("O") && pos[9].equals("O"))
        { return true; }
        else if (pos[1].equals("X") && pos[2].equals("X") && pos[3].equals("X")) // Horizontal
        { return true; }
        else if (pos[4].equals("X") && pos[5].equals("X") && pos[6].equals("X"))
        { return true; }
        else if (pos[7].equals("X") && pos[8].equals("X") && pos[9].equals("X"))
        { return true; }
        else if (pos[1].equals("X") && pos[5].equals("X") && pos[9].equals("X")) // Diagonal
        { return true; }
        else if (pos[7].equals("X") && pos[5].equals("X") && pos[3].equals("X"))
        { return true; }
        else if (pos[1].equals("X") && pos[4].equals("X") && pos[7].equals("X")) // Coloumns
        { return true; }
        else if (pos[2].equals("X") && pos[5].equals("X") && pos[8].equals("X"))
        { return true; }
        else if (pos[3].equals("X") && pos[6].equals("X") && pos[9].equals("X"))
        { return true; }
        else if(!pos[1].equals("1") && !pos[2].equals("2") && !pos[3].equals("3") && !pos[4].equals("4") && !pos[5].equals("5") && !pos[6].equals("6") && !pos[7].equals("7") && !pos[8].equals("8") && !pos[9].equals("9"))// No winner
        {
            DrawFlag = true;
            return true;
        }
        else
        { return false; }
    }
}
