package game.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import login.model.Register;

/**
 * ��Ϸģ��,ע��ģ�͵����ࡣ
 * ÿ��ע��һ�����û��������Ը��ݸ��û�ģ�͵õ�һ���µ���Ϸģ�ͣ��˺���û���������Ϸ��Ϊ�����ڴ�ģ�ͻ����Ϸ�����
 * @see login.model.Register
 * @author ��־��
 *
 */
public class GameModel extends Register{

	// �ų����ԣ�������Ϸģ��ʱ���ó�Ա��������ӵ�json
	@JsonIgnore
	private int difficulty=5; // �Ѷ�
	@JsonIgnore
	private double accuracy=0; // �������ʷ������ȷ��
	@JsonIgnore
	private long typeCount=0; // ������Ϸ���ָ���
	@JsonIgnore
	private long rightCount=0; // ������Ϸ������ȷ��
	
	/**
	 * ���췽�����ø����Ĭ�Ϲ��췽�����õ�һ����Ϸģ��ʵ����������ԭ�����Ծ�ΪĬ��ֵ��
	 * ���ڴ˻����������ˡ��Ѷȡ����ԣ������û��Ѿ�ͨ�ص���Ŀ������Ĭ���Ѷȡ�
	 * @see login.model.Register#Register()
	 */
	public GameModel(){
		super();
		switch(passNum) { // �����û��Ѿ�ͨ�ص���Ŀ������Ĭ���Ѷ�
		case 0:
			difficulty=5;
			break;
		case 1:
			difficulty=10;
			break;
		case 3:
			difficulty=15;
			break;
		}
	}
	
	public void setDifficulty(int d) {
		difficulty=d;
	}
	public int getDifficulty() {
		return difficulty;
	}

	public double getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
	
	public long getTypeCount() {
		return typeCount;
	}
	public void setTypeCount(long typeCount) {
		this.typeCount = typeCount;
	}

	public long getRightCount() {
		return rightCount;
	}
	public void setRightCount(long rightCount) {
		this.rightCount = rightCount;
	}

	
}
