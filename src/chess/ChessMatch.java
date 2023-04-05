
//this class set a compartment and rules of a chess match

package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

    private final Board board;
    private int turn;
    private  Color currentPlayer;
    private boolean check;
    private  boolean checkMate;

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

    public boolean getCheck(){

        return check;
    }

    public boolean getChecMate(){

        return checkMate;
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

        //checks if the current player has checked himself
        if(testCheck(currentPlayer)){

            undoMove(source,target,capturedPiece);
            throw new ChessExeption("You can't put yourself in check");
        }

        check = testCheck(opponent(currentPlayer));

        if(testCheckMate(opponent(currentPlayer))){

            checkMate = true;
        }else{

            nextTurn();
        }

        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position sourcePosition, Position targetPositon){

        ChessPiece p = (ChessPiece) board.removePiece(sourcePosition);
        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(targetPositon);
        board.placePiece(p,targetPositon);

        if(capturedPiece != null){

            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        return  capturedPiece;
    }

    //method to undo the movement performed
    private void undoMove(Position source, Position target, Piece capturedPiece){

        ChessPiece p = (ChessPiece) board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p,source);

        // if a piece was captured in the move it will be returned
        if(capturedPiece != null){

            board.placePiece(capturedPiece,target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
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

    //returns the color of the current player's opponent
    private Color opponent(Color color){

        return (color == Color.WHITE)? Color.BLACK : Color.WHITE;
    }

    //reposable by going through the matrix and finding a king of a certain color
    private ChessPiece King(Color color){

        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).toList();

        for(Piece p : list){

            if(p instanceof King){

                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("There is no " + color + "King on the board");
    }

    // test whether a king of a certain color is in check
    private boolean testCheck(Color color){

        Position kingPosition = King(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).toList();

        for(Piece p : opponentPieces){

            boolean[][] mat = p.possibleMoves();
            if(mat[kingPosition.getRow()][kingPosition.getColumn()]){

                return true;
            }
        }

        return false;
    }

    private boolean testCheckMate(Color color){

        if(!testCheck(color)) {
            return false;
        }
        List<Piece> list =  piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).toList();

        for(Piece p : list){

            boolean[][] mat = p.possibleMoves();

            for(int i=0;i< board.getRows();i++){
                for(int j=0; j< board.getColumns();j++){

                    if(mat[i][j]){

                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i,j);

                        Piece capturedPiece = makeMove(source,target);

                        boolean testCheck = testCheck(color);
                        undoMove(source,target,capturedPiece);

                        if(!testCheck){

                            return false;
                        }
                    }
                }
            }

        }
        return true;
    }

    //adds a piece to the board
    private void placeNewPiece(char column, int row, ChessPiece piece){

        board.placePiece(piece,new ChessPosition(column,row).toPosition());
        piecesOnTheBoard.add(piece);
    }



    //method responsible for starting the game by placing the pieces on the board
    private void initialSetup(){

        placeNewPiece('e',1, new King(board, Color.WHITE) );
        placeNewPiece('d',1, new Rook(board, Color.WHITE) );
        placeNewPiece('h',7, new Rook(board, Color.WHITE) );

        placeNewPiece('a',8, new King(board, Color.BLACK) );
        placeNewPiece('b',8, new Rook(board, Color.BLACK) );


    }
}
