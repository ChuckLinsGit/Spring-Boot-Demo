package com.example.demo.xmlconvert;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//使用了Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor

//JacksonXml*+ 标签用于对象转为XML
@JacksonXmlRootElement(localName = "User")
public  class UserForJacksonXML {
    @JacksonXmlProperty(localName = "name")
    public String name;
    @JacksonXmlProperty(localName = "birthday")
    public LocalDate birthday;
}