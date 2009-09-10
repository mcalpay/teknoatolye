//
//  BallCallbacks.m
//  pinpon
//
//  Created by Murat Can ALPAY on 9/10/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "BallCallbacks.h"


@implementation BallCallbacks

-(void) display {
	// loads the identity matrix
	glLoadIdentity();
	
	// floating point red, green, blue values 
	// 0 to 1 probably but may change I guess 3if ?
	glColor3f(1.0,0.0,0.0);
	glBegin(GL_POLYGON);
	
	for (int i = 0; i < 360; i++) {
		float x1 = (cos((M_PI*i)/180) * box.xr) + box.x;
		float y1 = (sin((M_PI*i)/180) * box.yr) + box.y;
		glVertex3f(x1,y1,0);
	}
	
	glEnd();
}

-(id) init {
	[super init];
	box.changesAngleAfterCollision = true;
	return self;
}

@end
