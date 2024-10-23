package tests;

import main.TDisplay;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class TestTDisplay {

    @Test
    public void testTDisplay() {
        final int S = 30;
        int [][] table = new int[S][S];
        for (int i = 0; i < S; i++) {
            for (int j = 0; j < S; j++) {
                table[i][j] = 0;
            }
        }
        table[1][1] = 1;
        table[2][2] = -1;
        table[3][3] = 6;

        TDisplay display = new TDisplay(table);
        display.display(true);
        display.setTitle("Test TDisplay");

        display.CONFIG.setCaseSize(15);
        display.CONFIG.setDisplayValues(false);
        display.CONFIG.setDefaultColor(Color.BLUE);
        display.CONFIG.setDefaultTextColor(Color.YELLOW);
        display.CONFIG.addColor(6, Color.GREEN);
        display.CONFIG.addTextColor(6, Color.RED);



        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        display.fill(-1);
        display.fillZone(1,1, S-2, S-2, 1);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        display.fillLine(1, -1);
        display.fillColumn(18, -1);
        display.fillZone(new Rectangle(8, 8, 10, 15), -1);
        display.set(S-1, S-1, 0);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        display.randomize();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert display.isShowing();

    }


}
