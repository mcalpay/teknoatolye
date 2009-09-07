//
//  PlatformCallbacks.m
//  pinpon
//
//  Created by Murat Can ALPAY on 9/7/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "PlatformCallbacks.h"


@implementation PlatformCallbacks

-(void) keyHandler:(unsigned char) key x:(int) x y:(int) y {
	if(key =='d') {
		box.angle = 0;
		box.distance = distance;
	} else if(key =='a') {
		box.angle = M_PI;
		box.distance = distance;
	}
}

-(void) keyUp:(unsigned char) key x:(int) x y:(int) y {
	box.distance = 0;	
}

-(id) initWithRect:(float) _x1 y1:(float) _y1 w:(float) _w h:(float) _h distance:(float) _d{
	[self initWithRect:_x1 y1:_y1 w:_w h:_h];
	distance = _d;
	return self;
}

@end
