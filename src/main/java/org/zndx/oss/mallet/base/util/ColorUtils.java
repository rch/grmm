/* Copyright (C) 2003 Univ. of Massachusetts Amherst, Computer Science Dept.
   This file is part of "MALLET" (MAchine Learning for LanguagE Toolkit).
   http://www.cs.umass.edu/~mccallum/mallet
   This software is provided under the terms of the Common Public License,
   version 1.0, as published by http://www.opensource.org.  For further
   information, see the file `LICENSE' included with this distribution. */
package org.zndx.oss.mallet.base.util;

import org.zndx.oss.mallet.base.types.MatrixOps;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * Utilities for dealing with RGB-style colors.
 *
 * Created: Mar 30, 2005
 *
 * @author <A HREF="mailto:casutton@cs.umass.edu>casutton@cs.umass.edu</A>
 * @version $Id: ColorUtils.java,v 1.1 2005/03/31 19:54:23 casutton Exp $
 */
public class ColorUtils {

  /**
   * Returns a list of hex color names of length n.
   *  Colors are generated by equally-spaced hues in HSB space.
   * @param n Number of "equally-spaced" colors to return
   * @param s Saturation of generated colors
   * @param b Brightness  
   * @return An array of hex color names, e.g., "#0033FF"
   */
  public static String[] rainbow (int n, float s, float b)
  {
    double[] vals = new double[n];
    for (int i = 0; i < n; i++) vals[i] = i;
    MatrixOps.timesEquals (vals, 1.0/n);

    String[] ret = new String[n];
    for (int i = 0; i < n; i++) {
      int rgb = Color.HSBtoRGB ((float) vals[i], s, b);
      Color color = new Color (rgb);
      ret[i] = colorToHexString (color);
    }

    return ret;
  }

  private static String colorToHexString (Color color)
  {
    int r = color.getRed ();
    int g = color.getGreen ();
    int b = color.getBlue ();

    StringBuffer ret = new StringBuffer ();
    ret.append ('#');
    if (r < 16) ret.append (0);
    ret.append (Integer.toHexString(r).toUpperCase());
    if (g < 16) ret.append (0);
    ret.append (Integer.toHexString(g).toUpperCase());
    if (b < 16) ret.append (0);
    ret.append (Integer.toHexString(b).toUpperCase());

    return ret.toString ();
  }

}
