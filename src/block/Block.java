package block;

import interactions.Interaction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The general type of object which may be placed on the board.
 *
 * @author Filip Mazurek, Daniel Chai, Aninda Manocha
 */
public abstract class Block implements ShallowBlock {
	private BlockType myBlockType;
	private String myName;
	private double myIdentifier;
	private int myRow;
	private int myCol;
	private boolean walkableStatus;
	private List<Interaction> myInteractions;

	public Block(String name, BlockType blockType, double id, int row, int col) {
        myBlockType = blockType;
	    myName = name;
	    myIdentifier = id;
		myRow = row;
		myCol = col;
		myInteractions = new ArrayList<Interaction>();
	}

	/*****GETTERS*****/

	public BlockType getBlockType() {
		return myBlockType;
	}

	public String getName() {
		return myName;
	}

	public double getIdentifier() {
		return myIdentifier;
	}

	public int getRow() {
		return myRow;
	}

	public int getCol() {
		return myCol;
	}

	public boolean isWalkable() {
		return walkableStatus;
	}

	//Interactions methods
	public List<Interaction> getInteractions() {
		return Collections.unmodifiableList(myInteractions);
	}

	protected boolean addInteraction(Interaction someInteraction) {
		return myInteractions.add(someInteraction);
	}

	protected void clearInteractions() {
		myInteractions.clear();
	}

	protected boolean removeInteraction(Interaction someInteraction) {
		return myInteractions.remove(someInteraction);
	}

	/*****SETTERS******/

	protected void setBlockType(BlockType blockType) {
		myBlockType = blockType;
	}

	protected void setWalkableStatus(boolean status) {
		walkableStatus = status;
	}

}
