package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
public class Test2 {
	   public static void main(String[] args) {
	      
	      System.out.println("--- 파일 업로드 서버 ---");
	      
	      //서버 포트 번호
	      int port = 10002;
	      
	      //서버 리슨 소켓
	      ServerSocket servSock = null;
	      
	      //서버 통신 소켓
	      Socket sock = null;
	      
	      //소켓 입력 스트림
	      BufferedInputStream in = null;
	      
	      
	      //==================================================================         
	      
	      //출력 대상 파일
	      File file = new File("./src/java14_net/quiz/", "receive.txt");
	      System.out.println("[TEST] exists : " + file.exists());         
	      
	      //데이터 출력 스트림
	      BufferedOutputStream dos = null;
	      
	      byte[] buf = new byte[1024];
	      int len = -1;
	      
	      try {
	         
	         //서버 리슨 소켓 생성
	         servSock = new ServerSocket( port );
	         System.out.println();
	         System.out.println("[TEST] -- 리슨소켓 정상 생성완료 --");
	         
	         System.out.println();
	         System.out.println("--- Listening ---");
	         sock = servSock.accept();
	         
	         System.out.println(" >> 서버 IP : " + InetAddress.getLocalHost().getHostAddress() );
	         System.out.println(" >> 서버 리슨 Port : " + sock.getLocalPort() );
	         
	         System.out.println();
	         System.out.println("클라이언트 접속 완료");
	         
	         InetAddress ip = sock.getInetAddress();
	         System.out.println(" >> 클라이언트 IP : "+ ip.getHostAddress() );
	         System.out.println(" >> 클라이언트 접속 port : " + sock.getPort());
	      
	         in = new BufferedInputStream( sock.getInputStream() );
	         
	      //==================================================================      

	         dos = new BufferedOutputStream(new FileOutputStream( file ) );         
	         
	         while( (len = in.read(buf) ) != -1)  {
	            dos.write(buf, 0, len);
	            dos.flush();
//	            System.out.print(new String(buf, 0, len));
	         }

	         
	      //예외처리-----------------------------------------------------------
	      } catch (IOException e) {
	         e.printStackTrace();
	      
	      } finally {
	         try {
	            //출력객체 클로즈
	            if(dos!=null)   dos.close();
	            //통신소켓 클로즈
	            if(sock!=null)   sock.close();
	         } catch (IOException e1) {
	            e1.printStackTrace();
	         }
	         
	         try {
	            //리슨소켓 클로즈
	            if(servSock!=null)   servSock.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	   }
	}
