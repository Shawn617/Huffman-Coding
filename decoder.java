import java.io.*;

/**
 * Created by xiyaoma on 4/3/17.
 */
public class decoder {

    String[] code_table = new String[1200000];

    public static void main(String[] args) throws IOException{

        decoder decoder = new decoder();
        String encoded_file_name = args[0];
        String code_table_file_name = args[1];

        long time_bf_decoding = System.currentTimeMillis();
        decoder.decode(decoder.build_decode_tree(code_table_file_name),encoded_file_name);
        long time_af_decoding = System.currentTimeMillis();
        System.out.println("Time to decode with 4-way heap is " + (time_af_decoding - time_bf_decoding) + " ms");
    }

    /**
     * the first step of decoding: build a huffman tree with a code table txt
     */
    public arraylist_node build_decode_tree(String file) throws IOException {
        //build huffman tree according to the code_table.txt
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(file)));
//        StringBuffer stringBuffer = new StringBuffer();
        String line;
        int end_index_of_value;
        String[] tmp_code;
        while ((line = bufferedReader.readLine()) != null) {
            tmp_code = line.split(" ");
            code_table[Integer.parseInt(tmp_code[0])] = tmp_code[1];
        }

        int i, j;
        String code;
        arraylist_node root_node = new arraylist_node();
        for (i = 0; i < 1100000; i++) {
            if (code_table[i] != null) {
                arraylist_node rootnode = root_node;
                rootnode.isleaf = false;
                code = code_table[i];
                for (j = 0; j < code.length() - 1; j++) {
                    if (code.charAt(j) == '0') {
                        if (rootnode.leftchild == null) {
                            rootnode.leftchild = new arraylist_node();
                            rootnode.isleaf = false;
                        }
                        rootnode = rootnode.leftchild;
                        rootnode.isleaf = false;
                    } else {
                        if (rootnode.rightchild == null) {
                            rootnode.rightchild = new arraylist_node();
                            rootnode.isleaf = false;
                        }
                        rootnode = rootnode.rightchild;
                        rootnode.isleaf = false;
                    }
                }
                if (code.charAt(code.length() - 1) == '0') {
                    rootnode.leftchild = new arraylist_node(i, "0");
                    rootnode.isleaf = true;
                } else {
                    rootnode.rightchild = new arraylist_node(i, "0");
                    rootnode.isleaf = true;
                }

            }
        }
        return root_node;
    }

    public void decode(arraylist_node x, String de_fpath) throws IOException {

        StringBuffer stringBuffer = new StringBuffer();
        String code = readcode(de_fpath);
        arraylist_node node = x;
        int i;
        for (i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '0') {
                node = node.leftchild;
                if (node.value != null) {
                    stringBuffer.append(node.key + "\n");
                    node = x;
                }

            } else {
                node = node.rightchild;
                if (node.value != null) {
                    stringBuffer.append(node.key + "\n");
                    node = x;
                }
            }
        }
        write(stringBuffer.toString(),"decoded.txt");
    }

    private String readcode(String filename) throws IOException {
        String sContent=null;
        byte [] buffer =null;
        File a_file = new File(filename);
        try
        {
            FileInputStream fileInputStream = new FileInputStream(filename);
            int length = (int)a_file.length();
            buffer = new byte [length];
            fileInputStream.read(buffer);
            fileInputStream.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        sContent = toBinary(buffer);
        return sContent;
    }
    public String toBinary( byte[] bytes )
    {
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        return sb.toString();
    }
    public void write(String string,String fileName) {
        File file = new File(fileName);
        try {
            FileWriter fWriter = new FileWriter(file);
            fWriter.write(string);
            fWriter.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
