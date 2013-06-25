/* Copyright (C) 2011 Garrett Fleenor

 This library is free software; you can redistribute it and/or modify it
 under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; either version 3.0 of the License, or (at
 your option) any later version.

 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
 License (COPYING.txt) for more details.

 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation,
 Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 This is a port of libnoise ( http://libnoise.sourceforge.net/index.html ).  Original implementation by Jason Bevins

*/

package shukaro.artifice.util.jlibnoise.module.modifier;

import shukaro.artifice.util.jlibnoise.exception.NoModuleException;
import shukaro.artifice.util.jlibnoise.module.Module;


public class Exponent extends Module {
    public static final double DEFAULT_EXPONENT = 1.0;
    protected double exponent = DEFAULT_EXPONENT;

    public Exponent() {
        super(1);
    }

    public double getExponent() {
        return exponent;
    }

    public void setExponent(double exponent) {
        this.exponent = exponent;
    }

    @Override
    public int GetSourceModuleCount() {
        return 1;
    }

    @Override
    public double GetValue(double x, double y, double z) {
        if (SourceModule[0] == null)
            throw new NoModuleException();
        double value = SourceModule[0].GetValue(x, y, z);
        return (Math.pow(Math.abs((value + 1.0) / 2.0), exponent) * 2.0 - 1.0);
    }

}
