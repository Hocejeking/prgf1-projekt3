package Model;

import transforms.Point3D;

public class Axis extends Solid {
    public Axis(String osa) {
        if (osa == "y") {
            vertexBuffer.add(new Point3D(0,0,0));
            vertexBuffer.add(new Point3D(0,5,0));
            indexBuffer.add(0);indexBuffer.add(1);
        }  else if (osa == "z") {
            vertexBuffer.add(new Point3D(0,0,0));
            vertexBuffer.add(new Point3D(0,0,5));
            indexBuffer.add(0);indexBuffer.add(1);
        } else {
            vertexBuffer.add(new Point3D(0,0,0));
            vertexBuffer.add(new Point3D(5,0,0));
            indexBuffer.add(0);indexBuffer.add(1);
        }
    }
}
