/* Copyright (C) 2002 Univ. of Massachusetts Amherst, Computer Science Dept.
This file is part of "MALLET" (MAchine Learning for LanguagE Toolkit).
http://www.cs.umass.edu/~mccallum/mallet
This software is provided under the terms of the Common Public License,
version 1.0, as published by http://www.opensource.org.  For further
information, see the file `LICENSE' included with this distribution. */


/**
 @author Ben Wellner
 */

package org.zndx.oss.mallet.projects.seg_plus_coref.coreference;

import org.zndx.oss.mallet.projects.seg_plus_coref.clustering.*;
import org.zndx.oss.mallet.projects.seg_plus_coref.graphs.*;
import salvo.jesus.graph.*;
import salvo.jesus.graph.VertexImpl;
import org.zndx.oss.mallet.base.types.*;
import org.zndx.oss.mallet.base.classify.*;
import org.zndx.oss.mallet.base.pipe.*;
import org.zndx.oss.mallet.base.pipe.iterator.*;
import org.zndx.oss.mallet.base.util.*;
import java.util.*;
import java.lang.*;
import java.io.*;

// this is simple structure for representing an "edge" between to
// vertices, but where we're o
public class PseudoEdge {

	double weight;
	Object o1;
	Object o2;

	public PseudoEdge (Object o1, Object o2, double weight) {
		this.o1 = o1;
		this.o2 = o2;
		this.weight = weight;
		
	}

}
