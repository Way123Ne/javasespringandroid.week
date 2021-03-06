﻿package com.wayne.io.c4.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 *获取指定目录下，指定扩展名的文件(包含子目录中的) 
 *将这些文件的绝对路径写入到一个文本文件中 
 * 
 *简单说：就是建立一个指定扩展名的文件的列表
 *
 *思路：
 *1〉必须进行深度遍历
 *2〉要在遍历的过程中进行过滤。将符合条件的内容都存储到容器中
 *3〉对容器中的过滤出的东西再进行遍历并将其绝对路径写入到文件中
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File dir = new File("e:\\J2ee workspace\\day22e");
		
		FilenameFilter filter = new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(".java");
			}
		};
		
		List<File> list = new ArrayList<File>();
		
		getFiles(dir,filter,list);
		
		File destFile = new File(dir,"javalist.txt");
	
		write2File(list,destFile);
	}

	/**
	 * 对指定目录中的内容进行深度遍历，并按照指定过滤器，进行过滤
	 * 将过滤后的内容存储到指定容器List中
	 * @param dir
	 * @param filter
	 * @param list
	 */
	public static void getFiles(File dir, FilenameFilter filter, List<File> list) {
		// TODO Auto-generated method stub

		File[] files = dir.listFiles();

		for (File file : files) {
			if (file.isDirectory()) {
				// 递归
				getFiles(file, filter, list);
			} else {
				// 对遍历到的问及那进行过滤器的过滤。将符合条件File对象，存储到List集合中
				if (filter.accept(dir, file.getName())) {
					list.add(file);
				}
			}
		}
	}

	public static void write2File(List<File> list,File destFile) {
		// TODO Auto-generated method stub

		BufferedWriter bufw = null;
		try{
		bufw = new BufferedWriter(new FileWriter(destFile));
		
		for(File file:list){
			bufw.write(file.getAbsolutePath());
			bufw.newLine();
			bufw.flush();  
		}
		} catch(IOException e){
			throw new RuntimeException("写入失败");
		}finally{
			if(bufw!=null)
				try{
					bufw.close();
				}catch(IOException e){
					throw new RuntimeException("关闭失败 ");
				}
		}
	}
}
