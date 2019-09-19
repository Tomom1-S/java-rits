package ch01.ex16;

import java.io.FileInputStream;
import java.io.IOException;

public class MyUtilities {
	public double[] getDataSet(String setName) throws BadDataSetException {
		String file = setName + ".dset";
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			return readDataSet(in);
		} catch (IOException e) {
			throw new BadDataSetException();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				; // 無視：データの読み込みは成功しているか、あるいは、
					// BadDataSetException をスローしている
			}
		}
	}

	private double[] readDataSet(FileInputStream in) {
		// TODO Auto-generated method stub
		return null;
	}
}
