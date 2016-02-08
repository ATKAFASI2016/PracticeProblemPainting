package atkafasi.model.data;

public class DataPart {

	private int startX;
	private int startY;
	private boolean[][] data;
	private int xLen;
	private int yLen;
	private int hashCount;

	public DataPart(int startX, int startY, boolean[][] fullData, int xLen, int yLen) {
		this.hashCount = 0;
		this.startX = startX;
		this.startY = startY;
		this.xLen = xLen;
		this.yLen = yLen;
		this.data = new boolean[yLen][xLen];
		for (int i = 0; i < yLen; i++) {
			for (int j = 0; j < xLen; j++) {
				this.data[i][j] = fullData[startY + i][startX + j];
				if (this.data[i][j]) {
					this.hashCount++;
				}
			}
		}
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public boolean[][] getData() {
		return data;
	}

	public int getxLen() {
		return xLen;
	}

	public int getyLen() {
		return yLen;
	}

	public int getHashCount() {
		return hashCount;
	}

}
