import javax.swing.JFrame;

public class Play{
	public static void main(String args[])
	{
		//�⺻ ����.
		Test frame;
		frame = new Test("Play");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		frame.pack();
		frame.setVisible(true);
	}//main()
}