package behavioral.interpreter.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ExpressionProcessor {
	
	public static Map<Character, java.lang.Integer> variables = new HashMap<>();

	public int calculate(String expression) {

		List<Token> tokens = lex(expression);
		Element element = parse(tokens);
		return element.eval();
	}

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
			default:
				StringBuilder sb = new StringBuilder("" + input.charAt(i));
				
				char nextChar = 0;
				
				if(Character.isDigit(input.charAt(i))) {
					result.add(new Token(Token.Type.INTEGER, sb.toString()));
					break;
				}else if(variables.containsKey(input.charAt(i))){
					if(input.length() > i +1)
						nextChar = input.charAt(i + 1);
					if(nextChar == '+' || nextChar == '-' || nextChar == 0)
						result.add(new Token(Token.Type.INTEGER, variables.get(input.charAt(i)).toString()));
					else {
						// invalid variable -> result must be 0
						result.clear();
					}
					break;
				}
				
			}
		}
		return result;
	}

	static Element parse(List<Token> tokens) {

		BinaryOperation result = new BinaryOperation();
		boolean haveLHS = false;

		for (int i = 0; i < tokens.size(); ++i) {
			Token token = tokens.get(i);

			// figure out what kind of token is
			switch (token.type) {
			case INTEGER:
				Integer integer = new Integer(java.lang.Integer.parseInt(token.text));
				if (!haveLHS) {
					result.left = integer;
					haveLHS = (i < tokens.size()-1);
				} else {
					result.right = integer;
				}
				break;

			case PLUS:
				result.type = BinaryOperation.Type.ADDITION;
				break;

			case MINUS:
				result.type = BinaryOperation.Type.SUBTRACTION;
				break;
			}
		}
		return result;
	}

}

class Token {

	public enum Type {
		INTEGER, PLUS, MINUS
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

interface Element {
	int eval();
}

class Integer implements Element {

	private int value;

	public Integer(int value) {
		this.value = value;
	}

	@Override
	public int eval() {
		return value;
	}

}

class BinaryOperation implements Element {

	public enum Type {
		ADDITION, SUBTRACTION
	}

	public Type type = Type.ADDITION;
	public Element left, right;

	@Override
	public int eval() {

		switch (type) {
		case ADDITION:
			if(left == null)
				return 0;
			return (right == null) ? left.eval() : left.eval() + right.eval();
		case SUBTRACTION:
			return left.eval() - right.eval();
		default:
			return 0;
		}
	}

}

public class InterpreterExercise {

}
