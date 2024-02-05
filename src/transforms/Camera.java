package transforms;

public class Camera {
	double azimuth, radius, zenith;

	boolean firstPerson; // true -> 1st person, 0 -> 3rd person

	Vec3D eye, eyeVector, pos;

	Mat4 view;

	void computeMatrix() {
		eyeVector = new Vec3D(Math.cos(azimuth) * Math.cos(zenith),
				Math.sin(azimuth) * Math.cos(zenith), Math
						.sin(zenith));
		if (firstPerson) {
			eye = new Vec3D(pos);
			view = new Mat4ViewRH(pos, eyeVector.mul(radius), new Vec3D(
					Math.cos(azimuth) * Math
							.cos(zenith + Math.PI / 2), Math
							.sin(azimuth) * Math.cos(zenith + Math.PI / 2),
					Math.sin(zenith + Math.PI / 2)));
		} else {
			eye = pos.add(eyeVector.mul(-1 * radius));
			view = new Mat4ViewRH(eye, eyeVector.mul(radius), new Vec3D(
					Math.cos(azimuth) * Math
							.cos(zenith + Math.PI / 2), Math
							.sin(azimuth) * Math.cos(zenith + Math.PI / 2),
					Math.sin(zenith + Math.PI / 2)));
		}
	}

	public Camera() {
		azimuth = zenith = 0.0f;
		radius = 1.0f;
		pos = new Vec3D(0.0f, 0.0f, 0.0f);
		firstPerson = true;
		computeMatrix();
	}

	public void setAzimuth(double ang) {
		azimuth = ang;
		computeMatrix();
	}

	public void setZenith(double ang) {
		zenith = ang;
		computeMatrix();
	}

	public void forward(double speed) {
		pos = pos.add(new Vec3D(
				Math.cos(azimuth) * Math.cos(zenith), Math
						.sin(azimuth) * Math.cos(zenith), Math
						.sin(zenith)).mul(speed));
		computeMatrix();
	}

	public void right(double speed) {
		pos = pos.add(new Vec3D(Math.cos(azimuth - Math.PI / 2),
				Math.sin(azimuth - Math.PI / 2), 0.0f).mul(speed));
		computeMatrix();

	}

	public void setPosition(Vec3D apos) {
		pos = new Vec3D(apos);
		computeMatrix();
	}

	public Mat4 getViewMatrix() {
		return view;
	}
}
