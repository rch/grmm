package org.zndx.oss.mallet.share.upenn.ner;

import org.zndx.oss.mallet.base.types.*;
import org.zndx.oss.mallet.base.pipe.*;

import java.util.*;

/**
 * A feature approximating string length.
 */
public class LengthBins extends Pipe implements java.io.Serializable {

    String name;
    int[] bins;
    String[] binNames;

    /**
     * <p>bins contains the maximum sizes of elements in each bin.
     * <p>For example, passing in {1,3,7} would produce 4 bins, for strings
     * of lengths 1, 2-3, 4-7, and 8+.
     */
    public LengthBins (String featureName, int[] binMaxes) {
		this.name = featureName;
        this.bins = binMaxes;
        Arrays.sort(bins);
        
        int min = 1;
        binNames = new String[bins.length+1];
        for (int i=0; i<bins.length; i++) {
            binNames[i] = (min == bins[i] ? "["+min+"]" :
                           "["+min+"-"+bins[i]+"]");
            min = bins[i]+1;
        }
        binNames[bins.length] = "["+min+"+]";
	}

    public Instance pipe (Instance carrier) {
        TokenSequence ts = (TokenSequence) carrier.getData();
        tokens:
        for (int i=0; i < ts.size(); i++) {
            Token t = ts.getToken(i);
            int length = t.getText().length();
            for (int j=0; j<bins.length; j++)
                if (length <= bins[j]) {
                    t.setFeatureValue(name+"="+binNames[j], 1.0);
                    continue tokens;
                }
            t.setFeatureValue(name+"="+binNames[bins.length], 1.0);
        }
        return carrier;
    }
}
