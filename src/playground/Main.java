package playground;

public class Main {
	public static void main(String args[]) {
		//CharacterSelectFrame csf = new CharacterSelectFrame();
		//csf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//csf.setVisible(true);
		/** The timer. */
		int timer = 0;

		/** Map�� �����带 �۵���Ű�� T1 Thread. */
		Thread T1;

		/** �ð��ʸ� ���� T2 Thread. */
		Thread T2;
		T1 = null;
		WaitingRoomView wv = new WaitingRoomView();
		CharacterSelectView cv = new CharacterSelectView(wv);
		StatusManager player = new StatusManager(cv);
		LaunchManager lm = new LaunchManager(cv);
		
		Mapmanager MapController = new Mapmanager(T1, player);
		//WaitPlay w = new WaitPlay();
		//w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//w.setVisible(true);
	}
}
