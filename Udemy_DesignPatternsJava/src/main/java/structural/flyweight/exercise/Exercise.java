package structural.flyweight.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Sentence {

	// shared cache among all instances
	private String[] words;
	// each object has its own indexes
	private Map<Integer, WordToken> tokens = new HashMap<>();

	public Sentence(String plainText) {

		words = plainText.split(" ");
	}

	public WordToken getWord(int index) {
		WordToken wd = new WordToken();
		tokens.put(index, wd);
		return tokens.get(index);
	}

	@Override
	public String toString() {
		ArrayList<String> ws = new ArrayList<>();
		for (int i = 0; i < words.length; ++i) {
			String w = words[i];
			if (tokens.containsKey(i) && tokens.get(i).capitalize)
				w = w.toUpperCase();
			ws.add(w);
		}
		return String.join(" ", ws);
	}

	class WordToken {
		public boolean capitalize;

		@Override
		public String toString() {
			return "WordToken [capitalize=" + capitalize + "]";
		}
		
	}
	
	Map<Integer, WordToken> getTokens(){
		return tokens;
	}
	
	String[] getWords() {
		return words;
	}
}

public class Exercise {

	public static void main(String[] args) {
		Sentence sentence = new Sentence("hello world");
		sentence.getWord(1).capitalize = true;
		System.out.println(sentence); // writes "hello WORLD"
		System.out.println(sentence.getWords());
		System.out.println(sentence.getTokens());
	}

}
