package appliation;

//User Interface

import chess.ChessPiece;
import chess.Color;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printBoard(ChessPiece[][] pieces){

        System.out.println();

        for (int i=0;i<pieces.length;i++){

            System.out.print((8-i)+" ");

            for (int j =0; j< pieces.length;j++){

                printPiece(pieces[i][j]);
            }
            System.out.println();
        }

        System.out.println("   a   b   c   d   e   f   g   h ");
    }

    private static void printPiece(ChessPiece piece){

        if(piece==null){

            System.out.print(" - ");

        }else{

            if(piece.getColor()== Color.WHITE){

                System.out.print(" "+ANSI_WHITE + piece + ANSI_RESET+" ");
            }else{

                System.out.print(" "+ANSI_YELLOW + piece + ANSI_RESET+" ");
            }
        }

        System.out.print(" ");
    }
}