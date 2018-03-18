package com.example;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class LuceneTest {


    static final String indexPath = "/home/tianfeng/lucene/";

    @Test
    public void testSaveLuceneDoc() throws IOException {

        final Directory dir = FSDirectory.open(new File(indexPath).toPath());
        final Analyzer analyzer = new StandardAnalyzer();
        final Document doc = new Document();

        FieldType fieldType = new FieldType();
        fieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
        doc.add(new Field("name", "tianfeng", fieldType));
        doc.add(new Field("address", "hangzhou", fieldType));
        doc.add(new Field("sex", "man", fieldType));
        doc.add(new Field("introduce", "i am a programmer", fieldType));
        final IndexWriter writer = new IndexWriter(dir, new IndexWriterConfig(analyzer));
        writer.addDocument(doc);
        writer.close();
    }

    @Test
    public void searchLuceneDoc() throws ParseException, IOException {

        final String queryStr = "tianfeng";
        final String[] fields = {"name", "introduce"};
        final Analyzer analyzer = new StandardAnalyzer();
        final QueryParser queryParser = new MultiFieldQueryParser(fields, analyzer);

        final Query query = queryParser.parse(queryStr);
        final IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(new SimpleFSDirectory(new File(indexPath).toPath())));
        final TopDocs topDocs = indexSearcher.search(query, 10000);
        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            int docNum = scoreDoc.doc;
            Document document = indexSearcher.doc(docNum);
            printDocumentInfo(document);
        }
        System.err.println(query);
    }

    static void printDocumentInfo(Document document) {
        String name = document.get("name");
        System.err.println("name : " + name);

        String sex = document.get("sex");
        System.err.println("sex : " + sex);

        String address = document.get("address");
        System.err.println("address : " + address);

        String introduce = document.get("introduce");
        System.err.println("introduce : " + introduce);
    }
}
