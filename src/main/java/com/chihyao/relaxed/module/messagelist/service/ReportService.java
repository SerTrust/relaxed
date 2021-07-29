package com.chihyao.relaxed.module.messagelist.service;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface ReportService {

    byte[] exportReport() throws FileNotFoundException, JRException;
}
