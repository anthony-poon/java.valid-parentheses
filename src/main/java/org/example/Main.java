package org.example;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println(assertFalse(""));
        System.out.println(assertTrue("{}"));
        System.out.println(assertFalse("{{}"));
        System.out.println(assertFalse("}"));
        System.out.println(assertFalse("(}"));
        System.out.println(assertTrue("(({}))"));
        System.out.println(assertFalse("(({)})"));
        System.out.println(assertTrue("((0{}0)0)"));
        System.out.println(assertTrue("00()")); // <--- This failed
    }

    public static boolean validParentheses(String input) {
        // TODO: implement this method

        //Trigger condition(false): the following bracket, if a closed bracket; must be the same as the last opening bracket
        //if condition not triggered; return true
        //check first bracket, if close bracket then return false
        if (input == null || input.length() == 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char first = input.charAt(0);
        if(first == ')' || first == ']' || first == '}'){ //if first char is closed, it's false
            return false;
        } else {
            stack.push(first); //add c to stack
        }

        for(int i = 1; i < input.length(); i++){ //loop through each char in the String
            //get the last open bracket stored
            char current = input.charAt(i);
            //check if current is a open bracket
            boolean isOpenBracket = current =='(' || current =='[' || current =='{';
            boolean isCloseBracket = current ==')' || current ==']' || current =='}';
            if(!isOpenBracket && !isCloseBracket){ //check for non-brackets, skip if true
                continue;
            }
            if(isOpenBracket){ //if c1 is open, move to next char
                stack.push(current); //if open bracket, push to stack
                continue;
            }

            // otherwise we check each closed bracket matches corres. open bracket
            char last = stack.pop(); // get previous open bracket and compare the 3 bracket cases
            if(current == ')' && last != '(') {
                return false;
            }

            if(current == ']' && last != '[') {
                return false;
            }

            if(current == '}' && last != '{') {
                return false;
            }
        }
        //check for any incomplete brackets, stack still has open
        return stack.isEmpty();
    }

    // Method that assist with testing and makes output pretty
    private static String assertTrue(String input) {
        if (validParentheses(input)) {
            return "'%s' test passed".formatted(input);
        }
        throw new RuntimeException("Expecting '%s' return true, but got false".formatted(input));
    }

    private static String assertFalse(String input) {
        if (!validParentheses(input)) {
            return "'%s' test passed".formatted(input);
        }
        throw new RuntimeException("Expecting '%s' return false, but got true".formatted(input));
    }
}