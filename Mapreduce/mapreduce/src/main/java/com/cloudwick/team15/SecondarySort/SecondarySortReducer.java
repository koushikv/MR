package com.cloudwick.team15.SecondarySort;
import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * Created by kaushik on 2/24/15.
 */
public class SecondarySortReducer extends Reducer<SecondarySort,Text,SecondarySort,Text> {

    @Override
    protected void reduce(SecondarySort key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {

            context.write(key, value);
        }
    }
}
