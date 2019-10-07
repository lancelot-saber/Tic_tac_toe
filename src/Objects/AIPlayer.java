package Objects;

import Interface.IGamePlayer;
import java.io.IOException;
import java.util.Random;

public class AIPlayer implements IGamePlayer
{
    public String Playername="";

    @Override
    public Boolean Inputposition(int choice, String[] pos, String mark, Boolean finishFlag) throws IOException
    {
        return true;
    }
    public void Inputposition(String[] pos, String mark, Boolean finishFlag) throws IOException
    {
        while (!finishFlag)
        {
            Random random = new Random();
            int choice = random.nextInt(10 - 1) + 1;
            int i = 0;
            if (pos[choice].equals("X") || pos[choice].equals("O"))
            {
                i++;
                if(i>10)
                {break;}
            }
            else
                {
                    pos[choice] = mark;
                    break;
                }
        }
    }
}
