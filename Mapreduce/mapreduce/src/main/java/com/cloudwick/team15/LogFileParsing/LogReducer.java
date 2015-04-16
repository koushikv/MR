package com.cloudwick.team15.LogFileParsing;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashSet;

/**
 * Created by kaushik on 2/25/15.
 */
public class LogReducer extends  Reducer<Text, Text, Text, IntWritable>  {
    private int result;
    HashSet ipCount=new HashSet();
    public void reduce(Text key, java.lang.Iterable<Text> values, Context context)
            throws IOException, InterruptedException

    {

        for (Text val : values)

        {
            ipCount.add(val);
        }
        context.write(key, new IntWritable(ipCount.size()));
    }
}
