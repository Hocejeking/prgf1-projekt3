package Model;

import transforms.Point3D;

public class Hexahedral extends Solid {
    public Hexahedral() {
        vertexBuffer.add(new Point3D(0.5,0,0.5));
        vertexBuffer.add(new Point3D(0.875,0.125,0.5));
        vertexBuffer.add(new Point3D(1,0.5,0.5));
        vertexBuffer.add(new Point3D(0.875,0.875,0.5));
        vertexBuffer.add(new Point3D(0.5,1,0.5));
        vertexBuffer.add(new Point3D(0.125,0.875,0.5));
        vertexBuffer.add(new Point3D(0,0.5,0.5));
        vertexBuffer.add(new Point3D(0.125,0.125,0.5));
        vertexBuffer.add(new Point3D(0.5,0.5,1));
        vertexBuffer.add(new Point3D(0.5,0.5,0));

        indexBuffer.add(0);
        indexBuffer.add(1);
        indexBuffer.add(1);
        indexBuffer.add(2);
        indexBuffer.add(2);
        indexBuffer.add(3);
        indexBuffer.add(3);
        indexBuffer.add(4);
        indexBuffer.add(4);
        indexBuffer.add(5);
        indexBuffer.add(5);
        indexBuffer.add(6);
        indexBuffer.add(6);
        indexBuffer.add(7);
        indexBuffer.add(7);
        indexBuffer.add(0);

        indexBuffer.add(0);
        indexBuffer.add(8);
        indexBuffer.add(1);
        indexBuffer.add(8);
        indexBuffer.add(2);
        indexBuffer.add(8);
        indexBuffer.add(3);
        indexBuffer.add(8);
        indexBuffer.add(4);
        indexBuffer.add(8);
        indexBuffer.add(5);
        indexBuffer.add(8);
        indexBuffer.add(6);
        indexBuffer.add(8);
        indexBuffer.add(7);
        indexBuffer.add(8);

        indexBuffer.add(9);
        indexBuffer.add(0);
        indexBuffer.add(9);
        indexBuffer.add(1);
        indexBuffer.add(9);
        indexBuffer.add(2);
        indexBuffer.add(9);
        indexBuffer.add(3);
        indexBuffer.add(9);
        indexBuffer.add(4);
        indexBuffer.add(9);
        indexBuffer.add(5);
        indexBuffer.add(9);
        indexBuffer.add(6);
        indexBuffer.add(9);
        indexBuffer.add(7);
    }
}
