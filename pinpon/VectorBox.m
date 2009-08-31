//
//  VectorBox.m
//  openglbir
//
//  Created by Murat Can ALPAY on 8/24/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "VectorBox.h"

#import <math.h>

@implementation VectorBox

@synthesize x, y, xr, yr;

-(id) initWithX:(float)_x y:(float)_y xr:(float)_xr yr:(float)_yr {
	x = _x;
	y = _y;
	xr = _xr;
	yr = _yr;
	return self;
}

@end
