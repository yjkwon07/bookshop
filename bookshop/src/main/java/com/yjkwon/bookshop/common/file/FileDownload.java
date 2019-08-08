package com.yjkwon.bookshop.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileDownload {
	private static String CURR_IMAGE_REPO_PATH="C:\\shopping\\file_repo";
	
	@RequestMapping("/download")
	protected void download (@RequestParam("fileName") String fileName,
							@RequestParam("goods_id") String goods_id,
							HttpServletResponse response) throws Exception {
		OutputStream outputStream = response.getOutputStream();
		String filePath = CURR_IMAGE_REPO_PATH+"\\"+goods_id+"\\"+fileName;
		File image = new File(filePath);
		
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName="+fileName);
		FileInputStream fileInputStream = new FileInputStream(image);
		byte[] buffer = new byte[1024*8];
		while(true) {
			int count=fileInputStream.read(buffer);
			if(count==-1) break;
			outputStream.write(buffer,0,count);
		}
		fileInputStream.close();
		outputStream.close();
	}
				
	@RequestMapping("/thumbnails")
	protected void thumbnails(@RequestParam("fileName") String fileName,
							  @RequestParam("goods_id") String goods_id,
							  HttpServletResponse response) throws Exception {
		OutputStream outputStream = response.getOutputStream();
		String filePath = CURR_IMAGE_REPO_PATH+"\\"+goods_id+"\\"+fileName;
		File image = new File(filePath);
		if(image.exists()) {
			Thumbnails.of(image).size(121,154).outputFormat("png").toOutputStream(outputStream);
		}
		byte[] buffer = new byte[1024*8];
		outputStream.write(buffer);
		outputStream.close();
	}
							
}
