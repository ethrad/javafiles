package Map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import minigame.*;

/**
 * �� ���� �Ŵ��� Ŭ����. Thread�� launcher�� �Բ� ���� �÷��̵��� ��� ���ư���.
 * 
 * @author Chungheon Yi
 */

public class Mapmanager extends JFrame implements Runnable {

	//private statusManager Man; -> ���� stat ������ ����
	
	// prvaite AImanager AI -> ���� AI�Ŵ��� �����ϸ� ����

	/** The hp */
	private JProgressBar HP = new JProgressBar(0, 100);

	/** The my location. */
	private int myLocation = 0; // �ڽ��� ��ġ���ڷ� ����

	/** �� �̸���. �� ���������� ���. */
	private String mapsName[] = { "���", "����", "�ڿ���", "��ȸ��", "�ι���", "����", "�濵��", "����", "����" };

	/** Ÿ�̸� */
	private int timer = 0; //

	/** �� 9�� */
	private map m[] = new map[9];

	JLabel ping = new JLabel(new ImageIcon("./src/image/mapImage/ping.png")); // �ڱ���ġ ����Ŵ

	JLabel text[] = new JLabel[9];

	/** ��ư 9�� */
	private JButton[] b = new JButton[9];

	/** Ÿ�̸� ������ ������ */
	private Thread myThread;

	/** �� Ŭ������ Frame. */
	private JFrame frame = new JFrame();

	/** ���� ��� �г� */
	private JPanel top = new JPanel();

	/** hp ��� */
	private JLabel forHp = new JLabel(" HP :");

	// nameofMap.setForeground(Color.BLUE);

	/** ���� ��� */
	private JLabel forDef = new JLabel(" ���� :");

	/** ������ġ ��� */
	private JLabel forLoc = new JLabel(" ���� ��ġ :   " + mapsName[myLocation]);

	/** �� ���� �÷��� �ð� ��� */
	private JLabel Mytime = new JLabel("                   " + timer);

	/** �ڱ����� ���ݰ���� Ȯ���Ҷ� ���̴� linkedlist */
	LinkedList<Integer> list;

	/**
	 * �� ��� �̵��Ҷ� Ȯ�� Ȥ�� ��Ҹ� ������ �˾�â�� ���� inner class, JDialog�� ��ü ����
	 * 
	 * @author Chungheon Yi
	 */
	private class MapLocationPopup extends JDialog {

		/** �̵� ��ư */
		private JButton Bt1 = new JButton(new ImageIcon("./src/image/button/buttonYES.png"));

		/** ��� ��ư */
		private JButton Bt2 = new JButton(new ImageIcon("./src/image/button/buttonNO.png"));

		/** �ϴ� �г� */
		private JPanel bottom = new JPanel();

		/** ��� �г� */
		private JPanel top = new JPanel();

		/**
		 * �̵� ���� �� fight or startMinigame
		 * 
		 * @param mv Ŭ���� ��ư�� map�� ���ڷ� ����
		 */
		public void moving(map mv) {
			myLocation = mv.getLoc(); // �ڽ��� ��ġ �̰����� �̵�
			forLoc.setText(" ���� ��ġ :   " + mapsName[myLocation]);

			ping.setBounds(50 + 280 * (myLocation % 3), 200 + 250 * (myLocation / 3), 230, 920 - 770);
			if (mv.getUserNumber() <= 0) // start minigame
			{
				new MinigameManager(mv, Mapmanager.this);
			} else {
				// fight manager();
			}

		}

		/**
		 * Ȯ�� ������ �� �̵�, ��� ������ ���.
		 *
		 * @param thisFrameList �̰� ������ �˾��� �ߺ��Ǵ� ������ ���ܼ� ���� �ڵ��Դϴ�..Ȯ���� ������ ��� JFrame�� �ݽ��ϴ�.
		 * @param M   �� m
		 * @param num �̰� ������(location)
		 */
		public MapLocationPopup(final LinkedList<JFrame> thisFrameList, map M, int num) {
			JFrame thisframe = new JFrame();

			thisFrameList.add(thisframe); // �� frame�� ���ؿ� ��Ƶ�

			Bt1.setBorderPainted(false);
			Bt1.setFocusPainted(false);
			Bt1.setContentAreaFilled(false); // ��ư �׵θ�, ��ĥ �� ����

			Bt2.setBorderPainted(false);
			Bt2.setFocusPainted(false);
			Bt2.setContentAreaFilled(false); // ��ư �׵θ�, ��ĥ �� ����

			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension screenSize = tk.getScreenSize();
			int screenHeight = screenSize.height;
			int screenWidth = screenSize.width;

			thisframe.setTitle(M.getMapName() + "�� �̵�?");

			thisframe.setIconImage(Toolkit.getDefaultToolkit().getImage(("./src/image/chonnam.png")));// ���� �ΰ�
			Bt1.addActionListener(

					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) // Ȯ�� ��ư�� ������ �� ȭ�鿡�� ���� Ȥ�� �̴ϰ��� ȭ������ �̵�
						{
							frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							frame.setVisible(false);

							while (!thisFrameList.isEmpty()) {
								thisFrameList.peekFirst().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								thisFrameList.pollFirst().setVisible(false);
							} // �̰� ������ �˾��� �ߺ��Ǵ� ������ ���ܼ� ���� �ڵ��Դϴ�..Ȯ���� ������
								// ��� �˾��� frame�� �����ݴϴ�

							moving(m[num]);
						}
					});

			Bt2.addActionListener(new ActionListener() // ��� ��ư�� �������� ������ ���ư�
			{
				public void actionPerformed(ActionEvent arg0) {

					thisframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					thisframe.setVisible(false);

				}
			});

			thisframe.add(Bt1);
			thisframe.add(Bt2);

			Bt1.setBounds(100, 300, 200, 100);
			Bt2.setBounds(400, 300, 200, 100);
			thisframe.setContentPane(new JLabel(M.getMapImage()));
			add(top);
			thisframe.add(Bt1);
			thisframe.add(Bt2);
			thisframe.add(bottom);

			setResizable(false);
			thisframe.setLocation(screenWidth / 3, screenHeight / 7);
			thisframe.setSize(720, 480);
			thisframe.setVisible(true);

			thisframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		// int result = JOptionPane.showConfirmDialog(null, "����� ���Դϱ�?")
	}

	/**
	 * @return �ڱ� �ڽ� ��ġ ����
	 */
	public map getMyLoc() {
		return m[myLocation];
	}

	/**
	 * @param �ڱ� �ڽ� ��ġ ����
	 */
	public void setMyLoc(int num) {
		this.myLocation = num;
	}

	/**
	 * Sets the thread.
	 * 
	 * @param the thread�� ���ڷ� ����.
	 */
	void setThread(Thread T1) {
		myThread = T1 = new Thread(this);

		// myThread == launcher�� Thread T1
	}

	/**
	 * Gets the thread.
	 *
	 * @return the thread
	 */
	public Thread getThread() {
		return this.myThread;
	}

	/**
	 * Sets the timer. ������ ���Ұ�
	 * 
	 * @param time the new timer
	 */
	public void setTimer(final int time) {
		this.timer = time;
	}

	/**
	 * Gets the timer.
	 * 
	 * @return the timer
	 */
	public int getTimer() {
		return timer;
	}

	/**
	 * Gets the map frame.
	 * 
	 * @return the map frame
	 */
	public JFrame getMapFrame() {
		return frame;
	}

	/**
	 * Sets the map frame.
	 * 
	 * @param frame the new map frame
	 */
	public void setMapFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Sets the hp bar. map, fight, minigame �Ŵ������� ���
	 * 
	 * @param CharHP the new hp
	 */
	public void setHP(int CharHP, map m, JProgressBar HPBar) {
		if (!m.getFlag())
			HPBar.setForeground(Color.magenta); // �ڱ��� �ȿ� �ִٰ� ǥ������
		else
			HPBar.setForeground(Color.RED); // ���� ����

		HPBar.setValue(CharHP); // ü�� set
	}

	/**
	 * ���� �ݰ����� ������� �����Ͽ��� ���������� ü���� ��´�.
	 *
	 * @param count       the count. C++�� ���� ����(&)�� �����ϱ� ���Ͽ� �迭�� ����Ͽ���. ĭ�� ��
	 *                    1ĭ(count)
	 * @param HPBar       �� ��ü���� ���� JProgressBar
	 * @param Threadspeed 1000 = 1, 250 = 4 , 500 = 2 , 100 = 10
	 */
	private void IsClosedMap(int count[], map m, JProgressBar HPBar, double Threadspeed) //
	{
		// count = a[0];

		if (!m.getFlag()) // flag�� 0�̸� ���� �ݰ��ִ°��̰� 1�̸� ü�� �ȱ���
		{
			if (count[0]++ > 4 * Threadspeed) { // ���� �������� �� �ִٸ� 4*Threadspeed�� ���Ŀ� ü���� 1 ����

				count[0] = 0;
				int hp = 100; // character.setHp(character.getHp()-1);
				setHP(--hp, m, HPBar); // ���� HP�� characterHP ����. �̺κ��� ���� character�� �����Ұ�
			}
		}
	}

	@Override
	public void run() {
		try {
			int whattime = 0; // �ð� launcher timer�� ��� ���� ������� Ÿ�̸�
			boolean notify = true; // notify = �̸� �˶��� boolean

			int[] count = new int[] { 0 }; // C++�� ����(&) ����, �����ϴٺ��� ���� ������ �� �ʿ䰡 ���� �Ǿ����� �׳� ����

			while (true) {

				// System.out.println(timer+"���� - mapmanager"); //Ȯ�ο� print
				Mytime.setText("                   " + timer); // �ð��� ��� ����

				IsClosedMap(count, m[myLocation], HP, (double) 10); // count = ������ ���� ���� ����(C++�� &)�� ���� �迭,
				// HP�� HP��, ������ speed(500millsec*10)

				if (!list.isEmpty()) // ��� �ݰ�ٸ� ���� ���� ����
				{
					if (notify && whattime > 750) // 75���Ŀ�, 15���� �̰��� ������ �޴´ٰ� �˷���
					{
						text[m[list.peekLast()].getLoc()].setForeground(Color.MAGENTA); // ��������� ���̸�����
						JOptionPane.showConfirmDialog(null, m[list.peekLast()].getMapName() + "���� �� �ڱ����� �����˴ϴ�.\n",
								"!!", JOptionPane.CLOSED_OPTION);

						notify = false;
					}

					whattime++;

					if (whattime > 900) // 90�ʸ��� �ݱ�
					{
						text[m[list.peekLast()].getLoc()].setForeground(Color.RED); // ����� ���̸�����
						m[list.peekLast()].setFlag(); // �̰��� ����
						JOptionPane.showConfirmDialog(null,
								m[list.pollLast()].getMapName() + "(��)�� �� �ڱ����� ���⿡ ��ϴ�!\n" + "� ����ġ���� !!", "!!",
								JOptionPane.CLOSED_OPTION);

						whattime = 0; // Ÿ�̸� ��� �ʱ�ȭ
						notify = true;
					}
				}

				Thread.sleep(100); // ������ sleep 0.1��
			}
		} catch (InterruptedException e) {
			// ���ͷ�Ʈ �Ͼ�� while�� ����
		} catch (Exception e) {
			e.printStackTrace(); // �׿� ���� üũ
		}

	}

	/**
	 * map�� �������ִ� �Ŵ��� ������.
	 */
	public Mapmanager(Thread T1) {

		final int ROW = 920; // ũ�� ���߿� ����
		final int COL = 920;

		for (int i = 0; i < 9; i++) {
			m[i] = new map(i, mapsName[i]);
		}

		list = new LinkedList<Integer>(); // �ڱ��� ����� LinkedList ����

		for (int i = 0; i < 9; i++)
			list.add(i); // [0,9]
		Collections.shuffle(list); // �����ϰ� ����

		m[0].setImage(new ImageIcon("./src/image/mapImage/map1.png"));
		m[1].setImage(new ImageIcon("./src/image/mapImage/map2.png"));
		m[2].setImage(new ImageIcon("./src/image/mapImage/map3.png"));
		m[3].setImage(new ImageIcon("./src/image/mapImage/map4.png"));
		m[4].setImage(new ImageIcon("./src/image/mapImage/map5.png"));
		m[5].setImage(new ImageIcon("./src/image/mapImage/map6.png"));
		m[6].setImage(new ImageIcon("./src/image/mapImage/map7.png"));
		m[7].setImage(new ImageIcon("./src/image/mapImage/map8.png"));
		m[8].setImage(new ImageIcon("./src/image/mapImage/map9.jpg")); // �̹��� ����

		m[0].setIconImage(new ImageIcon("./src/image/mapImage/icon1.PNG"));
		m[1].setIconImage(new ImageIcon("./src/image/mapImage/icon2.PNG"));
		m[2].setIconImage(new ImageIcon("./src/image/mapImage/icon3.PNG"));
		m[3].setIconImage(new ImageIcon("./src/image/mapImage/icon4.PNG"));
		m[4].setIconImage(new ImageIcon("./src/image/mapImage/icon5.PNG"));
		m[5].setIconImage(new ImageIcon("./src/image/mapImage/icon6.PNG"));
		m[6].setIconImage(new ImageIcon("./src/image/mapImage/icon7.PNG"));
		m[7].setIconImage(new ImageIcon("./src/image/mapImage/icon8.PNG"));
		m[8].setIconImage(new ImageIcon("./src/image/mapImage/icon9.PNG"));
		setThread(T1);

		frame.setLayout(null);

		JPanel topright = new JPanel();
		topright.setBounds(0, 90, 202, 30);
		topright.setBorder(new TitledBorder(new LineBorder(Color.black, 2)));

		topright.add(new JLabel(" ĳ���� �̸�"), BorderLayout.CENTER);

		top.setBounds(200, 0, 350, 120);
		top.setBorder(new TitledBorder(new LineBorder(Color.black, 2)));

		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		forHp.setBounds(200, 20, 40, 30);
		forDef.setBounds(200, 50, 200, 30);
		forLoc.setBounds(200, 80, 200, 30);

		HP.setStringPainted(true);
		HP.setForeground(Color.RED);
		HP.setValue(74); // ������ hp�� ���⿡ ����
		HP.setStringPainted(true);
		HP.setBounds(240, 20, 260, 30);

		JLabel timeTitle = new JLabel("                Time");
		timeTitle.setBorder(new TitledBorder(new LineBorder(Color.black, 2)));
		timeTitle.setBounds(550, 0, 140, 61);

		Mytime.setBorder(new TitledBorder(new LineBorder(Color.black, 2)));
		Mytime.setBounds(550, 60, 140, 60);

		frame.add(HP);
		frame.add(forHp);
		frame.add(forDef);
		frame.add(forLoc);
		frame.add(Mytime);
		frame.add(timeTitle);
		frame.add(topright);
		frame.add(top);

		for (int i = 0; i < 9; i++) {
			b[i] = new JButton(m[i].getIconImage());
			b[i].setBorderPainted(false);
			b[i].setFocusPainted(false);
			b[i].setContentAreaFilled(false); // ��ư �׵θ�, ��ĥ �� ����

			b[i].setFont(new Font("Serif", Font.ITALIC, 24));
			b[i].setForeground(Color.BLUE);
			
			
			
		}

		JButton Item = new JButton(new ImageIcon("./src/image/button/gabang.png"));// �������
		Item.setBorderPainted(false);
		Item.setFocusPainted(false);
		Item.setContentAreaFilled(false); // ��ư �׵θ�, ��ĥ �� ����

		Item.setBounds(730, 10, 140, 120);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i * 3 + j == myLocation)
					ping.setBounds(50 + 280 * j, 200 + 250 * i, 230, ROW - 770);

				text[i * 3 + j] = new JLabel(mapsName[i * 3 + j]); // �� �̸� �ؽ�Ʈ
				b[i * 3 + j].setBounds(50 + 280 * j, 200 + 250 * i, 230, ROW - 770);

				text[i * 3 + j].setBounds(70 + 280 * j, 230 + 250 * i, 200, 30); // �̸� �ؽ�Ʈ ����!
				// text[i*3+j].setFont(new Font("Serif",Font.BOLD,20)); ��Ʈ�Ȱǵ�°� ����
				frame.add(text[i * 3 + j]); // �����ӿ� �߰�
			}
		}

		frame.add(Item);
		frame.add(ping);

		LinkedList<JFrame> thisFrame = new LinkedList<>();
		//�̰� ������ �˾��� �ߺ��Ǵ� ������ ���ܼ� ���� �ڵ��Դϴ�.. �˾��� ��� frame�� ���ٶ� ���ϴ�.
		for (int i = 0; i < 9; i++) {
			final int mynum = i;
			b[i].addActionListener(new ActionListener() // ��Ҹ� ������ ���۵Ǵ� �̺�Ʈ
			{

				public void actionPerformed(ActionEvent e) {

					if (e.getSource() == b[mynum]) {
						new MapLocationPopup(thisFrame, m[mynum], mynum);

					} else {

					}
				}
			});
			
			b[i].addMouseListener(new MouseAdapter(){
			      
			      @Override
			      public void mouseEntered(MouseEvent e){
			         
			         b[mynum].setCursor(new Cursor(Cursor.HAND_CURSOR)); //��ư�� �� �ø��� �ڵ�Ŀ��

			      }
			    
			      public void mouseExited(MouseEvent e){
			       
			         b[mynum].setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //�⺻ Ŀ��

			      }
			});
			frame.add(b[i]);

		}

		Item.addActionListener(new ActionListener() // ������ ������ ���۵Ǵ� �̺�Ʈ
		{
			public void actionPerformed(ActionEvent e) {
				// new Item();
			}
		});

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		frame.setTitle("���� �׶��� - Map");
		frame.setSize(ROW, COL);

		frame.setLocation(screenWidth / 4, screenHeight / 10);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(("./src/image/chonnam.png")));// ���� �ΰ�

		myThread.setDaemon(true);
		myThread.start();
	}

}