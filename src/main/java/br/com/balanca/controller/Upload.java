package br.com.balanca.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.balanca.dto.BalanceRecord;
import br.com.csvparser.parser.impl.CsvParser;

@RestController
@RequestMapping(value="medicao")
public class Upload {
	
	@Autowired
	private CsvParser parser;
	
	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        try {
        	File f = new File("temp1.csv");
			OutputStream out = new FileOutputStream(f);
			out.write(file.getBytes());
			out.close();
			
			List<BalanceRecord> records = (List<BalanceRecord>) parser.parseCsv(f.getAbsolutePath(), BalanceRecord.class);
        }catch (Exception e) {
			e.printStackTrace();
		}
        
        return "redirect:/";
    }

}
