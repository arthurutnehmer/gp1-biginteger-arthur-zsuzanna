package edu.miracosta.cs113;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.*;

/**
 * BigInteger implements BigNumber to display, add and subtract large Integers using LinkedList
 */
public class BigInteger implements BigNumber {

    private LinkedList<Integer> bigInt ;
    private boolean negative ;

    /***************************************************************
     * This constructor Initializes the project with these numbers.
     * @param bigInt - Linked list of ints.
     * @param negative - boolean that tells us if the number is negative.
     ***************************************************************/
    public BigInteger(String n)
    {

        bigInt = new LinkedList<Integer>();
        negative = false;
        StringTokenizer StringsOfNumber = new StringTokenizer(n, ",");

        while (StringsOfNumber.hasMoreTokens())
        {
            int nextInt = Integer.parseInt(StringsOfNumber.nextToken());
            if(nextInt < 0)
            {
                negative = true;
            }
            bigInt.add(nextInt);
        }


    }

    /**
     * This constructor Initializes with empty list and false boolean..
     */
    public BigInteger()
    {
        bigInt = new LinkedList<Integer>();
        negative = false;
    }

    @Override
    public String toString() {

        // variables
        String digit = "" ;
        String digitTemp = "" ;
        Integer digitInt = 0 ;
        Iterator iter = bigInt.iterator() ;

        // initial node, which can have no leading zeros
        if (iter.hasNext()) {
            digitInt = (Integer) iter.next() ;
            digit +=  digitInt ;
            digit +="," ;
        }

        // gathering rest of the nodes
        while (iter.hasNext()) {
            digitInt = (Integer) iter.next() ;
            digitTemp = String.format("%03d", digitInt) ;
            digit +=  digitTemp ;
            digit +="," ;
        }

        // commas
        digit = digit.substring(0, digit.length() - 1) ;

        return digit ;
    }


    @Override
    public void add(int n)
    {
        LinkedList<Integer> newBigInt = new LinkedList<Integer>();
        LinkedList<Integer> SmallInt = new LinkedList<Integer>();
        StringTokenizer StringsOfNumber;

        //Number two.
        int n2 = n;

        int firstDigitOfNumberToAdd;
        //Seperate digits from the int.
        while (n2 > 0)
        {
            firstDigitOfNumberToAdd = ( n2 % 10);
            SmallInt.add(firstDigitOfNumberToAdd);
            n2= n2 / 10;
        }


        ListIterator iteratorTwo = SmallInt.listIterator();

        //Iterator for big number starting at last index.
        ListIterator it = bigInt.listIterator();
        it = bigInt.listIterator(bigInt.size());

        int remainder = 0;
        while (it.hasPrevious() || iteratorTwo.hasNext() || remainder>0)
        {   //large number;
            int number1;
            //If the number contains a Previous index, get it, if not, assume it is zero.
            if(it.hasPrevious())
            {
                number1 = (int)it.previous();
            }

            else
            {
                number1 = 0;
            }
            //Decide how to aplit and add depending upon how large the number is.
            if(number1 >=100)
            {
                while (number1 > 0)
                {
                    int NumberOne =  number1 % 10;
                    int NumberTwo;
                    int newNumber;
                    number1 = number1 / 10;

                    if(iteratorTwo.hasNext())
                    {
                        NumberTwo = (Integer)iteratorTwo.next();
                        newNumber = NumberOne + NumberTwo + remainder;
                        if(newNumber < 10)
                        {
                            newBigInt.add(newNumber);
                            remainder = 0;
                        }
                        else if(newNumber >= 10)
                        {
                            newBigInt.add(newNumber%10);
                            remainder = 1;
                        }

                    }
                    else
                    {
                        NumberTwo = 0;
                        newNumber = NumberOne + NumberTwo + remainder;
                        if(newNumber < 10)
                        {
                            newBigInt.add(newNumber);
                            remainder = 0;
                        }
                        else if(newNumber >= 10)
                        {
                            newBigInt.add(newNumber%10);
                            remainder = 1;
                        }
                    }

                }
            }

            else if( (10 < number1))
            {
                int i = 0;
                while (i< 3)
                {
                    int NumberOne =  number1 % 10;
                    int NumberTwo;
                    int newNumber;
                    number1 = number1 / 10;
                    if(iteratorTwo.hasNext())
                    {
                        NumberTwo = (Integer)iteratorTwo.next();
                        newNumber = NumberOne + NumberTwo + remainder;
                        if(newNumber < 10)
                        {
                            newBigInt.add(newNumber);
                            remainder = 0;
                        }
                        else if(newNumber >= 10)
                        {
                            newBigInt.add(newNumber%10);
                            remainder = 1;
                        }

                    }
                    else
                    {
                        NumberTwo = 0;
                        newNumber = NumberOne + NumberTwo + remainder;
                        if(!it.hasPrevious() && (newNumber == 0)  )
                        {
                            //If the iterator is out of numbers and their is no remainder.
                        }
                        else if(newNumber < 10)
                        {
                            newBigInt.add(newNumber);
                            remainder = 0;
                        }
                        else if(newNumber >= 10)
                        {
                            newBigInt.add(newNumber%10);
                            remainder = 1;
                        }
                    }
                    i++;

                }
            }
            else if(10 > number1)
            {
                int i = 0;
                while (i< 3)
                {
                    int NumberOne;
                    int NumberTwo;
                    int newNumber;

                    //Set varaibles for big number with no previouse number.
                    if(!it.hasPrevious())
                    {
                        NumberOne = number1;
                        number1 = 0;
                        i++;
                    }
                    //If the big number has a number that is after this one.
                    else
                    {
                        NumberOne =  number1;
                        number1 = number1 / 10;
                    }
                    //Set varaibles for small number with previouse number.
                    if(iteratorTwo.hasNext())
                    {
                        NumberTwo = (Integer)iteratorTwo.next();
                        newNumber = NumberOne + NumberTwo + remainder;
                    }
                    else
                    {
                        NumberTwo = 0;
                        newNumber = NumberOne + NumberTwo + remainder;
                    }
                    if(!it.hasPrevious() && (newNumber == 0)  )
                    {
                        //If the iterator is out of numbers and their is no remainder.
                        i++;
                    }
                    else if(newNumber < 10)
                    {
                        newBigInt.add(newNumber);
                        remainder = 0;
                    }
                    else if(newNumber >= 10)
                    {
                        newBigInt.add(newNumber%10);
                        remainder = 1;
                    }

                    i++;

                }
            }



        }

        //Turn digits into a string, add the commas in the process.
        int sizeOfLIst = newBigInt.size();
        iteratorTwo = newBigInt.listIterator(0);
        String nextDigit = "";
        bigInt.clear();
        while (iteratorTwo.hasNext())
        {
            if(sizeOfLIst > 4)
            {
                if(iteratorTwo.hasNext())
                {
                    nextDigit += (Integer)iteratorTwo.next();
                }
                if(iteratorTwo.hasNext())
                {
                    nextDigit += (Integer)iteratorTwo.next();
                }
                if(iteratorTwo.hasNext())
                {
                    nextDigit +=(Integer)iteratorTwo.next();
                }
                if(iteratorTwo.hasNext())
                {
                    nextDigit += ",";
                }
            }
            else if(sizeOfLIst == 4)
            {
                if(iteratorTwo.hasNext())
                {
                    nextDigit += (Integer)iteratorTwo.next();
                }
                if(iteratorTwo.hasNext())
                {
                    nextDigit += (Integer)iteratorTwo.next();
                }
                if(iteratorTwo.hasNext())
                {
                    nextDigit +=(Integer)iteratorTwo.next() +",";
                }
                if(iteratorTwo.hasNext())
                {
                    nextDigit +=(Integer)iteratorTwo.next();
                }
            }
            else if(sizeOfLIst < 4)
            {
                if(iteratorTwo.hasNext())
                {
                    nextDigit += (Integer)iteratorTwo.next();
                }
                if(iteratorTwo.hasNext())
                {
                    nextDigit += (Integer)iteratorTwo.next();
                }
                if(iteratorTwo.hasNext())
                {
                    nextDigit +=(Integer)iteratorTwo.next();
                }
            }

        }
        StringBuilder input1 = new StringBuilder();
        input1.append(nextDigit);
        input1 = input1.reverse();
        StringsOfNumber = new StringTokenizer(input1.toString(), ",");
        while (StringsOfNumber.hasMoreTokens())
        {
            int nextInt = Integer.parseInt(StringsOfNumber.nextToken());
            bigInt.add(nextInt);
        }
    public void subtract(int incoming) {
        String in = Integer.toString(incoming) ;
        subtract(in);
    }

    }

    public void subtract(int incoming)
    {
        String in = Integer.toString(incoming) ;
        subtract(in);
    }

    @Override
    public void subtract(String deductString) {
        // if Huge Number, use helper method
        if (deductString.length() > 10) {
            helperSubtract(deductString);
            return;
        }
        // else continue with normal Integer math

        // booleans for negative numbers
        boolean negDeduct = false;
        boolean negOriginal = false;
        if (deductString.charAt(0) == '-') {
            negDeduct = true;
        }

        // Stripping incoming
        deductString = deductString.replaceAll("[^0-9]", "");

        int deduct = Integer.parseInt(deductString);
        BigInteger originalList = new BigInteger(this.toString());
        String original = originalList.toString();
        if (original.charAt(0) == '-') {
            negOriginal = true;
        }

        // Stripping of commas
        String temp = original.replaceAll("[^0-9]", "");
        int total = 0;
        int thisOne = Integer.parseInt(temp);

        // negative condition
        if (negDeduct == true) {
            deduct = -deduct;
        }
        if (negOriginal == true) {
            thisOne = -thisOne;
        }

        total = thisOne - deduct;
        int newValue = 0;

        // using only last three digits
        newValue = total % 1000;

        // looping through list, computing and assigning new value
        for (int i = bigInt.size(); i > 0; i--) {
            total = total / 1000;
            ListIterator mover2 = bigInt.listIterator(i);
            mover2.previous();
            mover2.set(newValue);
            newValue = total % 1000;
        }

        // remove leading nodes with only zeros
        ListIterator mover3 = bigInt.listIterator(0);
        if (mover3.next() == (Integer) 0) {
           mover3.remove();
        }



    }

    /**
     * Helper Subtract method in case Integer math cannot be computed for Huge Integers
     * @param deductString incoming String object of Huge Integer to to deduct from this list
     */
    private void helperSubtract(String deductString)
    {
        String temp = "" ;

        // stripping incoming string of commas
        deductString = deductString.replaceAll("[^0-9]","");
        int value = 0 ;
        int total = 0 ;

        // move pointer to the end
        ListIterator m = bigInt.listIterator(0) ;
        while (m.hasNext()) {
            m.next() ;
        }

        int valueOrig = 0 ;
        boolean carry = false ;
        int end = deductString.length() ;
        int start = end  - 3 ;

        while (m.hasPrevious()) {
            if (start < 0) {
                start = 0; // guarding agains negative in substring index
            }
            temp = deductString.substring(start, end);

            end = start;
            start = end - 3;
            value = Integer.valueOf(temp);
            valueOrig = (int) m.previous();

            // if a need to borrow
            if (carry == true) {
                valueOrig -= 1;
            }
            carry = false;
            if (valueOrig < value) {
                valueOrig += 1000;
                carry = true;
            }

            total = valueOrig - value;
            m.set(total);
        }
        }

    @Override
    public void add(String n) {

    }


}
