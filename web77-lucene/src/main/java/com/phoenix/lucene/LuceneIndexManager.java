package com.phoenix.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * lucenne 索引管理
 *
 * 添加
 *
 * 删除
 *
 * 修改
 *
 * 查询、精准查询
 *
 */
public class LuceneIndexManager {

    public IndexWriter getIndexWriter() throws IOException {
        //1. 创建一个indexWriter对象
        //1.1 指定索引库的存放位置Directory
        Directory directory = FSDirectory.open(Paths.get("index"));
//        Analyzer analyzer = new StandardAnalyzer();//官方推荐
        Analyzer analyzer=new IKAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        //1.2 指定一个分析器 对文档进行分析
        return new IndexWriter(directory, indexWriterConfig);
    }

    /**
     * 全部删除
     * @throws IOException
     */
    @Test
    public void testDeleteAll() throws IOException {
        // 全部删除
        IndexWriter indexWriter = this.getIndexWriter();
        long count = indexWriter.deleteAll();
        System.out.println("count: "+count);
        indexWriter.close();
    }

    /**
     * 根据条件删除某些索引
     * @throws IOException
     */
    @Test
    public void testDeleteWithCondition() throws IOException {
        // 全部删除
        IndexWriter indexWriter = this.getIndexWriter();
        Query query=new TermQuery(new Term("fileName","01"));
        long count = indexWriter.deleteDocuments(query);
        System.out.println("count: "+count);
        indexWriter.close();
    }

    /**
     * 修改索引
     * @throws IOException
     */
    @Test
    public void testUpdate() throws IOException {
        IndexWriter indexWriter = this.getIndexWriter();
        Document doc=new Document();
        doc.add(new TextField("fileName","测试文件名", Field.Store.YES));
        doc.add(new TextField("fileContent","测试文件内容", Field.Store.YES));
        //将fileName中包含java但全部删除，然后替换为doc
        indexWriter.updateDocument(new Term("fileName","java"),doc);
        indexWriter.close();
    }

    /**
     * 获得IndexSearcher
     * @return
     * @throws IOException
     */
    public IndexSearcher getIndexSearcher() throws IOException {
        //1. 创建一个directory对象，也就是索引库存放但位置
        Directory directory = FSDirectory.open(Paths.get("index"));
        //2. 创建一个IndexReader，需要指定directory
        IndexReader indexReader= DirectoryReader.open(directory);
        //3. 创建一个IndexSearcher对象，需要指定IndexReader对象
        return new IndexSearcher(indexReader);
    }

    /**
     * 执行查询的结果
     *
     */
    public void printResult(IndexSearcher indexSearcher,Query query) throws IOException {
        //5. 执行查询
        TopDocs topDocs = indexSearcher.search(query, 10);
        //6. 返回查询结果，遍历查询结果并且输出
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        System.out.println(scoreDocs.length);
        for (ScoreDoc scoreDoc : scoreDocs) {
            int docId = scoreDoc.doc; //doc id
            Document document = indexSearcher.doc(docId);
            String fileName = document.get("fileName");
            System.out.println("fileName: " + fileName);
            String fileSize = document.get("fileSize");
            System.out.println("fileSize: " + fileSize);
            String filePath = document.get("filePath");
            System.out.println("filePath: " + filePath);
            String fileContent = document.get("fileContent");
            System.out.println("fileContent: " + fileContent);
        }
    }


    /**
     * 查询所有
     */
    @Test
    public void testMatchAllDocsQuery() throws IOException {
        IndexSearcher indexSearcher = getIndexSearcher();
        Query matchAllDocsQuery=new MatchAllDocsQuery();
        printResult(indexSearcher,matchAllDocsQuery);
        indexSearcher.getIndexReader().close();
    }

    /**
     * 查询某个范围
     */
    @Test
    public void testNumericRangeQuery() throws IOException {
        IndexSearcher indexSearcher = getIndexSearcher();
        Query rangeQuery = LongPoint.newRangeQuery("fileSize", 1, 20);
        System.out.println(rangeQuery);
        printResult(indexSearcher,rangeQuery);
        indexSearcher.getIndexReader().close();
    }

    /**
     * 组合查询
     */
    @Test
    public void testBooleanQuery() throws IOException {
        IndexSearcher indexSearcher = getIndexSearcher();
        Query query=new TermQuery(new Term("fileName","java"));
        BooleanClause clause = new BooleanClause(query, BooleanClause.Occur.MUST);
        Query query2=new TermQuery(new Term("fileName","01"));
        BooleanClause clause2 = new BooleanClause(query2, BooleanClause.Occur.MUST);
        BooleanQuery booleanQuery = new BooleanQuery.Builder().add(clause).add(clause2)
                .build();
        printResult(indexSearcher,booleanQuery);
        indexSearcher.getIndexReader().close();
    }

    //-----------------------------------------------------------------------------------------

    /**
     * 通过QueryParser创建Query对象
     * 需要依赖lucene-queryparser.jar包
     * 条件解析对象的查询
     */
    @Test
    public void testQueryParser() throws IOException, ParseException {
        IndexSearcher indexSearcher = getIndexSearcher();
        //参数1 默认查询的的域
        //参数2 查询的时候采用的分析器
        QueryParser queryParser=new QueryParser("fileName",new IKAnalyzer());

        //多个默认域
        //*:*   域:值
        Query query = queryParser.parse("fileName:java");
        printResult(indexSearcher,query);
        indexSearcher.getIndexReader().close();
    }

    /**
     * 条件解析对象的查询; 设置多个默认域
     * @throws IOException
     */
    @Test
    public void testMultiFieldQueryParser() throws IOException, ParseException {
        IndexSearcher indexSearcher = getIndexSearcher();
        //参数1 默认查询的的域
        //参数2 查询的时候采用的分析器
        QueryParser queryParser=new MultiFieldQueryParser( new String[]{"fileName","fileContent"},new IKAnalyzer());
        //*:*   域:值
        // 下面只要指定，则上面的默认域设置无效
        Query query = queryParser.parse("fileName:java");
        printResult(indexSearcher,query);
        indexSearcher.getIndexReader().close();
    }



}
