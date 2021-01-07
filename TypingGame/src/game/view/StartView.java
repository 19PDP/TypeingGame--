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
 * 开始视图：
 * 绘制开始界面
 * @author 段志超
 *
 */
public class StartView {
	
	JFrame jf = new JFrame("小霸王打字机");
	private GameModel user=null;
	
	final int WIDTH = 740;
	final int HEIGHT = 550;
	
	public StartView(GameModel nowUser){
		user=nowUser;
	}
	
	// 组装视图
	public void init() throws Exception {
		// 设置窗口相关属性
		jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2,
				(ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
//		jf.setLayout(new FlowLayout());
		jf.setResizable(true);
		jf.setIconImage(ImageIO.read(new File("images\\logo.png"))); //设置logo
		
		// 设置窗口内容
		// 背景图片
		BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images\\1.jpg")));
		bgPanel.setBounds(0,0,WIDTH,HEIGHT);
		
		
		// 选择关卡
		Box cBox1 = Box.createHorizontalBox();
		Box cBox2 = Box.createHorizontalBox();
//		Dimension preferredSize=new Dimension(160,40);
		String choiceStr []= {"第一关：沧海竞舟", "第二关：华山论剑", "第三关：珠峰争鼎", "自由模式：称霸武林"};
		JButton choiceBut []=new JButton[choiceStr.length];
		HandleChoice handleChoice = new HandleChoice(user);
		// 设置可以选择的关卡
		for(int i=0; i<choiceStr.length; i++) {
			choiceBut[i]=new JButton(choiceStr[i]);
			if(user.getPassNum()>=i) // 如果上一关已通过，将该关设置为可以进入
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
		
		
		// 开始游戏
		Box sBox = Box.createHorizontalBox();
		JButton startBut = new JButton("开始游戏");
		startBut.setBackground(Color.red);
//		preferredSize=new Dimension(150,70);
//		startBut.setPreferredSize(preferredSize);
		startBut.addActionListener(new ActionListener() { // 为“开始游戏”按钮注册监视器
			@Override
			public void actionPerformed(ActionEvent e) {
				// 跳转到游戏界面
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
		
		
		
		// 排行榜
		Box kBox=Box.createHorizontalBox();
		JButton rankListBut = new JButton("排  行  榜");
		rankListBut.addActionListener(new ActionListener() { // 为排行榜按钮注册监视器
			@Override
			public void actionPerformed(ActionEvent e) {
				List<GameModel> rankList = HandleRankList.sortUser(); // 获取所有用户排序后的列表
				try {
					 // 显示排行榜视图
					new RankListView(rankList).init();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		kBox.add(rankListBut);
		
		// 退出登陆
		JButton reLoginBut = new JButton("退出登陆");
		reLoginBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SaveData.saveUserData(user); // 退出登陆前先保存用户数据
					
					new LoginView().init(); // 登陆界面
					jf.dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		// 退出游戏
		JButton exitBut = new JButton("退出游戏");
		exitBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SaveData.saveUserData(user); // 退出游戏前先保存用户数据
				System.exit(0); // 退出
			}
		});
		
		Box rBox = Box.createHorizontalBox();
		rBox.add(reLoginBut);
		rBox.add(Box.createHorizontalStrut(80));
		rBox.add(exitBut);
		
		
		// 欢迎语
		JLabel label = new JLabel(user.getNickname()+"你好,小霸王打字机欢迎您！");
		label.setFont(new Font("楷体", Font.BOLD, 40));
		
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
