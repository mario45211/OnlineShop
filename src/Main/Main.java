package Main;

import java.awt.EventQueue;
import Frame.*;
import MainFrame.MainFrameController;
import MainFrame.MainFrameModel;
import MainFrame.MainFrameView;

public class Main {

	public static void main(String[] args) {

		
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				//new MainFrame("Komputerowo","src/file.dat");
				MainFrameModel model = new MainFrameModel("src/file.dat");
				MainFrameView view = new MainFrameView("Komputerowo");
				MainFrameController controller = new MainFrameController(model,view);
			}
		});
		
	}

}
