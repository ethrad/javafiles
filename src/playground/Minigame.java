package playground;

import javax.swing.ImageIcon;

/** �̴ϰ����� ��Ƶδ� class. Ŭ���ؼ� Ǫ�� ������ �̹����� ���� ���� �����ϴ� ���� 2����
 * @author Chungheon Yi
 *
 */
public class Minigame {
		
	/** ���� �̹��� */
	private ImageIcon image;

	/** ���� �� */
	private String str;

	/** �̴ϰ��� pause üũ �ϴµ� ���� boolean �� */
	private boolean isStop = false;
	
	/** ���ӿ� ���� ������ ��Ƴ��� ���ڿ� �迭, String[0]�� �� �̴ϰ����� �̸� */
	private String[] list = new String[4];
	
	/** ���� ���� �� */
	private int timer =30;
	
	
		/** The x. */
		private int x;
		
		/** The y. */
		private int y;
		
		/** ��ǥ�� 4�� ��Ƴ��� �ڽ�(Ŭ���ؼ� �� ��ǥ���� ���� �簢�� �ȿ� ���Ե��ִٸ� ����) */
		private int Point[];
	
	/**
		 * �� ������ ����� ������ �����Ѵ�. (��ǥ x)
		 * @param ima �̹���
		 * @param strings ���� ����
		 * @param howtoplay ���ӿ� ���� ������ ��Ƴ��� ���ڿ� �迭, String[0]�� �� �̴ϰ����� �̸�
		 */
	public Minigame(ImageIcon ima, String strings,String[] howtoplay) // ���� ����
	{
		this.image = ima;

		str = new String(strings);
		setList(howtoplay);
		
	}
	
	/**
	 * �� ������ ����� ������ �����Ѵ�.Ŭ���� (��ǥO)
	 * @param ima ������ �̹���
	 * @Param XY (x,y) ��ǥ 4���� for answer
	 * @param howtoplay ���ӿ� ���� ������ ��Ƴ��� ���ڿ� �迭, String[0]�� �� �̴ϰ����� �̸�
	 *  
	 */
	public Minigame(ImageIcon ima,int XY[], String[] howtoplay) // ���� ����
	{
		this.image = ima;
		setList(howtoplay);
		
		Point = new int[4];
		setPoint(XY);
		
	}
	
	/**
	 * Sets the x.
	 *
	 * @param num x�� ����
	 */
	void setX(int num) {
		x = num;
	}
	
	/**
	 * Sets the y.
	 *
	 * @param num y�� ����
	 */
	void setY(int num) {
		y = num;
	}
	
	/**
	 * Sets X and Y. + ������ Ŭ�� �̴ϰ��ӽ� �� ������ ������.
	 *
	 * @param num1 x�� ���ڷ� ����
	 * @param num2 y�� ���ڷ� ����
	 */
	void setXY(int num1,int num2) {
		x = num1;
		y=  num2;
		
		if(Point !=null)
		{  //(x�ּ�,x�ִ�,y�ּ�,y�ִ�)
			if(Point[0]<=x && Point[1]>=x && Point[2]<=y && Point[3]>=y)
			{
				str = "Ŭ���� ���� ������"; //���� ��ǥ�� �� �簢�� ���̶�� ����� true
			}
			else
				str = "you are wrong"; //�׿� 
		}
	}
	
	/**
	 * Gets the x.
	 * @return the x
	 */
	int getX() {
		return x;
	}
	
	/**
	 * Gets the y.
	 * @return the y
	 */
	int getY() {
		return y;
	}
	
	/**
	 * Sets the point.
	 * @param arr 4���� ��ǥ
	 */
	void setPoint(int[] arr)
	{
		try {
			for(int i=0; i<4; i++)
			{
				Point[i]=arr[i];
			}
		}
		catch(ArrayIndexOutOfBoundsException e) //�Ǽ� ������
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the point.
	 *
	 * @param index �ް���� Point �ε��� ��
	 * @return the point[index]
	 */
	int getPoint(int index)
	{
		return Point[index];
	}
	
	/**
	 * ���� �̹��� ��ȯ
	 * 
	 * @return image �̹��� ��ȯ
	 */
	public ImageIcon getImage() {
		return image;
	}
	
	/**
	 * Sets the image.
	 * @param ima the new image
	 */
	public void setImage(ImageIcon ima) {
		 image = ima;
	}
	
	
	/**
	 * Gets the timer.
	 * @return the timer
	 */
	public int getTimer() {
		return timer;
	}
	
	/**
	 * Sets the timer.
	 * @param timer the new timer
	 */
	public void setTimer(int timer) {
		this.timer = timer;
	}
	
	/**
	 * ���� �� ��ȯ
	 * 
	 * @return str ���� ��ȯ
	 */
	public String getAnswer() {
		return str;
	}

	public void setAnswer(String strings) {
		this.str = new String(strings);
	}
	/**
	 * �̴ϰ��� pause üũ �ϴµ� ���� boolean �� ��ȯ
	 * @return isstop ��ȯ
	 */
	public boolean getisStop() {
		return isStop;
	}

	
	/**	�̴ϰ��� pause üũ �ϴµ� ���� boolean �� setting
	 * @param bool = setting�� boolean��
	 */
	public void setisStop(boolean bool) {
		isStop = bool;
	}
	
	/**
	 * Sets the list.
	 *
	 * @param getstring the new list, list[0]�� �� ���� �̸�
	 */
	public void setList(String[] getstring) // ���� ������ ������´�.
	{
		try{
			for(int i=0; i<4; i++) list[i] = getstring[i]; 
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();  //�Ǽ�����
		}
	}
	
	/**
	 * Gets the list. list[0]�� �� �̴ϰ��� �̸�
	 *
	 * @param �ε��� ��ȣ
	 * @return the list
	 */
	public String getList(int i)
	{
		return list[i];
	}
	
}
