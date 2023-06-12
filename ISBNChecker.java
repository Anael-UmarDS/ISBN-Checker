import java.util.Scanner;

/* Information from https://www.cis.upenn.edu/~cis110/11fa/hw/hw04/index.html#:~:text=For%20example%2C%20consider%20the%20number,under%20which%20this%20product%20belongs. */

/* This program checks to see if an ISBN number given by the user is valid.
It can work for any type of ISBN number which are ISBN-10 and ISBN-13, each with their own unique algorithms */

public class ISBNChecker
{
    public static void main(String[] args)
    {
        // Initialize values

        Scanner sc = new Scanner(System.in);
        String isbn;

        //Get ISBN number

        System.out.println("This program checks to see if a given ISBN is valid.");
        System.out.println("It can process both ISBN-10 and ISBN-13 codes.");
        System.out.println("Enter the ISBN number: ");
        isbn = sc.nextLine();
        sc.close();

        //Change "-" with "" and " " with ""

        isbn = isbn.replace(" ", "");
        isbn = isbn.replace("-", "");

        //Check validity based off of length of ISBN number

        boolean isValid = false;
        if(isbn.length() == 10)
        {
            isValid = CheckISBN10(isbn);
        }
        else if(isbn.length() == 13)
        {
            isValid = CheckISBN13(isbn);
        }
        else
        {
            isValid = false;
        }

        /* Return validity based on value of isValid */
        if(isValid == true)
        {
            System.out.println(isbn + " is a valid ISBN number");
        }
        else
        {
            System.out.println(isbn + " is not a valid ISBN number");
        }
    }
        //Check for ISBN validity on ISBN 10 digits

    public static boolean CheckISBN10(String isbn)
    {
        // Sum digits of the 10-digit ISBN
        int sum = 0;
        String subStr;

        //Initialize for loop
        for(int i = 0; i < 10; i++)
        {
            // Check each character of isbn string
            subStr = isbn.substring(i, i + 1);

            //If i is a digit less than 9 or it is X, multiply the digit by its complement
            if(i < 9 || subStr != "X")
            {
                sum += Integer.parseInt(subStr) * (10 - i);
            }
            else
            {
                // Else you simply add 10 to the sum
                sum += 10;
            }
        }

        // Check to see if the final step of the algorithm return true or false based on value of to_return
        boolean sum_mod = false;
        int to_return = sum % 11;
        
        if(to_return == 0)
        {
            sum_mod = true;
        }
        return sum_mod;
    }

    public static boolean CheckISBN13(String isbn)
    {
        // Sum digits and multiply by 1, 2, 1, 3, ... 1 respectively
        int sum = 0;
        int value;

        for(int i = 0; i < 13; i++)
        {
            value = Integer.parseInt(isbn.substring(i, i + 1));
            if(i % 2 == 0)
            {
                //If number is even
                sum += value;                
            }
            else
            {
                //If number is odd
                sum += value * 3;
            }
        }

        // Check to see if the final step of the algorithm return true or false based on value of to_return
        boolean sum_mod = false;
        int to_return = sum % 10;

        if(to_return == 0)
        {
            sum_mod = true;
        }
        return sum_mod;

    }
}