package org.task5;
import java.util.Random;

public class PasswordGenerator {

    // Character pools and group sizes never change between calls, so they're class-level
    // constants now instead of being reallocated on every generatePassword() call.
    private static final char[] DIGITS = {
            '0','1','2','3','4',
            '5','6','7','8','9'
    };
    private static final char[] LETTERS = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z'
    };
    private static final char[] SYMBOLS = {'_', '$', '#', '%'};

    private static final int DIGIT_COUNT = 4;
    private static final int LETTER_COUNT = 2;
    private static final int SYMBOL_COUNT = 2;
    private static final int PASSWORD_LENGTH = DIGIT_COUNT + LETTER_COUNT + SYMBOL_COUNT;

    // group ids let us track which pool each character came from even after shuffling,
    // instead of re-deriving it from the character itself
    private static final int DIGIT_GROUP = 0;
    private static final int LETTER_GROUP = 1;
    private static final int SYMBOL_GROUP = 2;

    // reused across calls instead of a new Random() being created every generatePassword() call
    private final Random random = new Random();

    public String generatePassword() {
        char[] password = new char[PASSWORD_LENGTH];
        int[] groupOf = new int[PASSWORD_LENGTH];

        fillGroup(password, groupOf, 0, DIGIT_COUNT, DIGITS, DIGIT_GROUP);
        fillGroup(password, groupOf, DIGIT_COUNT, LETTER_COUNT, LETTERS, LETTER_GROUP);
        fillGroup(password, groupOf, DIGIT_COUNT + LETTER_COUNT, SYMBOL_COUNT, SYMBOLS, SYMBOL_GROUP);

        // Rule: no two characters from the same group may end up adjacent. A single random
        // shuffle doesn't guarantee that, so shuffle-and-check, retrying on violation; the
        // array is only 8 elements, so a few retries are effectively free.
        do {
            shuffle(password, groupOf);
        } while (hasAdjacentSameGroup(groupOf));

        return new String(password);
    }

    private void fillGroup(char[] password, int[] groupOf, int start, int count, char[] pool, int groupId) {
        for (int i = start; i < start + count; i++) {
            password[i] = pool[random.nextInt(pool.length)];
            groupOf[i] = groupId;
        }
    }

    private void shuffle(char[] password, int[] groupOf) {
        for (int i = 0; i < password.length; i++) {
            int randomIndex = random.nextInt(password.length);

            char tempChar = password[i];
            password[i] = password[randomIndex];
            password[randomIndex] = tempChar;

            // groupOf must be permuted identically to password so it keeps tracking
            // which group each shuffled character came from
            int tempGroup = groupOf[i];
            groupOf[i] = groupOf[randomIndex];
            groupOf[randomIndex] = tempGroup;
        }
    }

    private boolean hasAdjacentSameGroup(int[] groupOf) {
        for (int i = 0; i < groupOf.length - 1; i++) {
            if (groupOf[i] == groupOf[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
