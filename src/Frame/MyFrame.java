package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**klasa abstrakcyjna tworząca "szablon" okien graficznych**/
public abstract class MyFrame extends JFrame{
	
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Dimension screenDimension;
	public Dimension[] frameDimension;
	
	public MyFrame(String title){
		super(title);
		screenDimension = toolkit.getScreenSize();
		frameDimension = new Dimension[2]; //punkt poczatkowy na ekranie i rozmiar
		frameDimension[0] = new Dimension(screenDimension.width/3-200,screenDimension.height/3-200);
		frameDimension[1] = new Dimension(screenDimension.width/3+400,screenDimension.height/3+400);
		this.setBounds(frameDimension[0].width,frameDimension[0].height,frameDimension[1].width,frameDimension[1].height);
		
		this.getContentPane().setBackground(new Color(144,238,144));
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
