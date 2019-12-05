package playground;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class WaitingRoomView extends JFrame{
	
	/** Map�� �����带 �۵���Ű�� T1 Thread. */
	Thread T1;

	/** �ð��ʸ� ���� T2 Thread. */
	Thread T2;
	
	/** The timer. */
	public int timer = 0;

	/** launcher�� �����ϴ� MapController. */
	Mapmanager MapController;
	
	public WaitingRoomView() {
		setVisible(false);
		setTitle("Wait Playing");
		setSize(500, 500);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		
		JLabel winlose = new JLabel("Win : " +"Lose : 0");
		JLabel gameDes = new JLabel("���� ����~~");
		JButton startButton = new JButton("Start!");
		
		startButton.addActionListener(

				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) // Ȯ�� ��ư�� ������ �� ȭ�鿡�� ���� Ȥ�� �̴ϰ��� ȭ������ �̵�
					{
						/** * Instantiates a new launcher.*/
						gameStart();
					}
				});
		
		
		Container contentPane = getContentPane();
		
		winlose.setBounds(20, 10, 200, 70);
		gameDes.setBounds(40, 150, 250, 250);
		startButton.setSize(150, 70);
		startButton.setLocation(300, 10);
		panel.add(winlose);
		panel.add(gameDes);
		panel.add(startButton);
		contentPane.add(panel);
	}
	

	/**
	 * Game start.
	 * 
	 * @author ChungHeon Yi
	 */
	public void gameStart() {

		
		
		T1 = null; // Mapmanager�� �� Thread
		MapController = new Mapmanager(T1);

		T2 = new Thread() { // ���� ������
			@Override
			public void run() {

				// MinigameManager mini= new MinigameManager(new map(1,"���"));
				while (true) {

					try {
						MapController.setTimer(timer++); // �����̷� ���Ͽ� ������ �߻������� �����÷��̿� �������
						System.out.println(timer);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						break; // ���ͷ�Ʈ ĳġ
					} catch (Exception e) {
						e.printStackTrace(); // ���� ĳġ
					}
				}
			}
		};
		T2.start();

	}
	
	
}
