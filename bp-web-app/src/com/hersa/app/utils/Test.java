package com.hersa.app.utils;

import java.math.BigDecimal;

public class Test {

	public static void main(String[] args) {
		//System.err.println(solve(0, 3, 4, 2));
		//System.err.println(solve(0, 2, 5, 3));
		System.err.println(solve(43, 2, 70, 2));
		
		
	}
	
	public static String solve(int x1, int v1, int x2, int v2) {
		
		if ((x2 > x1) && (v2 > v1)) {
			return "NO";
		}
		/*
		 * EQUATION:
		 * 
		 * x1 + n * v1 = x2 + y * v2
		 * 
		 * */
		
		if(v2 - v1 == 0) {
			return "NO";
		}
		
		if ((x1 - x2) % (v2 - v1) == 0) {
			return "YES";
		}
		
		return "NO";
	}
}
