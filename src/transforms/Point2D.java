package transforms;

public class Point2D {
	public double x, y, w;

	/**
	 * Vytvari 2D bod v homogenich souradnicich lezici v pocatku souradnic
	 */
	public Point2D() {
		x = y = 0.0f;
		w = 1.0f;
	}

	/**
	 * Vytvari 2D bod v homogenich souradnicich
	 * 
	 * @param ax
	 *            souradnice x
	 * @param ay
	 *            souradnice y
	 */
	public Point2D(double ax, double ay) {
		x = ax;
		y = ay;
		w = 1.0f;
	}

	/**
	 * Vytvari 2D bod v homogenich souradnicich
	 * 
	 * @param ax
	 *            souradnice x
	 * @param ay
	 *            souradnice y
	 * @param az
	 *            souradnice z
	 * @param aw
	 *            souradnice w
	 */
	public Point2D(double ax, double ay, double aw) {
		x = ax;
		y = ay;
		w = aw;
	}

	/**
	 * Vytvari 2D bod v homogenich souradnicich
	 * 
	 * @param v
	 *            vektor souradnic (x,y,z)
	 */
	public Point2D(Vec2D v) {
		x = v.x;
		y = v.y;
		w = 1.0f;
	}

	/**
	 * Vytvari 2D bod v homogenich souradnicich
	 * 
	 * @param p
	 *            bod Point2D (x,y,w)
	 */
	public Point2D(Point2D p) {
		x = p.x;
		y = p.y;
		w = p.w;
	}

	/**
	 * Vytvari 2D bod v homogenich souradnicich
	 * 
	 * @param p
	 *            Point3D (x,y,z,w)
	 */
	public Point2D(Point3D p) {
		x = p.x;
		y = p.y;
		w = p.w;
	}

	/**
	 * Vytvari 2D bod v homogenich souradnicich
	 * 
	 * @param double[]
	 *            pole souradnic (x,y,z,w)
	 */
	public Point2D(double[] array) {
		x = array[0];
		y = array[1];
		w = array[3];
	}

	/**
	 * Nasobeni matici zprava
	 * 
	 * @param mat
	 *            matice 4x4
	 * @return nova instance Point2D
	 */
	public Point2D mul(Mat3 mat) {
		Point2D res = new Point2D();
		res.x = mat.mat[0][0] * x + mat.mat[1][0] * y + mat.mat[2][0] * w;
		res.y = mat.mat[0][1] * x + mat.mat[1][1] * y + mat.mat[2][1] * w;
		res.w = mat.mat[0][2] * x + mat.mat[1][2] * y + mat.mat[2][2] * w;
		return res;
	}
	
	/**
	 * Pricteni bodu
	 * 
	 * @param p
	 *            Point2D (x,y,w)
	 * @return nova instance Point2D
	 */
	public Point2D add(Point2D p) {
		return new Point2D(x + p.x, y + p.y, w + p.w);
	}

	/**
	 * Pricteni vektoru
	 * 
	 * @param v
	 *            Vec2D (x,y)
	 * @return nova instance Point2D
	 */
	public Point2D add(Vec2D v) {
		return new Point2D(x + v.x, y + v.y, w);
	}

	/**
	 * Nasobeni skalarem
	 * 
	 * @param f
	 *            skalar
	 * @return nova instance Point2D
	 */
	public Point2D mul(double f) {
		return new Point2D(x * f, y * f, w * f);
	}

	/**
	 * Dehmogenizace vektoru
	 * 
	 * @return nova instance Vec2D
	 */
	public Vec2D dehomog() {
		if (w == 0.0f)
			return new Vec2D(0,0);
		return new Vec2D(x / w, y / w);
	}

	/**
	 * Prevod na vektor (x,y,z), zanedbani w
	 * 
	 * @return nova instance Vec2D
	 */
	public Vec2D ignoreW() {
		return new Vec2D(x, y);
	}
	/**
	 * vypis bodu do stringu
	 * 	 
	 * @return textovy retezec
	 */
	public String string() {
		return String.format("[%4.1f;%4.1f;%4.1f]",x,y,w);
	}
	/**
	 * formatovamy vypis bodu do stringu
	 * @param format
	 *            String jedne slozky
	 * @return textovy retezec
	 */
	public String string(String format) {
		return String.format("["+format+";"+format+";"+format+"]",x,y,w);
	}
}