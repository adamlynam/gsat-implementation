/*
 * CNF.java
 *
 * Created on 30 October 2005, 15:46
 */

import java.util.*;

/**
 *
 * @author Mad_Fool
 */
public class CNF
{
    private ArrayList<Clause> clauses;
    private int variableCount;
    
    /** Creates a new instance of CNF */
    public CNF(ArrayList<Clause> newClauses, int newVariableCount)
    {
        clauses = newClauses;
        variableCount = newVariableCount;
    }
    
    public int numberOfClauses()
    {
        return clauses.size();
    }
    
    public int numberOfVariables()
    {
        return variableCount;
    }
    
    public int trySolve(boolean[] truthAssignments)
    {
        int numberSolved = 0;
        Iterator clauseList = clauses.iterator();
        
        while (clauseList.hasNext())
        {
            Clause currentClause = (Clause)clauseList.next();
            
            if(currentClause.trySolve(truthAssignments))
            {
                numberSolved++;
            }
        }
        
        return numberSolved;
    }
}
