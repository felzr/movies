package com.felzr.movies.api.util;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.felzr.movies.api.dto.MovieCsv;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadCsv {
    private static final String CSV_PATH = "classpath:movielist.csv";

    public List<MovieCsv> convertCsvToDto() throws IOException {
        List<MovieCsv> list;
        File csvFile = ResourceUtils.getFile(CSV_PATH);
        CsvMapper csvMapper = new CsvMapper();

        CsvSchema csvSchema = csvMapper
                .typedSchemaFor(MovieCsv.class)
                .withHeader()
                .withColumnSeparator(';')
                .withComments();

        MappingIterator<MovieCsv> complexUsersIter = csvMapper
                .readerWithTypedSchemaFor(MovieCsv.class)
                .with(csvSchema)
                .readValues(csvFile);
        list = complexUsersIter.readAll();

        return list;
    }
}
