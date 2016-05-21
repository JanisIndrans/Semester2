package guiLayer;
import javax.swing.SwingUtilities;


public class Run {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try{
				LogIn lg = new LogIn();//MainUI();
				lg.setVisible(true);
				lg.setLocationRelativeTo(null);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}

}
