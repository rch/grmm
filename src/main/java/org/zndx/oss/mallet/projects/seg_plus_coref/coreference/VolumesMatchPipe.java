package org.zndx.oss.mallet.projects.seg_plus_coref.coreference;

import com.wcohen.secondstring.*;
import org.zndx.oss.mallet.base.types.*;
import org.zndx.oss.mallet.base.classify.*;
import org.zndx.oss.mallet.base.pipe.*;
import org.zndx.oss.mallet.base.pipe.iterator.*;
import org.zndx.oss.mallet.base.util.*;
import java.util.*;
import java.util.logging.*;
import java.lang.*;
import java.io.*;


/** feature is 1 if both string have things that look like
 * volumes/editions, but their numbers don't match*/
public class VolumesMatchPipe extends Pipe {

	private static Logger logger = MalletLogger.getLogger(VolumesMatchPipe.class.getName());

	
	public VolumesMatchPipe () {
	}


	public Instance pipe (Instance carrier) {
		NodePair pair = (NodePair)carrier.getData();
		Citation c1 = (Citation)pair.getObject1();
		Citation c2 = (Citation)pair.getObject2();

		String tok1 = getVenueOrEditionTokens (c1);
		String tok2 = getVenueOrEditionTokens (c2);
		
		if (tok1.length() > 0 && tok2.length() > 0) {
			if (tok1.equals( tok2 )) 
				pair.setFeatureValue( "volumesMatch", 1.0 );
			else
				pair.setFeatureValue( "volumesDontMatch", 1.0 );
		}		
		return carrier;
	}
	
	private String getVenueOrEditionTokens (Citation c) {
		String ret = "";
		ret = c.getField( Citation.volume );
		if (ret.length() > 0) {
			ret = ret.toLowerCase();
			ret = ret.replaceAll( "volume", "" );
			ret = ret.replaceAll( "vol", "" );
			ret = ret.replaceAll( "v ", "" );
			return ret;
		}
		return ret;
	}
}
																
