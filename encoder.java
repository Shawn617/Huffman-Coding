import java.io.*;

/**
 * Created by xiyaoma on 4/3/17.
 */
public class encoder {

    String[] code_table = new String[1200000];

    public static void main(String[] args) throws IOException{

        huffman huff = new huffman();
        encoder encoder = new encoder();
        String input_file_name = args[0];
        huff.readtxtfile(input_file_name);

        /**
         * choose 4-way heap as data structure I will use, and build a huffman tree based on which I create a code table. Then encoding and decoding.
         */
//        System.out.println("Freq table: "+prep.freq_table);
        huff.wh.heapsort(huff.freq_table);//read input txt file and transfer to freq table via heap sort

        long time_bf_huff = System.currentTimeMillis();
        arraylist_node root_node = huff.build_huffman_4wayheap();
        long time_af_buff = System.currentTimeMillis();
        System.out.println("Time to build huffman tree with 4-way heap is " + (time_af_buff - time_bf_huff) + " ms");

        long time_bf_buildcode = System.currentTimeMillis();
        encoder.build_code_table(root_node,"");//build huffman tree and create a code table
        long time_af_buildcode = System.currentTimeMillis();
        System.out.println("Time to build code table with 4-way heap is " + (time_af_buildcode - time_bf_buildcode) + " ms");

        huff.write_txtfile(encoder.code_table,"code_table.txt");//output code_table.txt file

        long time_bf_encoding = System.currentTimeMillis();
        encoder.encode(input_file_name);//encoding input data by using code_table created in the last step, and output encoded.bin file
        long time_af_encoding = System.currentTimeMillis();
        System.out.println("Time to encode with 4-way heap is " + (time_af_encoding - time_bf_encoding) + " ms");


    }

    /**
     * make a code table based on huffman tree
     *
     * @param x
     * @param code
     * @return
     */
    public void build_code_table(arraylist_node x, String code) {

        if (x.isleaf == false) {
            build_code_table(x.leftchild, code + '0');
            build_code_table(x.rightchild, code + '1');
        } else {
            code_table[Integer.parseInt(x.value)] = code;
        }
    }

    /**
     * encode the input txt file according to code table and output the encoded bin file
     *
     * @param fpath
     * @throws IOException
     */
    public void encode(String fpath) throws IOException {
        File file = new File(fpath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuffer stringBuffer = new StringBuffer();

        BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream("encoded.bin"));

        String line;
        while ((line = bufferedReader.readLine()) != null) {

            if (code_table[Integer.parseInt(line)] != null) {
                stringBuffer.append(code_table[Integer.parseInt(line)]);
            }
        }
        int index = 0;
//        String sub;
        int length = stringBuffer.length();
        int num_loop = length / 8;
//        System.out.println("num_loop: "+num_loop);
        int i = 0;
        while (index < stringBuffer.length() && i < num_loop) {
            writer.write(Integer.valueOf(stringBuffer.substring(index, index + 8), 2).byteValue());
            index += 8;
            i++;
        }
        if (length % 8 != 0) {
            writer.write((byte)(Integer.valueOf(stringBuffer.substring(index), 2).byteValue() << (8 - length % 8)));
        }

        fileReader.close();
        writer.close();
//        System.out.println("Encoded String is "+stringBuffer.toString());
        //output encoded bin file
        System.out.println("Encoding complete.");
    }


}
