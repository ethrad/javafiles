package playground;

import javax.swing.JFrame;

/** �α��� �� ĳ���� ������ �����ϴ� Ŭ���� */
public class LaunchManager {
	/** ������� ���� */
	UserInfo userInfo = new UserInfo(null, null);
	LoginView lv;

	LaunchManager(CharacterSelectView cv) {
		lv = new LoginView(userInfo, cv);
		
		
		
	}



	
	 
}