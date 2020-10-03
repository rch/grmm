/* Copyright (C) 2002 Univ. of Massachusetts Amherst, Computer Science Dept.
   This file is part of "MALLET" (MAchine Learning for LanguagE Toolkit).
   http://www.cs.umass.edu/~mccallum/mallet
   This software is provided under the terms of the Common Public License,
   version 1.0, as published by http://www.opensource.org.  For further
   information, see the file `LICENSE' included with this distribution. */





package org.zndx.oss.mallet.base.classify;

import org.zndx.oss.mallet.base.types.*;
/**
	 Bagging Trainer.

   @author Andrew McCallum <a href="mailto:mccallum@cs.umass.edu">mccallum@cs.umass.edu</a>
 */

public class BaggingTrainer extends ClassifierTrainer
{
	ClassifierTrainer underlyingTrainer;
	int numBags;
	
	public BaggingTrainer (ClassifierTrainer underlyingTrainer, int numBags)
	{
		this.underlyingTrainer = underlyingTrainer;
		this.numBags = numBags;
	}

	public BaggingTrainer (ClassifierTrainer underlyingTrainer)
	{
		this (underlyingTrainer, 10);
	}
	
	public Classifier train (InstanceList trainingList,
													 InstanceList validationList,
													 InstanceList testSet,
													 ClassifierEvaluating evaluator,
													 Classifier initialClassifier)
	{
		Classifier[] classifiers = new Classifier[numBags];
		java.util.Random r = new java.util.Random ();
		for (int round = 0; round < numBags; round++) {
			InstanceList bag = trainingList.sampleWithReplacement (r, trainingList.size());
			classifiers[round] = underlyingTrainer.train (bag, validationList, testSet,
																										evaluator, initialClassifier);
		}
		return new BaggingClassifier (trainingList.getPipe(), classifiers);
	}
	
}
