package edu.miracosta.cs113;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

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
        String digit = "" ;
        String digitTemp = "" ;
        Integer digitInt = 0 ;
        Iterator iter = bigInt.iterator() ;

        if (iter.hasNext()) {
            digitInt = (Integer) iter.next() ;
            digit +=  digitInt ;
            digit +="," ;
        }
        while (iter.hasNext()) {
            digitInt = (Integer) iter.next() ;
            digitTemp = String.format("%03d", digitInt) ;
            digit +=  digitTemp ;
            digit +="," ;
        }
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

    }

    @Override
    public void add(String n)
    {

    }

    @Override
    public void subtract(int n) {

    }

    @Override
    public void subtract(String n) {

    }
}
