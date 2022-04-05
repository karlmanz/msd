package my.iium.hr;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ReportController {
	@Autowired
	DataSource dataSource;
	
	 @GetMapping("/report")
		public ResponseEntity<byte[]> getEmployeeRecordReport() {

			try {
				
				// Get your data source
				//JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(alltemplate);
				
				//dynamic parameters required for report
				Map<String, Object> empParams = new HashMap<String, Object>();
		
				JasperPrint empReport =
						JasperFillManager.fillReport
					   (
								JasperCompileManager.compileReport(
								//getClass().getResourceAsStream("/report/report7.jrxml"))
								ResourceUtils.getFile("classpath:report/report8.jrxml")
										.getAbsolutePath()) // path of the jasper report
								, empParams // dynamic parameters
								//, new JREmptyDataSource()
								, dataSource.getConnection()
						);

				HttpHeaders headers = new HttpHeaders();
				//set the PDF format
				headers.setContentType(MediaType.APPLICATION_PDF);
				headers.setContentDispositionFormData("filename", "template.pdf");
				//create the report in PDF format
				return new ResponseEntity<byte[]>
						(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

			} catch(Exception e) {
				System.out.print(e);
				return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	 

}
