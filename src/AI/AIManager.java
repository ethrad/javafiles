package AI;

import java.util.LinkedList;

import Map.*;
import playground.map;

/**
 * AI�� ��Ʈ�� �ϴ� Ŭ����
 * @author Chungheon Yi
 *
 */
public class AIManager {

	/**
	 * AI�� �����ϴ� AIManager ������ .
	 */
	public AIManager(map[] puthere)
	{
		String adj[]= {"�ȶ���","������","��������","��û��","�Ϳ���","�����","�����","Ű ū"};
		String name[]= {"����","��","���̺�","ȫ�浿","�����","����","����","����"};
		
		for(int i=0; i<5; i++) //AIgenerator i�� �Ѱ� = ai�� ����
			{
			String Ainame = adj[(int)(Math.random()*10)%8]+
					name[(int)(Math.random()*10)%8]; //�̸� ���� 
			
				int popAILocation = (int)(Math.random()*10)%9; //�̰��� ����
				puthere[popAILocation].setUserNumber
				(puthere[popAILocation].getUserNumber()+1);//�ش� �� ��ġ�� ������+1
			}
		
		
		
		
		
	}
	
	/**
	 * AI�� �����̴� Move algorithm.
	 *
	 * @param currentMap ai�� ���� �ִ� ��ġ
	 * @param worldMap the world map
	 * @param list ���� �������� �Ǵ��� �ȵǴ��� Ȯ��
	 */
	public void MoveAlgorithm(map currentMap, map[] worldMap,final LinkedList<Integer> list) 
	{
		int current = currentMap.getLoc(); //���߿� AI ��ġ�� �ٲٱ� �����Ұ�
		
		
		
		int move; //�̵��� ��ġ 
		
		if(list.isEmpty()) //���� ��� �ݰ����� �������� �̵�
			{	 
				move= (int)(Math.random()*10)%9;
			}
		else //�ƴϸ� �ݱ��� ���������� �̵�
			{
				move = list.get((int)(Math.random()*10)%list.size());
			}
		
			currentMap.setUserNumber(currentMap.getUserNumber()-1);
			worldMap[move].setUserNumber(worldMap[move].getUserNumber()+1);  //�̵�
		
	}
	
	public void AttackAlgorithm()
	{
		
	}
}
