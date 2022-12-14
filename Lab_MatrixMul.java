/*64050224*/
import java.util.Arrays;
public class Lab_MatrixMul {
	public static void main(String[] args) {
		int[][] inputA = { { 5, 6, 7 }, { 4, 8, 9 } };
		int[][] inputB = { { 6, 4 }, { 5, 7 }, { 1, 1 } };
		MyData matA = new MyData(inputA);
		MyData matB = new MyData(inputB);
		int matC_r = matA.data.length;
		int matC_c = matB.data[0].length;
		MyData matC = new MyData(matC_r, matC_c);
		//<Q4>
		Thread[][] thread = new Thread[matC_r][matC_c];
		for(int i = 0; i < matC_r; i++){
			for(int j = 0; j < matC_c; j++){
				thread[i][j] = new Thread(new MatrixMulThread(i, j, matA, matB, matC));
				thread[i][j].start();
			}
		}
		//</Q4>
	     	//<Q5>
		try{
			for(Thread[] eachRow : thread){
				for(Thread each : eachRow){
					each.join();
				}
			}
		}catch(Exception e){
			System.out.println(e);
		}
		//</Q5>
	     	matC.show();
	}
}
class MatrixMulThread implements Runnable {
	int processing_row;
	int processing_col;
	MyData datA;
	MyData datB;
	MyData datC;
	MatrixMulThread(int tRow, int tCol, MyData a, MyData b, MyData c) {
		//<Q1>
		processing_row = tRow;
		processing_col = tCol;
		datA = a;
		datB = b;
		datC = c;
		//</Q1>
	}
	/*<Q2>*/public/*</Q2>*/ void run() {
		//<Q3>
		System.out.printf("perform sum of multiplication of assigned row: %d and col: %d%n", processing_row, processing_col);
		int sum = 0;
		for(int i = 0; i < datA.data[0].length; i++){
			sum += datA.data[processing_row][i] * datB.data[i][processing_col];
		}
		datC.data[processing_row][processing_col] = sum;
		//</Q3>
	}
}
class MyData {
	int[][] data;
	MyData(int[][] m) {
	       	data = m; 
	}
	MyData(int r, int c) {
		data = new int[r][c];
		for (int[] aRow : data){
			Arrays.fill(aRow, 9);
		}
	}
	void show() {
		System.out.println(Arrays.deepToString(data));
	}
}
