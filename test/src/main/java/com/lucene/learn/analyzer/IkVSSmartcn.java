package com.lucene.learn.analyzer;

import java.io.IOException;
import java.io.StringReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class IkVSSmartcn {

	private static String str1 = "�����������ܻ��������� ��ǿ���ձ��������洦";
	private static String str2 = "IKAnalyzer��һ����Դ��,����java���Կ����������������ķִʹ��߰���";
	private static String str3 = "�������ҵĸ�!�й������з����˵ֿ����������ĵķ���!";

	public static void main(String[] args) throws IOException {
		Analyzer analyzer = null;
		System.out.println("����1:"+str1);
		System.out.println("SmartChineseAnalyzer�ִʽ��:");
		
		analyzer = new SmartChineseAnalyzer();
		printAnalyzer(analyzer, str1);
		System.out.println("IKAnalyzer�ִʽ��:");
		analyzer = new IKAnalyzer6x(true);
		printAnalyzer(analyzer, str1);
		
		
		System.out.println("-------------------------------------------");
		System.out.println("����2:"+str2);
		System.out.println("SmartChineseAnalyzer�ִʽ��:");
		analyzer = new SmartChineseAnalyzer();
		printAnalyzer(analyzer, str2);
		System.out.println("IKAnalyzer�ִʽ��:");
		analyzer = new IKAnalyzer6x(true);
		printAnalyzer(analyzer, str2);
	
		
		System.out.println("-------------------------------------------");
		System.out.println("����3:"+str3);
		System.out.println("SmartChineseAnalyzer�ִʽ��:");
		analyzer = new SmartChineseAnalyzer();
		printAnalyzer(analyzer, str3);
		System.out.println("IKAnalyzer�ִʽ��:");
		analyzer = new IKAnalyzer6x(true);
		printAnalyzer(analyzer, str3);
		analyzer.close();
	}

	public static void printAnalyzer(Analyzer analyzer, String str) throws IOException {
		StringReader reader = new StringReader(str);
		TokenStream toStream = analyzer.tokenStream(str, reader);
		toStream.reset();// �����
		CharTermAttribute teAttribute = toStream.getAttribute(CharTermAttribute.class);
		while (toStream.incrementToken()) {
			System.out.print(teAttribute.toString() + "|");
		}
		System.out.println();
	}
}
