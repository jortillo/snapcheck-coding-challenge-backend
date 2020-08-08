import java.lang.Math; 
import java.util.Scanner; 

public class Encryption
{ 
    public static void main(String args[]) 
    { 
        Scanner sc = new Scanner(System.in); 
        System.out.println("Input text to be encrypted");
        String input = sc.nextLine();
        sc.close();

        //eliminate all whitespaces from input string
        input = input.replaceAll("\\s", "");
        //check for contraints on input string, ascii range[a-z] only and length of 1-81
        validString(input);

        //grab length of string and determine SQRT of length
        int inputLength = input.length();
        int rows = (int) Math.floor(Math.sqrt(inputLength));
        int columns = (int) Math.ceil(Math.sqrt(inputLength));

        System.out.println("Length of '" + input + "' is " + inputLength); 
        System.out.println("Square root of " + inputLength + " is between " + rows + " and " + columns); 
        //if rows*columns < length of string, increment row by 1
        if(rows*columns < inputLength){
            rows = rows + 1;
        }
        System.out.println("\nEncoded Message:\n" + encryption(input, rows, columns));
    } 

    //function to encrypt a message
    public static String encryption(String s, int row, int col){
        char[][] gridArray = convertToGrid(s, row, col);
        String encoded = "";

        //concat the characters in a column, inserting a space, and then concating the next column and inserting a space, and so on
        for(int i = 0; i < col; i++){
            for(int j = 0; j < row; j++){

                //don't concat empty element
                if(gridArray[j][i] != 0){
                    encoded = encoded.concat(Character.toString(gridArray[j][i]));  
                }
            }
            encoded = encoded.concat(" ");
        }
        return encoded;
    }

    //function to convert input string into row x column grid layout
    public static char[][] convertToGrid(String s, int row, int col){
        System.out.println("\nInput String in row x column grid layout:\n");
        char[][] gridArray = new char[row][col];
        //traverse through each char in String
        int c = 0;

        //turn input string into row x column grid layout
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(c != s.length()){
                    gridArray[i][j] = s.charAt(c);
                    System.out.print(gridArray[i][j]);
                //increment c to get next char
                    c++;
                }
            }
            System.out.println("");
        }
        return gridArray;
    }

    //function to check for a valid string, ascii range[a-z] only and length of 1-81
    public static void validString(String s){
        for(int i = 0; i < s.length(); i++){
            if(!(s.charAt(i) >= 97 && s.charAt(i) <= 122)){
                throw new IllegalArgumentException("Invalid input, string can only contain characters in range ascii[a-z]");
            }
            else if(s.length() < 1 || s.length() > 81){
                throw new IllegalArgumentException("Invalid input, string length must be between 1 and 81");
            }
        }
    }
} 