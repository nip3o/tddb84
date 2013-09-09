package lab;

import java.applet.Applet;

public class Main extends Applet {

	private static final long serialVersionUID = -7680203860191253769L;

	private Model model;
	private View  view;
	private static final double DFLT_x0x = 100.0;
	private static final double DFLT_x0y = 0.0;
	private static final double DFLT_v0x = 0.0;
	private static final double DFLT_v0y = 1.29172365465683100186 / 1.4;
	private static final double DFLT_x1x = -100.0;
	private static final double DFLT_x1y = 0.0;
	private static final double DFLT_v1x = 0.0;
	private static final double DFLT_v1y = -1.29172365465683100186;
	private static final double DFLT_m0  = 1.0e13 * 1.4;
	private static final double DFLT_m1  = 1.0e13;

	private Thread thread;

	public Main() {

	}

	public void init() {

		double x0x, x0y, v0x, v0y, x1x, x1y, v1x, v1y, m0, m1;

		try {
			x0x = Double.parseDouble(getParameter("x0x"));
		} catch (RuntimeException np) {
			x0x = DFLT_x0x;
		}
		try {
			x0y = Double.parseDouble(getParameter("x0y"));
		} catch (RuntimeException e) {
			x0y = DFLT_x0y;
		}
		try {
			v0x = Double.parseDouble(getParameter("v0x"));
		} catch (RuntimeException e) {
			v0x = DFLT_v0x;
		}
		try {
			v0y = Double.parseDouble(getParameter("v0y"));
		} catch (RuntimeException e) {
			v0y = DFLT_v0y;
		}
		try {
			x1x = Double.parseDouble(getParameter("x1x"));
		} catch (RuntimeException e) {
			x1x = DFLT_x1x;
		}
		try {
			x1y = Double.parseDouble(getParameter("x1y"));
		} catch (RuntimeException e) {
			x1y = DFLT_x1y;
		}
		try {
			v1x = Double.parseDouble(getParameter("v1x"));
		} catch (RuntimeException e) {
			v1x = DFLT_v1x;
		}
		try {
			v1y = Double.parseDouble(getParameter("v1y"));
		} catch (RuntimeException e) {
			v1y = DFLT_v1y;
		}
		try {
			m0 = Double.parseDouble(getParameter("m0"));
		} catch (RuntimeException e) {
			m0 = DFLT_m0;
		}
		try {
			m1 = Double.parseDouble(getParameter("m1"));
		} catch (RuntimeException e) {
			m1 = DFLT_m1;
		}

		model = new Model(new Coord(x0x, x0y), new Coord(v0x, v0y),
			new Coord(x1x, x1y), new Coord(v1x, v1y), m0, m1);
		view = new View(model);
		add(view);
		resize(view.width, view.height);
		thread = new Thread(model);
		thread.start();
	}

	public void start() {

		model.resume();
	}

	public void stop() {

		model.suspend();
	}

	public void destroy() {

		if (model.isSuspended()) model.resume();
		model.stop();
	}
}
