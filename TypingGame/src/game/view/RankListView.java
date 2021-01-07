package game.view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import game.model.GameModel;
import login.view.BackGroundPanel;
import login.view.ScreenUtils;

/**
 * 排行榜视图
 * 绘制排行榜界面
 * @author 段志超
 *
 */
public class RankListView {

	JFrame jf=new JFrame("英雄榜");
	
	final int WIDTH = 350;
	final int HEIGHT = 400;
	GameModel user=null;
	List<GameModel> rankList=null;
	
	RankListView(List<GameModel> rankList){
		this.rankList=rankList;
	}
	
	public void init() throws Exception {
		// 设置窗口属性
		jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, 
				(ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT); // 设置窗口居中
		jf.setLayout(new FlowLayout());
		jf.setResizable(true);
		jf.setIconImage(ImageIO.read(new File("images\\logo.png"))); //设置logo
		
		// 设置窗口内容
		
		// 背景图片
		BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images\\1.jpg")));
		bgPanel.setBounds(0,0,WIDTH,HEIGHT);
		
		// 标题
		JLabel title = new JLabel("排行榜");
		title.setFont(new Font("楷体", Font.BOLD, 30));
		
		
		Box box = Box.createVerticalBox();
		Box boxH = Box.createHorizontalBox();
		Box boxHone = Box.createVerticalBox();
		Box boxHtwo = Box.createVerticalBox();
		Box boxHthree = Box.createVerticalBox();
		JLabel nameJL = new JLabel("姓名");
		JLabel truecount = new JLabel("打字总个数");
		JLabel scoreJL = new JLabel("正确率");
		
		Font f1 = new Font("楷体", Font.BOLD, 22);
        Font f2 = new Font("楷体", Font.BOLD, 20);
        nameJL.setFont(f1);
        truecount.setFont(f1);
        scoreJL.setFont(f1);
        box.add(title);
        box.add(Box.createVerticalStrut(10));
        
        boxHone.add(nameJL);
        boxHtwo.add(truecount);
        boxHthree.add(scoreJL);
        
        Iterator<GameModel> it = rankList.iterator();
        while(it.hasNext()){
        	user = it.next();
        	JLabel name = new JLabel(user.getNickname());
        	JLabel truenum = new JLabel("" + user.getTotalType());
        	JLabel score = new JLabel("" + (int)(user.getAccuracy()*100)+"%");
        	name.setFont(f2);
        	truenum.setFont(f2);
        	score.setFont(f2);
        	
        	boxHone.add(name);
        	boxHtwo.add(truenum);
        	boxHthree.add(score);
        }
        
        boxH.add(boxHone);
        boxH.add(Box.createHorizontalStrut(30));
        boxH.add(boxHtwo);
        boxH.add(Box.createHorizontalStrut(30));
        boxH.add(boxHthree);
        box.add(boxH);
		
        jf.add(box);
        jf.setVisible(true);
	}

}
