package org.task5;
import java.util.Random;

public class PasswordGenerator {

    public String generatePassword() {
        char[] password = new char[8];
        Random random = new Random();
        char[] numbers = {
                '0','1','2','3','4',
                '5','6','7','8','9'
        };
        char[] letters = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G',
                'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                'V', 'W', 'X', 'Y', 'Z'
        };
        char[] symbols = {'_', '$', '#', '%'};

        for (int j = 0; j < 4; j++){
            password[j] = numbers[random.nextInt(numbers.length)];
        }
        for (int k = 4; k < 6; k++){
            password[k] = letters[random.nextInt(letters.length)];
        }
        for (int d = 6; d < 8; d++){
            password[d] = symbols[random.nextInt(symbols.length)];
        }

        for (int i = 0; i < password.length; i++) {
            int randomIndex = random.nextInt(password.length);

            char temp = password[i];
            password[i] = password[randomIndex];
            password[randomIndex] = temp;
        }

        return new String(password);

    }
}
