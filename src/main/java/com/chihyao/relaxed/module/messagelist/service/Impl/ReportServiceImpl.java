package com.chihyao.relaxed.module.messagelist.service.Impl;

import com.chihyao.relaxed.module.messagelist.entity.User;
import com.chihyao.relaxed.module.messagelist.controller.request.ui.UserDto;
import com.chihyao.relaxed.module.messagelist.repository.UserRepository;
import com.chihyao.relaxed.module.messagelist.service.ReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public byte[] exportReport() throws FileNotFoundException, JRException {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = convertToDto(users);
        JRDataSource dataSource = new JRBeanCollectionDataSource(userDtos);
//      方法一
//      JasperDesign design = JRXmlLoader.load("classpath:report1.jrxml");
//      JasperReport report = JasperCompileManager.compileReport(design);
//      方法二
        File file = ResourceUtils.getFile("classpath:report1.jrxml");
        JasperReport report = JasperCompileManager.compileReport(file.getAbsolutePath());
        //todo 處理中文顯示問題
        JasperPrint print = JasperFillManager.fillReport(report, null, dataSource);

        return JasperExportManager.exportReportToPdf(print);
    }

    private List<UserDto> convertToDto(List<User> users) {
        ModelMapper modelMapper = new ModelMapper();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

//        return users.stream().map(user -> {
//            UserDto userDto = new UserDto();
//            userDto.setAccount(user.getAccount());
//            userDto.setName(user.getName());
//            userDto.setEmailAddress(user.getEmailAddress());
//            return userDto;
//        }).collect(Collectors.toList());
    }
}
