import Raster.RasterBufferedImage;
import Rasterizer.*;
import Model.*;
import transforms.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Canvas3D{
    private JPanel panel;
    private RasterBufferedImage raster;
    private LineRasterizer lineRasterizer;
    private WiredRenderer.Renderer wiredRenderer;
    private double camAzimuth = Math.toRadians(-170);
    private double camZenith = Math.toRadians(-25);
    private double forward = 0;
    private double right = 0;

    private double zoom = 1.0;
    private double rotaceX = 0, rotaceY = 0, rotaceZ = 0;
    private double posunX = 0, posunY = 0, posunZ = 0;
    private boolean pohled = true;
    private int teleso = 0;
    private Timer timer;
    private boolean anim = false;
    private final double KROK_KAMERY = 0.1;
    private final double KROK_ROTACE_KAMERY = Math.toRadians(0.5);
    private final double KROK_ROTACE_KAMERY_MYS = Math.toRadians(0.1);
    private final double TELESO_STEP = 0.05;
    private final double KROK_ROTACE = 0.1;
    private final double KROK_POSUNU = 0.1;
    private int x1;
    private int y1;
    int mouseClicked = 0;

    Canvas3D(int width, int height) {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        raster = new RasterBufferedImage(width, height);
        lineRasterizer = new LineRasterizerGraphics(raster);
        wiredRenderer = new WiredRenderer.Renderer(raster);

        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                present(g);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        panel.requestFocus();
        panel.requestFocusInWindow();

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W: // priblizeni kamery
                        forward += KROK_KAMERY;
                        break;
                    case KeyEvent.VK_S: // oddaleni kamery
                        forward -= KROK_KAMERY;
                        break;
                    case KeyEvent.VK_A: // kamera posun vlevo
                        right -= KROK_KAMERY;
                        break;
                    case KeyEvent.VK_D: // kamera posun vpravo
                        right += KROK_KAMERY;
                        break;
                    case KeyEvent.VK_Q: // kamera notocit vlevo
                        camAzimuth -= KROK_ROTACE_KAMERY;
                        break;
                    case KeyEvent.VK_E: // kamera notocit vpravo
                        camAzimuth += KROK_ROTACE_KAMERY;
                        break;
                    case KeyEvent.VK_NUMPAD1: // rotace podleX nahoru
                        rotaceX += KROK_ROTACE;
                        break;
                    case KeyEvent.VK_NUMPAD2: // rotace podleX dolu
                        rotaceX -= KROK_ROTACE;
                        break;
                    case KeyEvent.VK_NUMPAD4: // rotace podleY nahoru
                        rotaceY += KROK_ROTACE;
                        break;
                    case KeyEvent.VK_NUMPAD5: // rotace podleY dolu
                        rotaceY -= KROK_ROTACE;
                        break;
                    case KeyEvent.VK_NUMPAD7: // rotace podleZ nahoru
                        rotaceZ += KROK_ROTACE;
                        break;
                    case KeyEvent.VK_NUMPAD8: // rotace podleZ dolu
                        rotaceZ -= KROK_ROTACE;
                        break;
                    case KeyEvent.VK_LEFT: // posun podleX nahoru
                        posunX += KROK_POSUNU;
                        break;
                    case KeyEvent.VK_RIGHT: // posun podleX dolu
                        posunX -= KROK_POSUNU;
                        break;
                    case KeyEvent.VK_UP: // posun podleY nahoru
                        posunY += KROK_POSUNU;
                        break;
                    case KeyEvent.VK_DOWN: // posun podleY dolu
                        posunY -= KROK_POSUNU;
                        break;
                    case KeyEvent.VK_PAGE_UP: // posun podleZ nahoru
                        posunZ += KROK_POSUNU;
                        break;
                    case KeyEvent.VK_PAGE_DOWN: // posun podleZ dolu
                        posunZ -= KROK_POSUNU;
                        break;
                    case KeyEvent.VK_C:
                        pohled = !pohled;
                        break;
                    case KeyEvent.VK_R:
                        posunX = posunY = posunZ = rotaceX = rotaceY = rotaceZ = 0;
                        break;
                    case KeyEvent.VK_1:
                        teleso = 1;
                        break;
                    case KeyEvent.VK_2:
                        teleso = 2;
                        break;
                    case KeyEvent.VK_3:
                        teleso = 3;
                        break;
                    case KeyEvent.VK_4:
                        teleso = 4;
                        break;
                    case KeyEvent.VK_P:
                        anim = !anim;
                        if (anim)
                            timer.start();
                        else
                            timer.stop();
                        break;

                }
                draw();
            }
        });

       panel.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseClicked = 0;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseClicked = e.getButton();
                x1 = e.getX();
                y1 = e.getY();
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });

        panel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                switch (mouseClicked) {
                    case MouseEvent.BUTTON1:
                        double a = (e.getY() - y1) * KROK_ROTACE_KAMERY_MYS;
                        double b = (e.getX() - x1) * KROK_ROTACE_KAMERY_MYS;

                        x1 = e.getX();
                        y1 = e.getY();

                        camAzimuth += b;
                        camZenith += a;
                        draw();

                        break;
                }
            }
        });
        panel.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    zoom += TELESO_STEP;
                } else if (e.getWheelRotation() > 0) {
                    zoom -= TELESO_STEP;
                }
                draw();
            }
        });
        ActionListener animaceEvent=new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rotaceX += KROK_ROTACE;
                draw();
                panel.repaint();
            }
        };
        timer = new Timer(10,animaceEvent);
        draw();
    }

    public void draw() {
        clear(0);
        Camera cam = new Camera();

        cam.setPosition(new Vec3D(10, 2, 5));
        WiredRenderer.Renderer renderer = new WiredRenderer.Renderer(raster);

        cam.setZenith(camZenith);
        cam.setAzimuth(camAzimuth);
        cam.forward(forward);
        cam.right(right);

        renderer.setViewport(cam.getViewMatrix());

        if (pohled) {
            renderer.setProjection(new Mat4PerspRH(1, 1, 1, 50));
        } else {
            renderer.setProjection(new Mat4OrthoRH(5 * ((double) renderer.getWidth() / renderer
                    .getHeight()), 5 * ((double) renderer.getWidth() / renderer.getHeight()),
                    0.01, 200 + forward));
        }


        renderer.zoom(zoom);
        renderer.posun(posunX, posunY, posunZ);
        renderer.setModel(((new Mat4RotX(rotaceX)).mul((new Mat4RotY(rotaceY))))
                .mul((new Mat4RotZ(rotaceZ))));

        renderer.draw(new Cube(), Color.YELLOW);
        renderer.draw(new Hexahedral(), Color.CYAN);
        renderer.drawAxis(new Axis("x"), Color.RED);
        renderer.drawAxis(new Axis("y"), Color.GREEN);
        renderer.drawAxis(new Axis("z"), Color.BLUE);

        renderer.draw(new Coons(30000,new Point3D(-1,-2,2),new Point3D(-0.5,-2.5,0),new Point3D(0,-3,2),new Point3D(2,-6,3)),Color.MAGENTA);
        renderer.draw(new Bezier(30000, new Point3D(-1,-3,1),new Point3D(-0.5,-3,0),new Point3D(1,-3,2),new Point3D(2,-3,3)), Color.ORANGE);
        renderer.draw(new Ferguson(30000,new Point3D(1,0,0),new Point3D(0,0,0),new Point3D(0,1,1),new Point3D(0,0,1)), Color.MAGENTA);

        panel.repaint();
    }

    public void present(Graphics graphics) {
        raster.repaint(graphics);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Canvas3D(800, 600));
    }

    public void clear(int color) {
        raster.setClearColor(color);
        raster.clear();
    }
}

