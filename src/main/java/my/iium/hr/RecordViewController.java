package my.iium.hr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import my.iium.hr.Service.DocumentReviewService;
import my.iium.hr.Service.DocumentService;
import my.iium.hr.model.DocumentReview;

@Controller

@RequestMapping("/document/process")
public class RecordViewController {

	@Autowired
	private DocumentService documentService;
	@Autowired
	private DocumentReviewService documentReviewService;

	// Documents in Draft status
	@GetMapping("/draft")
	public String draft(Model model) {
//BEFORE DELETING THIS REMEMBER TO PULL THE GET DOCUMENT BY STATUS TO HERE.

		model.addAttribute("listRecord", documentService.findByStatus("Draft"));

		return "draft";
	}

	@GetMapping("/submitted")
	public String submitted(Model model) {
		List<DocumentReview> listRecord = documentReviewService.getAllDocumentReview();
		model.addAttribute("listRecord", listRecord);
		return "submitted";
	}

	// Recommended - not implemented 
	@GetMapping("/recommended")
	public String recommended(Model model) {

		model.addAttribute("listRecord", documentService.findByStatus("Recommeded"));

		return "recommended";
	}

	// Approved
	@GetMapping("/approved")
	public String approved(Model model) {

		model.addAttribute("listRecord", documentService.findByStatus("Approved"));

		return "approved";
	}

	// Recycle bin
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/recycleBin")
	public String recycleBin(Model model) {

		model.addAttribute("listRecord", documentService.findByStatus("RecycleBin"));

		return "recycleBin";
	}

}
