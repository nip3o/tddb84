package lab;

import java.util.AbstractList;
import java.util.Vector;

public class ShapeIterator extends AbstractIterator {
	private AbstractList<AbstractShape> shapes;
	int currentIndex;
	
	public ShapeIterator(Square root) {
		shapes = root.getListOfShapes(new Vector<AbstractShape>());
		currentIndex = 0;
	}
	
	@Override
	public void first() {
		shapes.get(0);
	}

	@Override
	public void next() {
		currentIndex++;
	}

	@Override
	public boolean isDone() {
		return currentIndex >= shapes.size();
	}

	@Override
	public AbstractShape currentItem() {
		return shapes.get(currentIndex);
	}

}
