package Model;

import transforms.Mat4;
import transforms.Point3D;
import transforms.Cubic;

import java.util.ArrayList;
import java.util.List;

public class Coons extends Solid{
    private Cubic cubic;
    public Coons(final int smoothness, Point3D a,Point3D b,Point3D c,Point3D d) {
        Mat4 coons;
        coons=Cubic.COONS;

        this.cubic = new Cubic(coons, a, b, c, d);

        for (int i = 0; i < smoothness; i++) {
            vertexBuffer.add(cubic.compute((double) i / smoothness));

            if (i != 0) {
                indexBuffer.add(i - 1);
                indexBuffer.add(i);
            }
        }
    }
}


