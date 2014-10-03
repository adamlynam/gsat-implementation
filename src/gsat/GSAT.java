/*
 * GSAT.java
 *
 * Created on 30 October 2005, 15:33
 */

import java.util.*;

/**
 *
 * @author Mad_Fool
 */
public class GSAT
{
    
    /** Creates a new instance of Main */
    public GSAT()
    {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //(P or Q or -S) and (-P or Q or R) and (-P or -R or -S) and (P or -S or T)
        /*
        ArrayList<Clause> testExampleClauses = new ArrayList<Clause>();
        testExampleClauses.add(new Clause(new Literal(1, true), new Literal(2, true), new Literal(4, false)));
        testExampleClauses.add(new Clause(new Literal(1, false), new Literal(2, true), new Literal(3, true)));
        testExampleClauses.add(new Clause(new Literal(1, false), new Literal(3, false), new Literal(4, false)));
        testExampleClauses.add(new Clause(new Literal(1, true), new Literal(4, false), new Literal(5, true)));
        CNF testExample = new CNF(testExampleClauses, 5);
        
        boolean[] solved = GSATAlgorithm(testExample, 5, 25);*/
        
        CNF generatedExample = generateCNF(100, 100);
        
        boolean[] solved = GSATAlgorithm(generatedExample, 10, 50);
        
        printSolved(solved);
    }
    
    public static CNF generateCNF(int numberOfVariables, int numberOfClauses)
    {
        ArrayList<Clause> clauses = new ArrayList<Clause>();
        
        for (int i = 0; i < numberOfClauses; i++)
        {
            Literal literalOne;
            Literal literalTwo;
            Literal literalThree;
            
            int literalOneIndex = (int) Math.floor(Math.random() * numberOfVariables);
            int literalTwoIndex = (int) Math.floor(Math.random() * numberOfVariables);
            int literalThreeIndex = (int) Math.floor(Math.random() * numberOfVariables);
            
            if (Math.random() > 0.5)
            {
                literalOne = new Literal(literalOneIndex, true);
            }
            else
            {
                literalOne = new Literal(literalOneIndex, false);
            }
            if (Math.random() > 0.5)
            {
                literalTwo = new Literal(literalTwoIndex, true);
            }
            else
            {
                literalTwo = new Literal(literalTwoIndex, false);
            }
            if (Math.random() > 0.5)
            {
                literalThree = new Literal(literalThreeIndex, true);
            }
            else
            {
                literalThree = new Literal(literalThreeIndex, false);
            }
            
            clauses.add(new Clause(literalOne, literalTwo, literalThree));
        }
        
        return new CNF(clauses, numberOfVariables);
    }
    
    public static boolean[] GSATAlgorithm(CNF cnfToSolve, int maxRestarts, int maxClimbs)
    {
        boolean[] truthAssignments = new boolean[cnfToSolve.numberOfVariables() + 1];
        for (int i = 1; i <= maxRestarts; i++)
        {
            randomlyGenerateTruthAssignments(truthAssignments);
            for (int j = 1; j <= maxClimbs; j++)
            {
                int clausesSolved = cnfToSolve.trySolve(truthAssignments);
                
                if (clausesSolved == cnfToSolve.numberOfClauses())
                {
                    truthAssignments[0] = true;
                    return truthAssignments;
                }
                
                truthAssignments = randomBestSuccesor(truthAssignments, cnfToSolve);
            }
        }
        truthAssignments[0] = false;
        return truthAssignments;
    }
    
    public static void randomlyGenerateTruthAssignments(boolean[] truthAssignments)
    {
        for (int i = 1; i < truthAssignments.length; i++)
        {
            if (Math.random() > 0.5)
            {
                truthAssignments[i] = true;
            }
            else
            {
                truthAssignments[i] = false;
            }
        }
    }
    
    public static boolean[] randomBestSuccesor(boolean[] oldTruthAssignments, CNF cnfToSolve)
    {
        ArrayList<boolean[]> potentialAssignments = new ArrayList<boolean[]>();
        for (int i = 1; i < oldTruthAssignments.length; i++)
        {
            boolean[] newTruthAssignments = oldTruthAssignments.clone();
            newTruthAssignments[i] = !oldTruthAssignments[i];
            potentialAssignments.add(newTruthAssignments);
        }
        
        ArrayList<boolean[]> bestSuccesors = new ArrayList<boolean[]>();
        for (int i = 0; i < 10; i++)
        {
            Iterator assignmentsList = potentialAssignments.iterator();
            
            int currentBestClausesSolved = -1;
            boolean[] currentBestAssignment = null;
            while (assignmentsList.hasNext())
            {
                boolean[] currentAssignment = (boolean[])assignmentsList.next();
                
                int currentClausesSolved = cnfToSolve.trySolve(currentAssignment);
                if (currentClausesSolved > currentBestClausesSolved)
                {
                    currentBestClausesSolved = currentClausesSolved;
                    currentBestAssignment = currentAssignment;
                }
            }
            
            potentialAssignments.remove(currentBestAssignment);
            bestSuccesors.add(currentBestAssignment);
        }
        
        int randomChoice = (int)Math.floor(Math.random() * 10.0);
        
        return bestSuccesors.get(randomChoice);
    }
    
    public static void printSolved(boolean[] solved)
    {
        if (solved[0])
        {
            System.out.println("An answer was found, it is as follows :");
            for (int i = 1; i < solved.length; i++)
            {
                System.out.println("Variable " + i + " is " + solved[i]);
            }
        }
        else
        {
            System.out.println("No answer was found, sorry");
        }
    }
}
