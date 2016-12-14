//********************************************************************
//  
//	프로그램의 panel들의 정보를 가지고 있는 클래스.
//  
//********************************************************************
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;

public class Test extends JFrame
{
    private mainPanel main;
	private gamePanel game;
	private gameEndPanel gameEnd;
	private dayPanel day;
	private wordPanel word;
    private CardLayout cards;
    

	public Test(){
		
		main = new mainPanel();
		main.setTest(this);
		game = new gamePanel();
		game.setTest(this);
		gameEnd = new gameEndPanel();
		gameEnd.setTest(this);
		word = new wordPanel();
		word.setTest(this);
		day = new dayPanel();
		day.setTest(this);

    	cards = new CardLayout();
	
		
		this.getContentPane().setLayout(cards);
		
		//패널 추가.
		this.getContentPane().add("main",main);
		this.getContentPane().add("game",game);
		this.getContentPane().add("gameEnd",gameEnd);
		this.getContentPane().add("day",day);
		this.getContentPane().add("word",word);
		
	}
	
	public Test(String str){
		
		super(str);
		
		main = new mainPanel();
		main.setTest(this);
		game = new gamePanel();
		game.setTest(this);
		gameEnd = new gameEndPanel();
		gameEnd.setTest(this);
		word = new wordPanel();
		word.setTest(this);
		day = new dayPanel();
		day.setTest(this);
		if(word==null) System.out.println("test:word is null");
		else System.out.println("test:word is  not null");
		day.setWordPanel(word);
    	cards = new CardLayout();
	
		
		this.getContentPane().setLayout(cards);
		
		//패널 추가.
		this.getContentPane().add("main",main);
		this.getContentPane().add("game",game);
		this.getContentPane().add("gameEnd",gameEnd);
		this.getContentPane().add("day",day);
		this.getContentPane().add("word",word);
		
	}
	
	
	public mainPanel getMain()
	{
		//다른 클래스에서 패널 클래스 접근 가능.
		return main;	
	}//getMain()
	
	public gameEndPanel getGameEnd()
	{
		return gameEnd;
	}//getGameEnd()
	
	public wordPanel getWord()
	{
		return word;
	}//getWord()
	
	public void changePanel(String str) 
	{	
		//화면 전화.
        cards.show(this.getContentPane(),str);
    }//changePanel()

}//Test class