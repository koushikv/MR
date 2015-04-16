package com.cloudwick.team15.CombinedInputFileFormat;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * Created by kaushik on 2/27/15.
 */
public class DriverCombineInputFormat {
    public static void main(String[] args) throws Exception {
        JobConf conf = new JobConf("DriverCombineFileInputFormat");
        conf.set("mapred.max.split.size", "134217728");//128 MB
        conf.setJarByClass(DriverCombineInputFormat.class);
        String[] jobArgs = new GenericOptionsParser(conf, args)
                .getRemainingArgs();

        conf.setMapperClass(CombinedInputFormatMapper.class);
        conf.setInputFormat(ExtendedFileInputFormat.class);
        ExtendedFileInputFormat.addInputPath(conf, new Path(jobArgs[0]));
        conf.setNumReduceTasks(0);
        FileSystem outFs = new Path(args[1]).getFileSystem(conf);
        outFs.delete(new Path(args[1]), true);

        conf.setOutputFormat(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(conf, new Path(jobArgs[1]));
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        RunningJob job = JobClient.runJob(conf);
        while (!job.isComplete()) {
            Thread.sleep(1000);
        }

        System.exit(job.isSuccessful() ? 0 : 2);
    }
}
