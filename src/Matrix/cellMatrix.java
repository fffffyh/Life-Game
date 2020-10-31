package Matrix;

public class cellMatrix {
	private int width = 10;// ���ο�ȣ��ᣩ��Ĭ��10
	private int length = 10;// ���γ���(��)��Ĭ��10
	private int flashTime = 200;// Ĭ��ˢ��ʱ��200ms
	private int[][] matrix;
	private boolean state = false;// ϸ�����0-����1-�Ĭ��0

	public cellMatrix(int length, int width)// ,int flashTime,int[][] matrix)
	{
		this.width = width;
		this.length = length;
		matrix = new int[length][width];

	}

	public cellMatrix(int width, int length, int flashTime, int[][] matrix) {
		this.width = width;
		this.length = length;
		this.flashTime = flashTime;
		this.matrix = matrix;
	}

	// ��ʼ������
	public void matrixinit() {
		matrix = new int[length][width];
		for (int i = 0; i < length; i++)
			for (int j = 0; j < width; j++) {
				matrix[i][j] = (int) (Math.random() + 0.2);
			}
	} 

	// ���¾���
	public void matrixflash() {
		int[][] Nmatrix = new int[length][width];
		for (int i = 0; i < length; i++)
			for (int j = 0; j < width; j++) {
				Nmatrix[i][j] = 0;
				if (environment(i, j) == 3)
					Nmatrix[i][j] = 1;
				else if (environment(i, j) == 2)
					Nmatrix[i][j] = matrix[i][j];

			}
		matrix = Nmatrix;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int environment(int x, int y) {
		int liveCell = 0;                     // ��Χ��ϸ������
		if (x - 1 >= 0 && y - 1 >= 0)
			liveCell += matrix[x - 1][y - 1]; // ���Ͻ�
		if (x - 1 >= 0)
			liveCell += matrix[x - 1][y];    // ��
		if (x - 1 >= 0 && y + 1 < width)
			liveCell += matrix[x - 1][y + 1]; // ���Ͻ�
		if (y - 1 >= 0)
			liveCell += matrix[x][y - 1];    // ��
		if (y + 1 < width) 
			liveCell += matrix[x][y + 1];    // ��
		if (x + 1 < length && y - 1 >= 0)
			liveCell += matrix[x + 1][y - 1]; // ���½�
		if (x + 1 < length)
			liveCell += matrix[x + 1][y];    // ��
		if (x + 1 < length && y + 1 < width)
			liveCell += matrix[x + 1][y + 1]; // ���½�
		return liveCell;
	}

}
