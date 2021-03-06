package com.cloudwick.team15.MultipleOutputFormat;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

/**
 * Created by kaushik on 2/27/15.
 */
public class MultipleOutputFormatReducer extends Reducer<Text, Text, Text, Text> {

    private MultipleOutputs mos;

    @Override
    protected void setup(Context context) throws IOException,
            InterruptedException {
        mos = new MultipleOutputs(context);

    }

    @Override
    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        for (Text value : values) {
            mos.write(key, value, key.toString());

        }
    }

    @Override
    protected void cleanup(Context context) throws IOException,
            InterruptedException {
        mos.close();
    }

}