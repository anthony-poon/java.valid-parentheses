package org.example;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static boolean validParentheses(String input) {
        // TODO: implement this method

        //Trigger condition(false): the following bracket, if a closed bracket; must be the same as the last opening bracket
        //if condition not triggered; return true
        //check first bracket, if close bracket then return false

        Stack<Character> stack = new Stack<>();
        char c = input.charAt(0);
        if(c == ')' || c == ']' || c == '}'){ //if first char is closed, it's false
            return false;
        }else{
            stack.push(c); //add c to stack
        }

        for(int i=1; i<input.length(); i++){ //loop through each char in the String
            //get the last open bracket stored
            char c1 = input.charAt(i);
            //check if c1 is a open bracket
            if(c1!='(' && c1!='[' && c1!='{' && c1!=')' && c1!=']' && c1!='}'){ //check for non-brackets, skip if true
                continue;
            }
            if(c1 == '(' || c1 == '[' || c1 == '{'){ //if c1 is open, move to next char
                stack.push(c1); //if open bracket, push to stack
                continue;
            }else //otherwise we check each closed bracket matches corres. open bracket
                c = stack.pop(); //get previous open bracket and compare the 3 bracket cases
            if(c1==')'){
                if(c=='('){
                    continue;
                }else return false;

            }else if(c1==']'){
                if(c=='['){
                    continue;
                }else return false;

            }else if(c1=='}'){
                if(c=='{'){
                    continue;
                }else return false;
            }
        }
        //check for any incomplete brackets, stack still has open
        if(stack.isEmpty()) {
            return true;
        } else return false;
    }
}