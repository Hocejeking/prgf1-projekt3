package Model;

import transforms.Cubic;
import transforms.Mat4;
import transforms.Point3D;

public class Bezier extends Solid {
    private Cubic cubic;

    public Bezier(final int smoothness, Point3D a,Point3D b,Point3D c,Point3D d) {
        Mat4 bezier;
        bezier=Cubic.BEZIER;

        this.cubic = new Cubic(bezier, a, b, c, d);
        for (int i = 0; i < smoothness; i++) {
            vertexBuffer.add(cubic.compute((double) i / smoothness));
            if (i != 0) {
                indexBuffer.add(i - 1);
                indexBuffer.add(i);
            }
        }
    }
}
