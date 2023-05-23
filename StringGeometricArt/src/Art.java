

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class Art {
	
	private BufferedImage img;
	private Graphics2D g2d;
	private Color backgroundColor;
	private Color foregroundColor;
	private int width;
	private int height;
	private int nails;
	private PVector[] nail;
	private float tickness;
	private int[][] set;
	private int shape;
	
	public Art(JLabel lbl, Color backgroundColor, Color foregroundColor, int nails) {
		this.backgroundColor = backgroundColor;
		this.foregroundColor = foregroundColor;
		this.nails = nails;
		this.width = lbl.getWidth();
		this.height = lbl.getHeight();
		this.img = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
		this.g2d = img.createGraphics();
		this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.g2d.setBackground(this.backgroundColor);
		this.g2d.setColor(this.foregroundColor);
		this.tickness = 1f;
		this.nail = new PVector[nails];
		this.set = new int[6][];
		this.shape = 0; // Circle
	}
	
	private void createCoord() {
		switch(this.shape) {
		case 0: // Circle
			double incDegree = 2 * Math.PI / this.nails;
			int center = this.width/2;
			int radius = center - 10;
			
			for (int i=0; i< this.nails; i++) {
				int x = (int)(center+Math.cos((i*incDegree)+Math.PI)*radius);
				int y = (int)(center+Math.sin((i*incDegree)+Math.PI)*radius);
				nail[i] = new PVector(x,y);
			}
			break;
		case 1: // Square
			int nailOnSide = (this.nails + 4) / 4;
			double step = (this.width-20) / (double)(nailOnSide-1);
			int index = 0;
			int x=0;
			int y=0;
			for (int i = 0; i< nailOnSide-1; i++) {
				x = (int)(10 + i * step);
				y = 10;
				img.setRGB((int)x, (int)y, Color.BLUE.getRGB());;
				nail[index] = new PVector(x,y);
				index++;
			}
			for (int i = 0; i< nailOnSide-1; i++) {
				x = (int)(((nailOnSide-1) * step)+10);
				y = (int)(10 + i * step);
				img.setRGB((int)x, (int)y, Color.BLUE.getRGB());;
				nail[index] = new PVector(x,y);
				index++;
			}
			for (int i = 0; i< nailOnSide-1; i++) {
				x = (int)((((nailOnSide-1) * step)+20)-(10 + i * step));
				y = (int)(((nailOnSide-1) * step)+10);
				img.setRGB((int)x, (int)y, Color.BLUE.getRGB());;
				nail[index] = new PVector(x,y);
				index++;
			}
			for (int i = 0; i< nailOnSide-1; i++) {
				x = 10;
				y = (int)((((nailOnSide-1) * step)+20)-(10 + i * step));
				img.setRGB((int)x, (int)y, Color.BLUE.getRGB());;
				nail[index] = new PVector(x,y);
				index++;
			}
			break;
		}
	}
	
	public BufferedImage drawNails() {
		this.createCoord();
		this.g2d.setColor(this.backgroundColor);
		g2d.fillRect(0, 0, width, height);
		this.g2d.setColor(this.foregroundColor);
		for (int i=0; i< this.nails; i++) {
			this.img.setRGB(nail[i].x, nail[i].y, this.foregroundColor.getRGB());;
		}
		return this.img;
	}
	
	public void setBackground(Color c) {
		this.backgroundColor = c;
	}
	
	public BufferedImage delete() {
		this.g2d.setColor(this.backgroundColor);
		g2d.fillRect(0, 0, width, height);
		return this.img;
	}
	
	public void setNails(int n) {
		
		switch (this.shape) {
		case 0: // Circle
			this.nails = n;
			break;
		case 1: // Square
			this.nails = n * 4 - 4;
			break;
		default:
			break;
		}

		this.nail = new PVector[nails];
		this.createCoord();
		
	}
	
	public BufferedImage addString(int set, int a, int b, int c, int d, int r, Color col) {
		this.set[set] = new int[r*2+2];
		this.g2d.setColor(col);
		Stroke strokePhoto = new BasicStroke(this.tickness);
		g2d.setStroke(strokePhoto);
		
		int start = a;
		int dest = b;
		int stepAC = c-a;
		int stepBD = d-b;
		this.set[set][0]=a;
		this.set[set][1]=b;
		for(int i=0; i<r; i++) {
			int A = Math.abs(start%this.nails);
			int B = Math.abs(dest%this.nails);
			int C = Math.abs((start+stepAC)%this.nails);
			int D = Math.abs((dest+stepBD)%this.nails);
			this.set[set][i*2+2]=C;
			this.set[set][i*2+3]=D;
			g2d.drawLine(nail[A].x, nail[A].y, nail[B].x, nail[B].y);
			g2d.drawLine(nail[B].x, nail[B].y, nail[C].x, nail[C].y);
			g2d.drawLine(nail[C].x, nail[C].y, nail[D].x, nail[D].y);
			start = start + stepAC;
			dest = dest + stepBD;
		}
		return this.img;
	}

	public void setTickness(float t) {
		this.tickness = t;
	}
	
	public int[][] getSet() {
		return this.set;
	}
	
	public void setShape(int shape) {
		this.shape = shape;
	}
}
