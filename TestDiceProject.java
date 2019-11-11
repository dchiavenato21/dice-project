import java.util.Scanner;

public class TestDiceProject
{
    private static double maxPoints;

    public static void main(String[] args)
    {
        maxPoints = 0;
        String ans="";
        String x="";
        double score = 0;
        boolean correct = false;
        Scanner in = new Scanner(System.in);
        System.out.print("Creating a new instance of Die... ");
        Die d = new Die();
        System.out.println("done!");

        int[] testCases = {1,2,3,4,5,6};
        for (int goal : testCases)
        {
            correct = tryRolling(d, goal, 500);
            score += test(true, correct, 1);
        }

        System.out.print("Rolling the die 1000 times to look for out-of-bounds face values... ");
        boolean invalidValue = false;
        for (int i=0; i < 1000; i++)
        {
            d.roll();
            if (d.getValue() < 1 || d.getValue() > 6)
            {
                invalidValue = true;
            }
        }
        if (invalidValue)
        {
            System.out.println("OUT-OF-BOUNDS FACE VALUE DETECTED!");
        }
        else
        {
            System.out.println("None detected");
        }
        score += test(false, invalidValue, 1);

        System.out.print("Creating a new instance of Game...");
        Game g = new Game();
        System.out.println("done!");

        System.out.println("Testing your counter method:");
        for (int goal : testCases)
        {
            System.out.print("To roll a " + goal + ": ");
            //int count = 0;
            int count = g.counter(goal);
            System.out.print("took " + count + " roll");
            if (count > 1)
            {
                System.out.print("s");
            }
            System.out.println(".");
            correct = (count >=1 && count < 35);
            score += test(true, correct, 0.5);
        }
        //         System.out.print("Correct? ");
        //         char response = in.nextLine().toUpperCase().charAt(0);
        //         maxPoints += 2;
        //         if (response == 'Y')
        //         {
        //             score += 2;
        //         }

        System.out.println("Testing playGame method of the Game class.");
        g.playGame();

        System.out.println("Testing playUnfairGame method of the Game class.");
        g.playUnfairGame();

        System.out.print("Creating a new instance of WeightedDie... ");
        WeightedDie wd = new WeightedDie();
        System.out.println("Done!");

        System.out.println("Testing weighted die:");
        correct = testWeightedDie(1000, 0.25);
        score += test(true, correct, 2);

        System.out.println("Testing your WeightedDie counter method:");
        for (int goal : testCases)
        {
            System.out.print("To roll a " + goal + ": ");
            //int count = 0;
            int count = g.weightedCounter(goal);
            System.out.print("took " + count + " roll");
            if (count > 1)
            {
                System.out.print("s");
            }
            System.out.println(".");
            correct = (count >=1 && count < 35);
            score += test(true, correct, .5);
        }

        System.out.print("Rolling the weighted die 1000 times to look for out-of-bounds face values... ");
        invalidValue = false;
        for (int i=0; i < 1000; i++)
        {
            wd.roll();
            if (wd.getValue() < 1 || wd.getValue() > 6)
            {
                invalidValue = true;
            }
        }
        if (invalidValue)
        {
            System.out.println("OUT-OF-BOUNDS FACE VALUE DETECTED!");
        }
        else
        {
            System.out.println("None detected");
        }
        score += test(false, invalidValue, 1);

        System.out.println("Testing your Die toString method...");
        String response = d.toString();
        System.out.println("Does |" + response + "| equal |" + (d.getValue() + "|?"));
        correct = ((d.getValue() + "").equals(response));
        score += test(true, correct, 1);

        System.out.println("FUNCTIONALITY SCORE: " + score + " / " + maxPoints);
        System.out.println("");
        if (score == maxPoints)
        {
            System.out.println("Awesome! Way to go!");
        }
        else if (score >= .9*maxPoints)
        {
            System.out.println("Nice work!");
        }
        else if (score >= .8*maxPoints)
        {
            System.out.println("Not bad, but your project has some problems.");
        }
        else
        {
            System.out.println("We should meet ASAP to go over this project.");
        }
    }

    /**
     * Rolls a weighted die the specified number of times and outputs
     * the outcomes.
     * @param n the number of times to roll the die.
     * @param marginOfError the acceptable margin of error from a ratio of 2.0
     */
    private static boolean testWeightedDie(int n, double marginOfError)
    {
        System.out.println("Rolling a weighted die " + n + " times...");
        WeightedDie die = new WeightedDie();
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int f = 0;
        for (int i=0; i < n; i++)
        {
            die.roll();
            if (die.getValue() == 1)
                a++;
            else if (die.getValue() == 2)
                b++;
            else if (die.getValue() == 3)
                c++;
            else if (die.getValue() == 4)
                d++;
            else if (die.getValue() == 5)
                e++;
            else
                f++;
        }
        System.out.println("\tNumber of times rolled a 1: " + a);
        System.out.println("\tNumber of times rolled a 2: " + b);
        System.out.println("\tNumber of times rolled a 3: " + c);
        System.out.println("\tNumber of times rolled a 4: " + d);
        System.out.println("\tNumber of times rolled a 5: " + e);
        System.out.println("\tNumber of times rolled a 6: " + f);
        System.out.println();
        System.out.println("\tNumber of even outcomes: " + (b + d + f));
        System.out.println("\tNumber of odd outcomes:  " + (a + c + e));
        double ratio = (b + d + f + 0.0) / (a + c + e);
        System.out.format("\tRatio of even to odd outcomes: %.2f%n" , ratio);
        if (Math.abs(2.0 - ratio) < marginOfError)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private static boolean tryRolling(Die die, int numToRoll, int numIterationsAllowed)
    {
        System.out.print("Testing to see if it is possible to roll a " + numToRoll + "... ");
        int counter = 0;

        do
        {
            die.roll();
            counter++;
        }
        while (counter < numIterationsAllowed && die.getValue() != numToRoll);

        if (counter == numIterationsAllowed)
        {
            System.out.println("Uh oh. Your die never rolled a " + numToRoll + " after " + numIterationsAllowed + " attempts.");
            return false;
        }
        else
        {
            System.out.print("It took " + counter + " roll");
            if (counter > 1)
            {
                System.out.print("s");
            }
            System.out.println(".");
            return true;
        }
    }

    private static double test(String shouldReturn, String didReturn)
    {
        return test(shouldReturn,didReturn,1);
    }

    private static double test(String shouldReturn, String didReturn, double numPoints)
    {
        maxPoints+=numPoints;
        System.out.println("\tShould return:  " + shouldReturn);   
        System.out.println("\tYours returned: " + didReturn);
        if (shouldReturn.equals(didReturn))
        {
            System.out.println("CORRECT!");
            return numPoints;
        }
        else
        {
            System.out.println("INCORRECT.");
            return 0;
        }
    }

    private static double test(int shouldReturn, int didReturn)
    {
        return test(shouldReturn,didReturn,1);
    }

    private static double test(int shouldReturn, int didReturn, double numPoints)
    {
        maxPoints+=numPoints;
        System.out.println("\tShould return:  " + shouldReturn);   
        System.out.println("\tYours returned: " + didReturn);
        if (shouldReturn == didReturn)
        {
            System.out.println("CORRECT!");
            return numPoints;
        }
        else
        {
            System.out.println("INCORRECT.");
            return 0;
        }
    }

    private static double test(boolean shouldReturn, boolean didReturn, double numPoints)
    {
        maxPoints+=numPoints;
        if (shouldReturn == didReturn)
        {
            System.out.println("CORRECT!");
            return numPoints;
        }
        else
        {
            System.out.println("INCORRECT.");
            return 0;
        }
    }

    private static void sr(String x)
    {
        System.out.println("\tShould return:  " + x);
    }

    private static void yr(String x)
    {
        System.out.println("\tYours returned: " + x);
    }
}