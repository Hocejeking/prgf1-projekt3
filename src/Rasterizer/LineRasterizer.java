package Rasterizer;
import Model.*;
import Raster.RasterBufferedImage;

import static java.lang.Math.abs;

public class LineRasterizer implements Rasterizer{
    public RasterBufferedImage raster;

    public LineRasterizer(RasterBufferedImage raster){
        this.raster = raster;
    }

    @Override
    public void rasterizeLine(int x1, int x2, int y1, int y2, int color) {
        int dx = abs(x2 - x1), sideX = x1 < x2 ? 1 : -1; //nastavíme směr pohybu (doleva[-1], doprava[1])
        int dy = -abs(y2 - y1), sideY = y1 < y2 ? 1 : -1;//nastavíme směr pohybu (dolů[-1], nahoru[1])
        int err = dx + dy; //nastavíme chybu
        int foo;//pomocná proměnná
        while (true) {
            raster.setPixel(x1, y1, color); //obarvíme jeden pixel na souřadnicích
            if (x1 == x2 && y1 == y2) { //pokud je dosáhnuto koncové bodu, ukončíme smyčku
                break;
            }
            foo = err << 1; //násobení 2
            if (foo > dy) { //pokud se pohybujeme po ose X, zvýšíme  chybu o rozdíl souřadnic a posunume po ose x ve správném směru (sideX)
                err += dy;
                x1 += sideX;
            }
            if (foo < dx) { //pokud se pohybujeme po ose Y, zvýšíme  chybu o rozdíl souřadnic a posunume po ose y ve správném směru (sideY)
                err += dx;
                y1 += sideY;
            }
        }
    }
    @Override
    public void rasterizeLine(Point a, Point b, int color){
        int dx = abs(b.x - a.x), sideX = a.x < b.x ? 1 : -1; //nastavíme směr pohybu (doleva[-1], doprava[1])
        int dy = -abs(b.y - a.y), sideY = a.y < b.y ? 1 : -1;//nastavíme směr pohybu (dolů[-1], nahoru[1])
        int err = dx + dy; //nastavíme chybu
        int foo;//pomocná proměnná
        while (true) {
            raster.setPixel(a.x, a.y, color); //obarvíme jeden pixel na souřadnicích
            if (a.x == b.x && a.y == b.y) { //pokud je dosáhnuto koncové bodu, ukončíme smyčku
                break;
            }
            foo = err << 1; //násobení 2
            if (foo > dy) { //pokud se pohybujeme po ose X, zvýšíme  chybu o rozdíl souřadnic a posunume po ose x ve správném směru (sideX)
                err += dy;
                a.x += sideX;
            }
            if (foo < dx) { //pokud se pohybujeme po ose Y, zvýšíme  chybu o rozdíl souřadnic a posunume po ose y ve správném směru (sideY)
                err += dx;
                a.y += sideY;
            }
        }
    }
}
