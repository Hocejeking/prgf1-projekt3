package transforms;

public class Mat4RotZ extends Mat4Identity {

	/**
	 * Vytvari transformacni matici 4x4 pro rotaci kolem osy Z ve 3D
	 * 
	 * @param alpha
	 *            uhel rotace v radianech
	 */
	public Mat4RotZ(double alpha) {
		mat[0][0] = Math.cos(alpha);
		mat[1][1] = Math.cos(alpha);
		mat[1][0] = -Math.sin(alpha);
		mat[0][1] = Math.sin(alpha);
	}
}