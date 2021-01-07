package game.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import game.model.GameModel;

/**
 * �������ݣ�
 * ���˳���½���˳���Ϸǰ�����û����������ݴ����û��ļ�
 * @author ��־��
 *
 */
public class SaveData {

	public static void saveUserData(GameModel nowUser) {
		
		// ���µ�ǰ�û�����
		nowUser.setTotalRight(nowUser.getTotalRight()+nowUser.getRightCount());
		nowUser.setTotalType(nowUser.getTotalType()+nowUser.getTypeCount());
		nowUser.setAccuracy((double)nowUser.getTotalRight()/nowUser.getTotalType());
		
		// ���� Jackson �ĺ��Ķ��� ObjectMapper
		ObjectMapper mapper=new ObjectMapper();
		List<String> users = new ArrayList<String>(); // �����û�
		
		
		try {
			String nowJson=mapper.writeValueAsString(nowUser); // ��ǰ�û���Ӧ�� Json �ַ���
			String readJson=null; // ��ȡ�����û���Ӧ�� Json �ַ���
			GameModel readUser=null; // ��ȡ�����û�
			File file=new File("UserInfo.json");
			Reader in=new FileReader(file); // �ײ���
			BufferedReader userRead=new BufferedReader(in); // �ϲ���
			while((readJson=userRead.readLine()) != null) { // ���϶�ȡ�û���Ӧ�� Json �ַ���
				// �����û�����
				readUser=mapper.readValue(readJson, GameModel.class);
				if(nowUser.getUsername().equals(readUser.getUsername())) {
					users.add(nowJson); // ��ȡ����ǰ�û���ԭʼ���ݣ��������ݼ����û��б�
				}
				else {
					users.add(readJson);
				}
			}
			userRead.close();
			
			Writer out=new FileWriter(file, false);
			BufferedWriter writeLine=new BufferedWriter(out); // �ϲ���
			Iterator<String> it = users.iterator();
			while(it.hasNext()) { // ���������ݺ���û��б�����д���ļ�
				writeLine.write(it.next());
				writeLine.newLine();
			}
			writeLine.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
