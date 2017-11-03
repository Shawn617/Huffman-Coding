import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;
/**
 * Created by xiyaoma on 3/28/17.
 */
public class test {

    public static void main(String[] arg) throws Exception{
        huffman huff = new huffman();
        String input_file_path = "sample_input_large.txt";
        huff.readtxtfile(input_file_path);

        int i;
        /**
         * test binary heap
         */
        for (i= 0;i<10;i++) {
            long time_start = System.currentTimeMillis();
            huff.bh.heapsort(huff.freq_table);//read input txt file and transfer to freq table via heap sort
            huff.build_huffman_binaryheap();
            long time_end = System.currentTimeMillis();
            System.out.println("Index: " + i+ "  Time to binary heap sort to freq table and build huffman tree is "+(time_end-time_start)+" ms");
        }
        /**
         * test 4-way heap
         */
        for (i= 0;i<10;i++) {
            long time_start = System.currentTimeMillis();
            huff.wh.heapsort(huff.freq_table);//read input txt file and transfer to freq table via heap sort
            huff.build_huffman_4wayheap();
            long time_end = System.currentTimeMillis();
            System.out.println("Index: " + i+ "  Time to 4-way heap sort to freq table and build huffman tree is "+(time_end-time_start)+" ms");
        }

        /**
         * test pairing heap
         */
        for (i= 0;i<10;i++) {
            long time_start = System.currentTimeMillis();
            huff.ph.heapsort(huff.freq_table);//read input txt file and transfer to freq table via heap sort
            huff.build_huffman_pairingheap();
            long time_end = System.currentTimeMillis();
            System.out.println("Index: " + i+ "  Time to pairing heap sort to freq table and build huffman tree is "+(time_end-time_start)+" ms");
        }

    }

}
