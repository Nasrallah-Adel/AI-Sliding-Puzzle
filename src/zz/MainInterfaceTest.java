package zz;

import javax.swing.JFrame;

/**
 * The Class MainInterfaceTest.
 * @author Manoj Shrestha
 */
public class MainInterfaceTest {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		MainInterface frame = new MainInterface("8-Puzzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();

		frame.setResizable(false);

		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		// frame.solve();
	}
}
