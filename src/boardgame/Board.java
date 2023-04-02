package boardgame;

public class Board {

    private final int rows;
    private final int columns;

    private Piece[][] pieces;

    public Board( int rows, int columns){

        if(rows<1 || columns< 1){

            throw new BoardExeption("Error creating board!");
        }

        this.rows=rows;
        this.columns=columns;

        pieces = new Piece[rows][columns];
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public Piece piece(int row, int column){

        if(!positionExists(row, column)){

            throw new BoardExeption("Position not on the board!");
        }

        return pieces[row][column];
    }

    public Piece piece(Position position){

        if(!positionExists(position)){

            throw new BoardExeption("Position not on the board!");
        }

        return pieces[position.getRow()][position.getColumn()];
    }

    //method responsible for inserting the piece in a position on the board
    public void placePiece(Piece piece, Position position){

        if(thereIsAPiece(position)) {
            throw new BoardExeption("There is already piece on position " + position);
        }

        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position){

        if(!positionExists(position)){

            throw new BoardExeption("Position not on the board !");
        }
        if(piece(position)==null){

            return null;
        }

        Piece aux = piece(position);
        aux.position=null;
        pieces[position.getRow()][position.getColumn()]=null;

        return aux;
    }

    //auxiliar method
    private boolean positionExists(int row, int column){

        return row>=0 && row < rows && column>=0 && column < columns;
    }

    //method to check if the position exists
    public boolean positionExists(Position position){

        return positionExists(position.getRow(), position.getColumn());
    }

    //method to check if a piece exists in position
    public boolean thereIsAPiece(Position position){

        if(!positionExists(position)){

            throw new BoardExeption("Position not on the board!");
        }
        return piece(position) != null;
    }




}
