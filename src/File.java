package com.company;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.util.Date;

public class File {
    double copy (String src, String dst, int bufSize) throws InvalidPathException
    {
        Path srcpath = Paths.get(src);
        Path dstpath = Paths.get(dst);

        long srcFileSize = 0;
        long dstFileSize = 0;

        ByteBuffer buffer = ByteBuffer.allocate(bufSize);

        try(SeekableByteChannel srcChan = Files.newByteChannel(srcpath);
            SeekableByteChannel dstChan = Files.newByteChannel(dstpath, StandardOpenOption.WRITE, StandardOpenOption.CREATE))
        {
            srcFileSize = Files.size(srcpath);
            int currBufSize = 0;

            do {
                currBufSize = srcChan.read(buffer);
                buffer.rewind();
                dstChan.write(buffer);
                dstFileSize += buffer.position();
                buffer.rewind();


                System.out.println("Прогресс: " + ((double)dstFileSize / (double)srcFileSize)*100+" %");
            }
            while (currBufSize > 0);

        }
        catch (IOException e)
        {

        }

        return 0;
    }
}