package Objects;

public class Boards {
    public void DrawBoard(String[] pos)// Draw board method
    {
        System.out.println(String.format("   %s  |  %s  |  %s   ", pos[1], pos[2], pos[3]));
        System.out.println("-------------------");
        System.out.println(String.format("   %s  |  %s  |  %s   ", pos[4], pos[5], pos[6]));
        System.out.println("-------------------");
        System.out.println(String.format("   %s  |  %s  |  %s   ", pos[7], pos[8], pos[9]));
    }
    public void Showhistory(String player1, String player2, int score1, int score2)
    {
        System.out.println(String.format("Score: %s - %d     %s - %d", player1, score1, player2, score2));
    }
}
