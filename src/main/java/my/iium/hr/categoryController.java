package my.iium.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import my.iium.hr.Service.DocumentService;

@Controller
@RequestMapping("/document/category")
public class categoryController {

	@Autowired
	private DocumentService documentService;

	// Display ALL Documents ordered by Titles
	@GetMapping("/title")
	public String title(Model model) {
		
		model.addAttribute("listRecord", documentService.getAllDocument());

		return "title";
	}

	// Display ALL Documents ordered by Year
	@GetMapping("/year")
	public String year(Model model) {

		model.addAttribute("listRecord", documentService.getAllDocument());
		
		return "year";
	}

	// Display ALL Documents ordered by StaffID
	@GetMapping("/staffID")
	public String staffID(Model model) {

		model.addAttribute("listRecord", documentService.getAllDocument());
		
		return "staffID";
	}

	// Display ALL Documents ordered by Main Topic
	@GetMapping("/mainTopic")
	public String mainTopic(Model model) {

		model.addAttribute("listRecord", documentService.getAllDocument());
		
		return "mainTopic";
	}

	// Display ALL Documents ordered by Reference Number
	@GetMapping("/refNo")
	public String refNo(Model model) {
		
		model.addAttribute("listRecord", documentService.getAllDocument());
	
		return "refNo";
	}

}
