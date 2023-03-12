import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Intervals {

    private static final Map<String, Map<String, Integer>> intervals;
    private static final Map<String, Map<String, Integer>> notes;
    private static final Map<String, Integer> accidentals;
    private static final String DEGREE = "degree";
    private static final String SEMITONES = "semitones";
    private static final int NUM_OF_DEGREES = 7;
    private static final int NUM_OF_SEMITONES = 12;

    private static boolean ascending = true;

    static {
        intervals = new HashMap<>();
        intervals.put("m2", Map.ofEntries(
                Map.entry(DEGREE, 2),
                Map.entry(SEMITONES, 1)
        ));
        intervals.put("M2", Map.ofEntries(
                Map.entry(DEGREE, 2),
                Map.entry(SEMITONES, 2)
        ));
        intervals.put("m3", Map.ofEntries(
                Map.entry(DEGREE, 3),
                Map.entry(SEMITONES, 3)
        ));
        intervals.put("M3", Map.ofEntries(
                Map.entry(DEGREE, 3),
                Map.entry(SEMITONES, 4)
        ));
        intervals.put("P4", Map.ofEntries(
                Map.entry(DEGREE, 4),
                Map.entry(SEMITONES, 5)
        ));
        intervals.put("P5", Map.ofEntries(
                Map.entry(DEGREE, 5),
                Map.entry(SEMITONES, 7)
        ));
        intervals.put("m6", Map.ofEntries(
                Map.entry(DEGREE, 6),
                Map.entry(SEMITONES, 8)
        ));
        intervals.put("M6", Map.ofEntries(
                Map.entry(DEGREE, 6),
                Map.entry(SEMITONES, 9)
        ));
        intervals.put("m7", Map.ofEntries(
                Map.entry(DEGREE, 7),
                Map.entry(SEMITONES, 10)
        ));
        intervals.put("M7", Map.ofEntries(
                Map.entry(DEGREE, 7),
                Map.entry(SEMITONES, 11)
        ));
        intervals.put("P8", Map.ofEntries(
                Map.entry(DEGREE, 8),
                Map.entry(SEMITONES, 12)
        ));

        notes = new HashMap<>();
        notes.put("C", Map.ofEntries(
                Map.entry(DEGREE, 1),
                Map.entry(SEMITONES, 1)
        ));
        notes.put("D", Map.ofEntries(
                Map.entry(DEGREE, 2),
                Map.entry(SEMITONES, 3)
        ));
        notes.put("E", Map.ofEntries(
                Map.entry(DEGREE, 3),
                Map.entry(SEMITONES, 5)
        ));
        notes.put("F", Map.ofEntries(
                Map.entry(DEGREE, 4),
                Map.entry(SEMITONES, 6)
        ));
        notes.put("G", Map.ofEntries(
                Map.entry(DEGREE, 5),
                Map.entry(SEMITONES, 8)
        ));
        notes.put("A", Map.ofEntries(
                Map.entry(DEGREE, 6),
                Map.entry(SEMITONES, 10)
        ));
        notes.put("B", Map.ofEntries(
                Map.entry(DEGREE, 7),
                Map.entry(SEMITONES, 12)
        ));

        accidentals = Map.ofEntries(
                Map.entry("##", 2),
                Map.entry("#", 1),
                Map.entry("", 0),
                Map.entry("b", -1),
                Map.entry("bb", -2)
        );

    }

    public static String intervalConstruction(String[] args) {
        validateIntervalConstructionInput(args);

        String interval = args[0];
        String note = args[1];

        int intervalDegree = getDegreeOfInterval(interval);
        int intervalSemitones = getSemitonesOfInterval(interval);
        int noteDegree = getDegreeOfNote(note);
        int noteSemitones = getSemitonesOfNote(note);

        int resultDegree;
        int resultSemitones;
        if (ascending) {
            resultDegree = getDegreeSummary(noteDegree, intervalDegree);
            resultSemitones = getSemitonesSummary(noteSemitones, intervalSemitones);
        } else {
            resultDegree = getDegreeDifference(noteDegree, intervalDegree);
            resultSemitones = getSemitonesDifference(noteSemitones, intervalSemitones);
        }

        return getNoteByDegreeAndSemitones(resultDegree, resultSemitones);
    }

    public static String intervalIdentification(String[] args) {
        validateIntervalIdentificationInput(args);

        String startNote = args[0];
        String endNote = args[1];

        int startNoteDegree = getDegreeOfNote(startNote);
        int startNoteSemitones = getSemitonesOfNote(startNote);
        int endNoteDegree = getDegreeOfNote(endNote);
        int endNoteSemitones = getSemitonesOfNote(endNote);

        int resultDegree;
        int resultSemitones;
        if (ascending) {
            resultDegree = getDegreeDifference(endNoteDegree, startNoteDegree);
            resultSemitones = getSemitonesDifference(endNoteSemitones, startNoteSemitones);
        } else {
            resultDegree = getDegreeDifference(startNoteDegree, endNoteDegree);
            resultSemitones = getSemitonesDifference(startNoteSemitones, endNoteSemitones);
        }

        return getIntervalByDegreeAndSemitones(resultDegree, resultSemitones);
    }

    private static int getDegreeDifference(int firstDegree, int secondDegree) {
        int result = firstDegree - secondDegree + 1;
        return result > 0 ? result : NUM_OF_DEGREES + result;
    }

    private static int getSemitonesDifference(int firstSemitones, int secondSemitones) {
        int result = firstSemitones - secondSemitones;
        return result > 0 ? result : NUM_OF_SEMITONES + result;
    }

    private static int getDegreeSummary(int firstDegree, int secondDegree) {
        int result = firstDegree + secondDegree - 1;
        return result <= NUM_OF_DEGREES ? result : result % NUM_OF_DEGREES;
    }

    private static int getSemitonesSummary(int firstSemitones, int secondSemitones) {
        int result = firstSemitones + secondSemitones;
        return result <= NUM_OF_SEMITONES ? result : result % NUM_OF_SEMITONES;
    }
    
    public static Integer getDegreeOfNote(String note) {
        String baseNote = note.length() == 1 ? note : String.valueOf(note.charAt(0));
        return notes.get(baseNote).get(DEGREE);
    }

    private static Integer getSemitonesOfNote(String note) {
        String baseNote = note.length() == 1 ? note : String.valueOf(note.charAt(0));
        int baseNoteSemitones = notes.get(baseNote).get(SEMITONES);

        String accidental = note.substring(1);
        int accidentalSemitones = getSemitonesOfAccidental(accidental);

        return baseNoteSemitones + accidentalSemitones;
    }

    private static int getSemitonesOfAccidental(String accidental) {
        return accidentals.get(accidental);
    }

    private static Integer getDegreeOfInterval(String interval) {
        return intervals.get(interval).get(DEGREE);
    }

    private static Integer getSemitonesOfInterval(String interval) {
        return intervals.get(interval).get(SEMITONES);
    }

    public static String getNoteByDegreeAndSemitones(int degree, int semitones) {
        String baseNote = getNoteByDegree(degree);
        int baseNoteSemitones = getSemitonesOfNote(baseNote);

        int accidentalSemitones = semitones - baseNoteSemitones;
        String accidental = getAccidentalBySemitones(accidentalSemitones);

        return baseNote + accidental;
    }

    public static String getNoteByDegree(int degree) {
        return notes.keySet()
                .stream()
                .filter(note -> getDegreeOfNote(note) == degree)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("there is no such note"));
    }

    private static String getAccidentalBySemitones(int semitones) {
        return accidentals.keySet()
                .stream()
                .filter(accidental -> getSemitonesOfAccidental(accidental) == semitones)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("there is no such accidental"));
    }

    private static String getIntervalByDegreeAndSemitones(int degree, int semitones) {
        return intervals.keySet()
                .stream()
                .filter(interval -> getDegreeOfInterval(interval) == degree)
                .filter(interval -> getSemitonesOfInterval(interval) == semitones)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Cannot identify the interval"));
    }

    private static void validateIntervalConstructionInput(String[] input) {
        validateNumberOfElements(input);

        String interval = input[0];
        String note = input[1];

        validateInterval(interval);
        validateIntervalConstructionNote(note);

        if (input.length == 3) {
            validateOrder(input[2]);
        }
    }

    private static void validateIntervalIdentificationInput(String[] input) {
        validateNumberOfElements(input);

        String startNote = input[0];
        String endNote = input[1];

        validateIntervalIdentificationNote(startNote);
        validateIntervalIdentificationNote(endNote);

        if (input.length == 3) {
            validateOrder(input[2]);
        }
    }

    public static void validateIntervalIdentificationNote(String note) {
        String notes = "^[A-G]";
        String accidentals = "(#|##|b|bb)?$";
        String regex = notes + accidentals;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(note);

        if (!matcher.find()) {
            throw new IllegalArgumentException("incorrect note");
        }
    }

    private static void validateNumberOfElements(String[] input) {
        if (input.length < 2 || input.length > 3) {
            throw new IllegalArgumentException("Illegal number of elements in input array");
        }
    }

    private static void validateIntervalConstructionNote(String note) {
        String notes = "^[A-G]";
        String accidentals = "[#b]?$";
        String regex = notes + accidentals;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(note);

        if (!matcher.find()) {
            throw new IllegalArgumentException("incorrect note");
        }
    }

    private static void validateInterval(String interval) {
        if (!intervals.containsKey(interval)) {
            throw new IllegalArgumentException("incorrect interval");
        }
    }

    private static void validateOrder(String order) {
        switch (order) {
            case "asc":
                break;
            case "dsc":
                ascending = false;
                break;
            default:
                throw new IllegalArgumentException("incorrect order");
        }
    }
}