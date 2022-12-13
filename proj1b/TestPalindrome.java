import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        Palindrome palin = new Palindrome();

        assertFalse(palin.isPalindrome("test"));

        assertTrue(palin.isPalindrome("noon"));

        assertTrue(palin.isPalindrome("racecar"));

        assertTrue(palin.isPalindrome("aaaaa"));

        assertTrue(palin.isPalindrome("cac"));

        assertTrue(palin.isPalindrome(""));

        assertTrue(palin.isPalindrome("%"));


        CharacterComparator off = new OffByOne();

        assertTrue(palin.isPalindrome("flake", off));

        assertTrue(palin.isPalindrome("", off));

        assertTrue(palin.isPalindrome("a", off));

        assertFalse(palin.isPalindrome("aba", off));

    }
}
