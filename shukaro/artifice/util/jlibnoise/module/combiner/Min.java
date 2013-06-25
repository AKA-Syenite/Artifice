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

package shukaro.artifice.util.jlibnoise.module.combiner;

import shukaro.artifice.util.jlibnoise.Utils;
import shukaro.artifice.util.jlibnoise.exception.NoModuleException;
import shukaro.artifice.util.jlibnoise.module.Module;


public class Min extends Module {

    public Min() {
        super(2);
    }

    @Override
    public int GetSourceModuleCount() {
        return 2;
    }

    @Override
    public double GetValue(double x, double y, double z) {
        if (SourceModule[0] == null)
            throw new NoModuleException();
        if (SourceModule[1] == null)
            throw new NoModuleException();

        double v0 = SourceModule[0].GetValue(x, y, z);
        double v1 = SourceModule[1].GetValue(x, y, z);
        return Utils.GetMin(v0, v1);
    }

}
