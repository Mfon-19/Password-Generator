import java.util.Scanner;

public class PasswordGenerator {
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("Hello! Welcome to Password Generator!");

        boolean another = true;

        do{
            System.out.println("Choose one of the options below to get started:\n");
            System.out.println("1. Check password strength");
            System.out.println("2. Create a password");
            int choice = scanner.nextInt();

            if(choice == 1){
                System.out.println("Enter password to check: ");
                String password = scanner.next();
                int score = checkPasswordStrength(password);

                if(score == 1) System.out.println("Very bad password :(. Definitely change it.");
                if(score == 2) System.out.println("Bad password :(. Definitely change it.");
                if(score == 3) System.out.println("Password is too simple. Recommend changing it.");
                if(score == 4) System.out.println("Password is good :).");
                if(score == 5) System.out.println("Password is amazing! :D");

                System.out.println("\nDo you want to go again (y/n)?");
                another = scanner.next().equalsIgnoreCase("y");
            }
            else if(choice == 2){
                System.out.println("Please answer the questions down below.\n");
                String string = createPassword();
                System.out.printf("Your generated password is: %s", string);

                System.out.println("\nDo you want to go again (y/n)?");
                another = scanner.next().equalsIgnoreCase("y");
            }
        }while(another);
    }

    private static int checkPasswordStrength(String password){
        //initialize score and set flags for checks
        int score = password.length() > 7 ? 1 : 0;
        boolean upperCase = false, lowerCase = false, digit = false, symbol = false;

        for(int i = 0; i < password.length(); i++){
            //has at least one upper case letter
            if(Character.isUpperCase(password.charAt(i)) && !upperCase){
                score++;
                upperCase = true;
            }

            //has at least one lower case letter
            if(Character.isLowerCase(password.charAt(i)) && !lowerCase) {
                score++;
                lowerCase = true;
            }

            //has at least one digit
            if(Character.isDigit(password.charAt(i)) && !digit) {
                score++;
                digit = true;
            }

            //has at least one symbol
            if((inRange(33, 47, password.charAt(i))
                    || inRange(58, 64, password.charAt(i))
                    || inRange(91, 96, password.charAt(i))
                    || inRange(123, 126, password.charAt(i))) && !symbol) {

                score++;
                symbol = true;
            }
        }

        return score;
    }

    private static boolean inRange(int minValue, int maxValue, int value) {
        if (maxValue < minValue) {
            return false;
        }
        return (value >= minValue) && (value <= maxValue);
    }

    private static String createPassword(){
        boolean includeUpper, includeLower, includeDigit, includeSymbol;
        int length, requiredLength = 0;
        String string;

        //get user choices
        do {
            includeUpper = includeUpper();
            includeLower = includeLower();
            includeDigit = includeDigit();
            includeSymbol = includeSymbol();

            //if the user did not select any option
            if(!includeUpper && !includeLower & !includeDigit && !includeSymbol)
                System.out.println("You chose no on all options. You must select at least one option\n");

        }while(!includeUpper && !includeLower & !includeDigit && !includeSymbol);

        //the minimum length required to meet user's demands
        if(includeUpper) requiredLength++;
        if(includeLower) requiredLength++;
        if(includeDigit) requiredLength++;
        if(includeSymbol) requiredLength++;

        length = getUserLength(requiredLength);

        Password password = new Password(includeUpper, includeLower, includeDigit, includeSymbol, length);
        string = password.createPassword();

        return string;
    }

    private static boolean includeUpper(){
        boolean include;
        String choice;

        //does the user want to include an upper case?
        do{
            System.out.println("Do you want to include upper case letters (y/n)? i.e. 'A, B, C,...'");
            choice = scanner.next();
            include = choice.equalsIgnoreCase("y");
            if(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"))
                System.out.println("Invalid input");

        }while(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));

        return include;
    }

    private static boolean includeLower(){
        boolean include;
        String choice;

        //does the user want to include an upper case?
        do{
            System.out.println("Do you want to include Lower Case letters (y/n)? i.e. 'a, b, c,...'");
            choice = scanner.next();
            include = choice.equalsIgnoreCase("y");

            if(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"))
                System.out.println("Invalid input");

        }while(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));

        return include;
    }

    private static boolean includeDigit(){
        boolean include;
        String choice;

        //does the user want to include an upper case?
        do{
            System.out.println("Do you want to include numbers (y/n)? i.e. '0, 1, 2,...'");
            choice = scanner.next();
            include = choice.equalsIgnoreCase("y");

            if(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"))
                System.out.println("Invalid input");

        }while(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));

        return include;
    }

    private static boolean includeSymbol(){
        boolean include;
        String choice;

        //does the user want to include an upper case?
        do{
            System.out.println("Do you want to include symbols (y/n)? i.e. '!, #, *,...'");
            choice = scanner.next();
            include = choice.equalsIgnoreCase("y");

            if(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"))
                System.out.println("Invalid input");

        }while(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));

        return include;
    }

    private static int getUserLength(int requiredLength){
        int length;
        do{
            System.out.println("What do you want the length of your password to be? ");
            length = scanner.nextInt();

            if(length < 2) System.out.println("The password length has to be greater than 2");
            if(length < requiredLength) System.out.printf("The password length has to be greater than %d", requiredLength);

        }while(length < 2 && length < requiredLength);

        return length;
    }
}
