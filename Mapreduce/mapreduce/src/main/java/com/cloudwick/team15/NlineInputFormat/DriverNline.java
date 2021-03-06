package com.cloudwick.team15.NlineInputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by kaushik on 2/27/15.
 */
public class DriverNline extends  Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {

        if (args.length != 2) {
            System.out
                    .printf("Two parameters are required for DriverNLineInputFormat- <input dir> <output dir>\n");
            return -1;
        }

        Job job = new Job(getConf());
        job.setJobName("NLineInputFormat example");
        job.setJarByClass(DriverNline.class);

        job.setInputFormatClass(NLineInputFormat.class);
        NLineInputFormat.addInputPath(job, new Path(args[0]));
        job.getConfiguration().setInt("mapreduce.input.lineinputformat.linespermap", 10000);
        LazyOutputFormat.setOutputFormatClass(job, TextOutputFormat.class);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setMapperClass(MapperNline.class);
        job.setNumReduceTasks(0);
        FileSystem outFs = new Path(args[1]).getFileSystem(getConf());
        outFs.delete(new Path(args[1]), true);


        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new Configuration(),
                new DriverNline(), args);
        System.exit(exitCode);
    }
}