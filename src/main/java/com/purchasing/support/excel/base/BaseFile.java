package com.purchasing.support.excel.base;

import java.io.File;
import java.util.Collection;

/**
 * Created by Vanessa on 12/10/15.
 */
public interface BaseFile {

    public File generateReport(Collection<Object> objects);
}
