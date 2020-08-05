package com.sample.spring.excel.controller;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.spring.excel.jpa.CustomerRepository;
import com.sample.spring.excel.model.Customer;
import com.sample.spring.excel.utils.ExcelFileExporter;
import com.sample.spring.excel.utils.ExcelGenerator;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("customers")
    public List<Customer> getAllCustomers() {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        return customers;
    }

    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> download() throws IOException {

        List<Customer> customers = (List<Customer>) customerRepository.findAll();

        ByteArrayResource resource = ExcelGenerator.customersToExcel(customers);
       
        return ResponseEntity.ok()
//                .headers(headers) // add headers if any
        		.header("Content-Disposition", "attachment; filename=customersFile.xlsx")
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
    
    @GetMapping("/download/{fileName:.+}")
    public void downloadCsv(HttpServletResponse response, 
    		@PathVariable("fileName") String fileName) throws IOException {
    	List<Customer> customers = (List<Customer>) customerRepository.findAll();
    	
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile(customers);
        IOUtils.copy(stream, response.getOutputStream());
    }

}
