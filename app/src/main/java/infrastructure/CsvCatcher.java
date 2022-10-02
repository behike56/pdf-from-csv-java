package infrastructure;

import domain.CsvDto;
import lombok.NonNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvCatcher {

    private CsvCatcher() {
    }

    public static List<List<String>> extractCsv() throws IOException {

        List<List<String>> list = new ArrayList<>();

        File file = new File(Cfg.CURRENT_PATH + Cfg.CSV_FILE_PATH);

        if (file.exists()) {

            try (Stream<String> stream = Files.lines(file.toPath())) {
                list = stream.map(data -> Arrays.asList(data.split(","))).collect(Collectors.toList());
            }
        }

        return list;
    }

    public static Map<String, List<CsvDto>> makePreData(@NonNull List<List<String>> rawList) {

        List<CsvDto> dtoList = rawList.stream()
                .map(CsvDto::createDto)
                .collect(Collectors.toUnmodifiableList());

        Map<String, List<CsvDto>> dtoMap = new HashMap<>();
        List<CsvDto> tempList = new ArrayList<>();
        String manageNum = "";

        Integer listSize = dtoList.size();
        Integer count = 1;
        boolean listClearFlag = false;

        for (CsvDto dto : dtoList) {

            // 最初のレコード
            if (isFirstRecord().test(manageNum)) {

                manageNum = dto.getManageNum();
            }

            if (isDivideRecord(dto).test(manageNum)) {

                dtoMap.put(manageNum, tempList);
                manageNum = dto.getManageNum();
                listClearFlag = true;
            }

            if (listClearFlag) {

                tempList = new ArrayList<>();
                listClearFlag = false;

            }

            tempList.add(dto);

            // 最後の要素の場合
            if (isLastRecord(count).test(listSize)) {
                dtoMap.put(dto.getManageNum(), tempList);
                count++;
            }
        }

        return dtoMap;
    }

    private static Predicate<String> isFirstRecord() {

        return String::isEmpty;
    }

    private static Predicate<String> isDivideRecord(CsvDto dto) {

        return str -> Objects.nonNull(str) && !(str.equals(dto.getManageNum()));
    }

    private static Predicate<Integer> isLastRecord(Integer count) {

        return listSize -> Objects.equals(listSize, count);
    }

}
