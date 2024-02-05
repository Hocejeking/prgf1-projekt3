package Model;

import transforms.Cubic;
import transforms.Mat4;
import transforms.Point3D;

public class Ferguson extends Solid{
    private Cubic cubic;

    public Ferguson(final int smoothness, Point3D a, Point3D b, Point3D c, Point3D d) {
        Mat4 ferguson;
        ferguson=Cubic.FERGUSON;

        this.cubic = new Cubic(ferguson, a, b, c, d);
        for (int i = 0; i < smoothness; i++) {
            vertexBuffer.add(cubic.compute((double) i / smoothness));
            if (i != 0) {
                indexBuffer.add(i - 1);
                indexBuffer.add(i);
            }
        }
    }
}
