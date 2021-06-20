package com.phoenix.manager.controller;

import com.phoenix.common.utils.FastDFSClient;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class MyTest {

//    public static void main(String[] args) throws Exception {
//        String conf="conf/client2.conf";
//        if (conf.contains("classpath:")) {
//            conf = conf.replace("classpath:", MyTest.class.getResource(File.separator).getPath());
//        }
//        System.out.println(conf);
//        System.out.println(MyTest.class.getResource("/").getPath());
//        FastDFSClient fastDFSClient=new FastDFSClient(conf);
//        String uploadFile = fastDFSClient.uploadFile(MyTest.class.getResource("/").getPath()+ "temp/temp.jpg");
//        System.out.println(uploadFile);
//    }

    @Test
    public void test01() throws IOException, MyException {
        //创建一个配置文件 文件名任意 ，内容就是tracker服务器的地址   (resources/conf/client.conf)
        //使用全局对象加载配置文件
        String conf="classpath:conf/client.conf";
        if (conf.contains("classpath:")) {
            conf = conf.replace("classpath:", MyTest.class.getResource(File.separator).getPath());
        }
        System.out.println(conf);

        String conf2="conf/client.conf";
        ClientGlobal.init(conf2);

        // 创建一个TrackerClient对象
        TrackerClient trackerClient=new TrackerClient();
        // 通过TrackClient获得一个TrackServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        System.out.println(trackerServer.getSocket().getRemoteSocketAddress()+" : "+trackerServer.getSocket().getPort());
        // 创建一个StorageServer的引用，可以是null，
//        StorageServer storageServer=null;
        // 创建一个StorageClient，参数需要TrackerServer和StorageServer
        StorageClient storageClient=new StorageClient(trackerServer,null);
        StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
        System.out.println("storage--------: "+storeStorage.getSocket().getInetAddress().getHostAddress()+" : "+storeStorage.getInetSocketAddress().getPort());
        StorageServer storeStorage2 = trackerClient.getStoreStorage(trackerServer);
        System.out.println("storage--------: "+storeStorage2.getSocket().getInetAddress().getHostAddress()+" : "+storeStorage2.getInetSocketAddress().getPort());
        StorageServer storeStorage3 = trackerClient.getStoreStorage(trackerServer);
        System.out.println("storage--------: "+storeStorage3.getSocket().getInetAddress().getHostAddress()+" : "+storeStorage3.getInetSocketAddress().getPort());
        StorageServer storeStorage4 = trackerClient.getStoreStorage(trackerServer);
        System.out.println("storage--------: "+storeStorage4.getSocket().getInetAddress().getHostAddress()+" : "+storeStorage4.getInetSocketAddress().getPort());


//        System.out.println("start");
//        System.out.println(storeStorage.getInetSocketAddress());
//        System.out.println("end");
        // 使用StorageClient 上传文件
        String[] uploadFile = storageClient.upload_file("src/test/resources/temp.jpg", "jpg", null);
        for(String str:uploadFile){
            System.out.println(str);
        }
    }




}
