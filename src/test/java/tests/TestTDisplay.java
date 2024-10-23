package tests;

import main.TDisplay;
import org.junit.jupiter.api.Test;

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

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        display.fill(-1);
        display.fillZone(1,1, S-2, S-2, 1);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert display.isShowing();

    }


}
