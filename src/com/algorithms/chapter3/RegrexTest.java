package com.algorithms.chapter3;

public class RegrexTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(isHsStock("nq60090"));
	}
	
	/**
     * 判断美股的股票代码
     */
    private static boolean isHsStock(String str) {
        String regex = "^sh[0-9]+";
        String regex2 = "^sz[0-9]+";
        String regex3 = "^nq[0-9]+";
        return str.matches(regex) || str.matches(regex2) || str.matches(regex3);
    }
    
    private static boolean isHkStock(String str) {
        String regex = "^hk[0-9]+";
        return str.matches(regex);
    }
    
    private static boolean isUsStock(String str) {
        String regex = "^us[A-Z.]+";
        return str.matches(regex);
    }
   
}
