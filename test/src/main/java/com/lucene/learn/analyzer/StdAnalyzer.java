package com.lucene.learn.analyzer;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class StdAnalyzer {
	private static String strCh = "�л����񹲺͹�����һ����13���˿ڵĹ���";
	private static String strEn = "Dogs can not achieve a place,eyes can reach";
	public static void main(String[] args) throws IOException {
		System.out.println("StandardAnalyzer �����ķִʣ�");
		stdAnalyzer(strCh);
		System.out.println("StandardAnalyzer ��Ӣ�ķִʣ�");
		stdAnalyzer(strEn);
	}
	private static void stdAnalyzer(String strCh2) throws IOException{
		Analyzer analyzer = null;
		analyzer = new StandardAnalyzer();
		StringReader reader = new StringReader(strCh2);
		TokenStream toStream = analyzer.tokenStream(strCh2, reader);
		toStream.reset();
		CharTermAttribute teAttribute = toStream.getAttribute(CharTermAttribute.class);
		System.out.println("�ִʽ����");
		while(toStream.incrementToken()) {
			System.out.print(teAttribute.toString()+"|");
		}
		System.out.println("\n");
		analyzer.close();
	}
}
