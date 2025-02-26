import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
//banner3 style
//big style 
//7 rows for every letter


/**
 * The {@code AsciiArt} class provides functionality to generate ASCII art for letters,
 * numbers, and symbols. It uses pre-defined 7-row ASCII patterns to represent characters.
 */

public class AsciiArt {

    /** ASCII representation of the letter. */
    private static String[] A = {           "   ###    " ,                                  
                                            "  ## ##   " ,                                  
                                            " ##   ##  " ,                                  
                                            "##     ## " ,                                  
                                            "######### " ,                                  
                                            "##     ## " ,                                  
                                            "##     ## "};
        
    /** ASCII representation of the letter. */
    private static String[] B = {           "########  " ,                                  
                                            "##     ## " ,                                  
                                            "##     ## " ,                                  
                                            "########  " ,                                  
                                            "##     ## " ,                                  
                                            "##     ## " ,                                  
                                            "########  "};
        
    /** ASCII representation of the letter. */                                    
    private static String[] C = {           " ######  " , 
                                            "##    ## " , 
                                            "##       " , 
                                            "##       " , 
                                            "##       " , 
                                            "##    ## " , 
                                            " ######  "};
                        
    /** ASCII representation of the letter. */
    private static String[] D = {           "########  " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "########  "};

    /** ASCII representation of the letter. */
    private static String[] E = {           "######## " ,
                                            "##       " ,                                    
                                            "##       " ,                                    
                                            "######   " ,                                    
                                            "##       " ,                                    
                                            "##       " ,                                  
                                            "######## "};

    /** ASCII representation of the letter. */            
    private static String[] F = {           "######## " , 
                                            "##       " , 
                                            "##       " , 
                                            "######   " , 
                                            "##       " , 
                                            "##       " , 
                                            "##       "};

    /** ASCII representation of the letter. */    
    private static String[] G = {           " ######   " , 
                                            "##    ##  " , 
                                            "##        " , 
                                            "##   #### " , 
                                            "##    ##  " , 
                                            "##    ##  " , 
                                            " ######   "};

    /** ASCII representation of the letter. */             
    private static String[] H = {           "##     ## " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "######### " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "##     ## "};

    /** ASCII representation of the letter. */            
    private static String[] I = {           "#### " , 
                                            " ##  " , 
                                            " ##  " , 
                                            " ##  " , 
                                            " ##  " , 
                                            " ##  " , 
                                            "#### "};

    /** ASCII representation of the letter. */              
    private static String[] J = {           "      ## " , 
                                            "      ## " , 
                                            "      ## " , 
                                            "      ## " , 
                                            "##    ## " , 
                                            "##    ## " , 
                                            " ######  "};
                                                    
    /** ASCII representation of the letter. */  
    private static String[] K = {           "##    ## " , 
                                            "##   ##  " , 
                                            "##  ##   " , 
                                            "#####    " , 
                                            "##  ##   " , 
                                            "##   ##  " , 
                                            "##    ## "};
                                                    
    /** ASCII representation of the letter. */      
    private static String[] L = {           "##       " , 
                                            "##       " , 
                                            "##       " , 
                                            "##       " , 
                                            "##       " , 
                                            "##       " , 
                                            "######## "};
                                                    
    /** ASCII representation of the letter. */      
    private static String[] M = {           "##     ## " , 
                                            "###   ### " , 
                                            "#### #### " , 
                                            "## ### ## " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "##     ## "};
                                                    
    /** ASCII representation of the letter. */              
    private static String[] N = {           "##    ## " , 
                                            "###   ## " , 
                                            "####  ## " , 
                                            "## ## ## " , 
                                            "##  #### " , 
                                            "##   ### " , 
                                            "##    ## "};
                                                    
    /** ASCII representation of the letter. */      
    private static String[] O = {           " #######  " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            " #######  "};
                                                    
    /** ASCII representation of the letter. */                 
    private static String[] P = {           "########  " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "########  " , 
                                            "##        " , 
                                            "##        " , 
                                            "##        "};
                                                    
    /** ASCII representation of the letter. */  
    private static String[] Q = {           " #######  " ,
                                            "##     ## " ,
                                            "##     ## " ,
                                            "##     ## " , 
                                            "##  ## ## " , 
                                            "##    ##  " , 
                                            " ##### ## "};
                                                    
    /** ASCII representation of the letter. */      
    private static String[] R = {           "########  " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "########  " , 
                                            "##   ##   " , 
                                            "##    ##  " , 
                                            "##     ## "};
                                                    
    /** ASCII representation of the letter. */      
    private static String[] S = {           " ######  " , 
                                            "##    ## " , 
                                            "##       " , 
                                            " ######  " , 
                                            "      ## " , 
                                            "##    ## " , 
                                            " ######  "};
                                                    
    /** ASCII representation of the letter. */                      
    private static String[] T = {           "######## " , 
                                            "   ##    " , 
                                            "   ##    " , 
                                            "   ##    " , 
                                            "   ##    " , 
                                            "   ##    " , 
                                            "   ##    "};
                                                    
    /** ASCII representation of the letter. */      
    private static String[] U = {           "##     ## " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            " #######  "};
                                                    
    /** ASCII representation of the letter. */                      
    private static String[] V = {           "##     ## " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            "##     ## " , 
                                            " ##   ##  " , 
                                            "  ## ##   " , 
                                            "   ###    "};
                                                    
    /** ASCII representation of the letter. */              
    private static String[] W = {           "##      ## " , 
                                            "##  ##  ## " , 
                                            "##  ##  ## " , 
                                            "##  ##  ## " , 
                                            "##  ##  ## " , 
                                            "##  ##  ## " , 
                                            " ###  ###  "};
                                                    
    /** ASCII representation of the letter. */              
    private static String[] X = {           "##     ## " , 
                                            " ##   ##  " , 
                                            "  ## ##   " , 
                                            "   ###    " , 
                                            "  ## ##   " , 
                                            " ##   ##  " , 
                                            "##     ## "};
                                                    
    /** ASCII representation of the letter. */      
    private static String[] Y = {           "##    ## " , 
                                            " ##  ##  " , 
                                            "  ####   " , 
                                            "   ##    " , 
                                            "   ##    " , 
                                            "   ##    " , 
                                            "   ##    "};
                                                    
    /** ASCII representation of the letter. */      
    private static String[] Z = {           "######## " , 
                                            "     ##  " , 
                                            "    ##   " , 
                                            "   ##    " , 
                                            "  ##     " , 
                                            " ##      " , 
                                            "######## "};
                                                    
    /** ASCII representation of space. */  
    private static String[] space = {       "    " ,
                                            "    " ,
                                            "    " ,
                                            "    " ,
                                            "    " ,
                                            "    " ,
                                            "    "};
                                                    
    /** ASCII representation of the number. */  
    private static String[] one = {     "   ##   " , 
                                        " ####   " , 
                                        "   ##   " , 
                                        "   ##   " , 
                                        "   ##   " , 
                                        "   ##   " , 
                                        " ###### "};
                                                    
    /** ASCII representation of the number. */  
    private static String[] two = {     " #######  " ,
                                        "##     ## " ,
                                        "       ## " ,
                                        " #######  " ,
                                        "##        " ,
                                        "##        " ,
                                        "######### "};
                                                    
    /** ASCII representation of the number. */  
    private static String[] three = {   " #######  " ,
                                        "##     ## " ,
                                        "       ## " ,
                                        " #######  " ,
                                        "       ## " ,
                                        "##     ## " ,
                                        " #######  "};
                                                    
    /** ASCII representation of the number. */  
    private static String[] four = {    "##        " ,
                                        "##    ##  " ,
                                        "##    ##  " ,
                                        "##    ##  " ,
                                        "######### " ,
                                        "      ##  " ,
                                        "      ##  "};
                                                    
    /** ASCII representation of the number. */  
    private static String[] five = {    "######## " ,
                                        "##       " ,
                                        "##       " ,
                                        "#######  " ,
                                        "      ## " ,
                                        "##    ## " ,
                                        " ######  "};
                                                    
    /** ASCII representation of the number. */  
    private static String[] six = {     " #######  " ,
                                        "##     ## " ,
                                        "##        " ,
                                        "########  " ,
                                        "##     ## " ,
                                        "##     ## " ,
                                        " #######  "};
                                                    
    /** ASCII representation of the number. */                      
    private static String[] seven = {   "######## " ,
                                        "##    ## " ,
                                        "    ##   " ,
                                        "   ##    " ,
                                        "  ##     " ,
                                        "  ##     " ,
                                        "  ##     "};
                                                    
    /** ASCII representation of the number. */  
    private static String[] eight = {   " #######  " ,
                                        "##     ## " ,
                                        "##     ## " ,
                                        " #######  " ,
                                        "##     ## " ,
                                        "##     ## " ,
                                        " #######  "};
                                                    
    /** ASCII representation of the number. */  
    private static String[] nine = {    " #######  " ,
                                        "##     ## " ,
                                        "##     ## " ,
                                        " ######## " ,
                                        "       ## " ,
                                        "##     ## " ,
                                        " #######  "};
                                                    
    /** ASCII representation of the number. */  
    private static String[] zero = {    "  #####   " ,
                                        " ##   ##  " ,
                                        "##     ## " ,
                                        "##     ## " ,
                                        "##     ## " ,
                                        " ##   ##  " ,
                                        "  #####   "};
                                                    
    /** ASCII representation of the number. */          
    private static String[] excMark = { "#### " , 
                                        "#### " , 
                                        "#### " , 
                                        " ##  " , 
                                        "     " , 
                                        "#### " , 
                                        "#### "};
                                                    
    /** ASCII representation of the number. */         
    private static String[] nonchar = { "        " ,
                                        "        " ,
                                        "        " ,
                                        "####### " ,
                                        "        " ,
                                        "        " ,
                                        "        "};
                                                    
    /** ASCII representation of the number. */  
    private static String[] dot = {     "    " ,
                                        "    " ,
                                        "    " ,
                                        "    " ,
                                        "    " ,
                                        "### " ,
                                        "### "};
                                                    
    /** ASCII representation of the number. */          
    private static String[] plus = {    "       " ,
                                        "  ##   " ,
                                        "  ##   " ,
                                        "###### " ,
                                        "  ##   " ,
                                        "  ##   " ,
                                        "       "};


    /**
     * Converts a string into a list of ASCII patterns based on the defined character set.
     *
     * @param input The input string to be converted to ASCII art.
     * @return An {@code ArrayList} containing arrays of ASCII patterns corresponding to each character in the input string.
     */
    private static ArrayList<String[]> toMessage(String input){

        input = input.toUpperCase();
        ArrayList<String[]> message = new ArrayList<>();
            for(char c:input.toCharArray()){
                switch(c){
                    case 'A':
                        message.add(A);
                        break;
                    case 'B':
                        message.add(B);
                        break;
                    case 'C':
                        message.add(C);
                        break;
                    case 'Ç':
                        message.add(C);
                        break;
                    case 'D':
                        message.add(D);
                        break;
                    case 'E':
                        message.add(E);
                        break;
                    case 'F':
                        message.add(F);
                        break;
                    case 'G':
                        message.add(G);
                        break;
                    case 'Ğ':
                        message.add(G);
                        break;
                    case 'H':
                        message.add(H);
                        break;
                    case 'I':
                        message.add(I);
                        break;
                    case 'İ':
                        message.add(I);
                        break;
                    case 'J':
                        message.add(J);
                        break;
                    case 'K':
                        message.add(K);
                        break;
                    case 'L':
                        message.add(L);
                        break;
                    case 'M':
                        message.add(M);
                        break;
                    case 'N':
                        message.add(N);
                        break;
                    case 'O':
                        message.add(O);
                        break;
                    case 'Ö':
                        message.add(O);
                        break;
                    case 'P':
                        message.add(P);
                        break;
                    case 'Q':
                        message.add(Q);
                        break;
                    case 'R':
                        message.add(R);
                        break;
                    case 'S':
                        message.add(S);
                        break;
                    case 'Ş':
                        message.add(S);
                        break;
                    case 'T':
                        message.add(T);
                        break;
                    case 'U':
                        message.add(U);
                        break;
                    case 'Ü':
                        message.add(U);
                        break;
                    case 'V':
                        message.add(V);
                        break;
                    case 'W':
                        message.add(W);
                        break;
                    case 'X':
                        message.add(X);
                        break;
                    case 'Y':
                        message.add(Y);
                        break;
                    case 'Z':
                        message.add(Z);
                        break;
                    case ' ':
                        message.add(space);
                        break;
                    case '1':
                        message.add(one);
                        break;
                    case '2':
                        message.add(two);
                        break;
                    case '3':
                        message.add(three);
                        break;
                    case '4':
                        message.add(four);
                        break;
                    case '5':
                        message.add(five);
                        break;
                    case '6':
                        message.add(six);
                        break;
                    case '7':
                        message.add(seven);
                        break;
                    case '8':
                        message.add(eight);
                        break;
                    case '9':
                        message.add(nine);
                        break;
                    case '0':
                        message.add(zero);
                        break;
                    case '!':
                        message.add(excMark);
                        break;
                    case '.':
                        message.add(dot);
                        break;
                    case '+':
                        message.add(plus);
                        break;
                    default:
                        message.add(nonchar);
                        break;
                }
            }
            return message;
    }

    //************************************************************************************************************************
    
    /**
     * Animates a flow of letters from left to right until the input message is fully displayed.
     *
     * @param input The input string to be displayed as a flowing animation.
     */
    public static void letterflow(String input){
        ArrayList<String[]> message=toMessage(input);
        String[][] letters = {space,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z};
    
        int[] pointers = new int[message.size()];
        int currentpos = 0;
        while(true){
            clearConsole();
        
            for(int i = 0;i<7;i++){//row of a letter
                for(int j = 0;j<=currentpos;j++){//each letter
                    System.out.print(letters[pointers[j]][i]);
                }
                System.out.print("\n");
            }

            if(letters[pointers[currentpos]]!=message.get(currentpos))
                pointers[currentpos]++;
            else    
                currentpos++;
            if(currentpos==pointers.length){
                break;
            }

            System.out.print("\n");
            freeze(70);
        }
        freeze(500);
        clearConsole();
    }
    //-----------------------------------------------------------------------------------------------------------------------
    
    /**
     * Blinks the input message a specified number of times in a specified color.
     *
     * @param input  The input string to blink.
     * @param repeat The number of times to blink the input.
     * @param freq   The frequency (in milliseconds) for the blink effect.
     * @param color  The color code for the text display.
     */
    public static void blink(String input,int repeat,int freq,String color){
        ArrayList<String[]> message=toMessage(input);
        for(int blink = 0;blink<repeat;blink++){
            clearConsole();
            freeze(freq);
            for(int i = 0;i<7;i++){
                for(int j = 0;j<message.size();j++){
                    System.out.print(color+message.get(j)[i]);
                }
                System.out.print("\n");
            }
            freeze(freq);
        }  
        clearConsole();
    }
    //-----------------------------------------------------------------------------------------------------------------------
    
    /**
     * Displays a group introduction message with an animation effect.
     *
     * @param scanner A Scanner object for user input.
     */
    public static void groupIntro(Scanner scanner){
        String[] array = {  "   _____ _____   ____  _    _ _____    __ _____                    \n" ,
                            "  / ____|  __ \\ / __ \\| |  | |  __ \\  /_ | ____|                   \n" ,
                            " | |  __| |__) | |  | | |  | | |__) |  | | |__                     \n" ,
                            " | | |_ |  _  /| |  | | |  | |  ___/   | |___ \\                    \n" ,
                            " | |__| | | \\ \\| |__| | |__| | |       | |___) |                   \n" ,
                            "  \\_____|_|  \\_\\\\____/ \\____/|_|       |_|____/                    \n" ,
                            "  ______ ______ ______ ______ ______ ______ ______ ______ ______   \n" ,
                            " |______|______|______|______|______|______|______|______|______|  \n" ,
                            "     /\\           | |       \\ \\   / (_) (_) |           | |        \n" ,
                            "    /  \\   _ __ __| | __ _   \\ \\_/ / _   _| | _____  ___| |        \n" ,
                            "   / /\\ \\ | '__/ _` |/ _` |   \\   / | | | | |/ / __|/ _ \\ |        \n" ,
                            "  / ____ \\| | | (_| | (_| |    | |  | |_| |   <\\__ \\  __/ |        \n" ,
                            " /_/___ \\_\\_|  \\__,_|\\__,_|    |_| _ \\__,_|_|\\_\\___/\\___|_|   _    \n" ,
                            " |  __ \\(_) |                 (_)_(_)         (_) (_)        | |   \n" ,
                            " | |  | |_| | __ _ _ __ __ _   / _ \\ _____   _ _   _ _ __ ___| | __\n" ,
                            " | |  | | | |/ _` | '__/ _` | | | | |_  / | | | | | | '__/ _ \\ |/ /\n" ,
                            " | |__| | | | (_| | | | (_| | | |_| |/ /| |_| | |_| | | |  __/   < \n" ,
                            " |_____/|_|_|\\__,_|_|  \\__,_|  \\___//___|\\__, |\\__,_|_|  \\___|_|\\_\\\n" ,
                            "  ______    _   _ _        _____ _   _ _  __/ |                    \n" ,
                            " |  ____|  | | (_) |      / ____(_) (_) ||___/                     \n" ,
                            " | |__ __ _| |_ _| |__   | |  __  ___ | | __ _  ___                \n" ,
                            " |  __/ _` | __| | '_ \\  | | |_ |/ _ \\| |/ _` |/ _ \\               \n" ,
                            " | | | (_| | |_| | | | | | |__| | (_) | | (_| |  __/               \n" ,
                            " |_|  \\__,_|\\__|_|_| |_|  \\_____|\\___/|_|\\__, |\\___|               \n" ,
                            "                                          __/ |                    \n" ,
                            "  _   _       _    _   _            _    |___/                     \n" ,
                            " (_)_(_)     | |  (_) (_)     /\\   | |     | |                     \n" ,
                            "  / _ \\ _   _| | ___   _     /  \\  | | __ _| |__   __ _ ___        \n" ,
                            " | | | | | | | |/ / | | |   / /\\ \\ | |/ _` | '_ \\ / _` / __|       \n" ,
                            " | |_| | |_| |   <| |_| |  / ____ \\| | (_| | |_) | (_| \\__ \\       \n" ,
                            "  \\___/ \\__, |_|\\_\\\\__,_| /_/    \\_\\_|\\__,_|_.__/ \\__,_|___/       \n" ,
                            "  _    _ __/ |            _                 ____       _)_) _ _    \n" ,
                            " | |  | |___/            | |               |  _ \\     | |  (_) |   \n" ,
                            " | |  | | __ _ _   _ _ __| | ____ _ _ __   | |_) | ___| | ___| |_  \n" ,
                            " | |  | |/ _` | | | | '__| |/ / _` | '_ \\  |  _ < / _ \\ |/ / | __| \n" ,
                            " | |__| | (_| | |_| | |  |   < (_| | | | | | |_) |  __/   <| | |_  \n" ,
                            "  \\____/ \\__, |\\__,_|_|  |_|\\_\\__,_|_| |_| |____/ \\___|_|\\_\\_|\\__| \n" ,
                            "          __/ |                                                    \n" ,
                            "         |___/                                                     \n"};

        for(int i = 0;i<array.length;i++){
            clearConsole();
            for(int j = 0;j<=i;j++){
                System.out.print(array[j]);
            }
            freeze(100);
        }
            
        System.out.print("\n\nPress enter to move to the main menu ");
        scanner.nextLine();
        clearConsole();
        for(int i = array.length-1;i>=0;i--){
            for(int j = 0;j<=i;j++){
                System.out.print(array[j]);
            }
            freeze(100);
            clearConsole();
        }
        freeze(325);
    }
    //-----------------------------------------------------------------------------------------------------------------------
    
    /**
     * Prints the input message without an animation in ASCII form.
     *
     * @param input The input string to print.
     */
    public static void standardPrint(String input){
        ArrayList<String[]> message=toMessage(input);
        for(int i = 0;i<7;i++){
            for(int j = 0;j<message.size();j++){
                System.out.print(message.get(j)[i]);
            }
            System.out.print("\u001B[0m"+"\n");
        }
        
    }
    /**
     * Prints the input message in a standard font with a specified color, skipping specified rows.
     *
     * @param input   The input string to print.
     * @param color   The color code for the text display.
     * @param deleted The number of rows to skip to be able to use an animation.
     */
    public static void standardPrint(String input,String color,int deleted){
        ArrayList<String[]> message=toMessage(input);
        for(int i = deleted;i<7;i++){
            for(int j = 0;j<message.size();j++){
                System.out.print(color+message.get(j)[i]);
            }
            System.out.print("\u001B[0m"+"\n");
        }
    }
    //-----------------------------------------------------------------------------------------------------------------------
    
    /**
     * Animates text sliding in from left to right.
     *
     * @param input The input string to animate.
     */
    public static void leftToRight(String input){
        ArrayList<String[]> message=toMessage(input);
        String[] closingText = {"","","","","","",""};
        for(String[] letter:message){
            for(int i = 0;i<7;i++){
                closingText[i] += letter[i];
            }
        }

        int width = closingText[0].length();
        int current_width = width-1;
        while(current_width>=0){
            clearConsole();

            for(int rows = 0;rows<7;rows++){
                for(int i = current_width;i<width;i++){
                    System.out.print(closingText[rows].charAt(i));
                }
                System.err.println();
            }
            freeze(25);   
            current_width--;
        }
        freeze(500);
        clearConsole();
    }
    //-----------------------------------------------------------------------------------------------------------------------
    
    /**
     * Displays a welcome message for a user.
     *
     * @param firstTime Indicates if this is the user's first time logging in to the system.
     * @param name      The name of the user.
     */
    public static void welcomeBack(boolean firstTime,String name){
        if(firstTime){
            standardPrint("welcome");
            System.out.println();
            standardPrint(name);
            System.out.println();
        }
        else{
            standardPrint("welcome back");
            System.out.println();
            standardPrint(name);
            System.out.println();
        }
    }
    //-----------------------------------------------------------------------------------------------------------------------
    
    /**
     * Displays a "goodbye" animation with sliding letters.
     *
     * @param input The input string to animate.
     */
    public static void goodBye(String input){
        ArrayList<String[]> message = toMessage(input);
        int width=getwidth(message);
        int spaceWidth = width - message.get(message.size()-1)[0].length();
        int currentSpaceWidth = spaceWidth;
        int pointer = 0;
        int initialTime = 9;
        String[] leftLetters = {"","","","","","",""};
        while(true){
            clearConsole();
            if(message.get(pointer).equals(space)){
                spaceWidth -= message.get(pointer)[0].length(); 
                currentSpaceWidth = spaceWidth;
                for(int i = 0;i<7;i++){
                    leftLetters[i] += message.get(pointer)[i];
                }
                pointer++;
            }
            for(int i = 0;i<7;i++){
                System.out.print(leftLetters[i]);
                for(int j = 0;j<currentSpaceWidth;j++){
                    System.out.print(" ");
                }
                System.out.println(message.get(pointer)[i]);
            }
            if(spaceWidth==0)
                break;
            freeze(initialTime);
            currentSpaceWidth--;
            if(currentSpaceWidth==0){
                spaceWidth -= message.get(pointer)[0].length(); 
                currentSpaceWidth = spaceWidth;
                for(int i = 0;i<7;i++){
                    leftLetters[i] += message.get(pointer)[i];
                }
                pointer++;
                initialTime += 3;
            }
        }
        freeze(500);
        clearConsole();
    }
    //-----------------------------------------------------------------------------------------------------------------------
    
    /**
     * a colorful ascii print for a firm management system. The colors change smoothly until the user enters an input.
     * 
     * @param scanner A Scanner object for user input.
     */
    public static void firmManagement(Scanner scanner){        

        int[][] colors = {
            {255, 0, 0},    // Kırmızı
            {255, 255, 0},  // Sarı
            {0, 255, 0},    // Yeşil
            {0, 255, 255},  // Cyan
            {0, 0, 255},    // Mavi
            {255, 0, 255},  // Magenta
            {255, 255, 255} // Beyaz
        };
        
        boolean outerloop = true;
        int i = -1;
        int j = -1;
        int[] startColor=colors[0];
        int[] endColor=colors[1];
        int steps = 100;

        while (true) {
            if(outerloop){
                i++;
                j=-1;
                outerloop=false;
                if(i==7){
                    i=0;
                }
                startColor = colors[i];
                endColor = colors[(i + 1) % colors.length];
            }
            j++;
            if(j==steps)
                outerloop=true;
                
            int r = interpolate(startColor[0], endColor[0], j, steps);
            int g = interpolate(startColor[1], endColor[1], j, steps);
            int b = interpolate(startColor[2], endColor[2], j, steps);

            try {
                if (System.in.available() > 0) {  // input taken
                    scanner.nextLine();
                    break;
                } else {
                    // no input
                    clearConsole();
                    System.out.printf("\u001b[38;2;%d;%d;%dm"  +        "  ______ _____ _____  __  __    __  __          _   _          _____ ______ __  __ ______ _   _ _______  \n" + 
                                                                        " |  ____|_   _|  __ \\|  \\/  |  |  \\/  |   /\\   | \\ | |   /\\   / ____|  ____|  \\/  |  ____| \\ | |__   __| \n" + 
                                                                        " | |__    | | | |__) | \\  / |  | \\  / |  /  \\  |  \\| |  /  \\ | |  __| |__  | \\  / | |__  |  \\| |  | |    \n" + 
                                                                        " |  __|   | | |  _  /| |\\/| |  | |\\/| | / /\\ \\ | . ` | / /\\ \\| | |_ |  __| | |\\/| |  __| | . ` |  | |    \n" + 
                                                                        " | |     _| |_| | \\ \\| |  | |  | |  | |/ ____ \\| |\\  |/ ____ \\ |__| | |____| |  | | |____| |\\  |  | |    \n" + 
                                                                        " |_|    |_____|_|  \\_\\_|  |_|  |_|__|_/_/  __\\_\\_| \\_/_/__ _\\_\\_____|______|_|  |_|______|_| \\_|  |_|    \n" + 
                                                                        "                              / ____\\ \\   / / ____|__   __|  ____|  \\/  |                                \n" + 
                                                                        "                             | (___  \\ \\_/ / (___    | |  | |__  | \\  / |                                \n" + 
                                                                        "                              \\___ \\  \\   / \\___ \\   | |  |  __| | |\\/| |                                \n" + 
                                                                        "                              ____) |  | |  ____) |  | |  | |____| |  | |                                \n" + 
                                                                        "                             |_____/   |_| |_____/   |_|  |______|_|  |_|                                \n" + 
                                                                        "                                                                                                         \n" + 
                                                                        "                                                                                                         "+"\u001b[0m",r,g,b);
                    System.out.println();
                    System.out.printf("\u001b[38;2;%d;%d;%dm"+"\t\t\t\t\tPress enter to continue"+"\u001b[0m",r,g,b);
                    freeze(10);
                    clearConsole();
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //-----------------------------------------------------------------------------------------------------------------------
    
    /**
    * Displays a burning animation for a given name. The animation simulates flames rising 
    * and progressively "burning away" the name until it disappears.
    *
    * @param name The string to be displayed and animated as if it is burning.
    */
    public static void burn(String name){
        final String[] FIRE_COLORS = {
            "\u001B[31m", // Kırmızı
            "\u001B[33m", // Sarı
            "\u001B[91m", // Açık Kırmızı
            "\u001B[93m",  // Açık Sarı
            "\u001B[37m"   //beyaz
        };
        final String RESET = "\u001B[0m";
        Random random = new Random();
        int flameHeight = 10; 
        int width = getwidth(name);
        int deleted = 0;
        int checker = -5;
        boolean ended = false;

        while(checker<8){
            clearConsole();
            for (int y = 0; y < flameHeight; y++) {
                StringBuilder line = new StringBuilder(" ".repeat(width));
                for (int x = 0;x<width;x++) {
                    if (random.nextInt(100) > 60) { // Rastgele yoğunluk
                        String color = FIRE_COLORS[random.nextInt(FIRE_COLORS.length)];
                        line.setCharAt(x, '*');
                        System.out.print(color + "*" + RESET);
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            standardPrint(name,FIRE_COLORS[0],deleted);
            System.out.print(RESET);
            checker++;
            if(checker==3&&!ended){
                deleted++;
                if(deleted!=8)
                    flameHeight++;
                checker=0;
            }   
            if(deleted==8&&!ended){
                ended=true; 
            }
            freeze(120);
        }
        clearConsole();
    }
    //-----------------------------------------------------------------------------------------------------------------------

    /**
     * Displays a blinking animation followed by a final display of a "hired" message with a green color.
     *
     * @param name The name to display as part of the "hired" message.
     */
    public static void hired(String name){
        String green="\u001B[32m";
        name = "+"+name;
        blink(name,4,230,green);
        standardPrint(name, green, 0);
        
    }

    //************************************************************************************************************************
    
    /**
     * Clears the terminal screen based on the operating system.
     * Supports both Windows and non-Windows environments.
     */
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Pauses the program for a specified duration.
     *
     * @param mil The duration to pause in milliseconds.
     */
    public static void freeze(int mil){
        try{
            Thread.sleep(mil);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Calculates the total width of the string in terms of the sum of all character widths.
     *
     * @param input The string whose width in ASCII form is to be calculated.
     * @return The total width of the string in ASCII form.
     */
    public static int getwidth(String input){
        ArrayList<String[]> message = toMessage(input);
        int width=0;
        for(String[] letter:message){
            width+=letter[0].length();
        }
        return width;
    }

    /**
     * Calculates the total width of the message represented as an ArrayList of strings.
     *
     * @param message The message as a list of string arrays, where each array represents a letter.
     * @return The total width of the message in ASCII form.
     */
    public static int getwidth(ArrayList<String[]> message){
        int width=0;
        for(String[] letter:message){
            width+=letter[0].length();
        }
        return width;
    }

    /**
     * Performs linear interpolation between two values over a number of steps.
     *
     * @param start The starting value.
     * @param end The ending value.
     * @param step The current step of interpolation.
     * @param totalSteps The total number of steps.
     * @return The interpolated value for the current step.
     */
    private static int interpolate(int start, int end, int step, int totalSteps) {
        return start + (end - start) * step / totalSteps;
    }
}
