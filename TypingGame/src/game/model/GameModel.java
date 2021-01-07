package game.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import login.model.Register;

/**
 * 游戏模型,注册模型的子类。
 * 每当注册一个新用户，即可以根据该用户模型得到一个新的游戏模型，此后该用户的所有游戏行为都将在此模型基础上发生。
 * @see login.model.Register
 * @author 段志超
 *
 */
public class GameModel extends Register{

	// 排除属性，处理游戏模型时，该成员变量不添加到json
	@JsonIgnore
	private int difficulty=5; // 难度
	@JsonIgnore
	private double accuracy=0; // 该玩家历史打字正确率
	@JsonIgnore
	private long typeCount=0; // 本次游戏打字个数
	@JsonIgnore
	private long rightCount=0; // 本次游戏打字正确数
	
	/**
	 * 构造方法调用父类的默认构造方法；得到一个游戏模型实例，其所有原有属性均为默认值；
	 * 但在此基础上新增了“难度”属性，根据用户已经通关的数目，设置默认难度。
	 * @see login.model.Register#Register()
	 */
	public GameModel(){
		super();
		switch(passNum) { // 根据用户已经通关的数目，设置默认难度
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
