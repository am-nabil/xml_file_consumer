package com.example.xml_file_consumer;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = XmlFileConsumerApplication.class)
@WebAppConfiguration
class ControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Test
    public void testParseXml() throws Exception {
        String uri = "/parseXml";
        String filepath = "src/test/resources/file.xml";
        String xml = Files.readAllLines(Paths.get(filepath), Charset.forName("UTF-8"))
                .stream().reduce(String::concat).orElse("");
        MockMultipartFile file = new MockMultipartFile("file", "file.xml", "application/xml", xml.getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart(uri)
                .file(file))
                .andExpect(status().is(200));
    }
}