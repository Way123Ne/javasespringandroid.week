﻿package com.wayne.inter;

//将小写字母转换成大写

public class LowerLetter implements ChangeLetter {

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	private String str;
	
	@Override
	public String change() {
		// TODO Auto-generated method stub
		return str.toLowerCase();
	}
	
}
