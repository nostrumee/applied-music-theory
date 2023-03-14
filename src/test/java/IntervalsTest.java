import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntervalsTest {

    private static final String ILLEGAL_NUMBER_OF_ELEMENTS = "Illegal number of elements in input array";
    private static final String CANNOT_IDENTIFY_THE_INTERVAL = "Cannot identify the interval";

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

    @Test
    void intervalIdentificationOfCAndDShouldReturnMajorSecond() {
        String expected = "M2";
        String[] input = new String[]{"C", "D"};
        String result = Intervals.intervalIdentification(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalIdentificationOfBAndFSharpAscendingShouldReturnPerfectFifth() {
        String expected = "P5";
        String[] input = new String[]{"B", "F#", "asc"};
        String result = Intervals.intervalIdentification(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalIdentificationOfFFlatAndGDoubleFlatShouldReturnMinorFifth() {
        String expected = "m2";
        String[] input = new String[]{"Fb", "Gbb"};
        String result = Intervals.intervalIdentification(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalIdentificationOfGAndFSharpAscendingShouldReturnMajorSeventh() {
        String expected = "M7";
        String[] input = new String[]{"G", "F#", "asc"};
        String result = Intervals.intervalIdentification(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalIdentificationOfBFlatAndADescendingShouldReturnMinorSecond() {
        String expected = "m2";
        String[] input = new String[]{"Bb", "A", "dsc"};
        String result = Intervals.intervalIdentification(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalIdentificationOfCFlatAndADoubleFlatDescendingShouldReturnMajorThird() {
        String expected = "M3";
        String[] input = new String[]{"Cb", "Abb", "dsc"};
        String result = Intervals.intervalIdentification(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalIdentificationOfGSharpAndDSharpDescendingShouldReturnPerfectFourth() {
        String expected = "P4";
        String[] input = new String[]{"G#", "D#", "dsc"};
        String result = Intervals.intervalIdentification(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalIdentificationOfEAndBDescendingShouldReturnPerfectFourth() {
        String expected = "P4";
        String[] input = new String[]{"E", "B", "dsc"};
        String result = Intervals.intervalIdentification(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalIdentificationOfESharpAndDSharpDescendingShouldReturnMajorSecond() {
        String expected = "M2";
        String[] input = new String[]{"E#", "D#", "dsc"};
        String result = Intervals.intervalIdentification(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalIdentificationOfBAndGSharpDescendingShouldReturnMinorThird() {
        String expected = "m3";
        String[] input = new String[]{"B", "G#", "dsc"};
        String result = Intervals.intervalIdentification(input);

        assertEquals(expected, result);
    }

    @Test
    void intervalConstructionWhenNumberOfElementsLessThanTwoOrGreaterThanThreeShouldThrowException() {
        String[] fourElementsInput = new String[]{"m2", "D#", "asc", "dummy"};
        var exception = assertThrows(IllegalArgumentException.class,
                () -> Intervals.intervalConstruction(fourElementsInput));
        assertEquals(ILLEGAL_NUMBER_OF_ELEMENTS, exception.getMessage());

        String[] oneElementInput = new String[]{"dummy"};
        exception = assertThrows(IllegalArgumentException.class,
                () -> Intervals.intervalConstruction(oneElementInput));
        assertEquals(ILLEGAL_NUMBER_OF_ELEMENTS, exception.getMessage());
    }

    @Test
    void intervalConstructionWithInvalidIntervalShouldThrowException() {
        String[] input = new String[]{"c2", "D#", "asc"};
        var exception = assertThrows(IllegalArgumentException.class,
                () -> Intervals.intervalConstruction(input));
        assertEquals(CANNOT_IDENTIFY_THE_INTERVAL, exception.getMessage());
    }
}