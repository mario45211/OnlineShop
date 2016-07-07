package Other;

import java.awt.Dimension;
import java.io.Serializable;

public class Dimension3D extends Dimension implements Serializable{
	
	public int depth;
	
	public Dimension3D(int width, int height, int depth){
		super(width, height);
		this.depth = depth;
	}
	public int getDepth(){
		return this.depth;
	}
}
