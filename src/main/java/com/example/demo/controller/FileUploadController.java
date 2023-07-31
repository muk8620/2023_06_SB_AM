package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.FileService;
import com.example.demo.util.Util;
import com.example.demo.vo.FileVO;

@Controller
public class FileUploadController {

	private FileService fileService;

	@Autowired
	public FileUploadController(FileService fileService) {
		this.fileService = fileService;
	}

	@RequestMapping("/usr/home/upload")
	@ResponseBody
	public String uploadFile(MultipartFile file) {

		try {
			fileService.saveFile(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Util.jsReplace("파일 업로드 성공", "/");
	}

	@RequestMapping("/usr/home/view")
	public String view(Model model) {		

		List<FileVO> files = fileService.getFiles();

		model.addAttribute("files", files);

		return "usr/home/view";
	}

	@RequestMapping("/usr/home/file/{fileId}")
	@ResponseBody
	public Resource downloadImage(@PathVariable("fileId") int id, Model model) throws IOException {

		FileVO fileVo = fileService.getFileById(id);

		return new UrlResource("file:" + fileVo.getSavedPath()); 
	}
}