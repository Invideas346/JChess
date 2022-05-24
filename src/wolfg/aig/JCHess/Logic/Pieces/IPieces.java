package wolfg.aig.JCHess.Logic.Pieces;

import wolfg.aig.JCHess.Logic.Util.Position;

import java.util.Collection;

public interface IPieces {
    Collection<Position> possibleMoves();
}
