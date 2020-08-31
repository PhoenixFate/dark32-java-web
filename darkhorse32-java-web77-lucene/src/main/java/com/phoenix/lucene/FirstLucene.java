package com.phoenix.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class FirstLucene {

    /**
     * lucene 入门
     * 创建索引
     */
    @Test
    public void testCreateIndex() throws IOException {
        //1. 创建一个indexWriter对象
//        Directory directory=new RAMDirectory();//保存directory到内存中
        Directory directory = FSDirectory.open(Paths.get("index"));
//        Analyzer analyzer = new StandardAnalyzer();//官方推荐
        Analyzer analyzer=new IKAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        //1.1 指定索引库的存放位置Directory
        //1.2 指定一个分析器 对文档进行分析
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        //3. 创建field对象，将field对象添加到document对象中
        File docs = new File("doc");
        File[] fileList = docs.listFiles();
        for (File file : fileList) {
            //2. 创建document对象
            Document document = new Document();
            //文件名称
            String fileName = file.getName();
            Field fileNameField = new TextField("fileName", fileName, Field.Store.YES);
            //文件大小
            long size = FileUtils.sizeOf(file);
            Field fileSizeField=new LongPoint("fileSize",size);
            //文件路径
            String filePath = file.getPath();
            Field filePathField = new StoredField("filePath", filePath);
            //文件内容
            String fileContent = FileUtils.readFileToString(file, "utf-8");
            Field fileContentField = new TextField("fileContent", fileContent, Field.Store.YES);
            //将field对象添加到document对象中
            document.add(fileNameField);
            document.add(fileSizeField);
            document.add(filePathField);
            document.add(fileContentField);
            //4. 使用indexWriter对象将document对象写入索引库 ，此过程进行索引创建，并将索引和document对象写入索引库
            indexWriter.addDocument(document);
        }
        //5. 关闭indexWriter对象
        indexWriter.close();

    }


    /**
     * lucene 入门
     * 搜索
     */
    @Test
    public void testSearchIndex() throws IOException {
        //1. 创建一个Directory对象，也就是索引库存放的位置
        Directory directory = FSDirectory.open(Paths.get("index"));
        //2. 创建一个IndexReader对象，需要指定Directory对象
        IndexReader indexReader = DirectoryReader.open(directory);
        //3. 创建一个IndexSearch对象，需要指定IndexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //4. 创建一个TermQuery对象， 指定查询的域和查询的查询的关键字
        Query query = new TermQuery(new Term("fileName", "java")); //TermQuery是精准查询
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
        //7. 关闭IndexReader对象
        indexReader.close();
    }


    // 查看标准分析器的分词效果
    @Test
    public void testTokenStream() throws Exception {
        // 创建一个标准分析器对象
		Analyzer analyzer = new StandardAnalyzer();
        // 获得tokenStream对象
        // 第一个参数：域名，可以随便给一个
        // 第二个参数：要分析的文本内容
		TokenStream tokenStream = analyzer.tokenStream("test",
				"The Spring Framework provides a comprehensive programming and configuration model.");
        // 添加一个引用，可以获得每个关键词
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        // 添加一个偏移量的引用，记录了关键词的开始位置以及结束位置
        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        // 将指针调整到列表的头部
        tokenStream.reset();
        // 遍历关键词列表，通过incrementToken方法判断列表是否结束
        while (tokenStream.incrementToken()) {
            // 关键词的起始位置
            System.out.println("start->" + offsetAttribute.startOffset());
            // 取关键词
            System.out.println(charTermAttribute);
            // 结束位置
            System.out.println("end->" + offsetAttribute.endOffset());
        }
        tokenStream.close();
    }


    // 查看标准分析器的分词效果
    @Test
    public void testTokenStreamChinese() throws Exception {
        // 创建一个标准分析器对象
//        Analyzer analyzer = new StandardAnalyzer();
        // smartChineseAnalyzer 能够简单拆封中文 但拓展性差
//        Analyzer analyzer=new SmartChineseAnalyzer();
        Analyzer analyzer=new IKAnalyzer();
        // 获得tokenStream对象
        // 第一个参数：域名，可以随便给一个
        // 第二个参数：要分析的文本内容
        TokenStream tokenStream = analyzer.tokenStream("test",
                "高富帅可以用二维表结构来逻辑表达实现的数据");
        // 添加一个引用，可以获得每个关键词
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        // 添加一个偏移量的引用，记录了关键词的开始位置以及结束位置
        OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
        // 将指针调整到列表的头部
        tokenStream.reset();
        // 遍历关键词列表，通过incrementToken方法判断列表是否结束
        while (tokenStream.incrementToken()) {
            // 关键词的起始位置
            System.out.println("start->" + offsetAttribute.startOffset());
            // 取关键词
            System.out.println(charTermAttribute);
            // 结束位置
            System.out.println("end->" + offsetAttribute.endOffset());
        }
        tokenStream.close();
    }
}
