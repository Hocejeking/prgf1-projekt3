package Rasterizer;
import Raster.*;

import java.awt.*;
    public class LineRasterizerGraphics extends LineRasterizer {

        public LineRasterizerGraphics(RasterBufferedImage raster) {
            super(raster);
        }

        @Override
        public void rasterizeLine(int x1, int y1, int x2, int y2, int color) {
            Graphics g = ((RasterBufferedImage)raster).getImg().getGraphics();
            g.setColor(new Color(color));
            g.drawLine(x1, y1, x2, y2);
        }

    }


