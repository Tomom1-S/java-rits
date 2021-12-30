package ch20.ex07;

import java.io.*;

public class Attr {
    private String name;
    private double[] values;

    public Attr(String name) {
        this.name = name;
    }

    public Attr(String name, double[] values) {
        this.name = name;
        this.values = values;
    }

    public Attr(DataInputStream input) throws IOException {
        name = "test";
        values = readData(input);
    }

    public String getName() {
        return name;
    }

    public double[] getValues() {
        return values;
    }

    public double[] setValues(double[] newValues) {
        double[] oldVal = values;
        values = newValues;
        return oldVal;
    }

    public String toString() {
        String str = name + " =";
        for (double v : values) {
            str += " " + v;
        }
        return str;
    }

    public void writeData(String file) throws IOException {
        final String ls = System.lineSeparator();
        OutputStream fout = new FileOutputStream(file);
        DataOutputStream out = new DataOutputStream(fout);
        out.writeBytes(String.valueOf(values.length));  // values の長さを保存
        out.writeBytes(ls);
        out.writeBytes(name);
        out.writeBytes(ls);
        for (double v : values) {
            out.writeBytes(String.valueOf(v));
            out.writeBytes(ls);
        }
        out.close();
    }

    public double[] readData(DataInputStream in) throws IOException {
        // ファイルの長さに合わせたい
        double[] data = new double[in.readInt()];   // java.lang.OutOfMemoryError: Java heap space
        for (int i = 0; i < data.length; i++) {
            if (i == 0) {
                // readUTF 使いたい
                data = new double[in.readInt()];
            } else if (i == 1) {
                name = String.valueOf(in.readByte());
            } else {
                // readDouble 使いたい
                data[i - 2] = in.readByte();
            }
        }
        in.close();
        return data;
    }
}
