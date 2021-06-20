package com.phoenix;

import com.phoenix.common.utils.FastDFSClient;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;
import java.io.IOException;
import java.net.InetSocketAddress;

public class FastDfsTest {

    @Test
    public void test01() throws IOException, MyException {
        //创建一个配置文件 文件名任意 ，内容就是tracker服务器的地址   (resources/conf/client.conf)
        //使用全局对象加载配置文件
        String conf="conf/client.conf";
        if (conf.contains("classpath:")) {
			conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
		}
        System.out.println(conf);

        ClientGlobal.init(conf);

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

    @Test
    public void test02() throws Exception {
//        FastDFSClient fastDFSClient=new FastDFSClient("conf/client2.conf");
//        String uploadFile = fastDFSClient.uploadFile("src/test/resources/temp.jpg");
//        System.out.println(uploadFile);
    }

    @Test
    public void test03() throws IOException, MyException {
        String storageAddressOld="172.19.1.1:23001,172.19.1.1:23002,172.19.1.1:23003";
        String storageAddressNew="47.99.113.229:19111,47.99.113.229:19112,47.99.113.229:19113";

        //创建一个配置文件 文件名任意 ，内容就是tracker服务器的地址   (resources/conf/client.conf)
        //使用全局对象加载配置文件
        ClientGlobal.init("conf/client2.conf");
        // 创建一个TrackerClient对象
        TrackerClient trackerClient=new TrackerClient();
        // 通过TrackClient获得一个TrackServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        InetSocketAddress inetSocketAddress = trackerServer.getInetSocketAddress();
        System.out.println(inetSocketAddress);
        // 创建一个StorageServer的引用，可以是null，
        // StorageServer storageServer=null;
        //创建一个StorageClient，参数需要TrackerServer和StorageServer

        //StorageServer storageServer=new StorageServer("47.99.113.229",19112,0);

        StorageClient storageClient=new StorageClient(trackerServer,null);
        StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
        System.out.println(storeStorage.getInetSocketAddress());

        // 使用StorageClient 上传文件
        String[] uploadFile = storageClient.upload_file("src/test/resources/temp.jpg", "jpg", null);
        for(String str:uploadFile){
            System.out.println(str);
        }
    }


    @Test
    public void test04() throws IOException, MyException {
        //创建一个配置文件 文件名任意 ，内容就是tracker服务器的地址   (resources/conf/client.conf)
        //使用全局对象加载配置文件
        ClientGlobal.init("conf/client.conf");
        // 创建一个TrackerClient对象
        TrackerClient trackerClient=new TrackerClient();
        // 通过TrackClient获得一个TrackServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        // 创建一个StorageServer的引用，可以是null，
//        StorageServer storageServer=null;
        // 创建一个StorageClient，参数需要TrackerServer和StorageServer
        StorageServer storageServer=new StorageServer("47.99.113.229",23001,0);


        StorageClient storageClient=new StorageClient(trackerServer,storageServer);
        InetSocketAddress inetSocketAddress = trackerServer.getInetSocketAddress();
        System.out.println(inetSocketAddress);
        StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
        System.out.println(storeStorage.getInetSocketAddress());

        // 使用StorageClient 上传文件
        String[] uploadFile = storageClient.upload_file("src/test/resources/temp.jpg", "jpg", null);
        for(String str:uploadFile){
            System.out.println(str);
        }
    }

    @Test
    public void test05(){
        String str="47.99.113.229:19111";
        System.out.println(str.split(":")[0]);
        System.out.println(str.split(":")[1]);
    }

    @Test
    public void test06() throws IOException, MyException {
        //创建一个配置文件 文件名任意 ，内容就是tracker服务器的地址   (resources/conf/client.conf)
        //使用全局对象加载配置文件
        ClientGlobal.init("conf/client3.conf");
        // 创建一个TrackerClient对象
        TrackerClient trackerClient=new TrackerClient();
        // 通过TrackClient获得一个TrackServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        InetSocketAddress inetSocketAddress = trackerServer.getInetSocketAddress();
        System.out.println(inetSocketAddress);
        // 创建一个StorageServer的引用，可以是null，
        // StorageServer storageServer=null;
        //创建一个StorageClient，参数需要TrackerServer和StorageServer

        StorageServer storageServer=new StorageServer("47.99.113.229",19112,0);

        StorageClient storageClient=new StorageClient(trackerServer,null);
        StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
        System.out.println(storeStorage.getInetSocketAddress());

        // 使用StorageClient 上传文件
        String[] uploadFile = storageClient.upload_file("src/test/resources/temp.jpg", "jpg", null);
        for(String str:uploadFile){
            System.out.println(str);
        }
    }

}
