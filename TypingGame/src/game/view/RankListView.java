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
 * ���а���ͼ
 * �������а����
 * @author ��־��
 *
 */
public class RankListView {

	JFrame jf=new JFrame("Ӣ�۰�");
	
	final int WIDTH = 350;
	final int HEIGHT = 400;
	GameModel user=null;
	List<GameModel> rankList=null;
	
	RankListView(List<GameModel> rankList){
		this.rankList=rankList;
	}
	
	public void init() throws Exception {
		// ���ô�������
		jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, 
				(ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT); // ���ô��ھ���
		jf.setLayout(new FlowLayout());
		jf.setResizable(true);
		jf.setIconImage(ImageIO.read(new File("images\\logo.png"))); //����logo
		
		// ���ô�������
		
		// ����ͼƬ
		BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File("images\\1.jpg")));
		bgPanel.setBounds(0,0,WIDTH,HEIGHT);
		
		// ����
		JLabel title = new JLabel("���а�");
		title.setFont(new Font("����", Font.BOLD, 30));
		
		
		Box box = Box.createVerticalBox();
		Box boxH = Box.createHorizontalBox();
		Box boxHone = Box.createVerticalBox();
		Box boxHtwo = Box.createVerticalBox();
		Box boxHthree = Box.createVerticalBox();
		JLabel nameJL = new JLabel("����");
		JLabel truecount = new JLabel("�����ܸ���");
		JLabel scoreJL = new JLabel("��ȷ��");
		
		Font f1 = new Font("����", Font.BOLD, 22);
        Font f2 = new Font("����", Font.BOLD, 20);
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
