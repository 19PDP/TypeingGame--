package game.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;


import javax.imageio.ImageIO;
import javax.swing.*;

import game.controller.HandleChoice;
import game.controller.HandleRankList;
import game.controller.SaveData;
import game.model.GameModel;
import login.view.BackGroundPanel;
import login.view.LoginView;
import login.view.ScreenUtils;

/**
 * ��ʼ��ͼ��
 * ���ƿ�ʼ����
 * @author ��־��
 *
 */
public class StartView {
	
	JFrame jf = new JFrame("С�������ֻ�");
	private GameModel user=null;
	
	final int WIDTH = 740;
	final int HEIGHT = 550;
	
	public StartView(GameModel nowUser){
		user=nowUser;
	}
	
	// ��װ��ͼ
	public void init() throws Exception {
		// ���ô����������
		jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2,
				(ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
//		jf.setLayout(new FlowLayout());
		jf.setResizable(true);
		jf.setIconImage(ImageIO.read(new File("images\\logo.png"))); //����logo
		
		// ���ô�������
		// ����ͼƬ
		BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images\\1.jpg")));
		bgPanel.setBounds(0,0,WIDTH,HEIGHT);
		
		
		// ѡ��ؿ�
		Box cBox1 = Box.createHorizontalBox();
		Box cBox2 = Box.createHorizontalBox();
//		Dimension preferredSize=new Dimension(160,40);
		String choiceStr []= {"��һ�أ��׺�����", "�ڶ��أ���ɽ�۽�", "�����أ��������", "����ģʽ���ư�����"};
		JButton choiceBut []=new JButton[choiceStr.length];
		HandleChoice handleChoice = new HandleChoice(user);
		// ���ÿ���ѡ��Ĺؿ�
		for(int i=0; i<choiceStr.length; i++) {
			choiceBut[i]=new JButton(choiceStr[i]);
			if(user.getPassNum()>=i) // �����һ����ͨ�������ù�����Ϊ���Խ���
				choiceBut[i].setEnabled(true); 
			else 
				choiceBut[i].setEnabled(false);
			choiceBut[i].addActionListener(handleChoice);
//			choiceBut[i].setPreferredSize(preferredSize);
		}
		
		cBox1.add(choiceBut[0]);
		cBox1.add(Box.createHorizontalStrut(20));
		cBox1.add(choiceBut[1]);
		cBox2.add(choiceBut[2]);
		cBox2.add(Box.createHorizontalStrut(20));
		cBox2.add(choiceBut[3]);
		
		
		// ��ʼ��Ϸ
		Box sBox = Box.createHorizontalBox();
		JButton startBut = new JButton("��ʼ��Ϸ");
		startBut.setBackground(Color.red);
//		preferredSize=new Dimension(150,70);
//		startBut.setPreferredSize(preferredSize);
		startBut.addActionListener(new ActionListener() { // Ϊ����ʼ��Ϸ����ťע�������
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ת����Ϸ����
				try {
//					new CharJFrame(user.getDifficulty());
					new GameView(user).init();
					jf.dispose();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		sBox.add(startBut);
		
		
		
		// ���а�
		Box kBox=Box.createHorizontalBox();
		JButton rankListBut = new JButton("��  ��  ��");
		rankListBut.addActionListener(new ActionListener() { // Ϊ���а�ťע�������
			@Override
			public void actionPerformed(ActionEvent e) {
				List<GameModel> rankList = HandleRankList.sortUser(); // ��ȡ�����û��������б�
				try {
					 // ��ʾ���а���ͼ
					new RankListView(rankList).init();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		kBox.add(rankListBut);
		
		// �˳���½
		JButton reLoginBut = new JButton("�˳���½");
		reLoginBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SaveData.saveUserData(user); // �˳���½ǰ�ȱ����û�����
					
					new LoginView().init(); // ��½����
					jf.dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		// �˳���Ϸ
		JButton exitBut = new JButton("�˳���Ϸ");
		exitBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SaveData.saveUserData(user); // �˳���Ϸǰ�ȱ����û�����
				System.exit(0); // �˳�
			}
		});
		
		Box rBox = Box.createHorizontalBox();
		rBox.add(reLoginBut);
		rBox.add(Box.createHorizontalStrut(80));
		rBox.add(exitBut);
		
		
		// ��ӭ��
		JLabel label = new JLabel(user.getNickname()+"���,С�������ֻ���ӭ����");
		label.setFont(new Font("����", Font.BOLD, 40));
		
		Box box=Box.createVerticalBox();
		box.add(Box.createVerticalStrut(120));
		box.add(cBox1);
		box.add(Box.createVerticalStrut(5));
		box.add(cBox2);
		box.add(Box.createVerticalStrut(10));
		box.add(sBox);
		box.add(Box.createVerticalStrut(40));
		box.add(kBox);
		box.add(Box.createVerticalStrut(80));
		box.add(rBox);
		
		bgPanel.add(label);
		bgPanel.add(box);
		
		jf.add(bgPanel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
