package com.sudo.modlog.sql;

public class ReportSession {

    private int reportStage;

    public ReportSession(String name) {
        reportStage = 1;
    }

    public int getReportStage() {
        return this.reportStage;
    }

    public void incStage() {
        this.reportStage += 1;
    }
}
