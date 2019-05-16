package com.qf.utils;

import java.util.Random;

public class VerificationCodeUtils {
	public static String[] az={"a","b","c","d","e","z","f","g","1","2","3","4","5","6","7","8","9"};
	
	public static String createVerificationCode(){
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			Random random = new Random();
			int number = random.nextInt(az.length)-1;
			builder.append(az[number]);
		}
		return builder.toString();
	}
	
	
}
