/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffmancompression;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Abdul Wakeel Khan
 */
public class BitOutput {
    private final FileOutputStream output;
    private int BinValue;
    private int count = 0;
    private final int True = 1; // The bits are all zeros except the last one
    
    
    //COnstructor of this class which calls FileOutputStream constructor for opening the file
    public BitOutput(File file) throws IOException {
      output = new FileOutputStream(file);//call constructor of FileOutput Stream
    }
    
    /*
    The writeBit() method get a String as its parameter and 
    passes its contents one by one to its overriding method writeBit()
    */
    public void writeBit(String bitString) throws IOException {
      for (int i = 0; i < bitString.length(); i++)
        writeBit(bitString.charAt(i));//pass one by one to the function
    }
    
    /*
    The writeBit() method get char as its parameter and writes 8bit (1byte) complete to the stream.
    ***Working***
    When a character (1 or 0) come as parameter the counter increases its value by 1.
    an integer variable value, its value is left shifted 1
    then if bit is 1 then 1 is stored in value as always one by '|' with mask variable having value 1
    then again that value is shifted to the left and the process continues unitil a byte is completed 
    which is determined by counter.
   and the the value is written to the stream and counter is set to 0 fom next bytes.
    */
    public void writeBit(char bit) throws IOException {
      count++;//increament count per bit
      BinValue = BinValue << 1;//left shift the bit 
      
      if (bit == '1') //if bit is one save it in value as 1
        BinValue = BinValue | True;
      
      if (count == 8) {// when 8bits completed write it to the stream
        output.write(BinValue);
        count = 0;
      }
    }
       
}
