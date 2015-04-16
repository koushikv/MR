package com.cloudwick.team15.word;



import java.io.IOException;
        import java.util.Enumeration;
        import java.util.Hashtable;
        import java.util.StringTokenizer;

        import org.apache.hadoop.conf.Configuration;
        import org.apache.hadoop.fs.Path;
        import org.apache.hadoop.io.Text;
        import org.apache.hadoop.mapred.OutputCollector;
        import org.apache.hadoop.mapreduce.lib.input.*;
        import org.apache.hadoop.mapreduce.Job;
        import org.apache.hadoop.mapreduce.Mapper;
        import org.apache.hadoop.io.*;
        import org.apache.hadoop.mapreduce.Mapper;


@SuppressWarnings("unused")
public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable offset, Text input, Context context) throws IOException, InterruptedException {
        String str1=input.toString();


        String[] words=str1.split(" ");
        for (String word : words) {
            context.write(new Text(word),new IntWritable(1));
        }

    }




}
