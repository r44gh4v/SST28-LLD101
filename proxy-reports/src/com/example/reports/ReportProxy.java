package com.example.reports;

public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("[proxy] ACCESS DENIED: " + user.getName()
                    + " (" + user.getRole() + ") cannot view " + classification + " report '" + title + "'");
            return;
        }
        if (realReport == null) {
            System.out.println("[proxy] lazy-loading real report for " + reportId);
            realReport = new RealReport(reportId, title, classification);
        } else {
            System.out.println("[proxy] using cached report for " + reportId);
        }
        realReport.display(user);
    }
}
