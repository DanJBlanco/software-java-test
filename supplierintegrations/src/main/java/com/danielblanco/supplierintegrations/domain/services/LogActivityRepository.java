package com.danielblanco.supplierintegrations.domain.services;

import com.danielblanco.supplierintegrations.domain.entity.LogActivity;

public interface LogActivityRepository {
    boolean readCSVFile(String filePath, LogActivity logActivityInput);
}
