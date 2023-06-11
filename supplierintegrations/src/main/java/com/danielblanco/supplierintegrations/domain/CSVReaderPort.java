package com.danielblanco.supplierintegrations.domain;

import org.apache.commons.csv.CSVRecord;

public interface CSVReaderPort {
    void processCSVRecords(Iterable<CSVRecord> records);
}
