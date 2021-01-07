package game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import game.model.GameModel;

/**
 * �Ѷ�ѡ����
 * �����û�ѡ��Ĺؿ���������Ϸ�Ѷ�
 * @author Administrator
 *
 */
public class HandleChoice implements ActionListener {

	GameModel user=null;
	
	public HandleChoice(GameModel user) {
		this.user=user;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// ���ò�ͬ�ؿ��Ѷ�
			switch(e.getActionCommand()) {
			case "��һ�أ��׺�����" :
				user.setDifficulty(5);
				break;
			case "�ڶ��أ���ɽ�۽�" :
				user.setDifficulty(10);
				break;
			case "�����أ��������" :
				user.setDifficulty(15);
				break;
			case "����ģʽ���ư�����" :
				String difficultyCustom=JOptionPane.showInputDialog(null,"����������Ҫ���Ѷ� (�˹�����֪֮��)",
						"�Ѷ��Զ���",JOptionPane.PLAIN_MESSAGE);
				if(difficultyCustom != null) {
					user.setDifficulty(Integer.parseInt(difficultyCustom));
				}
				break;
			}
	
	}

}
