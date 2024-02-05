package transforms;

public class Vec2D {
	public double x, y;

	public Vec2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vec2D add(Vec2D v) {
		return new Vec2D(x + v.x, y + v.y);
	}

	public Vec2D mul(double d) {
		return new Vec2D(x * d, y * d);
	}

	public Vec2D mul(Vec2D v) {
		return new Vec2D(x * v.x, y * v.y);
	}
}
