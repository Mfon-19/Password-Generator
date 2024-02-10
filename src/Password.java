import java.util.*;
public class Password {
    //characteristics of included password
    private static boolean includeUpper, includeLower, includeDigit, includeSymbol;

    //length of password
    private static int length;
    private static final Random random = new Random();

    public Password(boolean includeUpper, boolean includeLower, boolean includeDigit, boolean includeSymbol, int length){
        Password.includeUpper = includeUpper;
        Password.includeLower = includeLower;
        Password.includeDigit = includeDigit;
        Password.includeSymbol = includeSymbol;
        Password.length = length;
    }

    public String createPassword(){
        String password = "";
        int[] spaces = allocateSpaces();

        password += populateUpper(spaces[0]);
        password += populateLower(spaces[1]);
        password += populateDigit(spaces[2]);
        password += populateSymbols(spaces[3]);


        List<Character> chars = new ArrayList<>();
        for (char c : password.toCharArray()) {
            chars.add(c);
        }

        // Shuffle the list
        Collections.shuffle(chars);

        // Convert the shuffled list back to a string
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }
        password = sb.toString();

        return password;
    }

    //decide how many spaces each characteristic gets
    private static int[] allocateSpaces(){
        int pasLen = length;

        int[] spaces = new int[]{0, 0, 0, 0};

        while(pasLen != 0){
            if(includeUpper && pasLen != 0) {
                spaces[0]++; // 2
                pasLen--; //0
            }
            if(includeLower && pasLen != 0) {
                spaces[1]++; // 1
                pasLen--; // 3
            }
            if(includeDigit && pasLen != 0) {
                spaces[2]++; // 1
                pasLen--; // 2
            }
            if(includeSymbol && pasLen != 0) {
                spaces[3]++; // 1
                pasLen--; // 1
            }
        }

        return spaces;
    }

    private static String populateUpper(int spaces){
        StringBuilder result = new StringBuilder();

        if(spaces == 0) return "";

        for(int i = 0; i < spaces; i++){
            String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            result.append(upper.charAt(random.nextInt(upper.length())));
        }
        return result.toString();
    }

    private static String populateLower(int spaces){
        StringBuilder result = new StringBuilder();

        if(spaces == 0) return "";

        for(int i = 0; i < spaces; i++){
            String lower = "abcdefghijklmnopqrstuvwxyz";
            result.append(lower.charAt(random.nextInt(lower.length())));
        }

        return result.toString();
    }

    private static String populateDigit(int spaces){
        StringBuilder result = new StringBuilder();

        if(spaces == 0) return "";

        for(int i = 0; i < spaces; i++){
            result.append(random.nextInt(10));
        }

        return result.toString();
    }

    private static String populateSymbols(int spaces){
        StringBuilder result = new StringBuilder();

        if(spaces == 0) return "";

        for(int i = 0; i < spaces; i++){
            String symbols = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
            result.append(symbols.charAt(random.nextInt(symbols.length())));
        }

        return result.toString();
    }
}
