package Rasterizer;
import Model.Point;

public interface Rasterizer {
    void rasterizeLine(Point a, Point b, int color);
    void rasterizeLine(int x1, int x2, int y1, int y2, int color);
}
