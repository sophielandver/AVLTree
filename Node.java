public class Node {
	public int _data;
	public Node _left;
	public Node _right;
	public int _height;
	
	
	/**
	 * Constructor for Node. Sets _data to be the inputed integer and 
	 * sets _right to null and _left to null and _height to 0.
	 * @param int data
	 */
	public Node(int data)
	{
		_data = data;
		_left = null;
		_right = null;
		_height = 0;
	}

}
