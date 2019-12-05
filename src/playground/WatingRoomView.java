package playground;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class WaitingRoomView extends JFrame{
	Mapmanager mm = null;
	public WaitingRoomView(Mapmanager mm) {
		this.mm = mm;
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
						gamestart();
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
	public void gamestart() {
		Thread T1 = new Thread() { // ���� ������
			@Override
			public void run() {
				int timer = 0;
				// MinigameManager mini= new MinigameManager(new map(1,"���"));
				while (true) {
					try {
						mm.setTimer(timer++); // �����̷� ���Ͽ� ������ �߻������� �����÷��̿� �������
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
		T1.start();
	}
	
	public void setPlayer(StatusManager player) {
		System.out.println(player.getStatus().name);
		mm.setPlayer(player);
	}
	
}
