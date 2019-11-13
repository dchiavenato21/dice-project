
/**
 * Represents a die.
 *
 * @author (Daniel Chiavenato)
 * @version (11/1/19)
 */
public class Die
{
    private int diceValue;

    /**
     * Initializes the die with a random value
     */
    public Die()
    {
        roll();
    }

    /**
     * Gets the current value of the die
     * @return Current value of the die
     */
    public int getValue()
    {
        return diceValue;
    }

    /**
     * Randomizes the dice value
     */
    public void roll()
    {
        diceValue = (int)(Math.random() * 6 + 1);
    }

    /**
     * Returns the string format of the current value of the die
     * @return String format of current die value
     */
    @Override
    public String toString()
    {
        return ("" + diceValue);
    }
}
