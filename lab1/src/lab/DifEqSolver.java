package lab;

class DifEqSolver {

	static native void rk(double[] x00, double[] v00,
		double[] x10, double[] v10, double m0, double m1,
		double t, double h, double[] x0f, double[] v0f,
		double[] x1f, double[] v1f);

	private static final double K = 6.6742e-11;
	private static double q[] = new double[6];
	private static double x0[] = new double[6];
	private static double x1[] = new double[6];

	private static double rky0(double[] x, double t, double m0, double m1) {

		return x[3];
	}

	private static double rky1(double[] x, double t, double m0, double m1) {

		return x[4];
	}

	private static double rky2(double[] x, double t, double m0, double m1) {

		return x[5];
	}

	private static double rky3(double[] x, double t, double m0, double m1) {

		return -K * (m0 + m1) * x[0] / Math.pow(x[0] * x[0] +
			x[1] * x[1] + x[2] * x[2], 1.5);
	}

	private static double rky4(double[] x, double t, double m0, double m1) {

		return -K * (m0 + m1) * x[1] / Math.pow(x[0] * x[0] +
			x[1] * x[1] + x[2] * x[2], 1.5);
	}

	private static double rky5(double[] x, double t, double m0, double m1) {

		return -K * (m0 + m1) * x[2] / Math.pow(x[0] * x[0] +
			x[1] * x[1] + x[2] * x[2], 1.5);
	}

	private static void RungeKutta(double[] x0, double[] xf, double t,
		double h, double m0, double m1) {

		q[0] = x0[0] + h / 2.0 * rky0(x0, t, m0, m1);
		q[1] = x0[1] + h / 2.0 * rky1(x0, t, m0, m1);
		q[2] = x0[2] + h / 2.0 * rky2(x0, t, m0, m1);
		q[3] = x0[3] + h / 2.0 * rky3(x0, t, m0, m1);
		q[4] = x0[4] + h / 2.0 * rky4(x0, t, m0, m1);
		q[5] = x0[5] + h / 2.0 * rky5(x0, t, m0, m1);
		xf[0] = x0[0] + h * rky0(q, t + h / 2.0, m0, m1);
		xf[1] = x0[1] + h * rky1(q, t + h / 2.0, m0, m1);
		xf[2] = x0[2] + h * rky2(q, t + h / 2.0, m0, m1);
		xf[3] = x0[3] + h * rky3(q, t + h / 2.0, m0, m1);
		xf[4] = x0[4] + h * rky4(q, t + h / 2.0, m0, m1);
		xf[5] = x0[5] + h * rky5(q, t + h / 2.0, m0, m1);
	}

	public static void solve(double[] x00, double[] v00,
		double[] x10, double[] v10, double m0, double m1,
		double t, double h, double[] x0f, double[] v0f,
		double[] x1f, double[] v1f) {

		x0[0] = x10[0] - x00[0];
		x0[1] = x10[1] - x00[1];
		x0[2] = x10[2] - x00[2];
		x0[3] = v10[0] - v00[0];
		x0[4] = v10[1] - v00[1];
		x0[5] = v10[2] - v00[2];

		RungeKutta(x0, x1, t, h, m0, m1);

		x0f[0] = m0 / (m0 + m1) * (x00[0] + v00[0] * h) + m1 / (m0 + m1) * (x10[0] + v10[0] * h) - m1 / (m0 + m1) * x1[0];
		x0f[1] = m0 / (m0 + m1) * (x00[1] + v00[1] * h) + m1 / (m0 + m1) * (x10[1] + v10[1] * h) - m1 / (m0 + m1) * x1[1];
		x0f[2] = m0 / (m0 + m1) * (x00[2] + v00[2] * h) + m1 / (m0 + m1) * (x10[2] + v10[2] * h) - m1 / (m0 + m1) * x1[2];

		v0f[0] = m0 / (m0 + m1) * v00[0] + m1 / (m0 + m1) * v10[0] - m1 / (m0 + m1) * x1[3];
		v0f[1] = m0 / (m0 + m1) * v00[1] + m1 / (m0 + m1) * v10[1] - m1 / (m0 + m1) * x1[4];
		v0f[2] = m0 / (m0 + m1) * v00[2] + m1 / (m0 + m1) * v10[2] - m1 / (m0 + m1) * x1[5];

		x1f[0] = m0 / (m0 + m1) * (x00[0] + v00[0] * h) + m1 / (m0 + m1) * (x10[0] + v10[0] * h) + m0 / (m0 + m1) * x1[0];
		x1f[1] = m0 / (m0 + m1) * (x00[1] + v00[1] * h) + m1 / (m0 + m1) * (x10[1] + v10[1] * h) + m0 / (m0 + m1) * x1[1];
		x1f[2] = m0 / (m0 + m1) * (x00[2] + v00[2] * h) + m1 / (m0 + m1) * (x10[2] + v10[2] * h) + m0 / (m0 + m1) * x1[2];

		v1f[0] = m0 / (m0 + m1) * v00[0] + m1 / (m0 + m1) * v10[0] + m0 / (m0 + m1) * x1[3];
		v1f[1] = m0 / (m0 + m1) * v00[1] + m1 / (m0 + m1) * v10[1] + m0 / (m0 + m1) * x1[4];
		v1f[2] = m0 / (m0 + m1) * v00[2] + m1 / (m0 + m1) * v10[2] + m0 / (m0 + m1) * x1[5];
	}
}
