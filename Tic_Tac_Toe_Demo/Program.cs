using System;

namespace Tic_Tac_Toe_Demo
{
    class Program
    {
        private static string[] pos = new string[10] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }; // Array that contains board positions
        private static bool FinishFlag { get; set; }// finish game flag
        private static bool DrawFlag { get; set; }// draw game flag
        private static bool PlayingFlag { get; set; }// on game flag
        private static bool CPUFlag { get; set; }// CPU Mode flag
        private static bool CorrectInput { get; set; }// inputcheck flag
        private static int Choice { get; set; }// position number
        static void DrawBoard()// Draw board method
        {
            Console.WriteLine("   {0}  |  {1}  |  {2}   ", pos[1], pos[2], pos[3]);
            Console.WriteLine("-------------------");
            Console.WriteLine("   {0}  |  {1}  |  {2}   ", pos[4], pos[5], pos[6]);
            Console.WriteLine("-------------------");
            Console.WriteLine("   {0}  |  {1}  |  {2}   ", pos[7], pos[8], pos[9]);
        }
        static void Main(string[] args) //main part
        {
            string player1 = ""; string player2 = "";
            int turn = 1, score1 = 0, score2 = 0;
            Choice = 0; FinishFlag = false; PlayingFlag = true; CPUFlag = false; CorrectInput = false;
            Console.WriteLine("Hello! Welcome to Tic Tac Toe.");
            Console.WriteLine("Please select the MODE");
            Console.WriteLine("1. Play with CPU");
            Console.WriteLine("2. Play with your friends");

            #region choose Mode
            while (CorrectInput == false)
            {
                if (int.TryParse(Console.ReadLine(), out int choice))
                {
                    Choice = choice;
                    CorrectInput = CheckSelectionInput(Choice);
                }else
                {
                    Console.WriteLine("Please input the valid number.");
                    continue;
                }
            }
            CorrectInput = false; // Reset

            switch (Choice)
            {
                case 1:
                    Console.Clear();                  
                    CPUFlag = true; //CPU Mode
                    break;
                case 2:
                    Console.Clear(); //Friends Mode
                    break;
            }
            #endregion
            Console.WriteLine("What is the name of player 1?");
            player1 = Console.ReadLine();
            if (CPUFlag == false)
            { 
              Console.WriteLine("Very good. What is the name of player 2?"); 
              player2 = Console.ReadLine();
            }else
            {
                player2 = "Mr.CPU";
            }         
            Console.WriteLine("Okay good. {0} is O and {1} is X.", player1, player2);
            Console.WriteLine("{0} goes first.", player1);
            Console.ReadLine();
            Console.Clear();

            #region Playing part
            while (PlayingFlag == true)
            {
                while (FinishFlag == false)
                {
                    DrawBoard();
                    Console.WriteLine("Score: {0} - {1}     {2} - {3}", player1, score1, player2, score2);
                    if (turn == 1)
                    {
                        Console.WriteLine("{0}'s (O) turn", player1);
                        InputPosition();
                        if (pos[Choice] == "X" || pos[Choice] == "O") // Checks to see if spot is taken already
                        {
                            PosTakenMessage();
                            continue;
                        }else
                        {
                            pos[Choice] = "O";
                        }
                        FinishFlag = CheckWin();
                    }
                    if (turn == 2 && CPUFlag == false)
                    {
                        Console.WriteLine("{0}'s (X) turn", player2);
                        InputPosition();
                        if (pos[Choice] == "X" || pos[Choice] == "O") // Checks to see if spot is taken already
                        {
                            PosTakenMessage();
                            continue;
                        }else
                        {
                            pos[Choice] = "X";
                        }
                        FinishFlag = CheckWin();
                    }
                    else if(turn == 2 && CPUFlag == true)
                    {
                        //Easy Mode(Use random function, can try minMax Algorithm when have time)
                        Console.WriteLine("{0}'s (X) turn", player2);
                        Random random = new Random();
                        Choice = random.Next(1, 9);
                        if (pos[Choice] == "X" || pos[Choice] == "O")
                        {
                            Choice = random.Next(1, 9);
                            continue;
                        }else
                        {
                            pos[Choice] = "X";
                        }
                        FinishFlag = CheckWin();
                    }
                    if (FinishFlag == false)
                    {
                        turn = turn == 1 ? 2 : 1;
                        Console.Clear();
                    }
                }
                Console.Clear();

                //Show Results
                DrawBoard();
                for (int i = 1; i < 10; i++) // Resets board
                {
                    pos[i] = i.ToString();
                }
                if (FinishFlag == true && DrawFlag == true) // No one won
                {
                    Console.WriteLine("It's a draw!");
                    Console.WriteLine("Score: {0} - {1}     {2} - {3}", player1, score1, player2, score2);
                    Console.WriteLine("");
                    PlayAgainOrQuit(CorrectInput, Choice);
                }
                else if (FinishFlag == true) // Someone won
                {
                    if (turn == 1)
                    {
                        score1++;
                        Console.WriteLine("{0} wins!", player1);
                        PlayAgainOrQuit(CorrectInput, Choice);
                    }
                    else if (turn == 2)
                    {
                        score2++;
                        Console.WriteLine("{0} wins!", player2);
                        PlayAgainOrQuit(CorrectInput, Choice);
                    }
                }
            }
            #endregion
        }

        #region Method area     
        static void InputPosition()  //Check the Input position
        {
            while (CorrectInput == false)
            {
                Console.WriteLine("Which position would you like to take?");
                if (int.TryParse(Console.ReadLine(), out int choice))
                {
                    Choice = choice;
                    if (choice > 0 && choice < 10)
                    {
                        CorrectInput = true;
                    }else
                    {
                        Console.WriteLine("Please input the valid position number.");
                        continue;
                    }
                }else
                {
                    Console.WriteLine("Please only input with numbers.");
                    continue;
                }
            }
            CorrectInput = false; // Reset   
        }
        static void PlayAgainOrQuit(bool correctInput, int choice)
        {
            Console.WriteLine("What would you like to do now?");
            Console.WriteLine("1. Play again");
            Console.WriteLine("2. Leave");

            while (correctInput == false)
            {
                Console.WriteLine("Enter your option: ");
                if (int.TryParse(Console.ReadLine(), out choice))
                {
                    correctInput = CheckSelectionInput(choice);
                }else
                {
                    Console.WriteLine("Please input the valid number.");
                    continue;
                }
            }
            correctInput = false; // Reset
            switch (choice)
            {
                case 1:
                    Console.Clear();
                    FinishFlag = false;
                    break;
                case 2:
                    Console.Clear();
                    Console.WriteLine("Thanks for playing!");
                    Console.ReadLine();
                    PlayingFlag = false;
                    break;
            }
        }
        static bool CheckSelectionInput(int choice)
        {
            if (choice > 0 && choice < 3)
            { return true; }
            else
            {
                Console.WriteLine("Please input the valid number.");
                return false;
            }
        }
        static void PosTakenMessage()
        {
            Console.WriteLine("The position has been taken! ");
            Console.Write("Try again.");
            Console.ReadLine();
            Console.Clear();           
        }
        static bool CheckWin() //Winner checking method
        {
            if (pos[1] == "O" && pos[2] == "O" && pos[3] == "O") // Horizontal
            { return true; }
            else if (pos[4] == "O" && pos[5] == "O" && pos[6] == "O")
            { return true; }
            else if (pos[7] == "O" && pos[8] == "O" && pos[9] == "O")
            { return true; }
            else if (pos[1] == "O" && pos[5] == "O" && pos[9] == "O") // Diagonal
            { return true; }
            else if (pos[7] == "O" && pos[5] == "O" && pos[3] == "O")
            { return true; }
            else if (pos[1] == "O" && pos[4] == "O" && pos[7] == "O")// Coloumns 
            { return true; }
            else if (pos[2] == "O" && pos[5] == "O" && pos[8] == "O")
            { return true; }
            else if (pos[3] == "O" && pos[6] == "O" && pos[9] == "O")
            { return true; }
            else if (pos[1] == "X" && pos[2] == "X" && pos[3] == "X") // Horizontal
            { return true; }
            else if (pos[4] == "X" && pos[5] == "X" && pos[6] == "X")
            { return true; }
            else if (pos[7] == "X" && pos[8] == "X" && pos[9] == "X")
            { return true; }
            else if (pos[1] == "X" && pos[5] == "X" && pos[9] == "X") // Diagonal
            { return true; }
            else if (pos[7] == "X" && pos[5] == "X" && pos[3] == "X")
            { return true; }
            else if (pos[1] == "X" && pos[4] == "X" && pos[7] == "X") // Coloumns
            { return true; }
            else if (pos[2] == "X" && pos[5] == "X" && pos[8] == "X")
            { return true; }
            else if (pos[3] == "X" && pos[6] == "X" && pos[9] == "X")
            { return true; }
            else if(pos[1] != "1" && pos[2] != "2" && pos[3] != "3" && pos[4] != "4" && pos[5] != "5" && pos[6] != "6" && pos[7] != "7" && pos[8] != "8" && pos[9] != "9")// No winner
            {
                DrawFlag = true; 
                return true;
            }
            else 
            { return false; }
        }
        #endregion
    }
}
