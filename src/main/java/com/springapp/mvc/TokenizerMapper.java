package com.springapp.mvc;

//IntWritable 类表示的是一个整数,是一个以类表示的整数,是一个以类表示的可序列化的整数。
// 在 Java 里,要表 示一个整数,假如是 15,可以用 int 类型,int 类型是 Java 的基本类型,占 4 个字节,也可以用 Integer 类,Integer 类 封装了一个 int 类型,让整数成为类。
// Integer 类是可以序列化的。但 Hadoop 觉得 Java 的序列化不适合自己,于是实现 了 IntWritable 类
import org.apache.hadoop.io.IntWritable;
//Text 类是存储字符串的可比较可序列化类
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by zdsoft on 15-8-13.
 * 所谓标准形式的 MapReduce,就说需要写 MapReduce 的时候,脑海里立刻跳出的就是这个形式,一个 Map 的 Java 文件,一个 Reduce 的 Java 文件,一个负责调用的主程序 Java 文件。
 * 这个标准形式已经是最简了,没有多余的东 东可以删除,没有肥肉,是干货。
 * 写 MapReduce 和主程序的时候,分别引用哪些包哪些类,每个包每个类是什么作 用,这些要很清晰。
 *
 * Mapper 类很重要,它将输入键值对映射到输出键值对,也就是 MapReduce 里的 Map 过程
 */

// 它继承了 Hadoop 的 Mapper 类。
// “<Object, Text, Text, IntWritable>”
// 第一个参数类型是 Object,表示输入键 key 的参数类型,
// 第二个参数参数类型是 Text,表示输 入值的类型,
// 第三个参数类型也是 Text,表示输出键类型,
// 第四个参数类型是 IntWritable,表示输出值类型。
public class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

    IntWritable one = new IntWritable(1);
    Text word = new Text();

    /**
     * 定义 map 函数,函数有三个参数,key 是输入键,它是什么无所谓,实际上用不到它的,value 是输入值。
     * 在 map 函数中,出错的时候会抛出异常,所以有“throws IOException, InterruptedException ”。
     * 至于 Context 类,这个类的定 义是在 TokenizerMapper 的祖先类 Mapper 的内部,不需要引入,如果去查看 Mapper 类的源代码的话,能看到 Context 类是继承 MapContext 类的。
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString());
        while (itr.hasMoreTokens()){
            word.set(itr.nextToken());
            context.write(word, one);
        }

    }
}
