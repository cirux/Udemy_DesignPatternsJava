package behavioral.strategy;

import java.util.List;

// A publishing system wich can output different formats

enum OutputFormat{
	
	// A list is
	// * foo
	// * bar
	MARKDOWN, 
	
	/**
	 * A list is <ul><li></li></ul>
	 */
	HTML
}

interface ListStrategy{
	default void start(StringBuilder sb) { 
		// empty body for markdown strategy };
	}
	void addListItem(StringBuilder sb, String item);
	
	default void end(StringBuilder sb) {};
}

class MarkDownStrategy implements ListStrategy{

	@Override
	public void addListItem(StringBuilder sb, String item) {
		sb.append(" * ").append(item).append(System.lineSeparator());
	}

}

class HtmlStrategy implements ListStrategy{

	@Override
	public void addListItem(StringBuilder sb, String item) {
		sb.append(" <li>").append(item).append("</li>").append(System.lineSeparator());
	}

	@Override
	public void start(StringBuilder sb) {
		sb.append("<ul>").append(System.lineSeparator());
	}

	@Override
	public void end(StringBuilder sb) {
		sb.append("</ul>").append(System.lineSeparator());
	}
	
}

class TextProcessor{
	private StringBuilder sb = new StringBuilder();
	private ListStrategy listStrategy;
	
	public TextProcessor(OutputFormat format) {
		setOutputFormat(format);
	}

	void setOutputFormat(OutputFormat format) {
		switch (format) {
		case MARKDOWN:
			listStrategy = new MarkDownStrategy();
			break;
		case HTML:
			listStrategy = new HtmlStrategy();
			break;
		}
	}
	
	public void appendList(List<String> items) {
		// apply the strategy
		listStrategy.start(sb);
		for (String string : items) {
			listStrategy.addListItem(sb, string);
		}
		listStrategy.end(sb);
	}
	
	public void clear() {
		sb.setLength(0);
	}
	
	@Override
	public String toString() {
		return sb.toString();
	}
}

public class DynamicStrategyDemo {

	public static void main(String[] args) {
		
		TextProcessor tp = new TextProcessor(OutputFormat.MARKDOWN);
		tp.appendList(List.of("liberte", "egalite", "fraternite"));
		System.out.println(tp);
		
		tp.clear();
		// change strategy at runtime
		tp.setOutputFormat(OutputFormat.HTML);
		tp.appendList(List.of("inheritance", "encapsulation", "polymorphism"));
		System.out.println(tp);
	}

}
