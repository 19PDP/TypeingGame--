package game.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import game.model.GameModel;


/**
 * ���а���
 * ���û������ļ��ж�ȡ�����û����ݣ�����ĳһ���Խ���������
 * @author ��־��
 *
 */
public class HandleRankList {
	
	/**
	 * ��ȡ�����û������ݣ������û��б� userList �У�
	 * Ȼ���մ�����ȷ�ʴӸߵ�������
	 * @return �������б�
	 */
	public static List<GameModel> sortUser(){
		
		List<GameModel> userList = new ArrayList<GameModel>(); // �û��б�
		
		try {
			File file=new File("UserInfo.json");
			Reader in = new FileReader(file); // �ײ���
			BufferedReader userRead=new BufferedReader(in); // �ϲ���
			String json=null;
			GameModel readUser=null;
			ObjectMapper mapper = new ObjectMapper();
			while((json=userRead.readLine()) != null) { // ���϶�ȡ�û�����
				readUser=mapper.readValue(json, GameModel.class); // ��ȡ�����û�
				if(readUser.getTotalRight() != 0) {
					readUser.setAccuracy((double)readUser.getTotalRight()/readUser.getTotalType());
				}
				userList.add(readUser); // ����ȡ�����û���ӵ��û��б�
			}
			userRead.close(); // �ر��ϲ���
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		// ���û��б���ȷ�ʽ�������
		Collections.sort(userList, new Comparator<GameModel>() { // ʹ��Comparator�Ƚ����ӿ�
			@Override
			public int compare(GameModel user1, GameModel user2) {
				return ((Double)user2.getAccuracy()).compareTo(user1.getAccuracy()); // ����������
			}
		});
		
		return userList;
	}

}
