package com.duke.dls.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@RequestMapping("/api/v1/file-controller")
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

//    @Autowired
//    StudentService studentService;

    private static String UPLOADED_FOLDER = "msp/";


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
//    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
    public ResponseEntity upload(HttpServletRequest request, HttpServletResponse response) {
//        try {

            // Get the file and save it somewhere
//            File file = new File(request.getParameter("file"));
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//            Files.write(path, bytes);

//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        return "redirect:/uploadStatus";
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/test", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity uploadTest(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info(request.getContentType());
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()){
            LOGGER.info(params.nextElement());
        }

        Enumeration<String> attrs = request.getAttributeNames();
        while (attrs.hasMoreElements()){
            LOGGER.info(attrs.nextElement());
        }
        return ResponseEntity.ok("OK");
    }

}


