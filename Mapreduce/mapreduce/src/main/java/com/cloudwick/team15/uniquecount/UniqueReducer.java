package com.cloudwick.team15.uniquecount;


/**
 * Created by kaushik on 2/18/15.
 */

        import java.io.IOException;
        import java.util.HashSet;

        import org.apache.hadoop.io.IntWritable;
        import org.apache.hadoop.io.Text;
        import org.apache.hadoop.mapreduce.Reducer;
        import org.apache.hadoop.mapreduce.Mapper.Context;

public class UniqueReducer extends Reducer<Text, Text, Text, IntWritable> {

    private int result;
HashSet ipCount=new HashSet();
    public void reduce(Text key, java.lang.Iterable<Text> values, Context context)
            throws IOException, InterruptedException

    {

                int sum = 0;

        for (Text val : values)

        {
    ipCount.add(val);
        }
        context.write(key, new IntWritable(ipCount.size()));
    }
}
