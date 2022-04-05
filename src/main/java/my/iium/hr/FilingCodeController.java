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

import my.iium.hr.Service.FileRefNumberService;
import my.iium.hr.Service.FileSubTopicService;
import my.iium.hr.Service.FileTopicService;
import my.iium.hr.Service.OrganizationMainService;
import my.iium.hr.model.FileRefNumber;
import my.iium.hr.model.FileSubTopic;
import my.iium.hr.model.FileTopic;
import my.iium.hr.model.OrganizationMain;
import my.iium.hr.security.SecurityUtil;

@Controller
@RequestMapping("/filingCode")
public class FilingCodeController {

	@Autowired
	private FileRefNumberService fileRefNumberService;
	@Autowired
	private FileSubTopicService fileSubTopicService;
	@Autowired
	private FileTopicService fileTopicService;
	@Autowired
	private OrganizationMainService organizationMainService;

	// the current method will scam the user into thinking they are
	// still on the same page but i will direct them to a new page
	// with the same design.

	// redirecting visitor from filingcode to filingCode/topic
	// because some code is link sensitive and this method
	// doesn't do any harm!
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("")
	public String filing() {
		return "redirect:/filingCode/topic";
	}

	// First part is the default filing code page
	// which will display ....File Topic........
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/topic")
	public String filingCode(Model model) {
		// to make the add new in the same page
		// you have to add the next two lines to this function.
		// just YOLO and the code worked!

		FileTopic fileTopic = new FileTopic();
		model.addAttribute("fileTopic", fileTopic);

		model.addAttribute("listFileTopic", fileTopicService.getAllFileTopic());

		return "filing_topic";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/topic/saveTopic")
	public String saveTopic(@ModelAttribute("fileTopic") FileTopic fileTopic) {
		fileTopic.setTitle(SecurityUtil.cleanIt(fileTopic.getTitle()));
		fileTopic.setTopicClass(SecurityUtil.cleanIt(fileTopic.getTopicClass()));
		fileTopic.setEnterBy(SecurityUtil.cleanIt(fileTopic.getEnterBy()));
		if (fileTopic.getUpdateBy() != null)
			fileTopic.setUpdateBy(SecurityUtil.cleanIt(fileTopic.getUpdateBy()));

		// save Template to database
		fileTopicService.saveTopic(fileTopic);
		return "redirect:/filingCode/topic";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/topic/showFormForUpdateTopic/{id}")
	public String showFormForUpdateTopic(@PathVariable(value = "id") long id, Model model) {

		FileTopic fileTopic = fileTopicService.getTopic(id);

		model.addAttribute("fileTopic", fileTopic);
		return "update_topic";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/topic/deleteTopic/{id}")
	public String deleteTopic(@PathVariable(value = "id") long id) {

		// call delete employee method
		this.fileTopicService.deleteTopicById(id);
		return "redirect:/filingCode/topic";
	}

	// File Subtopic...
	// ..................File Subtopic...............
	// .................................File Subtopic...

	// need to add lists to pagination if an error occurs
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/subTopic")
	public String subTopic(Model model) {

		FileSubTopic fileSubTopic = new FileSubTopic();
		model.addAttribute("fileSubTopic", fileSubTopic);
		List<FileTopic> listFileTopic = fileTopicService.getAllFileTopic();
		model.addAttribute("listFileTopic", listFileTopic);

		model.addAttribute("listFileSubTopic", fileSubTopicService.getAllFileSubTopic());

		return "filing_subtopic";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/subTopic/saveSubTopic")
	public String saveSubTopic(@ModelAttribute("fileSubTopic") FileSubTopic fileSubTopic) {
		fileSubTopic.setSubTopic1(SecurityUtil.cleanIt(fileSubTopic.getSubTopic1()));
		fileSubTopic.setSubTopic2(SecurityUtil.cleanIt(fileSubTopic.getSubTopic2()));
		fileSubTopic.setSubTopic3(SecurityUtil.cleanIt(fileSubTopic.getSubTopic3()));
		fileSubTopic.setEnterBy(SecurityUtil.cleanIt(fileSubTopic.getEnterBy()));
		if (fileSubTopic.getUpdateBy() != null)
			fileSubTopic.setUpdateBy(SecurityUtil.cleanIt(fileSubTopic.getUpdateBy()));
		// save Template to database
		fileSubTopicService.saveSubTopic(fileSubTopic);
		return "redirect:/filingCode/subTopic";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/subTopic/showFormForUpdateSubTopic/{id}")
	public String showFormForUpdateSubTopic(@PathVariable(value = "id") long id, Model model) {

		FileSubTopic fileSubTopic = fileSubTopicService.getSubTopic(id);

		model.addAttribute("fileSubTopic", fileSubTopic);
		List<FileTopic> listFileTopic = fileTopicService.getAllFileTopic();
		model.addAttribute("listFileTopic", listFileTopic);

		return "update_subtopic";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/subTopic/deleteSubTopic/{id}")
	public String deleteSubTopic(@PathVariable(value = "id") long id) {

		// call delete employee method
		this.fileSubTopicService.deleteSubTopicById(id);
		return "redirect:/filingCode/subTopic";
	}

	// File RefNumber...
	// ..................File RefNumber...............
	// .................................File RefNumber...
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/refNumber")
	public String fileRefNumber(Model model) {

		FileRefNumber refNumber = new FileRefNumber();
		model.addAttribute("refNumber", refNumber);
		List<FileTopic> listFileTopic = fileTopicService.getAllFileTopic();
		model.addAttribute("listFileTopic", listFileTopic);
		List<FileSubTopic> listFileSubTopic = fileSubTopicService.getAllFileSubTopic();
		model.addAttribute("listFileSubTopic", listFileSubTopic);
		List<OrganizationMain> listOrganizationMain = organizationMainService.getAllOrganization();
		model.addAttribute("listOrganizationMain", listOrganizationMain);

		model.addAttribute("listFileRefNumber", fileRefNumberService.getAllFileRefNumber());

		return "filing_refNumber";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/refNumber/saveRefNumber")
	public String saverefNumber(@ModelAttribute("fileRefNumber") FileRefNumber fileRefNumber) {
		fileRefNumber.setRefCodeID(SecurityUtil.cleanIt(fileRefNumber.getRefCodeID()));
		fileRefNumber.setTopicClass(SecurityUtil.cleanIt(fileRefNumber.getTopicClass()));
		fileRefNumber.setHurisDept(SecurityUtil.cleanIt(fileRefNumber.getHurisDept()));
		fileRefNumber.setEnterBy(SecurityUtil.cleanIt(fileRefNumber.getEnterBy()));
		if (fileRefNumber.getUpdateBy() != null)
			fileRefNumber.setUpdateBy(SecurityUtil.cleanIt(fileRefNumber.getUpdateBy()));
		// save Template to database
		fileRefNumberService.saveRefNumber(fileRefNumber);
		return "redirect:/filingCode/refNumber";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/refNumber/showFormForUpdateRefNumber/{id}")
	public String showFormForUpdateRefNumber(@PathVariable(value = "id") long id, Model model) {

		FileRefNumber refNumber = fileRefNumberService.getRefNumber(id);
		model.addAttribute("refNumber", refNumber);
		List<FileTopic> listFileTopic = fileTopicService.getAllFileTopic();
		model.addAttribute("listFileTopic", listFileTopic);
		List<FileSubTopic> listFileSubTopic = fileSubTopicService.getAllFileSubTopic();
		model.addAttribute("listFileSubTopic", listFileSubTopic);
		List<OrganizationMain> listOrganizationMain = organizationMainService.getAllOrganization();
		model.addAttribute("listOrganizationMain", listOrganizationMain);

		return "update_refNumber";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/refNumber/deleteRefNumber/{id}")
	public String deleteRefNumber(@PathVariable(value = "id") long id) {

		// call delete employee method
		this.fileRefNumberService.deleteRefNumberById(id);
		return "redirect:/filingCode/refNumber";
	}

	// Organization...
	// ..................Organization...............
	// .................................Organization...
	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/organization")
	public String organizationMain(Model model) {

		OrganizationMain organization = new OrganizationMain();
		model.addAttribute("organization", organization);

		model.addAttribute("listOrganizationMain", organizationMainService.getAllOrganization());

		return "filing_organization";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/organization/saveOrganization")
	public String saveOrganization(@ModelAttribute("OrganizationMain") OrganizationMain organizationMain) {

		organizationMain.setAcronym(SecurityUtil.cleanIt(organizationMain.getAcronym()));
		organizationMain.setTitle(SecurityUtil.cleanIt(organizationMain.getTitle()));
		organizationMain.setEnterBy(SecurityUtil.cleanIt(organizationMain.getEnterBy()));
		if (organizationMain.getUpdateBy() != null)
			organizationMain.setUpdateBy(SecurityUtil.cleanIt(organizationMain.getUpdateBy()));

		// save Template to database
		organizationMainService.saveOrganization(organizationMain);
		return "redirect:/filingCode/organization";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/organization/showFormForUpdateOrganization/{id}")
	public String showFormForUpdateOrganization(@PathVariable(value = "id") long id, Model model) {

		OrganizationMain organizationMain = organizationMainService.getOrganization(id);

		model.addAttribute("organization", organizationMain);
		return "update_organization";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/organization/deleteOrganization/{id}")
	public String deleteOrganization(@PathVariable(value = "id") long id) {

		// call delete employee method
		this.organizationMainService.deleteOrganizationById(id);
		return "redirect:/filingCode/organization";
	}

}
