package com.lucene.learn.analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexDocs {

	public static void main(String[] args) throws IOException {

		File newsfile = new File("testfile/news.txt");

		String text1 = textToString(newsfile);

		// Analyzer smcAnalyzer = new SmartChineseAnalyzer(true);
		Analyzer smcAnalyzer = new IKAnalyzer6x(true);

		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(smcAnalyzer);
		indexWriterConfig.setOpenMode(OpenMode.CREATE);
		// �����Ĵ洢·��
		Directory directory = null;
		// ��������ɾ����indexWriter����
		IndexWriter indexWriter = null;
		directory = FSDirectory.open(Paths.get("indexdir"));
		indexWriter = new IndexWriter(directory, indexWriterConfig);

		// �½�FieldType,����ָ���ֶ�����ʱ����Ϣ
		FieldType type = new FieldType();
		// ����ʱ�����ĵ�������Ƶ�ʡ�λ����Ϣ��ƫ����Ϣ
		type.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
		type.setStored(true);// ԭʼ�ַ���ȫ����������������
		type.setStoreTermVectors(true);// �洢������
		type.setTokenized(true);// ������
		Document doc1 = new Document();
		Field field1 = new Field("content", text1, type);
		doc1.add(field1);
		indexWriter.addDocument(doc1);
		indexWriter.close();
		directory.close();
	}

	public static String textToString(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// ����һ��BufferedReader������ȡ�ļ�
			String str = null;
			while ((str = br.readLine()) != null) {// ʹ��readLine������һ�ζ�һ��
				result.append(System.lineSeparator() + str);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

}
