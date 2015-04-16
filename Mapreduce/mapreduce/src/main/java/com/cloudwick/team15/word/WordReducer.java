package com.cloudwick.team15.word;

/**
 * Created by kaushik on 2/18/15.
 */

import java.io.IOException;

        import org.apache.hadoop.io.IntWritable;
        import org.apache.hadoop.io.Text;
        import org.apache.hadoop.mapreduce.Reducer;
        import org.apache.hadoop.mapreduce.Mapper.Context;

public class WordReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private int result;

    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException

    {
        int sum = 0;

        for (IntWritable val : values)

        {
            sum = sum + 1;

        }
        context.write(key, new IntWritable(sum));

    }
}
