package edu.miracosta.cs113;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

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

    /***************************************************************
     * This constructor Initializes with empty list and false boolean..
     ***************************************************************/
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
    public void add(int n) {

    }

    @Override
    public void add(String n) {

    }

    @Override
    public void subtract(int n) {

    }

    @Override
    public void subtract(String n) {

    }
}
