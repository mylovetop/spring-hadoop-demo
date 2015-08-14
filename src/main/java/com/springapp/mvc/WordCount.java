package com.springapp.mvc;

//Configuration 类,顾名思义,读写和保存各种配置资源。
import org.apache.hadoop.conf.Configuration;
//引入 Path 类,Path 类保存文件或者目录的路径字符串。
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
//引入 Job 类。在 hadoop 里,每个需要执行的任务是一个 Job,这个 Job 负责很多事情,包括参数配置,设置,MapReduce 细节,提交到 Hadoop 集群,执行控制,查询执行状态,等等。
import org.apache.hadoop.mapreduce.Job;
//引入 FileInputFormat 类。这个类的很重要的作用就是将文件进行切分 split,因为只有切分才可以并行处理
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
//引入 FileOutputFormat 类,处理结果写入输出文件
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//引入 GenericOptionsParser 类,这个类负责解析 hadoop 的命令行参数。
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * Created by zdsoft on 15-8-13.
 */
public class WordCount {

    public static void main(String[] args) throws Exception{
        //默认情况下,Configuration 开始实例化的时候,会从 Hadoop 的配置文件里读取参数。
        Configuration configuration = new Configuration();
        String[] otherArgs = new GenericOptionsParser(configuration, args).getRemainingArgs();
        if (otherArgs.length != 2){
            System.err.println("Usage: wordcount<in><out>");
            System.exit(2);
        }

        Job job = new Job(configuration, "wordcount");
        //Jar 文件是 Java 语言的一个功能,可以将所有的类文件打包成一个 Jar 文件,setJarByClass 的意思是,根据 WordCount 类的位置设置 Jar 文件。
        job.setJarByClass(WordCount.class);
        //设置 Mapper。
        job.setMapperClass(TokenizerMapper.class);
        //设置 Reducer。
        job.setReducerClass(IntSumReducer.class);
        //设置输出键的类型。
        job.setOutputKeyClass(Text.class);
        //设置输出值的类型。
        job.setOutputValueClass(IntWritable.class);
        //设置要处理的文件,也就是输入文件,它是 otherArgs 的第一个参数。
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
