package org.zndx.oss.mallet.projects.seg_plus_coref.coreference;

import com.wcohen.secondstring.*;
import org.zndx.oss.mallet.base.types.*;
import org.zndx.oss.mallet.base.classify.*;
import org.zndx.oss.mallet.base.pipe.*;
import org.zndx.oss.mallet.base.pipe.iterator.*;
import org.zndx.oss.mallet.base.util.*;
import java.util.*;
import java.lang.*;
import java.io.*;


public class PlainFieldPipe extends Pipe
{
	StringDistance distMetric, distMetric2;
	double threshold = 0.9;

	boolean useNBest = false;
	
	public PlainFieldPipe(double threshold)
	{
		this.threshold = threshold;
		this.distMetric = new NeedlemanWunsch();
	}

	public PlainFieldPipe(StringDistance distMetric, StringDistance distMetric2) {
		this.distMetric = distMetric;
		this.distMetric2 = distMetric2;
	}

	public PlainFieldPipe(StringDistance distMetric) {
		this.distMetric = distMetric;
	}

	public Instance pipe (Instance carrier) 
	{
		NodePair pair = (NodePair)carrier.getData();
		Citation c1 = (Citation)pair.getObject1();
		Citation c2 = (Citation)pair.getObject2();
		Map fields1 = c1.getFields();
		Map fields2 = c2.getFields();

		NeedlemanWunsch nw = new NeedlemanWunsch();

		Set keys1 = fields1.keySet();
		Set keys2 = fields2.keySet();

		String [] possFields = c1.getPossibleFields();
		for (int k=0; k < possFields.length; k++) {
			String f1 = c1.getField(possFields[k]);
			String f2 = c2.getField(possFields[k]);
			if (f1 != null && f2 != null) {
				//System.out.println("Setting feature " + possFields[k] + " to : " +
				//distMetric.score(f1,f2));
				pair.setFeatureValue (new String(possFields[k] + "Similarity1"), distMetric.score(f1,f2));
				//pair.setFeatureValue (new String(possFields[k] + "AJLKDFAOIJAF2"), nw.score(f1,f2));
			} else if ((f1 == null && f2 != null) || (f1 != null && f2 == null)) {
				//System.out.println("Setting OnePresent");
				pair.setFeatureValue (possFields[k] + "OnePresent", 1.0);
			}
			else {
				//System.out.println("Setting NeitherPresent");
				pair.setFeatureValue (possFields[k] + "NeitherPresent", 1.0);
			}
		}
					
		return carrier;
	}


}
