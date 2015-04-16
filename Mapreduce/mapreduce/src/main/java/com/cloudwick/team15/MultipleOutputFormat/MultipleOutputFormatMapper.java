package com.cloudwick.team15.MultipleOutputFormat;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


/**
 * Created by kaushik on 2/27/15.
 */
public class MultipleOutputFormatMapper extends
        Mapper<LongWritable, Text, Text, Text> {

    private Text txtKey = new Text("");
    private Text txtValue = new Text("");

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        if (value.toString().length() > 0) {
            String arrEmpAttributes[] = value.toString().split(",");
            txtKey.set(arrEmpAttributes[4].toString());
            txtValue.set(arrEmpAttributes[3].toString() + "\t"
                    + arrEmpAttributes[2].toString() + "\t"
                    + arrEmpAttributes[1].toString());
            context.write(txtKey, txtValue);
        }

    }
}