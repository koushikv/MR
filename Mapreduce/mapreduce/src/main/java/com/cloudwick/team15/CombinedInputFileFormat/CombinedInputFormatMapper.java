package com.cloudwick.team15.CombinedInputFileFormat;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class CombinedInputFormatMapper extends MapReduceBase implements Mapper<LongWritable,Text,Text,Text> {

    Text txtKey = new Text("");
    Text txtValue = new Text("");
    public void map(LongWritable key, Text value,
                    OutputCollector<Text, Text> output, Reporter reporter)
            throws IOException {

        if (value.toString().length() > 0) {
            String[] arrEmpAttributes = value.toString().split(",");
            txtKey.set(arrEmpAttributes[0].toString());
            txtValue.set(arrEmpAttributes[2].toString() + "\t"
                    + arrEmpAttributes[3].toString());

            output.collect(txtKey, txtValue);
        }

    }

}
