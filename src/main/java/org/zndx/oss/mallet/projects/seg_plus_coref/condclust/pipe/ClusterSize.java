/* Copyright (C) 2002 Univ. of Massachusetts Amherst, Computer Science Dept.
   This file is part of "MALLET" (MAchine Learning for LanguagE Toolkit).
   http://www.cs.umass.edu/~mccallum/mallet
   This software is provided under the terms of the Common Public License,
   version 1.0, as published by http://www.opensource.org.  For further
   information, see the file `LICENSE' included with this distribution. */


package org.zndx.oss.mallet.projects.seg_plus_coref.condclust.pipe;
import org.zndx.oss.mallet.projects.seg_plus_coref.condclust.types.*;
import org.zndx.oss.mallet.projects.seg_plus_coref.coreference.*;
import org.zndx.oss.mallet.base.types.*;
import org.zndx.oss.mallet.base.pipe.*;
import java.util.*;

/** Feature is size of cluster...to penalize large clusters.*/
public class ClusterSize extends Pipe
{
	public ClusterSize ()	
	{	}

	public Instance pipe (Instance carrier) {
		NodeClusterPair pair = (NodeClusterPair)carrier.getData();
		Collection cluster = (Collection)pair.getCluster();
		pair.setFeatureValue ("LogOfClusterSize=", Math.log(cluster.size()));				
		return carrier;
	}
}
