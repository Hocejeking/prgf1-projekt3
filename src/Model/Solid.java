package Model;
import transforms.Mat4;
import transforms.Mat4Identity;
import transforms.Point3D;
import java.util.ArrayList;

public class Solid {
    protected ArrayList<Point3D> vertexBuffer = new ArrayList<>();
    protected ArrayList<Integer> indexBuffer = new ArrayList<>();
    protected Mat4 model = new Mat4Identity();

    public ArrayList<Point3D> getVertexBuffer() {
        return vertexBuffer;
    }

    public ArrayList<Integer> getIndexBuffer() {
        return indexBuffer;
    }
}


