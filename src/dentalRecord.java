import java.util.Locale;
import java.util.Scanner;


public class dentalRecord {

    public static void main(String[] args)
    {
        //Establish variables
        int famNum;
        String toothString;
        boolean stringChecker;

        Scanner input = new Scanner(System.in);


        //Welcome user
        System.out.println("Welcome to the Floridian dental records\n" +
                "---------------------------------------");

        //Get number of family members
        System.out.print("Please enter the number of people in the family(<6): ");
        do {
            famNum = input.nextInt();

            if(famNum > 5 || famNum < 1)
                System.out.print("Invalid number of people, try again: ");
        }while(famNum > 5 || famNum < 1);

        String[]famNames = new String[famNum+1];

        //I don't know why but the following loop won't run without these lines
        famNames[0] = input.nextLine();
        famNames[0] = null;

        //Establish teeth array
        char[][][] teethArray = new char[famNum][2][10];
        char[] toothChars = new char[10];

        //prompt for upper and lower teeth
        for(int i = 1; i <= famNum; i++)
        {
            System.out.print("Please enter the name of family member " + i + ": ");
            famNames[i] = input.nextLine();

            System.out.print("Please enter the uppers for " + famNames[i] + ": ");
            toothString = input.nextLine().toUpperCase();
            stringChecker = false;
            for(int j = 0; j < toothString.length(); j++) {
                if(toothString.charAt(j) != 'B' && toothString.charAt(j) != 'C' && toothString.charAt(j) != 'M')
                    stringChecker = true;
            }

            while(toothString.length() > 10 || stringChecker) {
                if (toothString.length() > 10) {
                    System.out.print("Too many teeth, try again: ");
                } else {
                    System.out.print("Invalid teeth types, try again: ");
                }
                toothString = input.nextLine().toUpperCase();
                for (int j = 0; j < toothString.length(); j++) {
                    if (toothString.charAt(j) != 'B' && toothString.charAt(j) != 'C' && toothString.charAt(j) != 'M')
                        stringChecker = true;
                    else
                        stringChecker = false;
                }
            }

            //turn toothString into array of chars
            for(int x = 0; x < toothString.length(); x++)
                toothChars[x] = toothString.charAt(x);

            for(int q = 0; q < toothChars.length; q++)
                teethArray[i-1][0][q] = toothChars[q];
//-----------------------------------------------grt lowers
            System.out.print("Please enter the lowers for " + famNames[i] + ": ");
            toothString = input.nextLine().toUpperCase();
            stringChecker = false;
            for(int j = 0; j < toothString.length(); j++) {
                if(toothString.charAt(j) != 'B' && toothString.charAt(j) != 'C' && toothString.charAt(j) != 'M')
                    stringChecker = true;
            }

            while(toothString.length() > 10 || stringChecker) {
                if (toothString.length() > 10) {
                    System.out.print("Too many teeth, try again: ");
                } else {
                    System.out.print("Invalid teeth types, try again: ");
                }
                toothString = input.nextLine().toUpperCase();
                for (int j = 0; j < toothString.length(); j++) {
                    if (toothString.charAt(j) != 'B' && toothString.charAt(j) != 'C' && toothString.charAt(j) != 'M')
                        stringChecker = true;
                    else
                        stringChecker = false;
                }
            }

            //turn toothString into array of chars
            for(int x = 0; x < toothString.length(); x++)
                toothChars[x] = toothString.charAt(x);

            for(int q = 0; q < toothChars.length; q++) {
                teethArray[i - 1][1][q] = toothChars[q];

            }
        }
        System.out.println("OOOGAHGHAHGH     "+ teethArray[0][0].length);
        printTeeth(teethArray, famNames);

    }

    public static void printTeeth(char[][][] teethArray, String[] names)
    {
        for(int i = 1; i < names.length; i++) {
            System.out.print(names[i] + "\nUppers:\t");
        //I forsee problems with the next line.
            for(int j = 0; j <= teethArray[0].length; j++)
            {
                System.out.print( (j+1) + ":" + teethArray[i-1][0][j] + "\t");

            }
        }

    }
}