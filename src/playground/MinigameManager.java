package playground;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * �̴ϰ����� �����ϴ� Ŭ����.
 *
 * @author Chungheon Yi
 */

public class MinigameManager extends JFrame implements Runnable {

	
	/** Ÿ�̸ӿ� ���̴� ����. */
	private int timer = 999;
	
	/**  �� �Ŵ��� ���� ������. */
	private Thread myThread;
	
	/** �� Ŭ������ frame. */
	private JFrame frame = new JFrame();
	
	/** ���. */
	private JPanel top = new JPanel();
	
	/** �߰�. */
	private JLabel middle = new JLabel();
	
	/** �ϴ�. */
	private JPanel bot = new JPanel();
	
	/** ���� ���1. */
	private JPanel topright = new JPanel();
	
	/** ���� ��� 2. */
	private JPanel topright1 = new JPanel();
	
	/** ���� ��� */
	private JPanel topleft = new JPanel();
	
	/** Ÿ�̸� ��¿� ��. */
	JLabel times = new JLabel(timer+"��");
	
	/** ���� ��ư */
	private JButton bt = new JButton(new ImageIcon("./src/image/button/button1.png"));
	
	/** �ؽ�Ʈ �ʵ� */
	private JTextField jp = new JTextField(20);
	
	/** The hp */
	private JProgressBar HP = new JProgressBar(0,100);
	
	private JLabel xy = new JLabel();
	/** Ŭ���� ���콺 ��ǥ x�� */
	private int x;
	
	/** Ŭ���� ���콺 ��ǥ y�� */
	private int y;
	
	/**  ���� ����. */
	private String answer = "��";

	/**  ���� �̴ϰ����� ����Ǵ� ���� ���. */
	private M currentMap;
	
	/**  ���ӵ�. ��ü ���� ������带 ���̱����Ͽ� 1ȸ�� �����ϸ� �� ������ ����.*/
	private static LinkedList<Minigame> miniGames;

	/**  ���õ� ����. */
	private Minigame currentMinigame;
	
	/** �ʸŴ��� manager. */
	private Mapmanager manager;
	/**
	 * ������ ������ ���߸� true, Ʋ���� false ��ȯ, �׸��� �ð��ʰ� ������ false ��ȯ.
	 * @return ������ ������ ���߸� true, Ʋ���� false ��ȯ
	 */
	public boolean gameresult() {
		
		try {
			answer = answer.replaceAll(" ", "");  //����� ���ڿ� ��ĭ����
			currentMinigame.setAnswer(currentMinigame.getAnswer().replaceAll(" ", ""));  //���� ��ĭ����. ������ġ
		}
			catch(NullPointerException e) //�׳� �����ϱ� �ٷ� ������ �����ߴµ� �̶� false �ݳ�
		{
				return false;
		}
		
		while(!answer.isEmpty() && answer.charAt(0) == '0') answer = answer.substring(1); //000012 == 12, 0�� ����Ұ�
		
		if (timer <= 0) //�ð����� 
			return false;

			try {
				if (answer.equals(currentMinigame.getAnswer())) // �Ȱ����� ���� �ƴϸ� ����
					return true;
				else
					return false;
			} catch (NullPointerException e) // ���� ��Ȳ�� ���� �ȶ�
			{
				e.printStackTrace(); //���� ȣ��
				return false;
			}
		}

	/**
	 *  �� Ŭ������ �����带 �����ϴ� �޼ҵ�.
	 * @param th the new thread
	 */
	public void setThread(Thread th) {
		this.myThread = th;
	}

	/**
	 *  �� Ŭ������ Thread�� ��ȯ�ϴ� �޼ҵ�.
	 * @return the thread
	 */
	public Thread getThread() {
		return this.myThread;
	}

	/**
	 *  ���ӵ� ������, miniGames(LinkedList) ���. �̴ϰ����� ó�� �����Ǿ����� �ѹ� �����ϰ� ���Ŀ��� �������� �ʰ� �ٽþ� 
	 */
	private void gamesGenerator() {
		
		bt.setBorderPainted(false);
		bt.setFocusPainted(false);
		bt.setContentAreaFilled(false); //��ư �׵θ�, ��ĥ �� ����
		
		xy.setVisible(false); //Ŭ�� ��ǥ �����ִ� ���� 
		
		if(miniGames == null)  // ��ü ���� �ð��� �Ƴ��� ���ؼ� �̹� ������� ������ ���� ���� �ð��� �ǳʶ�
		{
		miniGames = new LinkedList<Minigame>(); //Minigame ��ü �ֱ� ����
		
		/*�̹��� ������*/
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/game1.PNG"), "96",
				new String[]{"�� ����! ","������ ���� ","���� �߷��� ������!",""}
				));	// ����0
		miniGames.getLast().setTimer(45); //�ð��� - ���������� ���� ���� �����ڿ� ���� �ʰ� ���� ������
		
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/game2.PNG"), "12",
				new String[]{"����� �� ����? ","� ��Ģ���� ��ĥ�� ������ �̿��Ͽ� ","��� ���� ���� ������!",""}
				));	// ����1
		miniGames.getLast().setTimer(45); //�ð��� 
		
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/game3.PNG"), "�˹���",
				new String[]{"�ͼ��� ���� ! ","�� ���� �̸���?","(3����)",""}
				));	// ����2
		miniGames.getLast().setTimer(55); //�ð��� 
		
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/game4.PNG"), "��Ǫ",
				new String[]{"�ͼ��� ���� ! ","�� ���� �̸���?","(2����)",""}
				));	// ����3
		miniGames.getLast().setTimer(55); //�ð��� 
				
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/game5.PNG"), "���",
				new String[]{"�ͼ��� ���� ! ","�� �ҽ��� ���� �̸���?","(2����)",""}
				));	// ����4
		miniGames.getLast().setTimer(55); //�ð��� 
		
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/game6.PNG"), "������",
				new String[]{"���� ���� ","People live in EU like eating this","(�ѱ۷� 3����)",""}
				));	// ����5
		miniGames.getLast().setTimer(40); //�ð��� 
		/* �̹��� ���� �� */
		
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/willy1.PNG"),
				new int[] {640,686,430,515}, //�̰� �� �簢�� ��ǥ (x�ּ�,x�ִ�,y�ּ�,y�ִ�)
				new String[]{"������ ã�ƶ�! ","����(���� �ٹ���)�� ã���Ŀ� Ŭ���ϼ���!","Ŭ���� ���� ������",""}
				));	// ����6
		miniGames.getLast().setTimer(85); //�ð��� 
		
		
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/willy2.jpg"),
				new int[] {0,9000,0,9000},         //�̰� �� �簢�� ��ǥ (x�ּ�,x�ִ�,y�ּ�,y�ִ�)
				new String[]{"������ ã�ƶ�! ","����(���� �ٹ���)�� ã���Ŀ� Ŭ���ϼ���!","Ŭ���� ���� ������",""}
				));	// ����7
		miniGames.getLast().setTimer(85); //�ð��� 
		
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/willy3.PNG"),
				new int[] {740,775,480,533},         //�̰� �� �簢�� ��ǥ (x�ּ�,x�ִ�,y�ּ�,y�ִ�)
				new String[]{"������ ã�ƶ�! ","����(���� �ٹ���)�� ã���Ŀ� Ŭ���ϼ���!","Ŭ���� ���� ������",""}
				));	// ����8
		miniGames.getLast().setTimer(85); //�ð��� 
		
		}
		
		
		
		
		int currentgame = (int)(Math.random()*10)%(miniGames.size());
		
		currentMinigame = (miniGames.get(currentgame));
		if(currentgame<=5)
		{
		
		}	
		else
		{
			jp.setText("Ŭ���� ���� ������");
			jp.setEditable(false);
			
			xy.setVisible(true); //Ŭ�� ��ǥ ������ ���� ���
		}
		
		timer = miniGames.get(currentgame).getTimer();
		
		//currentMinigame = new Minigame(new ImageIcon("./image/minigameImage/image1.PNG"), "��",
				//new String[]{"���� ����! ","�̷��� ","�÷���","�ϴ°� "}
				//);// ������!
		
		
		
		
		// Minigame(img,answer);
	}
	
	@Override
	public void run() {

		try {
			
			int currentHP = 89;  //Character�� HP�� ����� ����
			
			while (timer >= 0 && !myThread.isInterrupted()) {

				//System.out.println("minigame manager thread");
				
				if(timer<10) times.setForeground(Color.RED);
				
				
				if (!currentMinigame.getisStop()) { // ����� �ٽ� �÷����ϸ� �����
					
					times.setText(timer--+" ��"); // 1�ʸ��� Ÿ�̸� 1�ʾ� ����
					manager.setHP(--currentHP,currentMap,HP);   //currentHP�� ĳ���� HP�� ������
					
				}
				
				
				
				Thread.sleep(1000); // 1�ʾ� sleep. �����̶����� ��Ȯ���� ������ ���� ���࿣ ū ������ ����
			}
		} catch (InterruptedException e) {
			
			// Interrupted catch - while�� ����

		} catch (Exception e) {
			e.printStackTrace(); // �׿� ������ ���
		}
		
		if(timer<=0)
		{
			bt.doClick(); //Ÿ�̸Ӱ� ������ ������ ����
		}
		
	}
	
	  
	
	/**
	 * �̴ϰ����� �����ϰ�, �����ϴ� �Ŵ���.
	 *
	 * @param m ���� ���� ���
	 * @param Mapmanager�� �Ѱܹ���. association
	 */
	public MinigameManager(M m,Mapmanager Manager) {
		
		frame.setContentPane(new JLabel(new ImageIcon("./src/image/mapImage/back11.jpg")));
		manager = Manager;
		currentMap = m;
		
		final int ROW = 920; // The row. 	 	
		final int COL = 920; // The col.
		
		
		setThread(new Thread(this));
		gamesGenerator();
			
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;


		frame.setTitle("���� �׶��� - Minigame"); //Ÿ��Ʋ ���� 
		frame.setLayout(null); //���̾ƿ� ����

		try {
		
		JLabel img = new JLabel(currentMap.getIconImage());
		img.setBounds(2,40,196,178);
		frame.add(img); //���� ��� �� �̹��� ����
		middle = new JLabel(currentMinigame.getImage());
		
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		
		JLabel timelimit = new JLabel("���� �ð�");
		
		timelimit.setBounds(815,0,70,70);
		frame.add(timelimit);
		times.setBounds(815,120,70,70);
		frame.add(times);	
		times.setVisible(false);        //times�� Ȯ���� ������ ���̰���
	
		HP.setStringPainted(true);
		if(currentMap.getFlag()) HP.setForeground(Color.RED);
		else  HP.setForeground(Color.MAGENTA);
		
		HP.setBounds(569,194,201,24);
		HP.setValue(74); // ���⿡ Character HP ����
		HP.setStringPainted(true);
		frame.add(HP); //show progressbar
		
		JLabel showhp = new JLabel("HP :");
		showhp.setBounds(535,195,30,20); 
		frame.add(showhp); //HP : ��ܿ� add
		
		JLabel nameofMap = new JLabel(currentMap.getMapName());
		nameofMap.setBounds(55,0,120,60);
		frame.add(nameofMap);
		
		JLabel[] howtoplay = new JLabel[5]; //���� �̴ϰ��ӿ� ���� ����,
		// howtoplay[0]�� �̴ϰ��� �̸���, howtoplay[4]�� "space�ٸ� ������ �ߴ�" ���
		
		/*JLabel how = new JLabel("���� ���� "); // the title
		
		how.setBounds(220,20,100,25);
		frame.add(how); // ���Ӽ��� ��ܿ� ����
		*/
		
		
		
		howtoplay[0] = new JLabel(currentMinigame.getList(0));  
		howtoplay[0].setBounds(220,50,250,25);
		
		howtoplay[4]  = new JLabel("���� ��� �ߴ��� �� �׸��� ��������.");		
		frame.add(howtoplay[0]);
		for(int i=1; i<5; i++)
		{
			if(i!=4)	howtoplay[i] = new JLabel(currentMinigame.getList(i));  
			howtoplay[i].setBounds(220,70+i*20,250,25);
			howtoplay[i].setFont(new Font("Serif",Font.ITALIC,13));
			frame.add(howtoplay[i]);
		}
		
		bot.add(new JLabel("����"));
		bot.add(jp);
		bot.add(bt, BorderLayout.LINE_END);
	
		/*��Ʈ*/
		times.setFont(new Font("Serif",Font.ITALIC,20));
		jp.setFont(new Font("Serif",Font.ITALIC,18));
		nameofMap.setFont(new Font("Serif",Font.PLAIN,24));
		
		
		
		top.setBounds(0,48, 200, 172);
		topright.setBounds(770, 0, 200, 100);
		topright1.setBounds(770,100,200,120);
		topleft.setBounds(200,0,600,220);
		middle.setBounds(0, 220, 920, 500);
		bot.setBounds(240, 800, 450, 300);
		
		bot.setBackground(new Color(255,182,193));
		top.setBackground(Color.WHITE);
		topright.setBackground(new Color(255,182,193));
		topleft.setBackground(new Color(255,182,193));
		topright1.setBackground(Color.WHITE);
		frame.setBounds(280,700,300,300);
		
		
		top.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		topright.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		topleft.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		topright1.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		//middle.setBorder(new TitledBorder(new LineBorder(Color.black,1)));
		
		
		frame.add(top);
		frame.add(topright);
		frame.add(topright1);
		frame.add(topleft);
		frame.add(bot,0);
		frame.add(middle);	
		
		xy.setBounds(800,850,200,30);
		frame.add(xy); //Ŭ�� �̴ϰ��ӿ��� ��ǥ�� ������
		
		middle.setVisible(false);//Ȯ���� ������ �̴ϰ����� ���� 
		frame.setSize(ROW, COL);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(("./src/image/chonnam.png")));//���� �ΰ�
		frame.setLocation(screenWidth / 4, screenHeight / 10);
		frame.setResizable(false);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		int result =  JOptionPane.showConfirmDialog(null,
				 currentMinigame.getList(1)+"\n"+
				 currentMinigame.getList(2)+"\n"+
				 currentMinigame.getList(3)+"\n",
				 "�̴ϰ��� - "+currentMinigame.getList(0)
				 ,JOptionPane.CLOSED_OPTION);
			
			if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {	
				middle.setVisible(true);
				times.setVisible(true); //Ȯ���� ������ timer�� ���� 
				myThread.start();
			
			}
		
		//���콺 Ŭ���� �ν��ϴ� addMouseListener
          frame.addMouseListener(new MouseAdapter(){
          
        	  @Override
        	  public void mousePressed(MouseEvent e)
        	  {
        		   x = e.getX();
        		   y = e.getY();
        		   
        		   currentMinigame.setXY(x, y); //Ŭ�� ����� �� ���� ����
        		   xy.setText("��ǥ : ("+currentMinigame.getX()+","+currentMinigame.getY()+")");//��ǥ���
        		 
        		  
        		  if(x>=2 && x<=198 && y>=70 && y<=246) // ���� ��� �׸��� ������ pause�ǰ� ���� , �� name�� �׸��� �ణ �̴°ɷ� ����
        			 {
        			  
        			  if(!currentMinigame.getisStop())
        			  {
        				  if(currentMap.getFlag())
        				  {
        					  currentMinigame.setisStop(!currentMinigame.getisStop());
        		        		 middle.setVisible(!currentMinigame.getisStop());  //�׸� ������ pause �ǰ� �̴ϰ����� �Ⱥ���
        		        		 int result = JOptionPane.showConfirmDialog(null,
        		        				"�˾��� ������ ����� �մϴ�.",
        		        				 "PAUSE - "+ currentMinigame.getList(0)
        		        				 ,JOptionPane.CLOSED_OPTION);

        		 				if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
        		 					currentMinigame.setisStop(!currentMinigame.getisStop());
        		 	        		middle.setVisible(!currentMinigame.getisStop());
        		 						} //�˾� ���Ž� �����
        				  }
        				  else
            			  {
            				  JOptionPane.showConfirmDialog(null, "�ڱ��忡 Ƣ������ �ִµ��ȿ���\nPause�Ҽ� �����ϴ�.", "XD", JOptionPane.CLOSED_OPTION);
            			  }
        			  }
        			
        		} //�˾� Ȯ�� Ȥ�� ��� ������ �����
        		  
        						 
        	  }
          }
		);
         
			
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String ans;

				answer = jp.getText(); // �Է¹��� �ؽ�Ʈ ����
				
				myThread.interrupt();
				
				if (gameresult())
					ans = "�̴ϰ��� ���� ! �������� Ȯ���մϴ�.";
				else
					ans = "�̴ϰ��� ���� ! \n�������� Ȯ������ ���Ͽ����ϴ�.";

				int result = JOptionPane.showConfirmDialog(null, ans, "Ȯ��", JOptionPane.CLOSED_OPTION);

				if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
					
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(false);
					
					
					/**/
					manager.getMapFrame().setVisible(true);
					manager.getMapFrame().setDefaultCloseOperation(EXIT_ON_CLOSE);
					/**/
					
				
					
					// ���� ���� �Ŵ��� ����
				}

			}
		});

	}

}
