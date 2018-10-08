package edu.miracosta.cs113;

public class Test
{
    public static void main(String[]args)
    {
        BigInteger test1 = new BigInteger();
        test1.add("99,999,999,999,999,999,999,999,999,999,999,999,999,999,999,999,999");
        test1.add("1");
        System.out.println(test1);

    }
}
