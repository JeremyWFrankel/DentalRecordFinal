import java.util.Locale;
import java.util.Scanner;


public class dentalRecord {

    public static void main(String[] args)
    {
        //Establish variables
        int famNum;
        String toothString;
        boolean stringChecker;
        char menu = ' ';
        char[] upTee;
        char[] lowTee;
        final int TEETH_IN_A_ROW = 10;
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
        char[][][] teethArray = new char[famNum][2][TEETH_IN_A_ROW];



        for(int i = 1; i <= famNum; i++)
        {
            //prompt for upper and lower teeth
            System.out.print("Please enter the name of family member " + i + ": ");
            famNames[i] = input.nextLine();

            System.out.print("Please enter the uppers for " + famNames[i] + ": ");
            toothString = input.nextLine().toUpperCase();
            char[] toothChars = new char[toothString.length()];
            stringChecker = false;
            for(int j = 0; j < toothString.length(); j++) {
                if(toothString.charAt(j) != 'B' && toothString.charAt(j) != 'C' && toothString.charAt(j) != 'M')
                    stringChecker = true;
            }
            //Error checking
            while(toothString.length() > TEETH_IN_A_ROW || stringChecker) {
                if (toothString.length() > TEETH_IN_A_ROW) {
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

            upTee = new char[toothString.length()];
            for(int q = 0; q < toothChars.length; q++) {
                upTee[q] = toothChars[q];
            }

//-----------------------------------------------get lowers
            System.out.print("Please enter the lowers for " + famNames[i] + ": ");
            toothString = input.nextLine().toUpperCase();
            stringChecker = false;
            for(int j = 0; j < toothString.length(); j++) {
                if(toothString.charAt(j) != 'B' && toothString.charAt(j) != 'C' && toothString.charAt(j) != 'M')
                    stringChecker = true;
            }

            //Error checking
            while(toothString.length() > TEETH_IN_A_ROW || stringChecker) {
                if (toothString.length() > TEETH_IN_A_ROW) {
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
            char[] lowToothChars = new char[toothString.length()];
            for(int x = 0; x < toothString.length(); x++)
                lowToothChars[x] = toothString.charAt(x);



            lowTee = new char[lowToothChars.length];

            for(int q = 0; q < lowToothChars.length; q++) {
                lowTee[q] = lowToothChars[q];
            }


            //prep char arrays to be added to main 3d array, checking for extra spaces and marking them
            if(lowTee.length > upTee.length) {
                for(int q = 0; q < toothChars.length; q++) {
                    teethArray[i-1][0][q] = toothChars[q];
                    if(q > teethArray[i-1][0].length)
                        teethArray[i-1][0][q] ='X';

                }
                for(int q = 0; q < lowToothChars.length; q++) {
                    teethArray[i - 1][1][q] = lowToothChars[q];
                }
            }
            else if(lowTee.length == upTee.length)
            {
                for(int q = 0; q < toothChars.length; q++)
                    teethArray[i-1][0][q] = toothChars[q];


                for(int q = 0; q < lowToothChars.length; q++) {
                        teethArray[i - 1][1][q] = lowToothChars[q];
                    }

            }
            else
            {

                for(int q = 0; q < toothChars.length; q++) {
                    teethArray[i-1][0][q] = toothChars[q];


                }
                for(int q = 0; q < lowToothChars.length; q++) {
                    teethArray[i - 1][1][q] = lowToothChars[q];
                    if(q > teethArray[i-1][1].length)
                        teethArray[i-1][1][q] ='X';
                }

            }


        }
        //Main menu
        while(menu != 'X'){
            System.out.print("(P)rint, (E)xtract, (R)oot canal, or e(X)it: ");
            menu = input.next().toUpperCase().charAt(0);

            if(menu == 'P')
                printTeeth(teethArray, famNames);
            else if(menu == 'E')
                extract(teethArray, famNames);
            else if(menu == 'R')
                root(teethArray, famNum);
            else if(menu == 'X')
                System.out.println("Goodbye :-)");
            else
                System.out.println("Incorrect menu entry, try again");
        }
    }

    //Extracts tooth
    public static void extract(char[][][] teethArray, String[] names)
    {
        Scanner input = new Scanner(System.in);
        String name, nameCheck;
        boolean check = true;
        int nameIndex = 0, rowIndex = 0, toothIndex = 0;
        char row;
        System.out.print("Which family member: ");
        do {
            name = input.nextLine();
            for (int i = 1; i < names.length; i++) {
                if (name.equals(names[i])) {
                    check = false;
                    nameIndex = i;
                }
            }
            if (check == true)
                System.out.println("Family member not found, please try again.");
        } while (check == true);

        System.out.print("Which row(U or L): ");
        do {
            row = input.next().toUpperCase().charAt(0);

            if(row != 'U' && row != 'L')
                System.out.println("Row not found, please try again.");
        }while(row != 'U' && row != 'L');
        if(row == 'U')
            rowIndex = 0;
        else
            rowIndex = 1;

        System.out.print("Please enter the # of the tooth to extract: ");
        do
        {
            toothIndex = input.nextInt();
            if(toothIndex > teethArray[nameIndex-1][rowIndex].length)
                System.out.println("Invalid tooth #, try again");
        } while(toothIndex > teethArray[nameIndex-1][rowIndex].length);

        teethArray[nameIndex-1][rowIndex][toothIndex-1] = 'M';

    }

    //Performs root canal calculation
    public static void root(char[][][] teethArray, int familyNum)
    {
        int cNum=0, bNum=0, mNum=0;
        double rInd1, rInd2, discrim;
        for(int famNum = 1; famNum <= familyNum; famNum++)
        {
            for (int i = 0; i < teethArray[famNum-1][0].length; i++) {
                if (teethArray[famNum-1][0][i] == 'C')
                    cNum++;
                if (teethArray[famNum-1][0][i] == 'B')
                    bNum++;
                if (teethArray[famNum-1][0][i] == 'M')
                    mNum++;

            }
            for (int i = 0; i < teethArray[famNum-1][1].length; i++) {
                if (teethArray[famNum-1][1][i] == 'C')
                    cNum++;
                if (teethArray[famNum-1][1][i] == 'B')
                    bNum++;
                if (teethArray[famNum-1][1][i] == 'M')
                    mNum++;

            }
        }
        //Bx^2 + Cx - M = 0
        //b^2 - 4ac
        discrim = Math.sqrt((cNum * cNum)-(4*bNum*-mNum));
        rInd1 = ((-cNum + discrim) / (2*bNum));
        rInd2 = ((-cNum - discrim) / (2*bNum));

        System.out.print( "One root canal at ");
        System.out.printf("%5.2f%n", rInd1);
        System.out.print("\nAnother at ");
        System.out.printf("%5.2f%n", rInd2);


    }

    //Prints all teeth and their owners
    public static void printTeeth(char[][][] teethArray, String[] names)
    {
        for(int i = 1; i < names.length; i++) {
            System.out.print(names[i] + "\nUppers:\t");
            for(int j = 0; j < 10; j++)
            {
                if(teethArray[i-1][0][j] != 'X' && teethArray[i-1][0][j] != 0)
                    System.out.print( (j+1) + ":" + teethArray[i-1][0][j] + "\t");

            }
            System.out.print("\nLowers:\t");
            for(int j = 0; j < 10; j++)
            {
                if(teethArray[i-1][1][j] != 'X' && teethArray[i-1][1][j] != 0)
                    System.out.print( (j+1) + ":" + teethArray[i-1][1][j] + "\t");

            }
            System.out.println();

        }

    }
}