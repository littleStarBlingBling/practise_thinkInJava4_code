package com.practise.io;

import com.practise.utils.ConstUtils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

public class TestEOF {

    public static void main(String[] args) throws IOException {
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(BufferedInputFile.read(ConstUtils.IO_PREFIX + "TestEOF.java").getBytes()));
            while (in.available() != 0) {
                System.out.print((char) in.readByte());
            }
        } catch (EOFException e) {
            System.err.println("End of stream");
        }
    }
}
