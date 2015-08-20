package com.springapp.mvc.mapReduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
//引入 hadoop 的 Reducer 类,这个类负责 MapReduce 的 Reduce 过程
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by zdsoft on 15-8-13.
 */
public class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    IntWritable result = new IntWritable();


    /**
     * 举例来说,假如处理一个字符串 ”This is a That is a“,
     * 那么,经过 Map 过程之后,到达 reduce 函数的时候,
     * 依次 传 递 给 reduce 函 数 的 是 : key=”This” , values=<1> ; key = “is” , values=<1, 1> ; key = “a”, values=<1, 1> ; key=”That”, values=<1>。
     * 注意,在 key = “is”和 key=”a”的时候,values 里有两个 1。
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //Reduce 过程
        //这个过程,就是用一个循环,不断从 values 里取值,然后累加计算和,循环结束后,将累加和赋值给 result 变量
        //然后,将键值和累加和作为一个键值对写入 context。继续以上一步的例子来说,写入 context 的键值对依次就是 <”This”,1>,<“is”,2>,<“a”, 2>,<”That”, 1>。
        int sum = 0;
        for (IntWritable val : values){
            sum += val.get();
        }
        result.set(sum);
        context.write(key, result);

    }
}
