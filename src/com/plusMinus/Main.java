package com.plusMinus;

import java.util.Arrays;
import java.util.HashMap;

public class Main {

//    Option2:
//    Coding question from my end :
//    Given two strings s and t, return true if t is an anagram
//    of s, and false otherwise.
//    An Anagram is a word or phrase formed by rearranging the letters of a different
//    word or phrase, typically using all the original letters exactly once.

    public static void main(String[] args) {

        String s = "fgds";
        String t = "sdfg";


        System.out.println(anagramMap(s,t));
        System.out.println(anagramSort(s,t));
        System.out.println(anagramCount(s,t));

    }

    public static boolean anagramMap(String s, String t){

        HashMap<Character, Integer> ourMap= new HashMap<>();
        HashMap<Character, Integer> ourSecondMap= new HashMap<>();

        for(int i = 0; i < s.length(); i++ ){
            if (ourMap.containsKey(s.charAt(i))){
                ourMap.put(s.charAt(i), ourMap.get(s.charAt(i)) + 1);
            }
            else{
                ourMap.put(s.charAt(i), 1);
            }
        }

        for(int i = 0; i < t.length(); i++ ){
            if (ourSecondMap.containsKey(t.charAt(i))){
                ourSecondMap.put(t.charAt(i), ourSecondMap.get(t.charAt(i)) + 1);
            }
            else{
                ourSecondMap.put(t.charAt(i), 1);
            }
        }

        if (ourMap.size() != ourSecondMap.size()) {
            return false;
        }
        return ourMap.entrySet().stream()
                .allMatch(e -> e.getValue().equals(ourSecondMap.get(e.getKey())));
    }

    public static boolean anagramSort(String s, String t){

        /*
        This is the sorting solution I was originally thinking of doing. It would have and O(n log n) time complexity
        due to the sort methods being used.

        This method will take in each string, assign them to character arrays, sort the arrays by value, then compare
        the arrays. If they are the same, they are anagrams.
         */



        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();


        Arrays.sort(sArr);
        Arrays.sort(tArr);

        if(Arrays.equals(sArr, tArr)){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean anagramCount(String s, String t){

        /*
        Found this solution while looking to simplify the Hashmap method we came up with.

        We can create an empty array of size 256 to represent all the characters in an ASCII set.
        Using the ASCII values of the characters, We can increment the index that represents each character by one for
        the first string, and subtract one for the second string. It should net all zeros. If there is a positive or
        negative value, one string had a character the other didn't. They would not be anagrams. This should have a time
        complexity of O(n). You could only need to run two loops, one that is the size of the strings (which are equal),
        and one for the character set size, which is 256 in this case. It wouldn't be as fast with UTF-8 since the
        character set size is much larger.
         */

        //Set by the ASCII character set size
        int charRange = 256;

        //If string are not the same length, they will not be anagrams
        if (s.length() != t.length()) {
            return false;
        }
        int count[] = new int[charRange];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
            count[t.charAt(i)]--;
        }
        for (int i = 0; i < charRange; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }





}
