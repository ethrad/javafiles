package playground;

public class Main {
	public static int timer = 0;
	
	public static void main(String args[]) {
		//CharacterSelectFrame csf = new CharacterSelectFrame();
		//csf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//csf.setVisible(true);
		/** The timer. */
		

		/** Map�� �����带 �۵���Ű�� T1 Thread. */
		Thread T1;

		/** �ð��ʸ� ���� T2 Thread. */
		Thread T2 = null;
		T1 = null;
		WaitingRoomView wv = new WaitingRoomView(T2);
		CharacterSelectView cv = new CharacterSelectView(wv);
		StatusManager player = new StatusManager(cv);
		LaunchManager lm = new LaunchManager(cv);
		
		Mapmanager MapController = new Mapmanager(T1, player);
		
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
		
		//WaitPlay w = new WaitPlay();
		//w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//w.setVisible(true);
	}
}
