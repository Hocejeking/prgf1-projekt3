package transforms;

public class Quat {
	public double r, i, j, k;

	public Quat(double r, double i, double j, double k) {
		this.i = i;
		this.j = j;
		this.k = k;
		this.r = r;
	}

	public Quat add(Quat q) {
		return new Quat(r + q.r, i + q.i, j + q.j, k + q.k);
	}

	public Quat mul(double a) {
		return new Quat(a * r, a * i, a * j, a * k);
	}

	public Quat mulR(Quat q) {
		return new Quat(this.r * q.r - this.i * q.i - this.j * q.j - this.k
				* q.k, this.r * q.i + this.i * q.r + this.j * q.k - this.k
				* q.j, this.r * q.j - this.i * q.k + this.j * q.r + this.k
				* q.i, this.r * q.k + this.i * q.j - this.j * q.i + this.k
				* q.r);
	}

	public Quat mul(Quat q) {
		return mulR(q);
	}

	public double norma() {
		return Math.sqrt(r * r + i * i + j * j + k * k);
	}

	public Quat inv() {
		double norm = this.norma();
		norm = norm * norm;
		if (norm > 0)
			return new Quat(this.r / norm, -this.i / norm, -this.j / norm,
					-this.k / norm);
		else
			return new Quat(0, 0, 0, 0);
	}

}
