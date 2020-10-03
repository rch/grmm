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

public class NodePairSaveSource extends Pipe
{
	public NodePairSaveSource()
	{
	}

	public Instance pipe (Instance carrier) 
	{
		NodePair pair = (NodePair)carrier.getData();
		Citation s1 = (Citation)pair.getObject1();
		Citation s2 = (Citation)pair.getObject2();

		carrier.setSource(new String( "Citation1:"+s1.getOrigString()+"\nCitation2:"+s2.getOrigString() ) );
		return carrier;
	}


}
