
/**
 * Uses the Die class for multiple tests and games
 *
 * @author (Daniel Chiavenato)
 * @version (11/1/19)
 */
public class Game
{
    Die gameDie = new Die();
    WeightedDie weightedDie = new WeightedDie();
    /**
     * Rolls the die for two players, and prints who got a higher number
     */
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

    /**
     * Same as playGame(), but player1 always wins
     */
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

    /**
     * Rolls the die two times, and gets the sum of the two rolls
     * @return sum of the two rolls
     */
    private int rollTwoDice()
    {
        int sum = 0;
        gameDie.roll();
        sum += gameDie.getValue();
        gameDie.roll();
        sum += gameDie.getValue();

        return sum;
    }

    /**
     * Counts how many rolls until the dice rolls an inputed integer
     * @param num the integer you want the die to roll
     * @return how many rolls it took to get the same number as num
     */
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

    /**
     * Same as counter, but uses the weighted die instead
     */
    public int weightedCounter(int num)
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
                weightedDie.roll();
                testNum = weightedDie.getValue();
                total++;
            }
            return total;
        }
    }
}
