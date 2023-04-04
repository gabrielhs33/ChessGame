
//this class set a compartment and rules of a chess match

package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {

    private final Board board;
    private int turn;
    private  Color currentPlayer;

    private final List<Piece> piecesOnTheBoard;
    private final List<Piece> capturedPieces;

    public ChessMatch(){

        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.WHITE;
        piecesOnTheBoard = new ArrayList<>();
        capturedPieces = new ArrayList<>();

        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessPiece[][] getPieces(){

         ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];

         for(int i=0; i< board.getRows();i++){
             for(int j=0;j< board.getColumns();j++){

                 mat[i][j]= (ChessPiece) board.piece(i, j);
             }
         }

         return mat;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){

        Position position = sourcePosition.toPosition();
        validSourcePosition(position);

        return board.piece(position).possibleMoves();
    }

    //move a piece from the source position to the target position
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){

        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();

        validSourcePosition(source);
        validTargetPosition(source,target);
        Piece capturedPiece = makeMove(source,target);

        nextTurn();

        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position sourcePosition, Position targetPositon){

        Piece p = board.removePiece(sourcePosition);
        Piece capturedPiece = board.removePiece(targetPositon);
        board.placePiece(p,targetPositon);

        if(capturedPiece != null){

            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        return  capturedPiece;
    }

    private void validSourcePosition(Position position){

        if(!board.thereIsAPiece(position)){

            throw new ChessExeption("There is no piece on sorce position");
        }

        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()){

            throw new ChessExeption("The chosen piece is not yours");
        }

        if(!board.piece(position).isThrereAnyPossibleMove()){

            throw new ChessExeption("There is no possible moves for the chosen piece");
        }
    }

    private void validTargetPosition(Position sourcePosition,Position targetPosition){

        if(!board.piece(sourcePosition).possibleMove(targetPosition)){

            throw new ChessExeption("The chosen piece can't move to target position");
        }
    }

    private void nextTurn(){

        turn++;
        currentPlayer = (currentPlayer == Color.WHITE)? Color.BLACK : Color.WHITE;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){

        board.placePiece(piece,new ChessPosition(column,row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    //method responsible for starting the game by placing the pieces on the board
    private void initialSetup(){

        placeNewPiece('d',1, new King(board, Color.WHITE) );
        placeNewPiece('e',8, new King(board, Color.BLACK) );

        placeNewPiece('a',8, new Rook(board, Color.BLACK) );
        placeNewPiece('h',8, new Rook(board, Color.BLACK) );
        placeNewPiece('a',1, new Rook(board, Color.WHITE) );
        placeNewPiece('h',1, new Rook(board, Color.WHITE) );
    }
}
