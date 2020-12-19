//package ch03.ex11;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import javax.swing.plaf.synth.SynthMenuBarUI;
//
//import static org.hamcrest.CoreMatchers.is;
//
//// p.97 の TestSort
//public class SimpleSortDoubleTest {
//    static double[] testData = {
//            0.3, 1.3e-2, 7.9, 3.17
//    };
//
//    public static void main(String[] args) {
//        SortDouble bsort = new SimpleSortDouble();
//        SortMetrics metrics = bsort.sort(testData);
//        System.out.println("Metrics: " + metrics);
//        for (int i = 0; i < testData.length; i++) {
//            System.out.println("\t" + testData[i]);
//        }
//    }
//
//    @Test
//    public void getMetricsで取得したメトリックスを書き換えると元のメトリックスも書き換えられる() {
//        SortDouble bsort = new SimpleSortDouble();
//        System.out.println(bsort.getMetrics());
//        SortMetrics metrics = bsort.sort(testData);
//        SortMetrics copy = bsort.sort(testData);
//
//        Assert.assertThat(copy, is(metrics));
//    }
//}