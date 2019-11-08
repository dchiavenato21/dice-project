
/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game
{
    Die gameDie = new Die();
    
    public void playGame()
    {
        int score1 = rollTwoDice();
        int score2 = rollTwoDice();
        System.out.println("Player 1: " + score1);
        System.out.println("Player 2: " + score2);
        if (score1 > score2)
            System.out.println("Player 1 Won!");
        else if (score1 < score2)
            System.out.println("Player 2 Won!");
        else
            System.out.println("Tie!");
    }
    
    public void playUnfairGame()
    {
        int score1 = rollTwoDice();
        int score2 = rollTwoDice();
        while (score1 < score2 || score1 == score2)
        {
            score1 = rollTwoDice();
            score2 = rollTwoDice();
        }
        System.out.println("Player 1: " + score1);
        System.out.println("Player 2: " + score2);
        System.out.println("Player 1 Won!");
    }
    
    private int rollTwoDice()
    {
        int sum = 0;
        gameDie.roll();
        sum += gameDie.getValue();
        gameDie.roll();
        sum += gameDie.getValue();
        
        return sum;
    }
    
    public int counter(int num)
    {
        if (!(num >= 1 && num <= 6))
        {
            System.out.println("You must enter a value between 1 and 6!");
            return 0;
        }
        else
        {
            int total = 0;
            int testNum = 0;
            while (num != testNum)
            {
                gameDie.roll();
                testNum = gameDie.getValue();
                total++;
            }
            return total;
        }
    }
}
