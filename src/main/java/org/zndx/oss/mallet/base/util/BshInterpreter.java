/* Copyright (C) 2002 Univ. of Massachusetts Amherst, Computer Science Dept.
   This file is part of "MALLET" (MAchine Learning for LanguagE Toolkit).
   http://www.cs.umass.edu/~mccallum/mallet
   This software is provided under the terms of the Common Public License,
   version 1.0, as published by http://www.opensource.org.  For further
   information, see the file `LICENSE' included with this distribution. */


package org.zndx.oss.mallet.base.util;

import java.io.*;
import java.util.*;
import bsh.Interpreter;

public class BshInterpreter extends bsh.Interpreter
{
	Interpreter interpreter;

	public BshInterpreter (String prefixCommands)
	{
		try {
			eval (
				"import java.util.*;"+
				"import java.util.regex.*;"+
				"import java.io.*;"+
				"import org.zndx.oss.mallet.base.types.*;"+
				"import org.zndx.oss.mallet.base.pipe.*;"+
				"import org.zndx.oss.mallet.base.pipe.iterator.*;"+
				"import org.zndx.oss.mallet.base.pipe.tsf.*;"+
				"import org.zndx.oss.mallet.base.classify.*;"+
				"import org.zndx.oss.mallet.base.extract.*;"+
				"import org.zndx.oss.mallet.base.fst.*;"+
				"import org.zndx.oss.mallet.base.minimize.*;");
			if (prefixCommands != null)
				eval (prefixCommands);
		} catch (bsh.EvalError e) {
			throw new IllegalArgumentException ("bsh Interpreter error: "+e);
		}
	}

	public BshInterpreter ()
	{
		this (null);
	}
	
}
