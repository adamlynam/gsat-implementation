/*
 * Literal.java
 *
 * Created on 30 October 2005, 16:18
 */

/**
 *
 * @author Mad_Fool
 */
public class Literal
{
    public int indexIntoTruthArray;
    public boolean positive;
    
    /** Creates a new instance of Literal */
    public Literal(int newIndexIntoTruthArray, boolean newPositive)
    {
        indexIntoTruthArray = newIndexIntoTruthArray;
        positive = newPositive;
    }
}
