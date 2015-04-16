package com.cloudwick.team15.uniquecount;


/**
 * Created by kaushik on 2/18/15.
 */




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
public class UniqueMapper extends Mapper<LongWritable, Text, Text, Text> {
    Text url=new Text();
    Text ip=new Text();

    @Override
    protected void map(LongWritable offset, Text input, Context context) throws IOException, InterruptedException {
        String str1=input.toString();


        String[] words=str1.split(",");
        url.set(words[0]);
        ip.set(words[1]);
System.out.println( words[0]+" test "+words[1]);
        context.write(url,ip);


    }

}
