package WiredRenderer;

import Raster.RasterBufferedImage;
import transforms.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import Model.Solid;

public class Renderer {

	private BufferedImage img;
	private Mat4 modelMatrix = new Mat4Identity() ;
	private Mat4 viewMatrix = new Mat4Identity();
	private Mat4 projectionMatrix = new Mat4Identity() ;
	private double scale = 1;
	private double posunX, posunY, posunZ = 0;
	public int width;
	public int height;
	private double wmin = 0.1;
	
	
	public Renderer(RasterBufferedImage img) {
		this.img = img.getImg();
		width = img.getWidth();
		height = img.getHeight();
	}
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	public void setModel(Mat4 modelMatrix) {
		this.modelMatrix = modelMatrix;
	}

	public void setViewport(Mat4 viewMatrix) {
		this.viewMatrix = viewMatrix;
	}

	public void setProjection(Mat4 projectionMatrix) {
		this.projectionMatrix = projectionMatrix;
	}
	
	public void zoom(double d) {
		this.scale = d;
	}

	public void posun(double x, double y, double z) {
		this.posunX = x;
		this.posunY = y;
		this.posunZ = z;
	}

	public void draw(Solid s, Color barva) {
		List<Point3D> vertexList = s.getVertexBuffer();	
		List<Point3D> transformedVertices = new ArrayList<Point3D>();
		modelMatrix = modelMatrix.mul(new Mat4Scale(scale, scale, scale));
		modelMatrix = modelMatrix.mul(new Mat4Transl(posunX, posunY, posunZ));
		
		Mat4 finalMatrix = modelMatrix.mul(viewMatrix.mul(projectionMatrix));
	
		for (Point3D point : vertexList) {
			Point3D a = point.mul(finalMatrix);
			transformedVertices.add(a);
		}
		
		for (int i = 0; i < s.getIndexBuffer().size() - 1; i += 2) {
			
			int indexA = s.getIndexBuffer().get(i);
			int indexB = s.getIndexBuffer().get(i + 1);
			
			Point3D point3DA = transformedVertices.get(indexA);
			Point3D point3DB = transformedVertices.get(indexB);

			drawLine(point3DA, point3DB, barva);
		}
	}

	public void drawAxis(Solid s, Color barva){
		List<Point3D> vertexList = s.getVertexBuffer();
		List<Point3D> transformedVertices = new ArrayList<Point3D>();

		Mat4 finalMatrix = viewMatrix.mul(projectionMatrix);

		for (Point3D point : vertexList) {
			Point3D a = point.mul(finalMatrix);
			transformedVertices.add(a);
		}

		for (int i = 0; i < s.getIndexBuffer().size() - 1; i += 2) {

			int indexA = s.getIndexBuffer().get(i);
			int indexB = s.getIndexBuffer().get(i + 1);

			Point3D point3DA = transformedVertices.get(indexA);
			Point3D point3DB = transformedVertices.get(indexB);

			drawLine(point3DA, point3DB, barva);
		}
	}

	private void drawLine(Point3D a, Point3D b, Color barva) {
		Graphics g = img.getGraphics();

		if (a.w < b.w) {Point3D x = a; a = b; b = x; }
		if (a.w < wmin)
			return;
		if (b.w < wmin) {
			double t = (a.w-wmin)/(a.w - b.w);
			a = a.mul(1-t).add(b.mul(t));
		}

		Vec3D vA = a.dehomog();
		Vec3D vB = b.dehomog();

		vA.x = 0.5*(width-1)*(vA.x+1);
		vB.x = 0.5*(width-1)*(vB.x+1);
		vA.y = 0.5*(height-1)*(1-vA.y);
		vB.y = 0.5*(height-1)*(1-vB.y);

		g.setColor(barva);
		g.drawLine((int) vA.x, (int) vA.y, (int) vB.x, (int) vB.y);
	}

}
