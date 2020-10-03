/* Copyright (C) 2002 Univ. of Massachusetts Amherst, Computer Science Dept.
   This file is part of "MALLET" (MAchine Learning for LanguagE Toolkit).
   http://www.cs.umass.edu/~mccallum/mallet
   This software is provided under the terms of the Common Public License,
   version 1.0, as published by http://www.opensource.org.  For further
   information, see the file `LICENSE' included with this distribution. */




package org.zndx.oss.mallet.base.classify;

import org.zndx.oss.mallet.base.classify.Classifier;
import org.zndx.oss.mallet.base.types.Instance;
import org.zndx.oss.mallet.base.types.InstanceList;
import org.zndx.oss.mallet.base.types.Instance;
import org.zndx.oss.mallet.base.types.Alphabet;
import org.zndx.oss.mallet.base.types.FeatureVector;
import org.zndx.oss.mallet.base.types.Labeling;
import org.zndx.oss.mallet.base.types.LabelVector;
import org.zndx.oss.mallet.base.types.Multinomial;
import org.zndx.oss.mallet.base.types.FeatureSelection;
import org.zndx.oss.mallet.base.util.MalletLogger;
import org.zndx.oss.mallet.base.pipe.Pipe;

import java.util.logging.*;
/**
	 A decision tree learner, roughly ID3.

	 Does not yet implement splitting of continuous-valued features, but
	 it should in the future.  Currently a feature is considered
	 "present" if it has positive value.
	 ftp://ftp.cs.cmu.edu/project/jair/volume4/quinlan96a.ps

	 Only set up for conventiently learning decision stubs:  there is no pruning or
	 good stopping rule.  Currently only stop by reaching a maximum depth.

   @author Andrew McCallum <a href="mailto:mccallum@cs.umass.edu">mccallum@cs.umass.edu</a>
 */
public class DecisionTreeTrainer extends ClassifierTrainer implements Boostable
{
	private static Logger logger = MalletLogger.getLogger(DecisionTreeTrainer.class.getName());
	int maxDepth = 5;
	int maxNumNodes = 99999;							// ignored for now
	double minInfoGainSplit = 0.001;
	
	public DecisionTreeTrainer (int maxDepth)
	{
		this.maxDepth = maxDepth;
	}

	public DecisionTreeTrainer ()
	{
		this(4);
	}

	protected void splitTree (DecisionTree.Node node, FeatureSelection selectedFeatures, int depth)
	{
		if (depth == maxDepth || node.getSplitInfoGain() < minInfoGainSplit)
			return;
		logger.info("Splitting feature \""+node.getSplitFeature()
												+"\" infogain="+node.getSplitInfoGain());
		node.split(selectedFeatures);
		splitTree (node.getFeaturePresentChild(), selectedFeatures, depth+1);
		splitTree (node.getFeatureAbsentChild(), selectedFeatures, depth+1);
	}

	public Classifier train (InstanceList trainingList,
													 InstanceList validationList,
													 InstanceList testSet,
													 ClassifierEvaluating evaluator,
													 Classifier initialClassifier)
	{
		FeatureSelection selectedFeatures = trainingList.getFeatureSelection();
		DecisionTree.Node root = new DecisionTree.Node (trainingList, null, selectedFeatures);
		splitTree (root, selectedFeatures, 0);
		root.stopGrowth();
		System.out.println ("DecisionTree learned:");
		root.print();
		return new DecisionTree (trainingList.getPipe(), root);
	}
	
	
}
