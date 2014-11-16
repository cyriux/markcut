package io.markcut;

public class SvgRenderer {

	String HEADER_OPEN = "<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"";
	String SIZES = " width=\"181mm\" height=\"181mm\"";
	String VIEWPORT = " viewBox=\"0 0 181 181\"";
	String HEADER_CLOSE = ">\n";

	String HEADER = HEADER_OPEN + SIZES + VIEWPORT + HEADER_CLOSE;

	String STYLE = "\n<defs>\n<style type=\"text/css\">\n<![CDATA[\n  .str0 {stroke:#0000FF;stroke-width:0.01}\n  .fil0 {fill:none}\n]]>\n</style>\n</defs>\n\n";
	String DEBUG_STYLE = "\n<defs>\n<style type=\"text/css\">\n<![CDATA[\n  .str0 {stroke:#0000FF;stroke-width:0.1}\n  .fil0 {fill:none}\n]]>\n</style>\n</defs>\n\n";
	
	final String OPEN_PATH = "<path class=\"fil0 str0\" d=\"";
	final String CLOSE_PATH = "\"/>";

	String FOOTER = "\n\n</svg>";

	public String render(Shape shape) {
		return HEADER + DEBUG_STYLE + OPEN_PATH + toPath(shape) + CLOSE_PATH + FOOTER + "\n";
	}

	private String toPath(Shape shape) {
		final StringBuilder b = new StringBuilder();
		String instruction = "M ";
		for (Point p : shape) {
			b.append(instruction);
			b.append(p.x());
			b.append(" ");
			b.append(p.y());
			instruction = " L ";
		}
		b.append(" z");
		return b.toString();
	}

}
