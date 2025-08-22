package com.jjst.rentManagement.renthouse.module.reports.service;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.jjst.rentManagement.renthouse.dto.BillingDto;
import com.jjst.rentManagement.renthouse.service.BillingService;
import com.jjst.rentManagement.renthouse.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private BillingService billingService;

    @Override
    public ByteArrayInputStream generateMonthlyRevenueReport() {
        List<BillingDto> billings = billingService.getAllBillings();

        Map<YearMonth, Double> monthlyRevenue = billings.stream()
                .filter(b -> b.getPaidDate() != null && !b.getPaidDate().isEmpty())
                .collect(Collectors.groupingBy(b -> YearMonth.from(LocalDate.parse(b.getPaidDate(), DateTimeFormatter.ISO_LOCAL_DATE)),
                        Collectors.summingDouble(b -> b.getAmount().doubleValue())));

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(out);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            document.add(new com.itextpdf.layout.element.Paragraph("Monthly Revenue Report"));

            Table table = new Table(2);
            table.addHeaderCell(new Cell().add(new com.itextpdf.layout.element.Paragraph("Year-Month")));
            table.addHeaderCell(new Cell().add(new com.itextpdf.layout.element.Paragraph("Revenue")));

            for (Map.Entry<YearMonth, Double> entry : monthlyRevenue.entrySet()) {
                table.addCell(new Cell().add(new com.itextpdf.layout.element.Paragraph(entry.getKey().toString())));
                table.addCell(new Cell().add(new com.itextpdf.layout.element.Paragraph(String.valueOf(entry.getValue()))));
            }

            document.add(table);

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF report: " + e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
