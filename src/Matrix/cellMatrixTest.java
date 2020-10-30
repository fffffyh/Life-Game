package Matrix;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class cellMatrixTest {

    private int[][] testMatrix;    //测试矩阵
	private cellMatrix cellmatrix=new cellMatrix(3,3);
	
	@Before
	public void setUp() throws Exception {
		testMatrix = new int[][] {
			{1, 1, 1}, 
			{1, 1, 1},
			{1, 1, 1}
		};
		cellmatrix.setMatrix(testMatrix);
	}

	@Test
	public void testmatrixflash() {
		//fail("尚未实现");
		//第一次更新
		cellmatrix.matrixflash(); 
		testMatrix = new int[][] {
			{1, 0, 1},
			{0, 0, 0},
			{1, 0, 1}
		};
		assertArrayEquals(testMatrix,cellmatrix.getMatrix());
		
		//第二次更新
		cellmatrix.matrixflash();
		testMatrix = new int[][] {
			{0, 0, 0},
			{0, 0, 0},
			{0, 0, 0}
		};
		assertArrayEquals(testMatrix,cellmatrix.getMatrix());
	}

	@Test
	public void testEnvironemnt() {
		assertEquals(3,cellmatrix.environment(0, 0));
		assertEquals(5,cellmatrix.environment(0, 1));
		assertEquals(3,cellmatrix.environment(0, 2));
		assertEquals(5,cellmatrix.environment(1, 0));
		assertEquals(8,cellmatrix.environment(1, 1));
		assertEquals(5,cellmatrix.environment(1, 2));
		assertEquals(3,cellmatrix.environment(2, 0));
		assertEquals(5,cellmatrix.environment(2, 1));
		assertEquals(3,cellmatrix.environment(2, 2));
	}
}