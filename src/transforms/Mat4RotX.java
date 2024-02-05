package transforms;

public class Mat4RotX extends Mat4Identity {

	/**
	 * Vytvari transformacni matici 4x4 pro rotaci kolem osy X ve 3D
	 * 
	 * @param alpha
	 *            uhel rotace v radianech
	 */
	public Mat4RotX(double alpha) {
		mat[1][1] = Math.cos(alpha);
		mat[2][2] = Math.cos(alpha);
		mat[2][1] = -Math.sin(alpha);
		mat[1][2] = Math.sin(alpha);
	}
}