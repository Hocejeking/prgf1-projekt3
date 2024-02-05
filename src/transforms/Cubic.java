package transforms;

public class Cubic {

	public static final Mat4 BEZIER = new Mat4(new double[] {
			-1, 3, -3, 1,
			3, -6, 3, 0,
			-3, 3, 0, 0,
			1, 0, 0, 0
	});

	public static final Mat4 COONS = new Mat4(new double[] {
			-1, 3, -3, 1,
			3, -6, 3, 0,
			-3, 0, 3, 0,
			1, 4, 1, 0
	}).mul(1.0 / 6.0);

	public static final Mat4 FERGUSON = new Mat4(new double[] {
			2, -2, 1, 1,
			-3, 3, -2, -1,
			0, 0, 1, 0,
			1, 0, 0, 0
	});

	private final Mat4 controlMat;


	public Cubic(final Mat4 baseMat, final Point3D p1, final Point3D p2, final Point3D p3, final Point3D p4) {
		controlMat = baseMat.mul(new Mat4(p1, p2, p3, p4));
	}


	public Cubic(final Mat4 baseMat, final Point3D[] points) {
		this(baseMat, points, 0);
	}

	public Cubic(final Mat4 baseMat, final Point3D[] points, final int startIndex) {
		controlMat = baseMat.mul(new Mat4(points[startIndex], points[startIndex + 1],
				points[startIndex + 2], points[startIndex + 3]));
	}

	public Point3D compute(final double param) {
		final double t = param > 0 ? param < 1 ? param : 1 : 0;
		final Point3D res = new Point3D(t * t * t, t * t, t, 1).mul(controlMat);
		return new Point3D(res.ignoreW());
	}

}
