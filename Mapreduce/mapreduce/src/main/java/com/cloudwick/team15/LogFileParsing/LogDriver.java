package com.cloudwick.team15.LogFileParsing;

/**
 * Created by kaushik on 2/25/15.
 */
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
public class LogDriver extends Configured implements Tool
{
    public int run(String[] args) throws Exception {
         Configuration conf = getConf();
        Job weblogJob = new Job(conf);
        weblogJob.setJobName("WebLog Transformer");
        weblogJob.setJarByClass(getClass());

        weblogJob.setMapperClass(LogMapper.class);
        weblogJob.setReducerClass(LogReducer.class);
        weblogJob.setNumReduceTasks(1);
        weblogJob.setMapOutputKeyClass(Text.class);
        weblogJob.setMapOutputValueClass(Text.class);
        weblogJob.setOutputKeyClass(Text.class);
        weblogJob.setOutputValueClass(IntWritable.class);
        FileSystem outFs = new Path(args[1]).getFileSystem(getConf());
        outFs.delete(new Path(args[1]), true);

        FileInputFormat.setInputPaths(weblogJob, new Path(args[0]));
        FileOutputFormat.setOutputPath(weblogJob, new Path(args[1]));
        if(weblogJob.waitForCompletion(true)) {
            return 0;
        }        return 1;
    }
    public static void main( String[] args ) throws Exception {
        int returnCode = ToolRunner.run(new LogDriver(), args);
        System.exit(returnCode);
    }
}

