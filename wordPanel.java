//********************************************************************
//  
//	�ܾ��� ȭ��.
//  
//********************************************************************
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class wordPanel extends JPanel
{
	private boolean modeSelect;
	private BufferedReader bufferedReader;
	private ButtonListener btnListener;;
	private FileReader fileReader;
	private JButton flip;
	private JButton goMain;
	private JButton prev,next;
	private JButton back;
	private JLabel cardLabel,card, labelIndex;
	private JLabel[][] scrollLabel = new JLabel[30][2];
	private JLabel[] scrollCard = new JLabel[30];
	private JPanel scrollPanel,wordBucket;
	private MouseListener mouseListener;
	private Point wordIndex;
	private JScrollPane scrollPane; 
	private static String[][] word= new String[30][2];
	private Test test;
	
	public wordPanel() 
	{
		
		//�⺻ ����.
		setPreferredSize(new Dimension(1028,720)); //wordPanel�� ũ�� ���� 
		setBackground(new Color(255,255,230)); // wordPanel�� ���� ���� 
		setLayout(null); // ��ġ�� ����� ���Ǵ�� ��
		
		modeSelect=true; // ��ũ�Ѱ� ī������ ������ �ϴ� ������ ����
		
		wordBucket= new JPanel(); // JScrollPane�� �� �ܾ ��� panel
		wordBucket.setPreferredSize(new Dimension(780,4220)); //ũ�� ����
		wordBucket.setBounds(114,120,800,480); // ��� ����
		wordBucket.setBackground(Color.white); // ���� ����
		wordBucket.setLayout(null); // ��ġ ����
		wordBucket.setVisible(true); // ���� ����
		
		scrollPane=new JScrollPane(wordBucket,// �ܾ� �г��� ���� JScrollPane 
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, // ��ũ�ѹ� ������ ������ ���ÿ� ����
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // �־��� �гε� ���ڷ� ����
        scrollPane.setBounds(114,120,800,480); // ��� ����
        scrollPane.setVisible(false); //���� ��
        add(scrollPane);//wordBucket�� ������
		
		
		
		// ī�带 Ŭ���ϸ� ���� �����ֱ� ���� mouseListener�� ���� �� �߰� 
		mouseListener= new MListener(); 
		this.addMouseListener(mouseListener);
		
		 

		//word�迭�� �ε����� �����ϴ� Point������ ��
		wordIndex=new Point();
		 
		//ī�� ��忡�� ��ư�� ����� ���� LAbel���� �� ���� 
		card = new JLabel(new ImageIcon("img\\card.png"),SwingConstants.CENTER); 
		card.setBounds(100,150,800,480);
		//wordPanel�� ������
		add(card);
		 
		 
		cardLabel= new JLabel(); //ī���忡�� �۾��� ������
		cardLabel.setBounds(10,10,800,450); //��� ����
		cardLabel.setFont(new Font("����",Font.BOLD,100)); //�۾�ü ����
		cardLabel.setVerticalAlignment(SwingConstants.CENTER); // ���� ���� ����
		cardLabel.setHorizontalAlignment(SwingConstants.CENTER);//���� ���� ����
		cardLabel.setVisible(true); //���� ����
		card.add(cardLabel); // ī�忡 ������
		
		
		labelIndex = new JLabel("1/30"); // ���° �ܾ �����ִ��� �˷��ִ� label
		labelIndex.setBounds(690,30,100,25); //��� ����
		labelIndex.setFont(new Font("����",Font.BOLD,20));//�۾�ü ����
		labelIndex.setVerticalAlignment(SwingConstants.CENTER); // ���� ����
		labelIndex.setHorizontalAlignment(SwingConstants.CENTER);//���� ����
		card.add(labelIndex); // ī�忡 ������
		

			
		
		
		for(int i=0;i<30;++i) //wordBucket�� �� Label���� ���� �� ��ġ
		{
			scrollLabel[i][0]=new JLabel(); // ����
			scrollLabel[i][0].setVerticalAlignment(SwingConstants.CENTER); //���� ����
			scrollLabel[i][0].setHorizontalAlignment(SwingConstants.CENTER);//���� ����
			scrollLabel[i][0].setBounds(20,140*i+20,350,100);// ��� ����
			scrollLabel[i][0].setFont(new Font("����",Font.BOLD,50));// �۾�ü ����
			wordBucket.add(scrollLabel[i][0]); //ī�忡 ������
			
			scrollLabel[i][1]=new JLabel(); //���� 
			scrollLabel[i][1].setVerticalAlignment(SwingConstants.CENTER);//���� ����
			scrollLabel[i][1].setHorizontalAlignment(SwingConstants.CENTER);//���� ����
			scrollLabel[i][1].setBounds(410,140*i+20,350,100); //��� ����
			scrollLabel[i][1].setFont(new Font("����",Font.BOLD,50));//�۾�ü ���� 
			wordBucket.add(scrollLabel[i][1]); // ī�忡 ������
		}
		
		for(int i=0;i<30;++i) // wordBucket �ܾ���� �޹���� �� label�� ���� �� ��
		{
			scrollCard[i]=new JLabel(new ImageIcon("img\\scrollCard.png"),SwingConstants.CENTER); // ������ ���ÿ� ����̹����� ���� 
			scrollCard[i].setBounds(20,140*i+20,740,100); // ��� ����
			wordBucket.add(scrollCard[i]); // wordBucket�� ������ 
		}
		
		//�޴� ����.
		
		goMain = new JButton (new ImageIcon("img\\home.png")); // �������� ���� ���� ��ư ������ ���ÿ� ��ư�̹����� ������
		goMain.setBounds(10,10,54,54); // ��� ���� 
		goMain.setContentAreaFilled(false); // ����� ä��� ����
		goMain.setBorderPainted(false); // ��輱 ��ĥ ����
		goMain.addActionListener (new ButtonListener()); //ActionListener�� ������ 
		goMain.setPressedIcon(new ImageIcon("img\\home_p.png"));// ����̹��� ����
		add(goMain);//wordPanel�� ������ 
		
		back = new JButton(new ImageIcon("img\\back.png"));// ��¥ ���� ȭ������ �������� ��ư ������ ���ÿ� ��ư�̹����� ������ 
		back.addActionListener (new ButtonListener()); //ActionListener�� ������ 
		back.setBounds(74,10,54,54);//��� ����
		back.setContentAreaFilled(false);// ����� ä��� ����
		back.setBorderPainted(false); //��� ��ĥ ����
		back.setVisible(true);//���� ����
		back.setPressedIcon(new ImageIcon("img\\back_p.png"));// �������� ��ư�̹��� ����
		add(back); //wordPanel�� ������
		
		prev = new JButton(new ImageIcon("img\\prev.png"));// ī���忡�� �մܾ�� �̵��ϱ� ���� ��ư ������ ���ÿ� ��ư �̹����� ������
		prev.addActionListener (new ButtonListener()); ////ActionListener�� ������ 
		prev.setBounds(10,210,100,50); // ��� ����
		prev.setContentAreaFilled(false); // ����� ä��� ����
		prev.setBorderPainted(false); // ��輱 ��ĥ  ����
		prev.setVisible(false); // ���� ����
		card.add(prev); //crad�� ������
		
		next = new JButton(new ImageIcon("img\\next.png"));// ī���忡�� �ڴܾ�� �̵��ϱ� ���� ��ư ������ ���ÿ� ��ư �̹����� ������
		next.addActionListener (new ButtonListener());//ActionListener�� ������ 
		next.setBounds(690,210,100,50); //��� ����
		next.setContentAreaFilled(false);// ����� ä��� ����
		next.setBorderPainted(false); // ��輱 ��ĥ ����
		next.setVisible(true);// ���� ����
		card.add(next);//card�� ������ 
		
		flip = new JButton(new ImageIcon("img\\change.png")); // ī���忡�� ��ũ�θ��� �ٲٱ� ���� ��ư ������ ���ÿ� ��ư �̹����� ������
		flip.addActionListener (new ButtonListener());//ActionListener�� ������ 
		flip.setBounds(964,10,54,54);//��� ����
		flip.setContentAreaFilled(false);//����� ä��� ����
		flip.setBorderPainted(false);// ��輱 åĥ���� 
		flip.setVisible(true);//���� ����
		flip.setPressedIcon(new ImageIcon("img\\change_p.png"));//�������� �̹��� ����
		add(flip);// wordPanel�� ������ 
		
		
		
		
	}//wordPanel()
	
	
	
	public static String getWordArr(int i, int j) // ���� ������� ���� ������ �ܾ �ε����� ���� ��ȯ 
	{
		return word[i][j];
	}
	
	private class ButtonListener implements ActionListener //��ư �̺�Ʈ�� �Ͼ� ������ ó���ϱ� ���� �̳� Ŭ����
	{
		public void actionPerformed(ActionEvent event)
		{
			Object obj= event.getSource();
			
			if(obj==goMain) // go main ��ư�� ������ �ܿ�
			{
				//�г� ��ȯ.
				test.changePanel("main");// �������� ȭ���� ��ȯ
				
			}else if(obj==prev){ //prev ��ư�� ������ ���
				if(wordIndex.x>0){   // ��ó�� �ܾ �ƴ϶��
					wordIndex.x-=1; //���� �ܾ� �ε����� 1�� ���̰�
					wordIndex.y=0;  // ī�尡 ���� ���̴� ��� �ٽ� ���ܾ ���̰� ���ְ�
					cardLabel.setText(word[wordIndex.x][wordIndex.y]);	// ����� �ε����� �°� ���� ���� �ϰ�
					if(wordIndex.x<1)prev.setVisible(false); // ù�ܾ� �ϰ�� prev ��ư�� �Ⱥ��̰� �ϰ�
					if(wordIndex.x<29) next.setVisible(true); // ������ �ܾ �ƴҰ�� next ��ư�� ���̰� �Ѵ�.
				}
			}else if(obj==next){
				if(wordIndex.x<29){ // ������ �ܾ �ƴ϶�� 
					wordIndex.x+=1; // ���� �ܾ� �������� 1�� �ø��� 
					wordIndex.y=0; // �ܾ��� ���� �����ְ� ���� ��� �ٽ� ��� �����ֵ��� ���� 
					cardLabel.setText(word[wordIndex.x][wordIndex.y]);// ���� �ε����� �¼� �� ����
					if(wordIndex.x>28) next.setVisible(false); // ������ �ܾ��� next ��ư�� �Ⱥ��̵��� ����
					if(wordIndex.x>0) prev.setVisible(true); // ù�ܾ �ƴ϶�� prev ��ư�� ���̵��� ����
					}	
			}else if(obj==flip){ // ��带 �ٲٴ� ���
				modeSelect=!modeSelect; // ��� ���� ������ ���� ! ��Ű��
				cardLabel.setVisible(modeSelect);// ī���� �� ���� ����
				scrollPane.setVisible(!modeSelect); // ��ũ�� ��� JScrollPane ���� 
				card.setVisible(modeSelect); // ī���� ��ư�� ��� ���� ����
			}else if(obj==back){ // bakc ��ư�� ������ ���
			
			test.changePanel("day");// ��¥ ���� ȭ������ ���ư�;
			}
			
			
			labelIndex.setText(wordIndex.x+1+"/30");// ī�� ��忡�� ���� ���° �ܾ ���� �ִ��� ����� ������ ���� 
			
		}//actionPerformed()
		
	}//ButtonListener class
	
	
	private class MListener implements MouseListener // ���콺 Ŭ�� �̺�Ʈ�� ó���ϱ� ���� �̳� Ŭ���� 
	{
		public void mouseClicked(MouseEvent event){ // ���콺�� Ŭ���� ��� 
			Point mousePoint=new Point();
			mousePoint= event.getPoint();
			
			if(mousePoint.x>114&mousePoint.x<914&mousePoint.y>120&mousePoint.y<600){ // ī�峻�� �������� Ȯ��
				 if(wordIndex.y==1) wordIndex.y=0; // �ܾ�� ���� ���̸� �ܾ �����ֵ��� ����
				 else wordIndex.y=1;
				 cardLabel.setText(word[wordIndex.x][wordIndex.y]); // ����� ������ �󺧿� �ݿ� 
			}
			
			
			}//mouseClicked()
		public void mousePressed(MouseEvent event){}
		public void mouseReleased(MouseEvent event){}
		public void mouseEntered(MouseEvent event){}
		public void mouseExited(MouseEvent event){}
		
		
	}// MListener class
	
	public void setTest(Test reference) //up call�� �̿��Ͽ� ȭ�� ��ȯ�� ���� �Լ� 
	{
		test=reference;
	}
	
	public void init() // �ʱ�ȭ �� ���ֱ� ���� �Լ� 
	{
		wordIndex.x=wordIndex.y=0;  // ù �ܾ �������� ����
		labelIndex.setText("1/30"); // ù�ܾ ���� ������ �˼� �ֵ��� ����
		prev.setVisible(false); // ������ prev ��ư�� �Ⱥ��̵��� ����
		
		modeSelect=true; // ī���� �̵��� ���� 
		cardLabel.setVisible(modeSelect); //������ ��带 �ݿ�
		scrollPane.setVisible(!modeSelect);//������ ��带 �ݿ�
		card.setVisible(modeSelect); // ������ ���带 �ݿ� 
		
		scrollPane.getVerticalScrollBar().setValue(0); // ��ũ�� �ٸ� ������ �ø�

		
		
	}
	
	public void setWord(int day){ // ���� ��ư�� ���� String�迭�� �ܾ� �� ���� �߰� ���ִ� �Լ� 
		
		try{
		fileReader = new FileReader("word\\day"+day+".txt"); // ���� �ϱ� ��ü�� ���� 

		}catch(Exception e){System.out.println("not find");}
		bufferedReader = new BufferedReader(fileReader); // ���� �б⸦ �����ִ� ������ ���� �� ����� ���ڷ� ���� 
		
		//������ �о�ͼ� �ʱ�ȭ 
		if(fileReader==null) System.out.println("not find"); // file�� ���� ��� 
		else
		{	
			wordIndex.x=0; // wordIndex �ʱ�ȭ
			wordIndex.y=0; // wordIndex �ʱ�ȭ
			for(int i=0;i<30;++i){ //�� 30�� �� �ܾ�� ���� ����
				try{
					//�ܾ�� ���� ���پ� �� �ִ� 
					word[i][0]=bufferedReader.readLine(); //�ܾ ���� �о�ͼ� 
					System.out.println(word[i][0]); //�Ҵ� 
					word[i][1]=bufferedReader.readLine();; // ���� ���� �о�ͼ� 
					System.out.println(word[i][1]); // �Ҵ� 
					} catch(Exception e){}  //���� ó�� 
			}
		}
		
		cardLabel.setText(word[wordIndex.x][wordIndex.y]); // ������ �ܾ ī���带 ���� �󺧿� �ݿ� 
		
		for(int i=0;i<30;++i){ // ������ �ܾ ��ũ�Ѹ�带 ���� �󺧿� �ݿ� 
			scrollLabel[i][0].setText(word[i][0]);
			scrollLabel[i][1].setText(word[i][1]);
		}
		
	}//setWord
	
	
}//wordPanel class