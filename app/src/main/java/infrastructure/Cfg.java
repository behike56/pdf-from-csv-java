package infrastructure;

import java.awt.Color;
import java.io.File;

public class Cfg {

    /*
     * file settings
     */
    static final String CURRENT_PATH = new File(".").getAbsoluteFile().getParent();
    static final String CSV_FILE_PATH = "/app/csvdata/test.csv";
    static final String TEMPLATE_IMG_PATH = "/app/resource/bg.png";
    static final String FONT_PATH = "/app/resource/MPLUS1p-Medium.ttf";
    static final String OUTPUT_PATH = "/app/output/";
    static final String SEP = ".";
    static final String EXTENSION = ".pdf";

    /*
     * page settings
     */
    static final float RECT_X = 0f;
    static final float RECT_Y = 0f;
    static final Color FONT_COLOR = Color.BLUE;
    static final int FONT_SIZE = 15;

    /*
     * axis settings
     */
    static final float MANAGE_X = 0f;
    static final float MANAGE_Y = 0f;
    static final float DATETIME_YM_X = 0f;
    static final float DATETIME_YM_Y = 0f;
    static final float DELTA_Y = -0f;
    static final float BEGIN_Y = 0f;
    static final float COL1_X = 0f;
    static final float COL2_X = 0f;
    static final float COL3_X = 0f;
    static final float COL4_X = 0f;
    static final float COL5_X = 0f;
    static final float COL6_X = 0f;
    static final float COL7_X = 0f;
}
