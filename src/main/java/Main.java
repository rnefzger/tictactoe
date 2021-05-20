import game.Game;
import game.Human;
import game.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final String VS_COM = "1";
    private static final String VS_HUMAN = "2";
    private static final String VS_COM_STR = "den Computer";
    private static final String VS_HUMAN_STR = "einen Gegenspieler";
    private static final String YES = "yes";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private static final String SPIELERANZAHL = ANSI_CYAN + "Bitte geben Sie die Anzahl der Spieler an. " +
            "Bei 1 wird ein Spiel gegen einen Computer eröffnet.\n" + ANSI_RESET +
            "HINWEIS: Es ist nur eine maximale Spieleranzahl von 2 Spielern möglich";
    private static final String ERROR_SPIELERANZAHL = ANSI_RED + "Diese Eingabe ist nicht möglich! " +
            "Bitte wählen Sie die Anzahl 1 oder 2 für ein gültiges Spiel!" + ANSI_RESET;
    private static final String ERROR_FALSE_INDEX = ANSI_RED + "Dieses Feld existiert nicht" + ANSI_RESET;
    private static final String INTRO = ANSI_CYAN + "Sie haben sich für ein Spiel gegen %s entschieden. " +
            "Die Felder sind der Reihe nach von 1 bis 9 durchnummeriert. " +
            "Zur Wahl eines Feldes geben Sie eine Zahl von 1 bis 9 ein. Viel Spaß!\n" + ANSI_RESET;
    private static final String CHOOSE = ANSI_CYAN + "Spieler %d: Wählen Sie ein Feld.\n" + ANSI_RESET;
    private static final String COM_TURN = ANSI_CYAN + "Der Computer ist an der Reihe." + ANSI_RESET;
    private static final String FIELD_ASSIGNED = ANSI_RED + "Dieses Feld ist bereits vergeben!" + ANSI_RESET;
    private static final String WIN = ANSI_BLUE + "Herzlichen Glückwunsch Spieler %d! Sie haben Gewonnen!\n" + ANSI_RESET;
    private static final String NO_WINNER = ANSI_BLUE + "Leider gibt es keinen Sieger." + ANSI_RESET;
    private static final String REPLAY = ANSI_CYAN + "Wollen Sie eine weitere Runde spielen?\n" +
            "Für ein ja geben Sie bitte yes ein, bei einer anderen Eingabe wird das Spiel beendet." + ANSI_RESET;

    private static boolean playable = true;
    private static final Scanner scanner = new Scanner(System.in);

    //TODO: Ausgabe zeigt noch Spieler 2 bei einem Spiel gegen den Computer an
    //TODO: Bei Eingabe eines Strings kommt eine Exception
    //Visible for testing
    public static void runGame(final String input, final boolean vsComputer) {
        System.out.printf(INTRO, input);

        Game game = new Game(vsComputer);
        System.out.println(game + "\n");

        while (game.getWinner().getId() == 2 && !game.gameOver()) {
            Player currentPlayer = game.getPlayers().get(game.getTurnCounter());
            int index = 0;
            boolean moved = false;

            try {
                if (currentPlayer instanceof Human) {
                    System.out.printf(CHOOSE, game.getTurnCounter() + 1);
                    index = scanner.nextInt();
                } else {
                    System.out.println(COM_TURN);
                }

                index = currentPlayer.turn(index);
                moved = game.conquer(index);

                if (!moved && currentPlayer instanceof Human) {
                    System.out.println(FIELD_ASSIGNED);
                }

            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println(ERROR_FALSE_INDEX);
            }

            System.out.println(game);
        }

        if (game.getWinner().getId() == 2) {
            System.out.println(NO_WINNER);
        } else {
            System.out.printf(WIN, game.getWinner().getId() + 1);
        }
    }

    //TODO: Nach Fehler wird nach einem neuem Spiel gefragt
    public static void main(String[] args) {
        while (playable) {
            System.out.println(SPIELERANZAHL);
            boolean error = false;
            String input = scanner.next();

            if (input.equals(VS_COM)) {
                runGame(VS_COM_STR, true);
            } else if (input.equals(VS_HUMAN)) {
                runGame(VS_HUMAN_STR, false);
            } else {
                System.out.println(ERROR_SPIELERANZAHL);
                error = true;
            }

            if (!error) {
                System.out.println(REPLAY);
                String replay = scanner.next();

                if (!replay.equals(YES)) {
                    playable = false;
                }
            }
        }
    }
}
