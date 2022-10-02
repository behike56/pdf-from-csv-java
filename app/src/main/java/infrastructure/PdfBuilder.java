package infrastructure;

import com.google.common.collect.Lists;
import domain.CsvDto;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PdfBuilder {

    private PdfBuilder() {
    }

    public static void putDocument(List<CsvDto> dtoList, String manageNum) throws IOException {

        List<List<CsvDto>> portions = Lists.partition(dtoList, 10);

        try (PDDocument document = new PDDocument();) {

            for (List<CsvDto> partList : portions) {

                putPage(document, partList);

                document.save(getFileName(manageNum));
            }
        }
    }

    private static void putPage(PDDocument document, List<CsvDto> partList)
            throws FileNotFoundException, IOException {

        PDRectangle rect = new PDRectangle(Cfg.RECT_X, Cfg.RECT_Y);

        PDPage page = new PDPage(rect);

        PDFont font = PDType0Font.load(document, new File(Cfg.CURRENT_PATH + Cfg.FONT_PATH));

        putContents(document, page, font, partList);
    }

    private static void putContents(PDDocument document, PDPage page, PDFont font,
                                    List<CsvDto> partList) throws IOException {

        try (PDPageContentStream content = new PDPageContentStream(document, page,
                PDPageContentStream.AppendMode.APPEND, true, true);) {

            PDImageXObject bgImg = PDImageXObject
                    .createFromFile(Cfg.CURRENT_PATH + Cfg.TEMPLATE_IMG_PATH, document);

            float deltaY = 0f;

            for (CsvDto dto : partList) {

                if (deltaY == 0f) {

                    putBackground(content, bgImg);
                    putManageNo(content, font, dto);
                    putDateTimeYM(content, font, dto);
                }

                putColumn1(content, font, dto, deltaY);
                putColumn2(content, font, dto, deltaY);
                putColumn3(content, font, dto, deltaY);
                putColumn5(content, font, dto, deltaY);
                putColumn6(content, font, dto, deltaY);
                putColumn7(content, font, dto, deltaY);

                deltaY += Cfg.DELTA_Y;
            }
        }

        document.addPage(page);
    }

    private static void putBackground(PDPageContentStream content, PDImageXObject bgImg)
            throws IOException {

        content.drawImage(bgImg, 0f, 0f);
    }

    private static void putManageNo(PDPageContentStream content, PDFont font, CsvDto dto)
            throws IOException {

        content.beginText();
        content.setNonStrokingColor(Cfg.FONT_COLOR);
        content.setFont(font, Cfg.FONT_SIZE);
        content.newLineAtOffset(calcFixture(font, Cfg.MANAGE_X, dto.getManageNum()), Cfg.MANAGE_Y);
        content.showText(dto.getManageNum());
        content.endText();
    }

    private static void putDateTimeYM(PDPageContentStream content, PDFont font, CsvDto dto) throws IOException {
        content.beginText();
        content.setNonStrokingColor(Cfg.FONT_COLOR);
        content.setFont(font, Cfg.FONT_SIZE);
        content.newLineAtOffset(calcFixture(font, Cfg.DATETIME_YM_X, dto.getDateTimeYM()), Cfg.DATETIME_YM_Y);
        content.showText(dto.getDateTimeYM());
        content.endText();
    }

    private static void putColumn1(PDPageContentStream content, PDFont font, CsvDto dto, float dy)
            throws IOException {

        content.beginText();
        content.setNonStrokingColor(Cfg.FONT_COLOR);
        content.setFont(font, Cfg.FONT_SIZE);
        content.newLineAtOffset(calcFixture(font, Cfg.COL1_X, dto.getCol1()), Cfg.BEGIN_Y + dy);
        content.showText(dto.getCol1());
        content.endText();
    }

    private static void putColumn2(PDPageContentStream content, PDFont font, CsvDto dto, float dy)
            throws IOException {

        content.beginText();
        content.setNonStrokingColor(Cfg.FONT_COLOR);
        content.setFont(font, Cfg.FONT_SIZE);
        content.newLineAtOffset(calcFixture(font, Cfg.COL2_X, dto.getCol2()), Cfg.BEGIN_Y + dy);
        content.showText(dto.getCol2());
        content.endText();
    }

    private static void putColumn3(PDPageContentStream content, PDFont font, CsvDto dto, float dy)
            throws IOException {

        content.beginText();
        content.setNonStrokingColor(Cfg.FONT_COLOR);
        content.setFont(font, Cfg.FONT_SIZE);
        content.newLineAtOffset(calcFixture(font, Cfg.COL3_X, dto.getCol3()), Cfg.BEGIN_Y + dy);
        content.showText(dto.getCol3());
        content.endText();
    }

    private static void putColumn4(PDPageContentStream content, PDFont font, CsvDto dto, float dy)
            throws IOException {

        content.beginText();
        content.setNonStrokingColor(Cfg.FONT_COLOR);
        content.setFont(font, Cfg.FONT_SIZE);
        content.newLineAtOffset(calcFixture(font, Cfg.COL4_X, dto.getCol4()), Cfg.BEGIN_Y + dy);
        content.showText(dto.getCol4());
        content.endText();
    }

    private static void putColumn5(PDPageContentStream content, PDFont font, CsvDto dto, float dy)
            throws IOException {

        content.beginText();
        content.setNonStrokingColor(Cfg.FONT_COLOR);
        content.setFont(font, Cfg.FONT_SIZE);
        content.newLineAtOffset(calcFixture(font, Cfg.COL5_X, dto.getCol5()), Cfg.BEGIN_Y + dy);
        content.showText(dto.getCol5());
        content.endText();
    }

    private static void putColumn6(PDPageContentStream content, PDFont font, CsvDto dto, float dy)
            throws IOException {

        content.beginText();
        content.setNonStrokingColor(Cfg.FONT_COLOR);
        content.setFont(font, Cfg.FONT_SIZE);
        content.newLineAtOffset(calcFixture(font, Cfg.COL6_X, dto.getCol6()), Cfg.BEGIN_Y + dy);
        content.showText(dto.getCol6());
        content.endText();
    }

    private static void putColumn7(PDPageContentStream content, PDFont font, CsvDto dto, float dy)
            throws IOException {

        content.beginText();
        content.setNonStrokingColor(Cfg.FONT_COLOR);
        content.setFont(font, Cfg.FONT_SIZE);
        content.newLineAtOffset(calcFixture(font, Cfg.COL7_X, dto.getCol7()), Cfg.BEGIN_Y + dy);
        content.showText(dto.getCol7());
        content.endText();
    }

    private static float calcFixture(PDFont font, float axisX, String text) throws IOException {

        return axisX - (font.getStringWidth(text) * Cfg.FONT_SIZE / 1000);
    }

    private static String getFileName(String manageNum) {

        return Cfg.CURRENT_PATH + Cfg.OUTPUT_PATH +
                manageNum + Cfg.SEP + getCurrentTime() + Cfg.EXTENSION;
    }

    public static String getCurrentTime() {

        return ZonedDateTime.now(ZoneId.of("Asia/Tokyo"))
                .format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
    }
}