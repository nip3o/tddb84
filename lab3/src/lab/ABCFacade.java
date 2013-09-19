package lab;

// YOUR CODE HERE
// Extends? Implements?
public class ABCFacade {

	private ModuleA a;
	private ModuleB b;
	private ModuleC c;

	public ABCFacade() {

		a = new ModuleA();
		b = new ModuleB();
		c = new ModuleC();
		new Thread(a).start();
	}
	// YOUR CODE HERE
	// Overwrite some methods that you inherit/implement.
}
