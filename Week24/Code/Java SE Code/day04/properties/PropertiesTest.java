﻿package com.wayne.io.c3.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/*
 * 定义功能：获取一个应用程序运行的次数，如果超过5次，给出使用次数应经到注册的提示。并无法继续使用该程序
 * 
 * 思路：
 * 1〉应该有一个计数器
 * 每次程序启动都需要计数一次，并且是在原有的次数上进行计数
 * 2〉计数器就是一个变量。突然冒出一个想法，程序启动时开始进行计数，计数器必须存在于内存中并进行运算
 * 可以程序一旦结束，计数器消失了。那么再次启动该程序，计数器又重新被初始化了
 * 而我们需要多次启动同一个应用程序，使用的是同一个计数器
 * 这就需要计数器的生命周期变长，从内存存储到硬盘文件中
 * 
 * 3〉如何使用这个计数器？
 * 首先，程序启动时，应该先读取这个用于记录计数器信息的配置文件
 * 获取上一次计数器记录的次数。并进行试用次数的判断
 * 其次，对该次数进行自增，并将自增后的次数重新存储到配置文件中
 * 
 * 4〉文件中的信息该如何进行存储并体现？
 * 直接存储次数值就ok,但是不明确该数据的含义。所以起名字很重要
 * 这就有了名字和值得对应，所以可以使用键值对
 * 从而想到映射关系map集合可以做到，有需要读取硬盘上的数据。所以map+io=Properties
 * 
 */
public class PropertiesTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		getAppCount();
	}

	public static void getAppCount() throws IOException {
		// TODO Auto-generated method stub
		
		//将配置文件封装成File对象
		File confile = new File("conunt.properties");
		
		if(!confile.exists()){
			confile.createNewFile();
		}
		
		//读取(启动)该文件
		FileInputStream fis = new FileInputStream(confile);
		
		Properties prop = new Properties();
		
		prop.load(fis);
		
		//从集合中通过键获取次数
		String value = prop.getProperty("time");
		//定义一个计数器。记录获取到的次数
		int count = 0;
		if(value!=null){
			count = Integer.parseInt(value);
			if(count>=5){
				// System.out.println("使用次数已经达到5次，请注册，并支付钱！");
				// return;
				throw new RuntimeException("使用次数已经达到5次，请注册，并支付钱！");
			}
		}
		count++;
		
		//将改变后的次数重新存储到集合中
		prop.setProperty("time", count+"");
		
		//将自增后的次数重新存储(写入)到配置文件中
		FileOutputStream fos = new FileOutputStream(confile);
		
		prop.store(fos, "");
		
		fos.close();
		fis.close();
	}

}