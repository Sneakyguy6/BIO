package britishOlympiad.y2018.q3;

import java.util.ArrayList;
import java.util.List;

public class Matrix
{
	private List<MatrixPair> adjecencyMatrix;
	public Matrix(int firstSerial)
	{
		this.adjecencyMatrix = new ArrayList<MatrixPair>();
		this.adjecencyMatrix.add(new MatrixPair(firstSerial, this.adjecencyMatrix.size()+1));
	}
	public void addAdjacency(int serial, int adjacentSerial)
	{
		int serialIndex = this.getSerialIndex(serial); //this should exist
		int adjacentSerialIndex = -1; //this shouldn't necessarily exist
		while(adjacentSerialIndex == -1)
			if((adjacentSerialIndex = this.getSerialIndex(adjacentSerial)) == -1)
				this.addNewSerial(adjacentSerial);
		this.adjecencyMatrix.get(serialIndex).changeState(adjacentSerialIndex);
		this.adjecencyMatrix.get(adjacentSerialIndex).changeState(serialIndex);
	}
	public int getSerialIndex(int serial)
	{
		for(int i = 0; i < this.adjecencyMatrix.size(); i++)
			if(this.adjecencyMatrix.get(i).getSerial() == serial)
				return i;
		return -1; //-1 indicates serial was not found
	}
	public void addNewSerial(int serial)
	{
		this.adjecencyMatrix.add(new MatrixPair(serial, this.adjecencyMatrix.size()));
		for(MatrixPair i : this.adjecencyMatrix)
			i.addState();
	}
	@Override
	public String toString()
	{
		String out = "";
		for(MatrixPair i : this.adjecencyMatrix)
			out += i.toString() + "\n";
		return out;
	}
	public void print()
	{
		for(MatrixPair i : this.adjecencyMatrix)
			System.out.println(i);
	}
}
class MatrixPair
{
	private int serial;
	private List<Integer> adjacencies;
	public MatrixPair(int serial, int newSizeOfMatrix)
	{
		this.serial = serial;
		this.adjacencies = new ArrayList<Integer>();
		for(int i = 0; i < newSizeOfMatrix; i++)
			this.adjacencies.add(0);
	}
	public void changeState(int index)
	{
		if(this.adjacencies.get(index) == 1)
			this.adjacencies.set(index, 0);
		else this.adjacencies.set(index, 1);
	}
	public void addState()
	{
		this.adjacencies.add(0);
	}
	public int getState(int index)
	{
		return this.adjacencies.get(index);
	}
	public int getSerial() {
		return this.serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public List<Integer> getAdjacencies() {
		return this.adjacencies;
	}
	public void setAdjacencies(List<Integer> adjacencies) {
		this.adjacencies = adjacencies;
	}
	@Override
	public String toString()
	{
		String adjacencies = "";
		for(int i : this.adjacencies)
			adjacencies += i + " ";
		return this.serial + ": " + adjacencies; 
	}
}