package com.purchasing.support.excel.base;

import com.purchasing.support.excel.entity.principal.Report;

import java.io.File;
import java.util.Collection;

/**
 * Created by Vanessa on 12/10/15.
 */
public interface BaseFile {

    public File generateReport(Collection<Object> objects);

    public File generateReportWithMultipleSearch(Report report);
}
