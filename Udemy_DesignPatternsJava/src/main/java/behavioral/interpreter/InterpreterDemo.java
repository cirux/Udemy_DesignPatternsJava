/**
 * 
 */
package behavioral.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ciro
 *
 */
public class InterpreterDemo {

	/**
	 * performs tokenization
	 * 
	 * @param input
	 * @return
	 */
	static List<Token> lex(String input) {

		ArrayList<Token> result = new ArrayList<>();

		for (int i = 0; i < input.length(); i++) {
			switch (input.charAt(i)) {
			case '+':
				result.add((new Token(Token.Type.PLUS, "+")));
				break;
			case '-':
				result.add((new Token(Token.Type.MINUS, "-")));
				break;
			case '(':
				result.add((new Token(Token.Type.LPAREN, "(")));
				break;
			case ')':
				result.add((new Token(Token.Type.RPAREN, ")")));
				break;
			default:
				StringBuilder sb = new StringBuilder("" + input.charAt(i));
				for (int j = i + 1; i < input.length(); ++j) {
					if (Character.isDigit(input.charAt(j))) {
						sb.append(input.charAt(j));
						++i;
					} else {
						result.add(new Token(Token.Type.INTEGER, sb.toString()));
						break;
					}
				}
				break;
			}
		}
		return result;
	}
	
	/**
	 * returns the root of the tree we are parsing
	 * @param tokens
	 * @return
	 */
	static Element parse(List<Token> tokens) {
		
		BinaryOperation result = new BinaryOperation();
		boolean haveLHS = false;
		
		for(int i=0; i< tokens.size(); ++i) {
			Token token = tokens.get(i);
			
			// figure out what kind of token is
			switch(token.type) {
			case INTEGER:
				Integer integer = new Integer(java.lang.Integer.parseInt(token.text));
				if(!haveLHS) {
					result.left = integer;
					haveLHS = true;
				}else {
					result.right = integer;
				}
				break;
				
			case PLUS:
				result.type = BinaryOperation.Type.ADDITION;
				break;
				
			case MINUS:
				result.type = BinaryOperation.Type.SUBTRACTION;
				break;
				
			case LPAREN:
				int j = i; 	// location of rparen
				for(; j<tokens.size(); ++j)
					if(tokens.get(j).type == Token.Type.RPAREN)
						break;
				
				// recursively parse token
				List<Token> subexpression = tokens.stream()
					.skip(i+1)	// skip already met tokens
					.limit(j-i-1) // limit by number of tokens inside expression
					.collect(Collectors.toList());
				Element element = parse(subexpression);
				if(!haveLHS) {
					result.left = element;
					haveLHS = true;
				}else {
					result.right = element;
				}
				i = j;	// continue after the subexpression
				break;
			}
		}
		return result;
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String input = "(13+4)-(12+1)";

		List<Token> tokens = lex(input);
		// print tab separated tokens in functional way
		System.out.println(tokens.stream().map(t -> t.toString()).collect(Collectors.joining("\t")));
		
		Element parsed = parse(tokens);
		System.out.println(input + " = " + parsed.eval());

	}

}

/**
 * for parsing 
 *
 */
interface Element
{
	int eval();
}

class Integer implements Element{

	private int value;
	
	public Integer(int value) {
		this.value = value;
	}
	
	@Override
	public int eval() {
		return value;
	}
	
}

class BinaryOperation implements Element{

	public enum Type {
		ADDITION, SUBTRACTION
	}
	public Type type;
	public Element left, right;
	
	@Override
	public int eval() {
		
		switch(type) {
		case ADDITION:
			return left.eval() + right.eval();
		case SUBTRACTION:
			return left.eval() - right.eval();
		default:
			return 0;
		}
	}
}


class Token {

	public enum Type {
		INTEGER, PLUS, MINUS, LPAREN, RPAREN
	}

	public Type type;
	public String text;

	public Token(Type type, String text) {
		super();
		this.type = type;
		this.text = text;
	}

	@Override
	public String toString() {
		return "'" + text + "'";
	}

}
