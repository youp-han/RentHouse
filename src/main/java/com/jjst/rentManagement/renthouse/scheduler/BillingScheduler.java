package com.jjst.rentManagement.renthouse.scheduler;

import com.jjst.rentManagement.renthouse.module.bills.entity.Billing;
import com.jjst.rentManagement.renthouse.module.bills.repository.BillingRepository;
import com.jjst.rentManagement.renthouse.module.leases.entity.Lease;
import com.jjst.rentManagement.renthouse.service.ActivityLogService;
import com.jjst.rentManagement.renthouse.service.BillingService;
import com.jjst.rentManagement.renthouse.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@EnableScheduling
public class BillingScheduler {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private ActivityLogService activityLogService;

    @Autowired
    private LeaseService leaseService;

    @Autowired
    private BillingService billingService;

    // 매월 1일 새벽 2시에 실행
    @Scheduled(cron = "0 0 2 1 * ?")
    public void generateMonthlyBills() {
        List<Lease> activeLeases = leaseService.getActiveLeases();
        for (Lease lease : activeLeases) {
            billingService.generateMonthlyBillingsForLease(lease);
        }
    }

    // 매일 새벽 1시에 실행
    @Scheduled(cron = "0 0 1 * * ?")
    public void checkForOverduePayments() {
        LocalDate today = LocalDate.now();
        List<Billing> overdueBillings = billingRepository.findByPaidFalseAndDueDateBefore(today);

        for (Billing billing : overdueBillings) {
            // 중복 알림을 피하기 위한 로직이 필요할 수 있지만, 우선은 로그만 남깁니다.
            String description = String.format("임대 계약 ID %d의 월세가 미납되었습니다. (마감일: %s)",
                    billing.getLease().getId(), billing.getDueDate().toString());
            activityLogService.logActivity(com.jjst.rentManagement.renthouse.module.activity.entity.ActivityType.OVERDUE_RENT, description, billing.getLease().getId());
        }
    }
}
