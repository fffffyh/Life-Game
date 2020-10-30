package Matrix;

public class cellMatrix {
	private int width = 10;// 矩形宽度（横），默认10
	private int length = 10;// 矩形长度(竖)，默认10
	private int flashTime = 200;// 默认刷新时间200ms
	private int[][] matrix;
	private boolean state = false;// 细胞死活，0-死，1-活，默认0

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

	// 初始化矩阵
	public void matrixinit() {
		matrix = new int[length][width];
		for (int i = 0; i < length; i++)
			for (int j = 0; j < width; j++) {
				matrix[i][j] = (int) (Math.random() + 0.2);
			}
	} 

	// 更新矩阵
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
		int liveCell = 0;                     // 周围活细胞个数
		if (x - 1 >= 0 && y - 1 >= 0)
			liveCell += matrix[x - 1][y - 1]; // 左上角
		if (x - 1 >= 0)
			liveCell += matrix[x - 1][y];    // 上
		if (x - 1 >= 0 && y + 1 < width)
			liveCell += matrix[x - 1][y + 1]; // 右上角
		if (y - 1 >= 0)
			liveCell += matrix[x][y - 1];    // 左
		if (y + 1 < width) 
			liveCell += matrix[x][y + 1];    // 右
		if (x + 1 < length && y - 1 >= 0)
			liveCell += matrix[x + 1][y - 1]; // 左下角
		if (x + 1 < length)
			liveCell += matrix[x + 1][y];    // 下
		if (x + 1 < length && y + 1 < width)
			liveCell += matrix[x + 1][y + 1]; // 右下角
		return liveCell;
	}

}
