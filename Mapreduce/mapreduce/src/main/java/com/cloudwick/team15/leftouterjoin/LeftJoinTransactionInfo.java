package com.cloudwick.team15.leftouterjoin;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LeftJoinTransactionInfo  extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable offset, Text input, Context context) throws IOException, InterruptedException {
        String str1=input.toString();
        String[] words=str1.split(",");
        context.write(new Text(words[0]),new Text(words[2]));
    }
}
