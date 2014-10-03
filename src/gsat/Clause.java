/*
 * Clause.java
 *
 * Created on 30 October 2005, 16:01
 */

/**
 *
 * @author Mad_Fool
 */
public class Clause
{
    private Literal one;
    private Literal two;
    private Literal three;
    /** Creates a new instance of Clause */
    public Clause(Literal newOne, Literal newTwo, Literal newThree)
    {
        one = newOne;
        two = newTwo;
        three = newThree;
    }
    
    public boolean trySolve (boolean[] truthAssignments)
    {
        if (truthAssignments[one.indexIntoTruthArray] == one.positive)
        {
            return true;
        }
        else if (truthAssignments[two.indexIntoTruthArray] == two.positive)
        {
            return true;
        }
        else if (truthAssignments[two.indexIntoTruthArray] == two.positive)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
