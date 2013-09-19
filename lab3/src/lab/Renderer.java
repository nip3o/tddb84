package lab;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.AbstractList;

interface Renderer {

	public void putBackground();
	public void putBody(AbstractList<Rectangle> body);
	public void setSnakeColor(Color color);
	public void putBonus(AbstractList<Rectangle> bonus);
	public void outside();
	public void stop();
}
