package wolfg.aig.JCHess.Logic.Pieces;

import wolfg.aig.JCHess.Logic.Util.Position;

import java.util.ArrayList;
import java.util.Collection;

public class Pawn extends Piece{
    private boolean alreadyMoved = false;
    @Override
    public Collection<Position> possibleMoves() {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        possibleMoves.add(new Position(position.x(), position.y() + 1));
        if(alreadyMoved)
            possibleMoves.add(new Position(position.x(), position.y() + 2));
        return possibleMoves;
    }

    public boolean isAlreadyMoved() {
        return alreadyMoved;
    }
}
