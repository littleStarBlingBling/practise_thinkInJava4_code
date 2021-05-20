package com.practise.io;

import java.nio.*;

public class ViewBuffers {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, 0, 0, 0, 'a'});
        buffer.rewind();

        System.out.print("Byte buffer ");
        while (buffer.hasRemaining()) {
            System.out.print(buffer.position() + " -> " + buffer.get() + ", ");
        }
        System.out.println();

        CharBuffer charBuffer = ((ByteBuffer) buffer.rewind()).asCharBuffer();
        System.out.print("Char buffer ");
        while (charBuffer.hasRemaining()) {
            System.out.print(charBuffer.position() + " -> " + charBuffer.get() + ", ");
        }
        System.out.println();

        FloatBuffer floatBuffer = ((ByteBuffer) buffer.rewind()).asFloatBuffer();
        System.out.print("Float buffer ");
        while (floatBuffer.hasRemaining()) {
            System.out.print(floatBuffer.position() + " -> " + floatBuffer.get() + ", ");
        }
        System.out.println();

        IntBuffer intBuffer = ((ByteBuffer) buffer.rewind()).asIntBuffer();
        System.out.print("Int buffer ");
        while (intBuffer.hasRemaining()) {
            System.out.print(intBuffer.position() + " -> " + intBuffer.get() + ", ");
        }
        System.out.println();

        LongBuffer longBuffer = ((ByteBuffer) buffer.rewind()).asLongBuffer();
        System.out.print("Long buffer ");
        while (longBuffer.hasRemaining()) {
            System.out.print(longBuffer.position() + " -> " + longBuffer.get() + ", ");
        }
        System.out.println();

        ShortBuffer shortBuffer = ((ByteBuffer) buffer.rewind()).asShortBuffer();
        System.out.print("Short buffer ");
        while (shortBuffer.hasRemaining()) {
            System.out.print(shortBuffer.position() + " -> " + shortBuffer.get() + ", ");
        }
        System.out.println();

        DoubleBuffer doubleBuffer = ((ByteBuffer) buffer.rewind()).asDoubleBuffer();
        System.out.print("Double buffer ");
        while (doubleBuffer.hasRemaining()) {
            System.out.print(doubleBuffer.position() + " -> " + doubleBuffer.get() + ", ");
        }
        System.out.println();
    }
}
