package DrawPackageOne;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DrawPackageOne.MouseTwo.HandlerClass;

public class ImageExp extends JPanel {
	private static final boolean DEBUG_GRAPHICS_LOADED = false;
	BufferedImage image;
	Dimension size = new Dimension();
	
	static final int XDIM = 280;
	static final int YDIM = 280;

	public ImageExp(BufferedImage image) {
		this.image = image;
		size.setSize(image.getWidth(), image.getHeight());
	}

	/**
	 * Drawing an image can allow for more flexibility in processing/editing.
	 */
	protected void paintComponent(Graphics g) {
		// Center image in this component.
		int x = (getWidth() - size.width) / 2;
		int y = (getHeight() - size.height) / 2;
		g.drawImage(image, x, y, this);
	}

	public Dimension getPreferredSize() {
		return size;
	}

	public static boolean mDown = false;
	

	
	public static void main(String[] args) throws IOException {
		

		String path = System.getProperty("user.dir") + "/legoWorker.jpg";
		BufferedImage image = new BufferedImage(XDIM, YDIM, 1); //ImageIO.read(new File(path));
		ImageExp test = new ImageExp(image);
		MouseTwo f = new MouseTwo();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new JScrollPane(test));
		
		HandlerClassOne handler = new HandlerClassOne();
		test.addMouseListener(handler);
		
		f.setSize(XDIM + 30, YDIM + 60);
		f.setLocation(400, 400);
		f.setVisible(true);

		int mouseX = 0;
		int mouseY = 0;

		while (true) {
			mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
			mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();

			int panX = (int) f.getX() + 9;
			int panY = (int) f.getY() + 50;

			//System.out.println(HandlerClassOne.msDown());
			
			int RADIUS = 10;

			//System.out.println("x: " + mouseX);
			//System.out.println("y: " + mouseY);
			int xx = 0;

			if ((mouseX > panX + RADIUS) && (mouseY > panY + RADIUS) && (mouseY < panY + YDIM - RADIUS) && (mouseX < panX + YDIM - RADIUS) && HandlerClassOne.msDown()) {
				for (int bi = 0; bi < 1; bi++) {
					for (double cr = 0; cr < RADIUS; cr += .5) {
						for (int li2 = 0; li2 < 2*cr; li2++) {
							//image.setRGB(mouseX - panX + li, mouseY - panY + li2, 16777215);
							
							xx =(int)( -cr + (double) li2);
							image.setRGB(mouseX - panX + xx + bi, mouseY - panY + (int) (Math.sqrt(cr*cr - xx*xx)), 16777215);
							image.setRGB(mouseX - panX + xx + bi, mouseY - panY - (int) (Math.sqrt(cr*cr - xx*xx)), 16777215);
							image.setRGB(mouseX - panX + (int) (Math.sqrt(cr*cr - xx*xx)), mouseY - panY + xx + bi, 16777215);
							image.setRGB(mouseX - panX - (int) (Math.sqrt(cr*cr - xx*xx)), mouseY - panY + xx + bi, 16777215);
						}
					}
				}
				//test.getRootPane().revalidate();
				f.repaint();
			}
		}

		// showIcon(image);
	}

	/**
	 * Easy way to show an image: load it into a JLabel and add the label to a
	 * container in your gui.
	 */
	private static void showIcon(BufferedImage image) {
		ImageIcon icon = new ImageIcon(image);
		JLabel label = new JLabel(icon, JLabel.CENTER);
		JOptionPane.showMessageDialog(null, label, "icon", -1);
	}
}