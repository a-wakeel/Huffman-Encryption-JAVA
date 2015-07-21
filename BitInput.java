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

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Abdul Wakeel Khan
 */
public class BitInput{
    private FileInputStream input;
    private int currentBit=8;
    private int currentByte;
    
    //constructor
    public BitInput(File file) throws FileNotFoundException{
        input=new FileInputStream(file);
       
    }
    
    /*
    The readBit() method returns boolean on the basis of some bitwise operation
    ***Working***
    The method first checks if the currentBit=8 then it reads a byte from file and return a boolean value by some bitwise operstion and it 
    will continue until the current bit again equal to 8.
    e.g
    if a byte is 124 which binary value is 1111100 the the process will be like this:
    when currentBit=0
    a bit "1" will be shifted 7 places to left by 1<<7-0 which will look like 1000000 then
    BitWise AND operation will be applied on currentByte and the above value by currentByte & (1<<(7-0)) which will look like
    1111100 & 1000000=1000000 which means boolean will be true as in below condition next currentBit will be increamented
    again it will be shifted 6 plaes to left 0100000 then 
    1111100 & 0100000=0100000 which means next bit is gained and this will continue till 7th bit then again a new byte will be read.
    */
    public boolean readBit() throws IOException{
        if(currentBit==8){
            currentBit=0;
            currentByte=input.read();//read byte from file
            
            if(currentByte==-1)//if end of file
                throw new EOFException();
        }
        boolean value=(currentByte &(1<<(7-currentBit)))!=0;//bitwise and op on binary of current byte and a bit left shifted according to current bit
        currentBit++;
        return value;
    }

    
}
