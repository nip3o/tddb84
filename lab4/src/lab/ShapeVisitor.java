package lab;

import java.awt.Graphics;

public class ShapeVisitor extends AbstractVisitor {
	private Graphics g;
	private int totalNumber = 0;
	
	
	public ShapeVisitor(Graphics g) {
		this.g = g;
	}
	
	public ShapeVisitor() {}

	@Override
	public void visit(AbstractSquare s) {
		if(g != null) {
			s.paint(g);
		}
		totalNumber++;
	}

	@Override
	public void visit(Rectangle r) {
		if(g != null) {
			r.paint(g);
		}
		totalNumber++;
	}

	@Override
	public void visit(Circle c) {
		if(g != null) {
			c.paint(g);
		}
		totalNumber++;
	}

	@Override
	public void visit(Triangle t) {
		if(g != null) {
			t.paint(g);
		}
		totalNumber++;
	}
	
	public int getTotalNumber() {
		return totalNumber;
	}

}
