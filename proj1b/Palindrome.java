public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        //int index = 0;
        Deque<Character> chars = new ArrayDeque<>();

//        while(Character.isLetter(word.charAt(index))) {
//            chars.addLast(word.charAt(index));
//            index++;
//        }
        for (int i = 0; i < word.length(); i++) {
            chars.addLast(word.charAt(i));
        }
        return chars;
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        boolean isPal = true;
        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                isPal = false;
                break;
            }
        }
        return isPal;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        boolean ispal = true;
        for (int i = 0; i < word.length() / 2; i++) {
            if (!cc.equalChars(word.charAt(word.length() - i - 1), word.charAt(i))) {
                ispal = false;
                break;
            }
        }
        return ispal;
    }
}
