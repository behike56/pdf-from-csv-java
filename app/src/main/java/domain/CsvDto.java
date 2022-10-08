package domain;

import lombok.Data;

import java.util.List;

@Data
public class CsvDto {

    private String manageNum;
    private String dateTimeYM;
    private String col1;
    private String col2;
    private String col3;
    private String col4;
    private String col5;
    private String col6;
    private String col7;

    public static CsvDto createDto(List<String> csvList){

        CsvDto dto = new CsvDto();

        int idx = 0;

        dto.setManageNum(csvList.get(idx++));
        dto.setDateTimeYM(csvList.get(idx++));
        dto.setCol1(csvList.get(idx++));
        dto.setCol2(csvList.get(idx++));
        dto.setCol3(csvList.get(idx++));
        dto.setCol4(csvList.get(idx++));
        dto.setCol5(csvList.get(idx++));
        dto.setCol6(csvList.get(idx++));
        dto.setCol7(csvList.get(idx++));

        return dto;
    }
}
