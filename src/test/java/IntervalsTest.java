import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntervalsTest {

    @Test
    void intervalConstructionOfMajorSecondAndCAscendingShouldReturnD() {
        String expected = "D";
        String[] input = new String[]{"M2", "C", "asc"};
        String result = Intervals.intervalConstruction(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalConstructionOfPerfectFifthAndBAscendingShouldReturnFSharp() {
        String expected = "F#";
        String[] input = new String[]{"P5", "B", "asc"};
        String result = Intervals.intervalConstruction(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalConstructionOfMinorSecondAndBFlatDescendingShouldReturnA() {
        String expected = "A";
        String[] input = new String[]{"m2", "Bb", "dsc"};
        String result = Intervals.intervalConstruction(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalConstructionOfMajorThirdAndCFlatDescendingShouldReturnADoubleFlat() {
        String expected = "Abb";
        String[] input = new String[]{"M3", "Cb", "dsc"};
        String result = Intervals.intervalConstruction(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalConstructionOfPerfectFourthAndGSharpDescendingShouldReturnDSharp() {
        String expected = "D#";
        String[] input = new String[]{"P4", "G#", "dsc"};
        String result = Intervals.intervalConstruction(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalConstructionOfMinorThirdAndBDescendingShouldReturnGSharp() {
        String expected = "G#";
        String[] input = new String[]{"m3", "B", "dsc"};
        String result = Intervals.intervalConstruction(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalConstructionOfMinorSecondAndFFlatAscendingShouldReturnGDoubleFlat() {
        String expected = "Gbb";
        String[] input = new String[]{"m2", "Fb", "asc"};
        String result = Intervals.intervalConstruction(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalConstructionOfMajorSecondAndESharpDescendingShouldReturnDSharp() {
        String expected = "D#";
        String[] input = new String[]{"M2", "E#", "dsc"};
        String result = Intervals.intervalConstruction(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalConstructionOfPerfectFourthAndEDescendingShouldReturnB() {
        String expected = "B";
        String[] input = new String[]{"P4", "E", "dsc"};
        String result = Intervals.intervalConstruction(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalConstructionOfMinorSecondAndDSharpAscendingShouldReturnE() {
        String expected = "E";
        String[] input = new String[]{"m2", "D#", "asc"};
        String result = Intervals.intervalConstruction(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalConstructionOfMajorSeventhAndGAscendingShouldReturnFSharp() {
        String expected = "F#";
        String[] input = new String[]{"M7", "G", "asc"};
        String result = Intervals.intervalConstruction(input);

        assertEquals(expected, result);
    }
}