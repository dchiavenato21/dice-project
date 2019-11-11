
/**
 * Write a description of class Die here.
 *
 * @author (Daniel Chiavenato)
 * @version (11/1/19)
 */
public class WeightedDie
{
    private int diceValue;
    
    /**
     * Initializes the die with a random value
     */
    public WeightedDie()
    {
        roll();
    }
    
    /**
     * @return Current value of the die
     */
    public int getValue()
    {
        return diceValue;
    }
    
    /**
     * Randomizes the dice value, with a greater chance of an even number
     */
    public void roll()
    {
        int rollNum = (int)(Math.random() * 6 + 1);
        if (rollNum % 2 == 0)
            diceValue = rollNum;
        else
        {
            rollNum = (int)(Math.random() * 6 + 1);
            diceValue = rollNum;
        }
    }
    
    /**
     * @return String format of current die value
     */
    @Override
    public String toString()
    {
        return ("" + diceValue);
    }
}
