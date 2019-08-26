package com.revature.eval.java.core;

import java.lang.reflect.Array;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] chaArray = string.toCharArray();
		if(chaArray.length == 0) {//empty string
			return string;
		}
		else {
			int i = 0;
			int j = chaArray.length-1;
			char temp = 0;
			while(i<j) {
				temp = chaArray[i];
				chaArray[i] = chaArray[j];
				chaArray[j] = temp;
				i++;
				j--;
			}
			string=String.valueOf(chaArray);
			return string;
		}
		
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		if(phrase == null) {
			return null;
		}
		String[] wordArr = phrase.trim().split("[^a-zA-Z0-9]+");
		
		if(wordArr.length == 0) {
			return "empty phrase!";
		}
		char[] acrArr = new char[wordArr.length];
		
		for(int i = 0; i < wordArr.length; i++) {
			acrArr[i] = Character.toUpperCase(wordArr[i].charAt(0));			
		}
		
		return String.valueOf(acrArr);
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			
			if(this.sideOne == this.sideTwo && this.sideOne == this.sideThree) {
				return true;
			}
			else return false;
		}

		public boolean isIsosceles() {
			
			if(this.isEquilateral()) {
				return true;
			}
			else if(this.sideOne == this.sideTwo || this.sideOne == this.sideThree || this.sideTwo == this.sideThree) {
				return true;
			}
			else return false;
		}

		public boolean isScalene() {
			
			if (!this.isEquilateral() && !this.isIsosceles()) {
				return true;
			}
			else return false;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		
		int score = 0;
		
		Map<char[],Integer> letterPoint = new HashMap<>();
		
		char[] key1 = {'A','E','I','O','U','L','N','R','S','T'};		
		letterPoint.put(key1,1);
		
		char[] key2 = {'D','G'};
		letterPoint.put(key2,2);
		
		char[] key3 = {'B','C','M','P'};
		letterPoint.put(key3,3);
		
		char[] key4 = {'F','H','V','W','Y'};
		letterPoint.put(key4,4);
		
		char[] key5 = {'K'};
		letterPoint.put(key5,5);
		
		char[] key8 = {'J','X'};
		letterPoint.put(key8,8);
		
		char[] key10 = {'Q','Z'};
		letterPoint.put(key10,10);
		
		char[] letterArr = string.toCharArray();
		Set<char[]> keySet = letterPoint.keySet();
		for(char letter: letterArr) {
			char letterUpperCase = Character.toUpperCase(letter);
			for (char[] key: keySet) {
				for(int i=0; i<key.length;i++) {
					if(key[i] == letterUpperCase) {
						score = score + letterPoint.get(key);
					}
		
				}
					
			}
		//		else continue;
		}
			
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		
		String strDigits = string.replaceAll("[\\-\\(\\)\\s\\.+]", "");
		System.out.println(strDigits);
		
		char[] telArr = strDigits.toCharArray();
		
		if(strDigits.length() >= 11) {
			throw new IllegalArgumentException ("IllegalArgumentException");
			}
		char[] result = new char[telArr.length];
		List<Character> valNum = Arrays.asList('0','1','2','3','4','5','6','7','8','9');
		boolean start = true;
		int j = 0;  //index for accessing result element
		for(char ele: telArr) {
			if(start) {
				if(valNum.subList(2,  10).contains(ele)) {
					result[j] = ele;
					start = false;
					j++;
				}
			}
			else {
				if(valNum.contains(ele)) {
					result[j] = ele;
					j++;
				}
				else {
					throw new IllegalArgumentException("IllegalArgumentException");
				}
			}
		}
		return String.valueOf(result);
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		String[] wordList = string.trim().split("[^a-zA-Z0-9]+");
		Map<String, Integer> count = new HashMap<>();
		for(String word: wordList) {
			if(!count.containsKey(word)) {
				count.put(word, 1);
			}
			else {
				count.put(word, count.get(word) +1);
			}
		}
		
		return count;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T extends Comparable <T>> {
		private List<T> sortedList;

		public int indexOf(T t) {
			int left = 0;
			int right = this.sortedList.size()-1;
			boolean found = false;
			int index = 0;
			
			if(t.compareTo(this.sortedList.get(left)) == 0) {
				found = true;
				index = left;
//				return index;
			}
			
			else if(t.compareTo(this.sortedList.get(right)) == 0) {
				found = true;
				index = right;
//				return index;
			}
			else {
				while(left < right-1) {
					
					int middle = (int) (Math.floor((right + left)/2.0));
					T middleValue = this.sortedList.get(middle);
					
					if(t.compareTo(middleValue) == 0) {
						
						index = middle;
						found = true;					
						break;
						
					}
					
					else if(t.compareTo(middleValue) < 0) {
						right = middle;
					}
					
					else left = middle;
				}
			}
			
			if(found) {
			
				return index;
			}
			else {
				System.out.println("not found");
			    return -1;
			}
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		// TODO Write an implementation for this method declaration
    	char[] vowels = {'a','e','i','o','u'};
		String[] wordList = string.trim().split(" ");
//		for(String word: wordList) {
//			System.out.println(word);
//		}
		String[] pigLatin = new String[wordList.length];
		for(int i = 0; i < wordList.length; i++) {
			if(String.valueOf(Character.toLowerCase(wordList[i].charAt(0))).matches("[aeiou]")) {
	             pigLatin[i] = wordList[i] + "ay";
			}
			else {
				int indexVowel = 0;
				for(int j = 0; j< wordList[i].length(); j++) {
		//			boolean isVowel = false;
		//			for(char vowel: vowels) {
		//				if(Character.toLowerCase(wordList[i].charAt(j)) == vowel) {
		//					isVowel = true;
		//					break;
		//				}
		//			}
					if(Character.toLowerCase(wordList[i].charAt(j)) == 'u') {
						if(String.valueOf(Character.toLowerCase(wordList[i].charAt(j+1))).matches("[aeiou]")) {
							  indexVowel = j +1;
						}
						else indexVowel = j;
						System.out.println(String.valueOf(indexVowel));
						break;
					}
					else if(String.valueOf(Character.toLowerCase(wordList[i].charAt(j))).matches("[aeio]")) {
						indexVowel = j; 
						System.out.println(String.valueOf(indexVowel));
						break;
					}			
				    
				}
				
				pigLatin[i] = wordList[i].substring(indexVowel) + wordList[i].substring(0, indexVowel) + "ay" ;	
			}
			
		}
		StringBuilder result = new StringBuilder();
		for(String word: pigLatin) {
			result.append(word).append(' ');
		}
		System.out.println(result.toString().trim());
		return result.toString().trim();
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		String strNum = Integer.toString(input);
		char[] numArr = strNum.toCharArray();
		int power = numArr.length;
		double sum = 0;
		for(char num: numArr) {
			sum = sum + Math.pow(Character.getNumericValue(num), power);
		}
		
		if(sum == input) {
			return true;
		}
		else return false;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> primeFactors = new ArrayList<Long>();
		
		if(l == 1) {
			primeFactors.add(1L);
			return primeFactors;
		}
		while(l > 1) {
			int divider = 2;
			while(l%divider != 0) {
				  if(divider == 2) {
					  divider += 1;
				  }
				  else divider += 2;
			}
			primeFactors.add(Long.valueOf(divider));
			l = l/divider;
		}		
		return primeFactors;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			
			String alphabet ="abcdefghijklmnopqrstuvwxyz";
			String newAlphabet =alphabet.substring(key) + alphabet.substring(0, key-1);
	
			System.out.println(newAlphabet);
			
			char[] strArr = string.toCharArray() ;
			char[] resultArr = new char[strArr.length];
						
			
			for(int index = 0; index < strArr.length; index++) {
				
				System.out.println(String.valueOf(index));
			
				if(Character.isLetter(strArr[index])) {				
				    if(Character.isUpperCase(strArr[index])) {
				           char lowChar = newAlphabet.charAt(alphabet.indexOf(Character.toLowerCase(strArr[index])));
					       resultArr[index] = Character.toUpperCase(lowChar);
				    }
				    else {
					      resultArr[index] = newAlphabet.charAt(alphabet.indexOf(strArr[index]));
				    }
					
				    System.out.println(String.valueOf(resultArr[index]));	
			   }
				
				
			   else {
				   resultArr[index] = strArr[index];
				   System.out.println(String.valueOf(resultArr[index]));
			 }
	      }
		  System.out.println(String.valueOf(resultArr));
		  return String.valueOf(resultArr);
	   }
	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		// TODO Write an implementation for this method declaration
		int counter = 1;
		int num =2;
		while(counter != i) {
			if(num == 2) {
				num = num+1;
				counter++;
			}
			else {
				num = num + 2;
				boolean isPrime = true;
				for(int divider = 3; divider <= Math.ceil((double)Math.sqrt((double)num)); divider += 2) {
					if(num%divider == 0) {
						isPrime = false;
						break;
					}
				}
				if(isPrime) {
					counter++;
				}
			}
		}
		return num;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		static String plain = "abcdefghijklmnopqrstuvwxyz";
		static String cipher = "zyxwvutsrqponmlkjihgfedcba";
//		public String encodeResult = null;
//		public String decodeResult = null;
		public static String encode(String string) {
			char[] textList = string.replaceAll("[^a-zA-Z0-9]+", "").toCharArray();
//			System.out.println(String.valueOf(textList));
			char[] encodeTextList = new char[(int) (textList.length + Math.floor((double)textList.length/5))];
			int j = 0; //index of encode text array 
			for(int i =0; i < textList.length; i++) {
				     if(i%5 == 0 && i != 0) {
					       encodeTextList[j]=' ';
					       j++;
				     }
				     if(Character.isDigit(textList[i])) {
				    	 encodeTextList[j] = textList[i];
				     }
				     else {
				    	 encodeTextList[j] = cipher.charAt(plain.indexOf(Character.toLowerCase(textList[i])));	 
				     }
				     
				     j++;
			}
//		   	System.out.println(String.valueOf(encodeTextList));
            return String.valueOf(encodeTextList).trim();
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			String strNoSpace = string.replaceAll("\\s+", "");
			System.out.println(strNoSpace);
			char[] letterArr = strNoSpace.toCharArray();
			char[] decodeArr = new char[letterArr.length];
			for(int i = 0; i < letterArr.length; i++) {
				if(Character.isDigit(letterArr[i])) {
					decodeArr[i] = letterArr[i];
				}
				else {
					decodeArr[i] = plain.charAt(cipher.indexOf(letterArr[i]));
				}
			}
			return String.valueOf(decodeArr);
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
        char[] digitArr =string.replaceAll("-","").toCharArray();
        int sum = 0;
        int factor = 10;
        for(char digit:digitArr) {
        	if(digit == 'X') {
        		 sum = sum + factor*10;
        		 factor--;
        	}
        	else if(Character.isDigit(digit)) {
        	     sum = sum + factor*Integer.parseInt(String.valueOf(digit));
        	     factor--;
        	}
        	else return false;
        }
		if(sum%11== 0) {
			return true;
		}
		else return false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		String noSpaceStr = string.replaceAll("\\s", "");
		if(noSpaceStr.length()<26) {
			return false;
		}
		System.out.println(noSpaceStr);
		
		char[] charArr = noSpaceStr.toCharArray();
		List<Character> alphasInclude = new ArrayList<Character>();
		for(char letter:charArr) {
			if(!alphasInclude.contains(letter)) {
				alphasInclude.add(letter);
			}
		}
		if(alphasInclude.size() == 26) {
			return true;
		}
		else return false;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		
		return null;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {

		int sum = 0;
		List<Integer> multiples = new ArrayList<Integer>(); 
		for(int num: set) {
			  int factor = 1;
			  while(num*factor < i) {
				  if(!multiples.contains(num*factor)) {
					  multiples.add(num*factor);
				  }
				  factor++;
			  }
		}
		
		for(int ele:multiples) {
			sum = sum + ele;
		}
			
		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {

		if(string.length()<=1) {
			System.out.println("Invalid Input Number!");
			return false;
		}
		
		String stringNoSpace = string.replaceAll("\\s+","");
		System.out.println(stringNoSpace);
		
		if(stringNoSpace.contains("[^0-9]+")) {
			System.out.println("Invalid Input Number!");
			return false;
		}
		char[] digitList = stringNoSpace.toCharArray();
		int numDigits = digitList.length;
		int sum = 0; //the sum of all transfered numbers
//		int index = 1;
		int digit = 0;//each digit in the string number
		for(int i = numDigits-1; i >= 0; i--) {
			
			  digit = Character.getNumericValue(digitList[i]);
			  if((numDigits-i)%2 != 0) {
				    sum = sum + digit;
			  }
			  else {
				    if( digit*2 > 9) {
				    	   sum = sum + (digit*2-9);
				    }
				    else {
				    	   sum = sum +  digit*2;
				    }
			  }
//			  index++;			 
		}
	
		if(sum%10 == 0) {
			return true;
		}
		else return false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		
		String[] wordList = string.replaceAll("[^a-zA-Z0-9\\-]"," ").toLowerCase().split("\\s+"); 
		String[] operand = new String[2];
		
		String[] operators = {"plus","minus","multiplied", "divided"};
		String operator = null;
		int indexOperand = 0;
		int result = 0;

		
		//get the operator and operands
		for(String word: wordList) {
			    if(word.matches("[\\-0-9]+$")) {
				             if(indexOperand<2) {
				                   operand[indexOperand] = word;
				                   indexOperand++;
				              }
			    }
			    else {
				         for(String opEle: operators) {
//				        	    System.out.println(opEle);
					            if(opEle.equalsIgnoreCase(word)) {
						                 operator = word;
					             }
				         }
			   }	
		}
		System.out.println(operand[0]+" "+ operand[1]+" "+operator);
		switch(operator) {
		       case "plus":
			          result = Integer.parseInt(operand[0]) + Integer.parseInt(operand[1]);
		              break;
		       case "minus":
			          result = Integer.parseInt(operand[0]) - Integer.parseInt(operand[1]);
			         break;
		       case "multiplied":
			          result = Integer.parseInt(operand[0]) * Integer.parseInt(operand[1]);
			         break;
		       default:
			          result = Integer.parseInt(operand[0]) / Integer.parseInt(operand[1]);
		}
			
		return result;
	 }

}
