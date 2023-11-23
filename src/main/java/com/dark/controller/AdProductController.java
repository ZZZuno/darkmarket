package com.dark.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dark.domain.ItemVO;
import com.dark.dto.Criteria;
import com.dark.dto.PageDTO;
import com.dark.service.AdCategoryService;
import com.dark.service.AdProductService;
import com.dark.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor
@Log4j
@RequestMapping("/admin/product/*")
@Controller
public class AdProductController {

	private final AdProductService adProductService;
	private final AdCategoryService adCategoryService;
	
	// 메인 및 썸네일이미지 업로드 폴더경로 주입작업.
	@Resource(name = "uploadPath") // servlet-context.xml의 bean이름 참조를 해야함.
	private String uploadPath;
	
	// CKEDitor에서 사용되는 업로드 폴더경로
	@Resource(name = "uploadCKPath") // servlet-context.xml의 bean이름 참조를 해야함.
	private String uploadCKPath;
	
	@GetMapping("/pro_insert")
	public void pro_insert() {
//		log.info("상품등록 페이지 호출");
		
	}
	
	//상품정보 저장
		// 파일업로드 기능 : 1)스프링에서 내장된 기본라이브러리. servlet-context.xml 에서 MultipartFile 대한 bean등록작업 
		//              2)외부라이브러리(commons-fileupload)를 이용(pom.xml). servlet-context.xml 에서 MultipartFile 대한 bean등록작업 
		// MultipartFile uploadFile : <input type="file" class="form-control" name="uploadFile" id="uploadFile">
		// MultipartFile uploadFile 필드명과 input태그의 name="uploadFile" 이름이 일치.
	
	@PostMapping("/pro_insert")
	public String pro_insert(ItemVO vo, MultipartFile uploadFile, RedirectAttributes rttr) {
		
		
		
		String dateFolder = FileUtils.getDateFolder();
		String savedFileName = FileUtils.uploadFile(uploadPath, dateFolder, uploadFile);
		
		vo.setItem_img(savedFileName);
		vo.setItem_up_folder(dateFolder);
		
		log.info("상품정보: " + vo);
		
		//db에 저장
		adProductService.pro_insert(vo);
		
		return "redirect:/admin/product/pro_list";
	}
	
	@PostMapping("/imageUpload")
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, MultipartFile upload) {
		
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		try {
			
			// 1) 파일업로드 작업
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes(); // 업로드 한 파일을 byte배열로 읽어들임.
			
			String ckUploadPath = uploadCKPath + fileName;
			
			out = new FileOutputStream(new File(ckUploadPath));
			
			out.write(bytes);
			out.flush();
			
			// 2) 파일업로드 작업 후 파일정보를 CKEditor로 보내는 작업
			printWriter = response.getWriter();
			
			//CKEditor에서 업로드된 파일경로를 보내준다.(매핑주소와 파일명이 포함)
			String fileUrl = "/ckupload/" + fileName;
			
			printWriter.println("{\"filename\":\"" +  fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}");
			printWriter.flush();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				try {
					out.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			if(printWriter != null) printWriter.close();
			
		}
			
	}
	
	// 상품리스트. (목록과페이징)
	@GetMapping("/pro_list")
	public void pro_list(Criteria cri, Model model) throws Exception {
		
		cri.setAmount(2);
		
		List<ItemVO> pro_list = adProductService.pro_list(cri);
		
		// 날짜폴더의 역슬래시 슬래시로 바꾸는 작업
		pro_list.forEach(vo -> {
			vo.setItem_up_folder(vo.getItem_up_folder().replace("\\", "/"));
	  	});
		
		model.addAttribute("pro_list", pro_list);
		
		int totalCount = adProductService.getTotalCount(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}
	
	// 상품리스트에서 보여줄 이미지. <img src="매핑주소">로 jsp에서 작업.
	@ResponseBody
	@GetMapping("/imageDisplay")
	public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName) throws Exception {
		return FileUtils.getFile(uploadPath + dateFolderName, fileName);
	}
//	@ResponseBody
//	@PostMapping("/pro_checked_modify")
	
}

