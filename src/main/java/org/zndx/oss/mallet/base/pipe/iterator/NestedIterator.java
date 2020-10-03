/* Copyright (C) 2002 Univ. of Massachusetts Amherst, Computer Science Dept.
   This file is part of "MALLET" (MAchine Learning for LanguagE Toolkit).
   http://www.cs.umass.edu/~mccallum/mallet
   This software is provided under the terms of the Common Public License,
   version 1.0, as published by http://www.opensource.org.  For further
   information, see the file `LICENSE' included with this distribution. */




/** 
   @author Andrew McCallum <a href="mailto:mccallum@cs.umass.edu">mccallum@cs.umass.edu</a>
 */

package org.zndx.oss.mallet.base.pipe.iterator;

import org.zndx.oss.mallet.base.pipe.*;
import org.zndx.oss.mallet.base.types.Instance;

public class NestedIterator extends AbstractPipeInputIterator
{
	PipeInputIterator[] iterators;
	Object next = null;

	public NestedIterator (PipeInputIterator[] iterators)
	{
		this.iterators = iterators;
	}

	private boolean setNext()
	{
		return false;
	}

	public boolean hasNext ()
	{
		return next != null;
	}

	public Instance nextInstance ()
	{
		throw new UnsupportedOperationException ("Not yet implemented");
	}


}



