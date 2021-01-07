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
 * 保存数据：
 * 在退出登陆或退出游戏前将该用户的所有数据存入用户文件
 * @author 段志超
 *
 */
public class SaveData {

	public static void saveUserData(GameModel nowUser) {
		
		// 更新当前用户数据
		nowUser.setTotalRight(nowUser.getTotalRight()+nowUser.getRightCount());
		nowUser.setTotalType(nowUser.getTotalType()+nowUser.getTypeCount());
		nowUser.setAccuracy((double)nowUser.getTotalRight()/nowUser.getTotalType());
		
		// 创建 Jackson 的核心对象 ObjectMapper
		ObjectMapper mapper=new ObjectMapper();
		List<String> users = new ArrayList<String>(); // 所有用户
		
		
		try {
			String nowJson=mapper.writeValueAsString(nowUser); // 当前用户对应的 Json 字符串
			String readJson=null; // 读取到的用户对应的 Json 字符串
			GameModel readUser=null; // 读取到的用户
			File file=new File("UserInfo.json");
			Reader in=new FileReader(file); // 底层流
			BufferedReader userRead=new BufferedReader(in); // 上层流
			while((readJson=userRead.readLine()) != null) { // 不断读取用户对应的 Json 字符串
				// 更新用户数据
				readUser=mapper.readValue(readJson, GameModel.class);
				if(nowUser.getUsername().equals(readUser.getUsername())) {
					users.add(nowJson); // 读取到当前用户的原始数据，将新数据加入用户列表
				}
				else {
					users.add(readJson);
				}
			}
			userRead.close();
			
			Writer out=new FileWriter(file, false);
			BufferedWriter writeLine=new BufferedWriter(out); // 上层流
			Iterator<String> it = users.iterator();
			while(it.hasNext()) { // 将跟新数据后的用户列表重新写入文件
				writeLine.write(it.next());
				writeLine.newLine();
			}
			writeLine.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
