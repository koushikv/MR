package com.cloudwick.team15.PigUdf;

import org.apache.pig.FilterFunc;
import org.apache.pig.data.Tuple;

import java.io.IOException;

/**
 * Created by kaushik on 3/1/15.
 */
public class PigFilter extends FilterFunc {


    @Override
    public Boolean exec(Tuple input) throws IOException {
        return true;
    }
}
