package com.example.demotestsuperpractice.controller;

import com.example.demotestsuperpractice.entities.Report;
import com.example.demotestsuperpractice.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Report>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllEmployeeReports());
    }

    @GetMapping("/byIds")
    public List<Report> getReportsByEmployeeIds(@RequestParam List<Integer> ids) {
        return reportService.getReportsByEmployeeIds(ids);
    }
}