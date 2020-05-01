package phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class Solution
{
	// Top-down recursive function to find all possible combinations
	// of words formed from mobile keypad
	static Set<String> pagination = new HashSet<>();
	public static void findCombinations(List<List<Character>> keypad,
										int[] input, String res, int index)
	{
		
		// if we have processed every digit of key, print result
		if (index == -1)
		{
			//System.out.print(res + " ");
			pagination.add(res);
			return;
		}

		// stores current digit
		int digit = input[index];

		// size of the list corresponding to current digit
		int len = keypad.get(digit).size();

		// one by one replace the digit with each character in the
		// corresponding list and recur for next digit
		for (int i = 0; i < len; i++) {
			findCombinations(keypad, input, keypad.get(digit).get(i) + res,
							index - 1);
		}
	}
	
	static Set<String> paginate(int start,int end){
		List<String> list = new ArrayList<>(pagination);
		Set<String> subSet = new LinkedHashSet<>(list.subList(start, end));
		return subSet;
	}
	

	public static void main(String[] args)
	{
		phoneNumberSetUp();
		
		paginationHelper();
		
		
	}

	private static void paginationHelper() {
		int totalCount = pagination.size();
		int pageCount = (int) Math.ceil(totalCount/5); // 5 is page size 
		int start = 0; 
		for(int i=0;i<pageCount;i++) {
			
		    int end = start + 5;
		    System.out.print(start + " : "+ end);
		    if(end < totalCount ) {
			  System.out.println(paginate(start,end));
		    } else {
		    	System.out.println(paginate(start,totalCount));
		    }
			start = end;
		}
	}



	private static void phoneNumberSetUp() {
		List<List<Character>> keypad = Arrays.asList(
				// 0 and 1 digit don't have any characters associated
				Arrays.asList(),
				Arrays.asList(),
				Arrays.asList( 'A', 'B', 'C' ),
				Arrays.asList( 'D', 'E', 'F' ),
				Arrays.asList( 'G', 'H', 'I' ),
				Arrays.asList( 'J', 'K', 'L' ),
				Arrays.asList( 'M', 'N', 'O' ),
				Arrays.asList( 'P', 'Q', 'R', 'S'),
				Arrays.asList( 'T', 'U', 'V' ),
				Arrays.asList( 'W', 'X', 'Y', 'Z')
		);

		// input number in the form of an array
		// (number can't start from 0 or 1)
		int[] input = {4,5,6 };

		// find all combinations
		findCombinations(keypad, input, "",  input.length - 1);
	}
}