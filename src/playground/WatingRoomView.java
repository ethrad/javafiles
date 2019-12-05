package playground;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class WaitingRoomView extends JFrame{
	Thread T2;
	public WaitingRoomView(Thread T2) {
		this.T2 = T2;
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
	
	public void gameStart() {
		T2.start();
	}
}
