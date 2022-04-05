package my.iium.hr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import my.iium.hr.Service.DocumentReviewService;
import my.iium.hr.Service.DocumentService;
import my.iium.hr.Service.FileRefNumberService;
import my.iium.hr.Service.TemplateMainService;
import my.iium.hr.model.Document;
import my.iium.hr.model.DocumentReview;
import my.iium.hr.model.FileRefNumber;
import my.iium.hr.model.TemplateMain;
import my.iium.hr.security.MyUserService;
import my.iium.hr.security.SecurityUtil;

@Controller
@RequestMapping("/document/process")
public class RecordController {

	@Autowired
	private DocumentService documentService;
	@Autowired
	private DocumentReviewService documentReviewService;
	@Autowired
	private FileRefNumberService fileRefNumberService;
	@Autowired
	private TemplateMainService templateMainService;
	@Autowired
	private MyUserService user;

	/////////////////////
	// USER PAGES
	////////////////////

	// THE PART RELATED TO STAFF IS IN APICONTROLLER A.K.A CHOOSE STAFF
	@GetMapping("/chooseTemplate")
	public String chooseTemplate(Model model) {

		model.addAttribute("template", new TemplateMain());
		model.addAttribute("listTemplate", templateMainService.getAllTemplateMain());

		return "docTemplate";
	}

	@GetMapping("/chooseTemplate/{id}/new")
	public String showNewDocumentForm(@PathVariable(value = "id") long id, Model model) {

		if (id != 0)
			model.addAttribute("template", templateMainService.getTemplateById(id));
		else
			model.addAttribute("template", "0");

		Document document = new Document();
		// DocumentReview documentReview = new DocumentReview(); // trying to create
		model.addAttribute("document", document);
		// model.addAttribute("documentReview", documentReview);
		List<FileRefNumber> listFileRefNumber = fileRefNumberService.getAllFileRefNumber();
		model.addAttribute("listFileRefNumber", listFileRefNumber);

		return "new_draft";
	}

	@PostMapping("/saveDocument")
	public String saveDocument(@ModelAttribute("document") Document document, Model model) {
		// save Document to database
		document.setStaffID(SecurityUtil.cleanIt(document.getStaffID()));
		document.setRecipientName(SecurityUtil.cleanIt(document.getRecipientName()));
		document.setAddress(SecurityUtil.cleanIt(document.getAddress()));
		document.setTitle(SecurityUtil.cleanIt(document.getTitle()));
		if (document.getContentSign() != null)
			document.setContentSign(SecurityUtil.cleanIt(document.getContentSign()));
		if (document.getContentCC() != null)
			document.setContentCC(SecurityUtil.cleanIt(document.getContentCC()));
		document.setContent(SecurityUtil.cleanIt(document.getContent()));
		document.setEnterBy(SecurityUtil.cleanIt(document.getEnterBy()));
		if (document.getUpdateBy() != null)
			document.setUpdateBy(SecurityUtil.cleanIt(document.getUpdateBy()));
		document.setStatus("Draft");

		documentService.saveDocument(document);
		model.addAttribute("document", document);
		// to keep track of the document for the next process "send to supervisor"
		String id = Long.toString(document.getId());

		return "redirect:/document/process/showFormForSendToSupervisor/" + id;
	}

	@GetMapping("/showFormForUpdateDocument/{id}")
	public String showFormForUpdateDocument(@PathVariable(value = "id") long id, Model model) {

		// get document from the service
		Document document = documentService.getDocument(id);

		// set document as a model attribute to pre-populate the form
		model.addAttribute("document", document);

		List<FileRefNumber> listFileRefNumber = fileRefNumberService.getAllFileRefNumber();
		model.addAttribute("listFileRefNumber", listFileRefNumber);
		return "update_record";
	}

	// this will just change the status of the document
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/deleteDocument/{page}/{id}")
	public String deleteDocument(@PathVariable(value = "id") long id, @PathVariable(value = "page") String page) {

		// get document from the service
		Document document = documentService.getDocument(id);
		document.setStatus("RecycleBin");
		documentService.saveDocument(document);

		return "redirect:/document/process/{page}";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/deleteDocumentPermanently/{page}/{id}")
	public String deleteDocumentPerma(@PathVariable(value = "id") long id, @PathVariable(value = "page") String page) {

		this.documentService.deleteDocumentById(id);
		return "redirect:/document/process/recycleBin";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/restoreDocument/{page}/{id}")
	public String restoreDocument(@PathVariable(value = "id") long id, @PathVariable(value = "page") String page) {
		// get document from the service
		Document document = documentService.getDocument(id);
		document.setStatus("Draft");
		documentService.saveDocument(document);

		return "redirect:/document/process/recycleBin";
	}

	//////////////////////////////
	// USER to SUPERVISOR PAGES
	/////////////////////////////
	@GetMapping("/showFormForSendToSupervisor/{id}")
	public String showFormForSendToSupervisor(@PathVariable(value = "id") long id, Model model) {
		// DO NECESSARY CHANGES FOR THIS PART ACCODING TO NEW DRAFT CHANGE
		// getDocumentReview(id) : don't try to create a new docReview we already have
		// one.
		Document document = documentService.getDocument(id); // getDocument
		model.addAttribute("document", document);
		DocumentReview documentReview = new DocumentReview(); // trying to create
		model.addAttribute("documentReview", documentReview);
		model.addAttribute("listSV", user.getAllSupervisors());

		return "send_letter_toSupervisor";
	}

	@PostMapping("/saveDocumentReview")
	public String saveDocumentReview(@ModelAttribute("documentReview") DocumentReview documentReview) {
		documentReview.setDocumentStaffID(SecurityUtil.cleanIt(documentReview.getDocument().getStaffID()));
		documentReview.setDocumentRecipientName(SecurityUtil.cleanIt(documentReview.getDocument().getRecipientName()));
		documentReview.setDocumentAddress(SecurityUtil.cleanIt(documentReview.getDocument().getAddress()));
		documentReview.setDocumentTitle(SecurityUtil.cleanIt(documentReview.getDocument().getTitle()));
		if (documentReview.getDocument().getContentSign() != null)
			documentReview.setDocumentSign(SecurityUtil.cleanIt(documentReview.getDocument().getContentSign()));
		if (documentReview.getDocument().getContentCC() != null)
			documentReview.setDocumentCC(SecurityUtil.cleanIt(documentReview.getDocument().getContentCC()));
		documentReview.setDocumentContent(SecurityUtil.cleanIt(documentReview.getDocument().getContent()));
		documentReview.setDocumentEnterBy(SecurityUtil.cleanIt(documentReview.getDocument().getEnterBy()));
		if (documentReview.getDocument().getUpdateBy() != null)
			documentReview.setDocumentUpdateBy(SecurityUtil.cleanIt(documentReview.getDocument().getUpdateBy()));
		documentReview.setRefCodeID(SecurityUtil.cleanIt(documentReview.getRefCodeID()));
		documentReview.setSupervisorID(SecurityUtil.cleanIt(documentReview.getSupervisorID()));
		if (documentReview.getComment() != null)
			documentReview.setComment(SecurityUtil.cleanIt(documentReview.getComment()));
		documentReview.setDocumentStatus("Submitted");

		// save Document to database
		documentReviewService.saveDocumentReview(documentReview);
		return "redirect:/document/process/submitted";

	}

	@GetMapping("/printDocument/{id}")
	public String printDocument(@PathVariable(value = "id") long id, Model model) {

		// get template from the service
		Document document = documentService.getDocument(id);

		// set template as a model attribute to pre-populate the form
		model.addAttribute("document", document);
		return "print_record";
	}
	
	@GetMapping("/printDoc/{id}")
	public String printDocument2(@PathVariable(value = "id") long id, Model model) {

		// get template from the service
		Document document = documentService.getDocument(id);

		// set template as a model attribute to pre-populate the form
		model.addAttribute("document", document);
		return "print_doc";
	}
	
	@GetMapping("/printDocLH/{id}")
	public String printDocumentLH(@PathVariable(value = "id") long id, Model model) {

		// get template from the service
		Document document = documentService.getDocument(id);

		// set template as a model attribute to pre-populate the form
		model.addAttribute("document", document);
		return "print_doclh";
	}

	///////////////////////////////////////
	// SUPERVISOR PAGES
	//////////////////////////////////////
	@GetMapping("/reviewDocument/{id}")
	public String showFormForReviewDocument(@PathVariable(value = "id") long id, Model model) {// test for docid

		DocumentReview documentReview = documentReviewService.getDocumentReview(id);
		// set documentReview as a model attribute to pre-populate the form
		model.addAttribute("documentReview", documentReview);

		return "reviewDocument";
	}
	
	@GetMapping("/showDocument/{id}")
	public String showFormForViewDocument(@PathVariable(value = "id") long id, Model model) {// test for docid
		
		Document document = documentService.getDocument(id);		
		model.addAttribute("document", document);
		
		//List<DocumentReview> documentReview = documentReviewService.getDocumentReviewByDocID(id);		
		//model.addAttribute("documentReview", documentReview);

		return "showDocument";
	}

	@GetMapping("/showFormForUpdateDocumentbySupervisor/{id}")
	public String showFormForUpdateDocumentbySupervisor(@PathVariable(value = "id") long id, Model model) {

		DocumentReview documentReview = documentReviewService.getDocumentReview(id);
		// set template as a model attribute to pre-populate the form
		model.addAttribute("documentReview", documentReview);

		List<FileRefNumber> listFileRefNumber = fileRefNumberService.getAllFileRefNumber();
		model.addAttribute("listFileRefNumber", listFileRefNumber);

		return "update_record_bySupervisor";
	}

	
	//problem with the jsoup 
	@PostMapping("/saveDocumentbySupervisor/{id}")
	public String saveDocumentbySupervisor(@ModelAttribute("documentReview") DocumentReview documentReview,
			@PathVariable(value = "id") long id) {//, Model model
		//model.addAttribute("documentReview", documentReview);
		/*documentReview.setDocumentStaffID(SecurityUtil.cleanIt(documentReview.getDocument().getStaffID()));
		documentReview.setDocumentRecipientName(SecurityUtil.cleanIt(documentReview.getDocument().getRecipientName()));
		documentReview.setDocumentAddress(SecurityUtil.cleanIt(documentReview.getDocument().getAddress()));
		documentReview.setDocumentTitle(SecurityUtil.cleanIt(documentReview.getDocument().getTitle()));
		if (documentReview.getDocument().getContentSign() != null)
			documentReview.setDocumentSign(SecurityUtil.cleanIt(documentReview.getDocument().getContentSign()));
		if (documentReview.getDocument().getContentCC() != null)
			documentReview.setDocumentCC(SecurityUtil.cleanIt(documentReview.getDocument().getContentCC()));
		documentReview.setDocumentContent(SecurityUtil.cleanIt(documentReview.getDocument().getContent()));
		documentReview.setDocumentEnterBy(SecurityUtil.cleanIt(documentReview.getDocument().getEnterBy()));
		if (documentReview.getDocument().getUpdateBy() != null)
			documentReview.setDocumentUpdateBy(SecurityUtil.cleanIt(documentReview.getDocument().getUpdateBy()));
		documentReview.setRefCodeID(SecurityUtil.cleanIt(documentReview.getRefCodeID()));
		documentReview.setSupervisorID(SecurityUtil.cleanIt(documentReview.getSupervisorID()));
		if (documentReview.getComment() != null)
			documentReview.setComment(SecurityUtil.cleanIt(documentReview.getComment()));
		documentReview.setDocumentStatus(SecurityUtil.cleanIt(documentReview.getDocument().getStatus()));
		*/
		// save Document to database
		documentReviewService.saveDocumentReview(documentReview);
		
		// sending the id of the documentReview through the url with a pathVariable =
		// very smart move! probably the only
		// possible one as well ;-;
		return "redirect:/document/process/reviewDocument/" + id;
	}

	// this method is like an overloaded method in java
	// it enables the id to change the status depending on which button was
	// pressed in the html
	@PostMapping("/saveDocumentReview/draft")
	public String saveDocumentReviewDraft(@ModelAttribute("documentReview") DocumentReview documentReview) {
		documentReview.setDocumentStaffID(SecurityUtil.cleanIt(documentReview.getDocument().getStaffID()));
		documentReview.setDocumentRecipientName(SecurityUtil.cleanIt(documentReview.getDocument().getRecipientName()));
		documentReview.setDocumentAddress(SecurityUtil.cleanIt(documentReview.getDocument().getAddress()));
		documentReview.setDocumentTitle(SecurityUtil.cleanIt(documentReview.getDocument().getTitle()));
		if (documentReview.getDocument().getContentSign() != null)
			documentReview.setDocumentSign(SecurityUtil.cleanIt(documentReview.getDocument().getContentSign()));
		if (documentReview.getDocument().getContentCC() != null)
			documentReview.setDocumentCC(SecurityUtil.cleanIt(documentReview.getDocument().getContentCC()));
		documentReview.setDocumentContent(SecurityUtil.cleanIt(documentReview.getDocument().getContent()));
		documentReview.setDocumentEnterBy(SecurityUtil.cleanIt(documentReview.getDocument().getEnterBy()));
		if (documentReview.getDocument().getUpdateBy() != null)
			documentReview.setDocumentUpdateBy(SecurityUtil.cleanIt(documentReview.getDocument().getUpdateBy()));
		documentReview.setRefCodeID(SecurityUtil.cleanIt(documentReview.getRefCodeID()));
		documentReview.setSupervisorID(SecurityUtil.cleanIt(documentReview.getSupervisorID()));
		if (documentReview.getComment() != null)
			documentReview.setComment(SecurityUtil.cleanIt(documentReview.getComment()));
		documentReview.setComment(SecurityUtil.cleanIt(documentReview.getComment()));
		// documentService.getDocument(documentReview.getDocumentId());
		documentReview.setDocumentStatus("Draft");
		// documentService.saveDocument(document);
		// save Document to database
		documentReviewService.saveDocumentReview(documentReview);

		return "redirect:/document/process/submitted";
	}

	// part two of the overloaded method or the other button to be clear.
	@PostMapping("/saveDocumentReview/approved")
	public String saveDocumentReviewAccept(@ModelAttribute("documentReview") DocumentReview documentReview) {
		documentReview.setDocumentStaffID(SecurityUtil.cleanIt(documentReview.getDocument().getStaffID()));
		documentReview.setDocumentRecipientName(SecurityUtil.cleanIt(documentReview.getDocument().getRecipientName()));
		documentReview.setDocumentAddress(SecurityUtil.cleanIt(documentReview.getDocument().getAddress()));
		documentReview.setDocumentTitle(SecurityUtil.cleanIt(documentReview.getDocument().getTitle()));
		if (documentReview.getDocument().getContentSign() != null)
			documentReview.setDocumentSign(SecurityUtil.cleanIt(documentReview.getDocument().getContentSign()));
		if (documentReview.getDocument().getContentCC() != null)
			documentReview.setDocumentCC(SecurityUtil.cleanIt(documentReview.getDocument().getContentCC()));
		documentReview.setDocumentContent(SecurityUtil.cleanIt(documentReview.getDocument().getContent()));
		documentReview.setDocumentEnterBy(SecurityUtil.cleanIt(documentReview.getDocument().getEnterBy()));
		if (documentReview.getDocument().getUpdateBy() != null)
			documentReview.setDocumentUpdateBy(SecurityUtil.cleanIt(documentReview.getDocument().getUpdateBy()));
		documentReview.setRefCodeID(SecurityUtil.cleanIt(documentReview.getRefCodeID()));
		documentReview.setSupervisorID(SecurityUtil.cleanIt(documentReview.getSupervisorID()));
		if (documentReview.getComment() != null)
			documentReview.setComment(SecurityUtil.cleanIt(documentReview.getComment()));
		documentReview.setComment(SecurityUtil.cleanIt(documentReview.getComment()));
		// get template from the service
		// documentService.getDocument(documentReview.getDocumentId());
		documentReview.setDocumentStatus("Approved");
		// save Document to database
		documentReviewService.saveDocumentReview(documentReview);

		return "redirect:/document/process/submitted";
	}
}
