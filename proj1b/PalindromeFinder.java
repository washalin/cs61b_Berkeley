public class PalindromeFinder {
    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("../library-sp19/data/words.txt");
        Palindrome palindrome = new Palindrome();
        CharacterComparator cc = new OffByN(2);

        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word)) {
                System.out.println(word);
            }

            if (palindrome.isPalindrome(word, cc)) {
                System.out.println("word off by 2");
                System.out.println(word);
            }
        }
    }
}
