import javax.swing.JFrame;

public class FrameOfTheGame extends JFrame {

	FrameOfTheGame(){
		
		
		
		this.add(new PanelOfTheGame());
		this.setTitle("JavaSnakeGame");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		
		
	}
	
}
