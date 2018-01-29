package DrawPackageOne;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
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

public class ImageExp extends JPanel {
	private static final boolean DEBUG_GRAPHICS_LOADED = false;
	BufferedImage image;
	Dimension size = new Dimension();

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

	public static void main(String[] args) throws IOException {
		String path = System.getProperty("user.dir") + "/legoWorker.jpg";
		BufferedImage image = ImageIO.read(new File(path));
		ImageExp test = new ImageExp(image);
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new JScrollPane(test));
		f.setSize(400, 400);
		f.setLocation(200, 200);
		f.setVisible(true);

		int mouseX = 0;
		int mouseY = 0;

		while (true) {
			mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
			mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();

			int panX = (int) f.getX() + 9;
			int panY = (int) f.getY() + 31;

			int RADIUS = 10;

			System.out.println("x: " + mouseX);
			System.out.println("y: " + mouseY);

			if ((mouseX > panX) && (mouseY > panY)) {
				for (int li = 0; li < RADIUS; li++) {
					for (int li2 = 0; li2 < RADIUS; li2++) {
						image.setRGB(mouseX - panX + li, mouseY - panY + li2, 0);
					}
				}
				test.getRootPane().revalidate();
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