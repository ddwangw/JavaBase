package com.lucene.learn.analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class CreateIndex {

	public static void main(String[] args) {

		// ����2��News����
		News news1 = new News();
		news1.setId(1);
		news1.setTitle("�����������ܻ��������� ��ǿ���ձ��������洦");
		news1.setContent("���������ձ����ల�������ƻ�2��10���ڻ�ʢ����������ͳ�����վ��л���ʱ����Ӵ��ձ�������Ͷ�ʵ�����");
		news1.setReply(672);
		News news2 = new News();
		news2.setId(2);
		news2.setTitle("����ӭ4380������ ũ��ѧ��700���˽������");
		news2.setContent("���죬������ѧӭ��4380������ȫ�����ؼ���ʮ�����ҵı������������У�ũ��ѧ����700������Ϊ�������...");
		news2.setReply(995);

		News news3 = new News();
		news3.setId(3);
		news3.setTitle("����������(Donald Trump)����������45����ͳ");
		news3.setContent("����ʱ��1��20�գ����ɵ¡��������������������ľ�ְ����ʽ��Ϊ������45����ͳ��");
		news3.setReply(1872);
		// ��ʼʱ��

		Date start = new Date();
		System.out.println("**********��ʼ��������**********");
		// ����IK�ִ���
		Analyzer analyzer = new IKAnalyzer6x();
		IndexWriterConfig icw = new IndexWriterConfig(analyzer);
		icw.setOpenMode(OpenMode.CREATE);
		Directory dir = null;
		IndexWriter inWriter = null;
		// ����Ŀ¼
		Path indexPath = Paths.get("indexdir");

		try {
			if (!Files.isReadable(indexPath)) {
				System.out.println("Document directory '" + indexPath.toAbsolutePath()
						+ "' does not exist or is not readable, please check the path");
				System.exit(1);
			}
			dir = FSDirectory.open(indexPath);
			inWriter = new IndexWriter(dir, icw);

			FieldType idType = new FieldType();
			idType.setIndexOptions(IndexOptions.DOCS);
			idType.setStored(true);

			FieldType titleType = new FieldType();
			titleType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
			titleType.setStored(true);
			titleType.setTokenized(true);

			FieldType contentType = new FieldType();
			contentType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
			contentType.setStored(true);
			contentType.setTokenized(true);
			contentType.setStoreTermVectors(true);
			contentType.setStoreTermVectorPositions(true);
			contentType.setStoreTermVectorOffsets(true);

			Document doc1 = new Document();
			doc1.add(new Field("id", String.valueOf(news1.getId()), idType));
			doc1.add(new Field("title", news1.getTitle(), titleType));
			doc1.add(new Field("content", news1.getContent(), contentType));
			doc1.add(new IntPoint("reply", news1.getReply()));
			doc1.add(new StoredField("reply_display", news1.getReply()));
			
			Document doc2 = new Document();
			doc2.add(new Field("id", String.valueOf(news2.getId()), idType));
			doc2.add(new Field("title", news2.getTitle(), titleType));
			doc2.add(new Field("content", news2.getContent(), contentType));
			doc2.add(new IntPoint("reply", news2.getReply()));
			doc2.add(new StoredField("reply_display", news2.getReply()));

			Document doc3 = new Document();
			doc3.add(new Field("id", String.valueOf(news3.getId()), idType));
			doc3.add(new Field("title", news3.getTitle(), titleType));
			doc3.add(new Field("content", news3.getContent(), contentType));
			doc3.add(new IntPoint("reply", news3.getReply()));
			doc3.add(new StoredField("reply_display", news3.getReply()));

			inWriter.addDocument(doc1);
			inWriter.addDocument(doc2);
			inWriter.addDocument(doc3);

			inWriter.commit();

			inWriter.close();
			dir.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		Date end = new Date();
		System.out.println("�����ĵ���ʱ:" + (end.getTime() - start.getTime()) + " milliseconds");
		System.out.println("**********�����������**********");
	}
}
