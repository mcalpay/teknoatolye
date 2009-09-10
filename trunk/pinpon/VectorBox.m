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

@synthesize x, y, xr, yr, distance, angle, changesAngleAfterCollision;

-(id) detectCollision:(VectorBox *) obox {
	if(obox.x + obox.xr > x && obox.x - obox.xr < x ) {
		if(obox.y + obox.yr > y + yr && obox.y - obox.yr < y + yr) {
			angle = 2*M_PI - angle;
		}
	}
	
	if(obox.x + obox.xr > x && obox.x - obox.xr < x ) {
		if(obox.y + obox.yr > y - yr && obox.y - obox.yr < y - yr) {
			angle = 2*M_PI - angle;
		}
	}
	
	if(obox.y + obox.yr > y && obox.y - obox.yr < y ) {
		if(obox.x + obox.xr > x + xr && obox.x - obox.xr < x + xr) {
			angle = 3*M_PI - angle;
		}
	}
	
	if(obox.y + obox.yr > y && obox.y - obox.yr < y ) {
		if(obox.x + obox.xr > x - xr && obox.x - obox.xr < x - xr) {
			angle = 3*M_PI - angle;
		}
	}

	return self;
}

-(id) initWithX:(float)_x y:(float)_y xr:(float)_xr yr:(float)_yr {
	x = _x;
	y = _y;
	xr = _xr;
	yr = _yr;
	distance = 0;
	angle = 0;
	changesAngleAfterCollision = false;
	return self;
}

-(id) initWithX:(float)_x y:(float)_y xr:(float)_xr yr:(float)_yr  distance:(float) _d angle:(float) _a {
	x = _x;
	y = _y;
	xr = _xr;
	yr = _yr;
	distance = _d;
	angle = _a;
	changesAngleAfterCollision = false;
	return self;
}

@end
