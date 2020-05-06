package com.example.xml_file_consumer;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;


@RestController
public class Controller {
    @GetMapping(value = "/status")
    public String getStatus() {
        return "OK :)";
    }

    @PostMapping(value = "/parseXml")
    public String postXmlFile(@RequestParam("file") MultipartFile file) throws IOException, XmlException {
        XmlObject xml = XmlObject.Factory.parse(new ByteArrayInputStream(file.getBytes()));
        return xml.toString();
    }

}
