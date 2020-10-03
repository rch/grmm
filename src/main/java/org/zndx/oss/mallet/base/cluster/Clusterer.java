/* Copyright (C) 2003 Univ. of Massachusetts Amherst, Computer Science Dept.
   This file is part of "MALLET" (MAchine Learning for LanguagE Toolkit).
   http://www.cs.umass.edu/~mccallum/mallet
   This software is provided under the terms of the Common Public License,
   version 1.0, as published by http://www.opensource.org.  For further
   information, see the file `LICENSE' included with this distribution. */

/** An abstract class for clustering a set of points.
    @author Jerod Weinman <A HREF="mailto:weinman@cs.umass.edu">weinman@cs.umass.edu</A>
*/

package org.zndx.oss.mallet.base.cluster;

import org.zndx.oss.mallet.base.cluster.Clustering;
import org.zndx.oss.mallet.base.types.InstanceList;
import org.zndx.oss.mallet.base.pipe.Pipe;


public abstract class Clusterer {
    
    Pipe instancePipe;

    public Clusterer(Pipe instancePipe)
    {
	this.instancePipe = instancePipe;
    }

    /** Return a clustering of the training set */
    public abstract Clustering cluster( InstanceList trainingSet );

}
