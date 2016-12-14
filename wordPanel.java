//********************************************************************
//  
//	단어장 화면.
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
		
		//기본 설정.
		setPreferredSize(new Dimension(1028,720)); //wordPanel의 크기 설정 
		setBackground(new Color(255,255,230)); // wordPanel의 배경색 설정 
		setLayout(null); // 배치를 사용자 임의대로 함
		
		modeSelect=true; // 스크롤과 카드모드의 선택을 하는 변수로 설정
		
		wordBucket= new JPanel(); // JScrollPane에 들어갈 단어를 담는 panel
		wordBucket.setPreferredSize(new Dimension(780,4220)); //크기 설정
		wordBucket.setBounds(114,120,800,480); // 경계 설정
		wordBucket.setBackground(Color.white); // 배경색 설정
		wordBucket.setLayout(null); // 배치 설정
		wordBucket.setVisible(true); // 가시 설정
		
		scrollPane=new JScrollPane(wordBucket,// 단어 패널을 담을 JScrollPane 
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, // 스크롤바 설정을 생성과 동시에 해줌
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // 넣어줄 패널도 인자로 전달
        scrollPane.setBounds(114,120,800,480); // 경계 설정
        scrollPane.setVisible(false); //가시 설
        add(scrollPane);//wordBucket에 더해줌
		
		
		
		// 카드를 클릭하면 뜻을 보여주기 위해 mouseListener를 생성 및 추가 
		mouseListener= new MListener(); 
		this.addMouseListener(mouseListener);
		
		 

		//word배열의 인덱스에 관여하는 Point변수를 생
		wordIndex=new Point();
		 
		//카드 모드에서 버튼과 배경을 담을 LAbel생성 및 설정 
		card = new JLabel(new ImageIcon("img\\card.png"),SwingConstants.CENTER); 
		card.setBounds(100,150,800,480);
		//wordPanel에 더해줌
		add(card);
		 
		 
		cardLabel= new JLabel(); //카드모드에서 글씨를 보여줌
		cardLabel.setBounds(10,10,800,450); //경계 설정
		cardLabel.setFont(new Font("돋움",Font.BOLD,100)); //글씨체 설정
		cardLabel.setVerticalAlignment(SwingConstants.CENTER); // 세로 정렬 설정
		cardLabel.setHorizontalAlignment(SwingConstants.CENTER);//가로 정렬 설정
		cardLabel.setVisible(true); //가시 설정
		card.add(cardLabel); // 카드에 더해줌
		
		
		labelIndex = new JLabel("1/30"); // 몇번째 단어를 보고있는지 알려주는 label
		labelIndex.setBounds(690,30,100,25); //경계 설정
		labelIndex.setFont(new Font("돋움",Font.BOLD,20));//글씨체 설정
		labelIndex.setVerticalAlignment(SwingConstants.CENTER); // 세로 정렬
		labelIndex.setHorizontalAlignment(SwingConstants.CENTER);//가로 정렬
		card.add(labelIndex); // 카드에 더해줌
		

			
		
		
		for(int i=0;i<30;++i) //wordBucket에 들어갈 Label들을 생성 및 배치
		{
			scrollLabel[i][0]=new JLabel(); // 생성
			scrollLabel[i][0].setVerticalAlignment(SwingConstants.CENTER); //세로 정럴
			scrollLabel[i][0].setHorizontalAlignment(SwingConstants.CENTER);//가로 정렬
			scrollLabel[i][0].setBounds(20,140*i+20,350,100);// 경계 설정
			scrollLabel[i][0].setFont(new Font("돋움",Font.BOLD,50));// 글씨체 설정
			wordBucket.add(scrollLabel[i][0]); //카드에 더해줌
			
			scrollLabel[i][1]=new JLabel(); //생성 
			scrollLabel[i][1].setVerticalAlignment(SwingConstants.CENTER);//세로 정렬
			scrollLabel[i][1].setHorizontalAlignment(SwingConstants.CENTER);//가로 정렬
			scrollLabel[i][1].setBounds(410,140*i+20,350,100); //경계 설정
			scrollLabel[i][1].setFont(new Font("돋움",Font.BOLD,50));//글씨체 설정 
			wordBucket.add(scrollLabel[i][1]); // 카드에 더해줌
		}
		
		for(int i=0;i<30;++i) // wordBucket 단어들의 뒷배경이 될 label을 생성 및 배
		{
			scrollCard[i]=new JLabel(new ImageIcon("img\\scrollCard.png"),SwingConstants.CENTER); // 생성과 동시에 배경이미지를 설정 
			scrollCard[i].setBounds(20,140*i+20,740,100); // 경계 설정
			wordBucket.add(scrollCard[i]); // wordBucket에 더해줌 
		}
		
		//메뉴 생성.
		
		goMain = new JButton (new ImageIcon("img\\home.png")); // 메인으로 가기 위한 버튼 생성과 동시에 버튼이미지를 가져옴
		goMain.setBounds(10,10,54,54); // 경계 설정 
		goMain.setContentAreaFilled(false); // 빈공간 채우기 설정
		goMain.setBorderPainted(false); // 경계선 색칠 설정
		goMain.addActionListener (new ButtonListener()); //ActionListener를 더해줌 
		goMain.setPressedIcon(new ImageIcon("img\\home_p.png"));// 배경이미지 설정
		add(goMain);//wordPanel에 더해줌 
		
		back = new JButton(new ImageIcon("img\\back.png"));// 날짜 선택 화면으로 가기위한 버튼 생성과 동시에 버튼이미지를 가져옴 
		back.addActionListener (new ButtonListener()); //ActionListener를 더해줌 
		back.setBounds(74,10,54,54);//경계 설정
		back.setContentAreaFilled(false);// 빈공간 채우기 설정
		back.setBorderPainted(false); //경계 색칠 설정
		back.setVisible(true);//가시 설정
		back.setPressedIcon(new ImageIcon("img\\back_p.png"));// 눌렸을떄 버튼이미지 설정
		add(back); //wordPanel에 더해줌
		
		prev = new JButton(new ImageIcon("img\\prev.png"));// 카드모드에서 앞단어로 이동하기 위한 버튼 생성과 동시에 버튼 이미지를 가져옴
		prev.addActionListener (new ButtonListener()); ////ActionListener를 더해줌 
		prev.setBounds(10,210,100,50); // 경계 설정
		prev.setContentAreaFilled(false); // 빈공간 채우기 설정
		prev.setBorderPainted(false); // 경계선 색칠  설정
		prev.setVisible(false); // 가시 설정
		card.add(prev); //crad에 더해줌
		
		next = new JButton(new ImageIcon("img\\next.png"));// 카드모드에서 뒤단어로 이동하기 위한 버튼 생성과 동시에 버튼 이미지를 가져옴
		next.addActionListener (new ButtonListener());//ActionListener를 더해줌 
		next.setBounds(690,210,100,50); //경계 설정
		next.setContentAreaFilled(false);// 빈공간 채우기 설정
		next.setBorderPainted(false); // 경계선 색칠 설정
		next.setVisible(true);// 가시 설정
		card.add(next);//card에 더해줌 
		
		flip = new JButton(new ImageIcon("img\\change.png")); // 카드모드에서 스크로모드로 바꾸기 위한 버튼 생성과 동시에 버튼 이미지를 가져옴
		flip.addActionListener (new ButtonListener());//ActionListener를 더해줌 
		flip.setBounds(964,10,54,54);//경계 설정
		flip.setContentAreaFilled(false);//빈공간 채우기 설정
		flip.setBorderPainted(false);// 경계선 책칠설정 
		flip.setVisible(true);//가시 설정
		flip.setPressedIcon(new ImageIcon("img\\change_p.png"));//눌렸을때 이미지 설정
		add(flip);// wordPanel에 더해줌 
		
		
		
		
	}//wordPanel()
	
	
	
	public static String getWordArr(int i, int j) // 파일 입출력을 통해 가져온 단어를 인덱스에 따라 반환 
	{
		return word[i][j];
	}
	
	private class ButtonListener implements ActionListener //버튼 이벤트가 일어 났을떄 처리하기 위한 이너 클래스
	{
		public void actionPerformed(ActionEvent event)
		{
			Object obj= event.getSource();
			
			if(obj==goMain) // go main 버튼이 눌렸을 겨우
			{
				//패널 전환.
				test.changePanel("main");// 메인으로 화면을 전환
				
			}else if(obj==prev){ //prev 버튼이 눌렸을 경우
				if(wordIndex.x>0){   // 맨처음 단어가 아니라면
					wordIndex.x-=1; //현재 단어 인덱스를 1개 줄이고
					wordIndex.y=0;  // 카드가 뜻이 보이는 경우 다시 영단어가 보이게 해주고
					cardLabel.setText(word[wordIndex.x][wordIndex.y]);	// 변경된 인덱스에 맞게 라벨을 설정 하고
					if(wordIndex.x<1)prev.setVisible(false); // 첫단어 일경우 prev 버튼을 안보이게 하고
					if(wordIndex.x<29) next.setVisible(true); // 마지막 단어가 아닐경우 next 버튼을 보이게 한다.
				}
			}else if(obj==next){
				if(wordIndex.x<29){ // 마지막 단어가 아니라면 
					wordIndex.x+=1; // 현재 단어 인젝스를 1개 늘리고 
					wordIndex.y=0; // 단어의 뜻을 보여주고 있을 경우 다시 영어를 보여주도록 설정 
					cardLabel.setText(word[wordIndex.x][wordIndex.y]);// 변한 인덱스에 맞세 라벨 설정
					if(wordIndex.x>28) next.setVisible(false); // 마지막 단어라면 next 버튼을 안보이도록 설정
					if(wordIndex.x>0) prev.setVisible(true); // 첫단어가 아니라면 prev 버튼이 보이도록 설정
					}	
			}else if(obj==flip){ // 모드를 바꾸는 경우
				modeSelect=!modeSelect; // 모드 선택 변수의 값을 ! 시키고
				cardLabel.setVisible(modeSelect);// 카드모드 라벨 가시 설정
				scrollPane.setVisible(!modeSelect); // 스크롤 모드 JScrollPane 설정 
				card.setVisible(modeSelect); // 카드모드 버튼과 배경 가시 설정
			}else if(obj==back){ // bakc 버튼이 눌렸을 경우
			
			test.changePanel("day");// 날짜 선택 화면으로 돌아감;
			}
			
			
			labelIndex.setText(wordIndex.x+1+"/30");// 카드 모드에서 현재 몇번째 단어를 보고 있는지 변경되 사항을 적용 
			
		}//actionPerformed()
		
	}//ButtonListener class
	
	
	private class MListener implements MouseListener // 마우스 클릭 이벤트를 처리하기 위한 이너 클래스 
	{
		public void mouseClicked(MouseEvent event){ // 마우스가 클릭된 경우 
			Point mousePoint=new Point();
			mousePoint= event.getPoint();
			
			if(mousePoint.x>114&mousePoint.x<914&mousePoint.y>120&mousePoint.y<600){ // 카드내의 범위인지 확인
				 if(wordIndex.y==1) wordIndex.y=0; // 단어면 뜻을 뜻이면 단어를 보여주도록 설정
				 else wordIndex.y=1;
				 cardLabel.setText(word[wordIndex.x][wordIndex.y]); // 변경된 내용을 라벨에 반영 
			}
			
			
			}//mouseClicked()
		public void mousePressed(MouseEvent event){}
		public void mouseReleased(MouseEvent event){}
		public void mouseEntered(MouseEvent event){}
		public void mouseExited(MouseEvent event){}
		
		
	}// MListener class
	
	public void setTest(Test reference) //up call을 이용하여 화면 전환을 위한 함수 
	{
		test=reference;
	}
	
	public void init() // 초기화 를 해주기 위한 함수 
	{
		wordIndex.x=wordIndex.y=0;  // 첫 단어가 나오도록 설정
		labelIndex.setText("1/30"); // 첫단어를 보고 있음을 알수 있도록 설정
		prev.setVisible(false); // 들어갔을때 prev 버튼이 안보이도록 설정
		
		modeSelect=true; // 카드모드 이도록 설정 
		cardLabel.setVisible(modeSelect); //설정된 모드를 반영
		scrollPane.setVisible(!modeSelect);//설정된 모드를 반영
		card.setVisible(modeSelect); // 설정된 보드를 반영 
		
		scrollPane.getVerticalScrollBar().setValue(0); // 스크롤 바를 맨위로 올림

		
		
	}
	
	public void setWord(int day){ // 눌린 버튼에 따라 String배열에 단어 및 뜻을 추가 해주는 함수 
		
		try{
		fileReader = new FileReader("word\\day"+day+".txt"); // 파일 일기 객체를 생성 

		}catch(Exception e){System.out.println("not find");}
		bufferedReader = new BufferedReader(fileReader); // 파일 읽기를 도아주는 버퍼의 생성 및 대상을 인자로 전달 
		
		//파일을 읽어와서 초기화 
		if(fileReader==null) System.out.println("not find"); // file이 없을 경우 
		else
		{	
			wordIndex.x=0; // wordIndex 초기화
			wordIndex.y=0; // wordIndex 초기화
			for(int i=0;i<30;++i){ //총 30개 의 단어와 뜻이 있음
				try{
					//단어와 뜻은 한줄씩 들어가 있다 
					word[i][0]=bufferedReader.readLine(); //단어를 한줄 읽어와서 
					System.out.println(word[i][0]); //할당 
					word[i][1]=bufferedReader.readLine();; // 뜻을 한줄 읽어와서 
					System.out.println(word[i][1]); // 할당 
					} catch(Exception e){}  //예외 처리 
			}
		}
		
		cardLabel.setText(word[wordIndex.x][wordIndex.y]); // 설정된 단어를 카드모드를 위한 라벨에 반영 
		
		for(int i=0;i<30;++i){ // 설정된 단어를 스크롤모드를 위한 라벨에 반영 
			scrollLabel[i][0].setText(word[i][0]);
			scrollLabel[i][1].setText(word[i][1]);
		}
		
	}//setWord
	
	
}//wordPanel class