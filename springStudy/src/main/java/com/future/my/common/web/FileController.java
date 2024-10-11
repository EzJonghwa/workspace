package com.future.my.common.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class FileController {

	@Value("#{util['file.upload.path']}")
	private String CURR_IMAGE_PATH;
	
	@Value("#{util['file.download.path']}")
	private String WEB_PATH;
	
	@RequestMapping("/download")
	public void download(String imageFileName, HttpServletResponse response) throws IOException {
		OutputStream out = response.getOutputStream();
		String downloadFile = CURR_IMAGE_PATH+"\\"+ imageFileName;
		File file = new File(downloadFile);
		
		// 로컬에서 요청 파일을 찾아서 전달
		if(!file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,"file not found");
		}
		// 로컬에서 요청 파일을 찾아서 전달
		// 바이너리로 전달
		// 못찾을 시 404 error 리턴
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Content-Dispositon", "attachment; fileName="+imageFileName);
		try(FileInputStream in = new FileInputStream(file)){
			byte[] buffer = new byte[1024*8];
			while(true) {
				int count = in.read(buffer);
				if(count ==-1) {
					break;
				}
				out.write(buffer,0,count);  // 실시간 전송
			}
		}catch(IOException e) {
			
		}finally {
			out.close();
		}
	}
	@RequestMapping("/multiImgUpload")
	   public void multiImgUpload(HttpServletRequest req, HttpServletResponse res) {
	      // 저장 후 이미지 저장 정보 전달
	     try {
	      String sFileInfo ="";
	      String fileName = req.getHeader("file-name");
	      String prifix= fileName.substring(fileName.lastIndexOf(".") +1);
	      prifix = prifix.toLowerCase();
	      String path = CURR_IMAGE_PATH;
	      File file = new File(path);
	      if(!file.exists()) {
	         file.mkdir();
	      }
	      //서버에 저장될 파일 이름
	      String realName = UUID.randomUUID().toString()+ "." +prifix;
	      InputStream is =req.getInputStream();
	      OutputStream os = new FileOutputStream(new File(path + "\\" + realName));
	      int read = 0;
	      byte b [] =new byte[1204];
	      while((read = is.read(b))!= -1) {
	         os.write(b,0,read);
	      }
	      if(is !=null) {
	         is.close();
	         
	      }
	      os.flush();
	      os.close();
	      // 전달 정보
	      sFileInfo +="&bNewLine=true";
	      sFileInfo +="&sFileName=" +fileName;
	      sFileInfo +="&sFileURL="+ WEB_PATH + realName;
	      PrintWriter print = res.getWriter();
	      System.out.println(sFileInfo);
	      print.print(sFileInfo);
	      print.flush();
	      print.close();
	   }catch(IOException e) {
	      e.printStackTrace();
	   }

	}
	
	
	
	
}
