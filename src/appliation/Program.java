package appliation;

import boardgame.Position;
import chess.ChessExeption;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args){

        ChessMatch chessMatch = new ChessMatch();
        Scanner sc = new Scanner(System.in);

        while (true){

            try {
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces());
                System.out.println("Source: ");
                ChessPosition source = UI.readChessPossition(sc);

                System.out.println("Target: ");
                ChessPosition target = UI.readChessPossition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

            }catch (ChessExeption | InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();

            }
        }
    }
}
