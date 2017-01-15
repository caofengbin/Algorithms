package com.algorithms.chapter2;

public class SortAlgorithmsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Character[] arrays = {'S','O','R','T','E','X','A','M','P','L','E'};
		//Character[] arrays = {'A','B','C','D','E','F','G','H','I','J','K'};
		//Character[] arrays = {'Z','Y','I','H','G','F','E','D','C','B','A'};
		Character[] arrays = {'E','X','A','M','P','L','E'};
		BaseSort sortUtil = new InsertionSort();
		sortUtil.reset();
		sortUtil.show(arrays);
		sortUtil.sort(arrays);
		sortUtil.show(arrays);
		System.out.println("进行比较的次数为:" + sortUtil.getCompareCount());
		System.out.println("进行交换的次数为:" + sortUtil.getExchageCount());
	}

}
