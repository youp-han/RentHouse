package com.jjst.rentManagement.renthouse.module.reports.service;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.jjst.rentManagement.renthouse.dto.BillingDto;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;
import com.jjst.rentManagement.renthouse.module.properties.entity.Property;
import com.jjst.rentManagement.renthouse.module.properties.entity.Unit;
import com.jjst.rentManagement.renthouse.module.tenants.entity.Tenant;
import com.jjst.rentManagement.renthouse.service.BillingService;
import com.jjst.rentManagement.renthouse.service.LeaseService;
import com.jjst.rentManagement.renthouse.service.PropertyService;
import com.jjst.rentManagement.renthouse.service.ReportService;
import com.jjst.rentManagement.renthouse.service.TenantService;
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

    @Autowired
    private TenantService tenantService;

    @Autowired
    private LeaseService leaseService;

    @Autowired
    private PropertyService propertyService;

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

            document.add(new Paragraph("Monthly Revenue Report"));

            Table table = new Table(2);
            table.addHeaderCell(new Cell().add(new Paragraph("Year-Month")));
            table.addHeaderCell(new Cell().add(new Paragraph("Revenue")));

            for (Map.Entry<YearMonth, Double> entry : monthlyRevenue.entrySet()) {
                table.addCell(new Cell().add(new Paragraph(entry.getKey().toString())));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(entry.getValue()))));
            }

            document.add(table);

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF report: " + e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    @Override
    public ByteArrayInputStream generateTenantStatusReport() {
        List<Tenant> tenants = tenantService.getAllTenants();
        List<Lease> allLeases = leaseService.getAllLeases(); // Get all leases
        List<Property> properties = propertyService.getAllProperties();
        List<Unit> units = propertyService.getAllUnits();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(out);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            document.add(new Paragraph("Tenant Status Report"));

            float[] columnWidths = {100F, 100F, 100F, 100F, 100F, 100F}; // Adjust as needed
            Table table = new Table(columnWidths);
            table.addHeaderCell(new Cell().add(new Paragraph("Tenant Name")));
            table.addHeaderCell(new Cell().add(new Paragraph("Email")));
            table.addHeaderCell(new Cell().add(new Paragraph("Phone")));
            table.addHeaderCell(new Cell().add(new Paragraph("Property")));
            table.addHeaderCell(new Cell().add(new Paragraph("Unit")));
            table.addHeaderCell(new Cell().add(new Paragraph("Lease Status")));

            LocalDate today = LocalDate.now(); // Get current date

            for (Tenant tenant : tenants) {
                // Find active lease for the tenant
                Lease activeLease = allLeases.stream()
                        .filter(lease -> lease.getTenant() != null && lease.getTenant().getId().equals(tenant.getId()))
                        .filter(lease -> {
                            // Directly use LocalDate objects, no parsing needed
                            LocalDate startDate = lease.getStartDate();
                            LocalDate endDate = lease.getEndDate();
                            return !startDate.isAfter(today) && !endDate.isBefore(today); // Lease started and not ended
                        })
                        .findFirst()
                        .orElse(null);

                if (activeLease != null) {
                    table.addCell(new Cell().add(new Paragraph(tenant.getName())));
                    table.addCell(new Cell().add(new Paragraph(tenant.getEmail())));
                    table.addCell(new Cell().add(new Paragraph(tenant.getPhone())));

                    // Find associated unit and property
                    Unit associatedUnit = units.stream()
                            .filter(unit -> unit.getId().equals(activeLease.getUnit().getId()))
                            .findFirst()
                            .orElse(null);

                    if (associatedUnit != null) {
                        Property associatedProperty = properties.stream()
                                .filter(property -> property.getId().equals(associatedUnit.getProperty().getId()))
                                .findFirst()
                                .orElse(null);

                        table.addCell(new Cell().add(new Paragraph(associatedProperty != null ? associatedProperty.getName() : "N/A")));
                        table.addCell(new Cell().add(new Paragraph(associatedUnit.getUnitNumber())));
                        table.addCell(new Cell().add(new Paragraph(activeLease.getLeaseStatus() != null ? activeLease.getLeaseStatus().name() : "N/A")));
                    } else {
                        table.addCell(new Cell().add(new Paragraph("N/A")));
                        table.addCell(new Cell().add(new Paragraph("N/A")));
                        table.addCell(new Cell().add(new Paragraph("N/A")));
                    }
                }
                // If no active lease, this tenant is skipped from the report based on the new criteria
            }

            document.add(table);

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PDF report: " + e.getMessage());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
