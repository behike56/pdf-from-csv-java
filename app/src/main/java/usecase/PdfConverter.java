package usecase;

import domain.CsvDto;
import infrastructure.CsvCatcher;
import infrastructure.PdfBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PdfConverter {
    
    public static void convert() throws IOException {

        Map<String, List<CsvDto>> dtoMap = getCsData();

        buildPDF(dtoMap);
    }

    private static Map<String, List<CsvDto>> getCsData() throws IOException {

        List<List<String>> rawList = CsvCatcher.extractCsv();
        
        return CsvCatcher.makePreData(rawList);
    }

    private static void buildPDF (Map<String, List<CsvDto>> dtoMap) throws IOException {

        int pageIdx = 0;
        
        for (Map.Entry<String, List<CsvDto>> entry : dtoMap.entrySet()) {
            
            PdfBuilder.putDocument(entry.getValue(), entry.getKey());
            pageIdx++;
        }
    }
}
