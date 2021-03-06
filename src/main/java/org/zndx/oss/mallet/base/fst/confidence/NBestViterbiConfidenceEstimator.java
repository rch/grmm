/* Copyright (C) 2002 Univ. of Massachusetts Amherst, Computer Science Dept.
   This file is part of "MALLET" (MAchine Learning for LanguagE Toolkit).
   http://www.cs.umass.edu/~mccallum/mallet
   This software is provided under the terms of the Common Public License,
   version 1.0, as published by http://www.opensource.org.  For further
   information, see the file `LICENSE' included with this distribution. */

/** 
		@author Aron Culotta <a href="mailto:culotta@cs.umass.edu">culotta@cs.umass.edu</a>
*/

package org.zndx.oss.mallet.base.fst.confidence;

import org.zndx.oss.mallet.base.types.*;
import org.zndx.oss.mallet.base.util.MalletLogger;
import java.util.logging.*;
import org.zndx.oss.mallet.base.pipe.iterator.*;
import org.zndx.oss.mallet.base.fst.*;
import java.util.*;

/**
	 Estimates the confidence of an entire sequence by the probability
	 that one of the the Viterbi paths rank 2->N is correct. Note that
	 this is a strange definition of confidence, and is mainly used for
	 {@link MultipleChoiceCRFActiveLearner}, where we want to find
	 Instances that are mislabeled, but are likely to have a correct
	 labeling in the top N Viterbi paths.
 */
public class NBestViterbiConfidenceEstimator extends TransducerSequenceConfidenceEstimator
{
	/** total number of Viterbi paths */
	int N;
	
	private static Logger logger = MalletLogger.getLogger(
		NBestViterbiConfidenceEstimator.class.getName());


	public NBestViterbiConfidenceEstimator (Transducer model, int N) {
		super(model);
		this.N = N;
	}

	/**
		 Calculates the confidence in the tagging of a {@link Instance}.
	 */
	public double estimateConfidenceFor (Instance instance,
																			 Object[] startTags,
																			 Object[] inTags) {
		Transducer.Lattice lattice = model.forwardBackward ((Sequence)instance.getData());
		double[] costs = model.viterbiPath_NBest ((Sequence)instance.getData(), N).costNBest();
		double latticeCost = lattice.getCost();
		double prFirstIsCorrect = Math.exp( latticeCost - costs[0] );
		double prOtherIsCorrect = 0.0;
		for (int i=1; i < N; i++)
			prOtherIsCorrect += Math.exp( latticeCost - costs[i] );
		return prFirstIsCorrect / prOtherIsCorrect;
	}
}
