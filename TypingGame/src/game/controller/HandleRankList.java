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
 * 排行榜处理：
 * 从用户数据文件中读取所有用户数据，并按某一属性进行排名。
 * @author 段志超
 *
 */
public class HandleRankList {
	
	/**
	 * 读取所有用户的数据，存入用户列表 userList 中；
	 * 然后按照打字正确率从高到底排序
	 * @return 排序后的列表
	 */
	public static List<GameModel> sortUser(){
		
		List<GameModel> userList = new ArrayList<GameModel>(); // 用户列表
		
		try {
			File file=new File("UserInfo.json");
			Reader in = new FileReader(file); // 底层流
			BufferedReader userRead=new BufferedReader(in); // 上层流
			String json=null;
			GameModel readUser=null;
			ObjectMapper mapper = new ObjectMapper();
			while((json=userRead.readLine()) != null) { // 不断读取用户数据
				readUser=mapper.readValue(json, GameModel.class); // 读取到的用户
				if(readUser.getTotalRight() != 0) {
					readUser.setAccuracy((double)readUser.getTotalRight()/readUser.getTotalType());
				}
				userList.add(readUser); // 将读取到的用户添加到用户列表
			}
			userRead.close(); // 关闭上层流
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		// 对用户列表按正确率进行排序
		Collections.sort(userList, new Comparator<GameModel>() { // 使用Comparator比较器接口
			@Override
			public int compare(GameModel user1, GameModel user2) {
				return ((Double)user2.getAccuracy()).compareTo(user1.getAccuracy()); // 按降序排列
			}
		});
		
		return userList;
	}

}
