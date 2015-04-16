package com.cloudwick.team15.leftouterjoin;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by kaushik on 2/25/15.
 */
public class LeftJoinMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable offset, Text input, Context context) throws IOException, InterruptedException {
        String str1=input.toString();

Text testObj=new Text();
      /*  StringBuffer bufObj=new StringBuffer();*/
        String[] userInfo=str1.split(",");

        context.write(new Text(userInfo[0]),new Text(userInfo[3]));



    }




}
