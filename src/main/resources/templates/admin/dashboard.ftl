<#include "../common/layout.ftl">

    <#macro content>
        <!-- Page Heading -->
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">대시보드</h1>
            <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                    class="fas fa-download fa-sm text-white-50"></i> 보고서 생성</a>
        </div>

        <!-- 1. 주요 통계 (Key Statistics) -->
        <div class="row">
            <!-- 총 부동산/세대 수 -->
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-primary shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                    총 부동산/세대 수</div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">${totalProperties} / ${totalUnits}</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-building fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 공실률 (Vacancy Rate) -->
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-success shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                    공실률</div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">${vacancyRate}%</div>
                                <div class="text-xs text-gray-600">(${totalUnits - occupiedUnits} / ${totalUnits})</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-check-circle fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 연체된 임대료 -->
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-danger shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-danger text-uppercase mb-1">
                                    연체된 임대료</div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">₩${overdueAmount?string.number}</div>
                                <div class="text-xs text-gray-600">(${overdueCount} 건)</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 계약 만료 예정 -->
            <div class="col-xl-3 col-md-6 mb-4">
                <div class="card border-left-warning shadow h-100 py-2">
                    <div class="card-body">
                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                    계약 만료 예정 (30일 내)</div>
                                <div class="h5 mb-0 font-weight-bold text-gray-800">${expiringLeaseCount} 건</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-file-contract fa-2x text-gray-300"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <!-- Area Chart -->
            <div class="col-xl-8 col-lg-7">
                <div class="card shadow mb-4">
                    <!-- Card Header - Dropdown -->
                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 class="m-0 font-weight-bold text-primary">월별 수입 현황</h6>
                    </div>
                    <!-- Card Body -->
                    <div class="card-body">
                        <div class="chart-area">
                            <canvas id="monthlyIncomeChart"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <!-- 2. 최신 활동 (Recent Activity) -->
            <div class="col-lg-6 mb-4">
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">최신 활동</h6>
                    </div>
                    <div class="card-body">
                        <#if recentActivities?has_content>
                            <ul class="list-group list-group-flush">
                                <#list recentActivities as activity>
                                    <li class="list-group-item d-flex justify-content-between align-items-center">
                                        <span>${activity.description}</span>
                                        <small class="text-muted">${activity.createTime?string("MM-dd HH:mm")}</small>
                                    </li>
                                </#list>
                            </ul>
                        <#else>
                            <p>최근 활동 내역이 없습니다.</p>
                        </#if>
                    </div>
                </div>
            </div>

            <!-- 3. 알림 (Notifications) -->
            <div class="col-lg-6 mb-4">
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">알림</h6>
                    </div>
                    <div class="card-body">
                        <p>TODO: 주요 알림이 여기에 표시됩니다.</p>
                    </div>
                </div>
            </div>
        </div>

         <script>
                // Set new default font family and font color to mimic Bootstrap's default styling
                Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
                Chart.defaults.global.defaultFontColor = '#858796';

                function number_format(number, decimals, dec_point, thousands_sep) {
                // ... (omitted for brevity)
                return (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
                }

                        // Bar Chart Example
                        var ctx = document.getElementById("monthlyIncomeChart");
                        var monthlyIncomeChart = new Chart(ctx, {
                type: 'bar',
                data: {
                labels: [<#list monthlyIncomeLabels as label>"${label}"<#if label_has_next>,</#if></#list>],
                datasets: [{
                label: "수입",
                backgroundColor: "#4e73df",
                hoverBackgroundColor: "#2e59d9",
                borderColor: "#4e73df",
                data: [<#list monthlyIncomeData as data>${data?c}<#if data_has_next>,</#if></#list>],
                }],
                            },
                            options: {
                maintainAspectRatio: false,
                layout: {
                padding: {
                left: 10,
                right: 25,
                top: 25,
                bottom: 0
                }
                                },
                                scales: {
                xAxes: [{
                time: {
                unit: 'month'
                },
                                        gridLines: {
                display: false,
                drawBorder: false
                },
                                        ticks: {
                maxTicksLimit: 6
                },
                                        maxBarThickness: 25,
                                    }],
                                    yAxes: [{
                ticks: {
                min: 0,
                maxTicksLimit: 5,
                padding: 10,
                // Include a dollar sign in the ticks
                callback: function(value, index, values) {
                return '₩' + new Intl.NumberFormat().format(value);
                }
                                        },
                                        gridLines: {
                color: "rgb(234, 236, 244)",
                zeroLineColor: "rgb(234, 236, 244)",
                drawBorder: false,
                borderDash: [2],
                zeroLineBorderDash: [2]
                }
                                    }],
                                },
                                legend: {
                display: false
                },
                                tooltips: {
                titleMarginBottom: 10,
                titleFontColor: '#6e707e',
                titleFontSize: 14,
                backgroundColor: "rgb(255,255,255)",
                bodyFontColor: "#858796",
                borderColor: '#dddfeb',
                borderWidth: 1,
                xPadding: 15,
                yPadding: 15,
                displayColors: false,
                caretPadding: 10,
                callbacks: {
                label: function(tooltipItem, chart) {
                var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                return datasetLabel + ': ₩' + new Intl.NumberFormat().format(tooltipItem.yLabel);
                }
                                    }
                                },
                            }
            });
            </script>
    </#macro>


