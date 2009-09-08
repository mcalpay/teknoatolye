//
//  PlatformCallbacks.h
//  pinpon
//
//  Created by Murat Can ALPAY on 9/7/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "BarCallbacks.h"

@interface PlatformCallbacks : BarCallbacks {
	float distance;
}

-(id) initWithRect:(float) _x1 y1:(float) _y1 w:(float) _w h:(float) _h distance:(float) _d;

@end
