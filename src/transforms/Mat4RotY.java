package transforms;

public class Mat4RotY extends Mat4Identity {

	/**
	 * Vytvari transformacni matici 4x4 pro rotaci kolem osy y ve 3D
	 * 
	 * @param alpha
	 *            uhel rotace v radianech
	 */
	public Mat4RotY(double alpha) {
		mat[0][0] = Math.cos(alpha);
		mat[2][2] = Math.cos(alpha);
		mat[2][0] = Math.sin(alpha);
		mat[0][2] = -Math.sin(alpha);
	}
}