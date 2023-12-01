import org.example.CalculateCalibration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateCalibrationTest {



    @Test
    void calculate(){
        CalculateCalibration calculateCalibration = new CalculateCalibration();
        String text = "1abc2\n" +
                "pqr3stu8vwx\n" +
                "a1b2c3d4e5f\n" +
                "treb7uchet";

        assertEquals(142, calculateCalibration.calculate(text));
    }

    @Test
    void calculateWithLettersToo(){
        CalculateCalibration calculateCalibration = new CalculateCalibration();
        String text = "two1nine\n" +
                "eightwothree\n" +
                "abcone2threexyz\n" +
                "xtwone3four\n" +
                "4nineeightseven2\n" +
                "zoneight234\n" +
                "7pqrstsixteen";

        assertEquals(281, calculateCalibration.calculateWithLettersToo(text));
    }
}
