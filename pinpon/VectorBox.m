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

@synthesize x, y, xr, yr, distance, angle;

-(id) initWithX:(float)_x y:(float)_y xr:(float)_xr yr:(float)_yr {
	x = _x;
	y = _y;
	xr = _xr;
	yr = _yr;
	distance = 0;
	angle = 0;
	return self;
}

-(id) initWithX:(float)_x y:(float)_y xr:(float)_xr yr:(float)_yr  distance:(float) _d angle:(float) _a {
	x = _x;
	y = _y;
	xr = _xr;
	yr = _yr;
	distance = _d;
	angle = _a;
	return self;
}

@end
