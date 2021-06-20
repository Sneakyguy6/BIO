package britishOlympiad.y2018.q3;

public class Question3
{
	private Matrix matrix;
	public Question3(int serial)
	{
		this.matrix = new Matrix(serial);
		System.out.println(this.matrix);
		this.matrix.addNewSerial(142635);
		System.out.println(this.matrix);
		this.matrix.addNewSerial(412635);
		System.out.println(this.matrix);
		this.matrix.addAdjacency(142635, 412635);
		System.out.println(this.matrix);
	}
}
