package Objects;

import Interface.IGamePlayer;

import java.io.IOException;

public class HumanPlayer implements IGamePlayer
{
    public String Playername="";
    @Override
    public Boolean Inputposition(int choice, String[] pos, String mark, Boolean finishFlag ) throws IOException {
        while (!finishFlag)
        {
            if (choice > 0 && choice < 10) {
                if (pos[choice].equals("X") || pos[choice].equals("O")) // Checks to see if spot is taken already
                {
                    PosTakenMessage();
                    break;
                } else {
                    pos[choice] = mark;
                   break;
                }
            } else {
                System.out.println("Please input the valid number.");
            }
        }
        return pos[choice].equals(mark);
    }
    private static void PosTakenMessage() throws IOException {
        System.out.println("The position has been taken! ");
        System.out.println("Try again.");
        System.in.read();
        clearScreen();
    }
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
