package com.springapp.mvc.mapReduce.fileCopy;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by zdsoft on 15-8-14.
 */
public class FileCopy {

    public static void main(String[] args) throws Exception{
        String path = FileCopy.class.getResource("").getPath();
        args = new String[]{path + "FileCopy.class", path + "test.class"};
        Configuration configuration = new Configuration();
        InputStream in = new BufferedInputStream(new FileInputStream(args[0]));
        FileSystem fs = FileSystem.get(URI.create(args[1]), configuration);
        OutputStream out = fs.create(new Path(args[1]));
        IOUtils.copyBytes(in, out, 4096, true);
    }
}
