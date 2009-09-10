//
//  BarCallbacks.m
//  openglbir
//
//  Created by Murat Can ALPAY on 8/30/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "BarCallbacks.h"

#import <GLUT/GLUT.h>

@implementation BarCallbacks


-(void) display {
	
	// loads the identity matrix
	glLoadIdentity();
	
	// floating point red, green, blue values 
	// 0 to 1 probably but may change I guess 3if ?
	glColor3f(0.0,0.0,1.0);
	glRectf(box.x - box.xr, box.y - box.yr, box.x + box.xr, box.y + box.yr);
}

@end
